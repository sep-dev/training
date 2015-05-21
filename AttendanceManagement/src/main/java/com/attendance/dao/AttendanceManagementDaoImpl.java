package com.attendance.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.attendance.entity.AttendancePastData;
import com.attendance.entity.HourMst;
import com.attendance.entity.Lesson;
import com.attendance.entity.TodayLecture;
import com.attendance.form.SearchAttendancePastDataForm;
import com.attendance.helper.ShareHelper;
import com.attendance.repository.HourMstRepository;
import com.attendance.repository.LessonRepository;

@Component
public class AttendanceManagementDaoImpl implements AttendanceManagementDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private HourMstRepository hourRepository;

    @Autowired
    private LessonRepository lessonRepository;

    @Override
    @Transactional
    public List<TodayLecture> getTodayLectureList(Integer studentId) {
        final List<HourMst> hourList = hourRepository.findAll(); // 時限リスト取得

        List<TodayLecture> list = jdbcTemplate.query(
                this.getLectureListQuery(), new RowMapper<TodayLecture>() {
                    @Override
                    public TodayLecture mapRow(ResultSet rs, int rowNum)
                            throws SQLException {
                        TodayLecture lecture = new TodayLecture();
                        lecture.setLectureId(rs.getInt("lecture_id"));
                        lecture.setLessonName(rs.getString("lesson_name"));
                        lecture.setHour(rs.getInt("hour"));
                        lecture.setTeacherName(rs.getString("teacher_name"));

                        int attendance_state_type = ShareHelper.ATTENDANCE_OK; // 初期値として、出席済み
                        if (rs.getInt("attendance_state") == 0) {
                            Time startTime = hourList
                                    .get(lecture.getHour() - 1).getStartTime(); // 開始時刻を取得
                            attendance_state_type = isTenMinutesBefore(startTime);
                        }

                        lecture.setAttendanceState(attendance_state_type);
                        return lecture;
                    }
                }, ShareHelper.formatHyphenDate(new Date()), studentId);
        return list;
    }

    // 本日の受講可能講義一覧を取得するクエリを格納
    private String getLectureListQuery() {
        StringBuilder query = new StringBuilder();
        query.append("SELECT ")
                .append("A.lecture_id as 'lecture_id' ,B.lesson_name as 'lesson_name',")
                .append("A.lecture_hour as 'hour',C.teacher_name as 'teacher_name',")
                .append("coalesce(D.lecture_id,0,1) as 'attendance_state' ")
                .append("FROM ")
                .append("(SELECT lecture_id,lesson_id,lecture_hour FROM lectures WHERE lecture_date = ?) as A ")
                .append("INNER JOIN ")
                .append("lessons as B ")
                .append("ON A.lesson_id = B.lesson_id ")
                .append("INNER JOIN ")
                .append("teachers as C ")
                .append("ON B.lesson_teacher_id = C.teacher_id ")
                .append("LEFT OUTER JOIN ")
                .append("(SELECT lecture_id FROM lecture_attendance WHERE student_id = ?) as D ")
                .append("ON A.lecture_id = D.lecture_id ")
                .append("ORDER BY A.lecture_hour ASC,B.lesson_name ASC");
        return query.toString();
    }

    // 現在時刻が、引数に渡された時刻よりも10分以内かどうかを判定する
    private int isTenMinutesBefore(Time time) {
        Calendar now = Calendar.getInstance(Locale.JAPAN); // 現在
        Calendar base = Calendar.getInstance();
        base.setTime(new Date(time.getTime()));

        int diffMinutes = getMinutes(base) - getMinutes(now); // 差分取得

        if (diffMinutes < 0) {
            return ShareHelper.ATTENDANCE_KESSEKI; // 値がマイナスなら欠席している
        } else if (diffMinutes <= 10) {
            return ShareHelper.ATTENDANCE_NO; // 未登録のため、出席登録が可能
        } else {
            return ShareHelper.ATTENDANCE_BAT; // 開始時刻よりも10分以上前なので、登録を許可しない
        }
    }

    // 現在時刻を分で返す
    private int getMinutes(Calendar calendar) {
        return calendar.get(Calendar.HOUR_OF_DAY) * 60
                + calendar.get(Calendar.MINUTE);
    }

    @Override
    @Transactional
    public List<AttendancePastData> getAttendancePastData(Integer studentId,
            SearchAttendancePastDataForm sapdf) {
        /*
         * クエリにバインドする値を格納する。 クエリ取得関数内で引数の有無でクエリを変更しているので、バインドする値の数も可変にする必要がある
         */
        ArrayList<String> bindList = new ArrayList<String>();

        bindList.add(studentId.toString()); // 生徒ID追加
        bindList.add(sapdf.getStartDate()); // 開始日付追加
        bindList.add(sapdf.getEndDate()); // 終了日付追加

        if (!sapdf.getLesson_id().equals("0"))
            bindList.add(sapdf.getLesson_id().toString()); // 科目ID追加
        if (!sapdf.getHour().equals("0"))
            bindList.add(sapdf.getHour().toString()); // 時限追加

        List<AttendancePastData> list = jdbcTemplate.query(
                getAttendancePastDataQuery(studentId, sapdf),
                bindList.toArray(), new RowMapper<AttendancePastData>() {
                    @Override
                    public AttendancePastData mapRow(ResultSet rs, int rowNum)
                            throws SQLException {
                        AttendancePastData apd = new AttendancePastData();
                        apd.setDate(rs.getString("date"));
                        apd.setHour(rs.getString("hour"));
                        apd.setLessonName(rs.getString("lesson_name"));
                        return apd;
                    }
                });
        return list;
    }

    private String getAttendancePastDataQuery(Integer studentId,
            SearchAttendancePastDataForm sapdf) {

        StringBuilder query = new StringBuilder();
        query.append(
                "SELECT B.lecture_date AS date,B.lecture_hour AS hour ,C.lesson_name AS lesson_name ")
                .append("FROM ")
                .append("(SELECT * FROM lecture_attendance WHERE student_id = ?) AS A ")
                .append("INNER JOIN ")
                .append("(SELECT * FROM lectures WHERE lecture_date BETWEEN ? AND ? "); // 期間は必須

        if (!sapdf.getLesson_id().equals("0"))
            query.append(" AND lesson_id = ?");
        if (!sapdf.getHour().equals("0"))
            query.append(" AND lecture_hour = ?");

        query.append(") AS B ");
        query.append("ON A.lecture_id = B.lecture_id ").append("INNER JOIN ")
                .append("lessons AS C ")
                .append("ON B.lesson_id = C.lesson_id ")
                .append("ORDER BY B.lecture_date ASC,B.lecture_hour ASC,C.lesson_name ASC");
        System.out.println(query.toString());
        return query.toString();
    }

    //引数の生徒IDから、登録されている出席データ内の一番若い日付を取得する
    @Transactional
    @Override
    public String getMinDate(Integer studentId){
        String query =
                "( SELECT MIN(B.lecture_date) AS minDate "
                + "FROM "
                + " (SELECT * FROM lecture_attendance WHERE student_id = ?) AS A "
                + " INNER JOIN "
                + " (SELECT lecture_id,lecture_date FROM lectures) AS B "
                + " ON A.lecture_id = B.lecture_id"
                + ")";
        String minDate = ShareHelper.formatHyphenDate(new Date());
        Map<String,Object> result = jdbcTemplate.queryForMap(query, studentId);
        if(!result.isEmpty() && result.get("minDate")!=null){
            minDate = result.get("minDate").toString();
        }
        return minDate;
    }

    @Override
    public Map<String, String> getSearcHourMap() {
        Map<String, String> hourMap = new LinkedHashMap<String, String>();
        hourMap.put("0", "指定なし");
        for (HourMst h : hourRepository.findAll()) {
            hourMap.put(h.getHourId().toString(), h.getHourId().toString());
        }
        return hourMap;
    }

    @Override
    public Map<String, String> getSearchLessonMap() {
        Map<String, String> lessonMap = new LinkedHashMap<String, String>();
        lessonMap.put("0", "指定なし");
        for (Lesson l : lessonRepository.findAll()) {
            lessonMap.put(l.getLessonId().toString(), l.getLessonName());
        }
        return lessonMap;
    }
      
}
