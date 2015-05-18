package com.attendance.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.attendance.entity.Teacher;
import com.attendance.helper.ShareHelper;
import com.attendance.repository.StudentRepository;
import com.attendance.repository.TeacherRepository;

@Service
public class LoginServiceImpl implements LoginService {

	@Autowired
	TeacherRepository teacherRepository;
	@Autowired
	StudentRepository studentRepository;

	// 存在する生徒であり、パスワードが一致するか
	@Override
	public boolean isStudent(Integer studentId, String password) {
		if (!studentRepository.exists(studentId))
			return false;
		return password.equals(studentRepository.findByStudentId(studentId)
				.getStudentPassword());
	}

	// 存在する管理者であり、パスワードが一致するか
	@Override
	public boolean isManager(Integer managerId, String password) {
		if (!teacherRepository.exists(managerId))
			return false;
		return password.equals(teacherRepository.findByTeacherId(managerId)
				.getTeacherPassword());
	}

	// 講師情報初回更新
	@Override
	public boolean teacherUpdate(Teacher updateTeacher) {
		Teacher teacher = teacherRepository.findByTeacherId(updateTeacher
				.getTeacherId());
		if (teacher == null)
			return false; // 存在しなければfalse

		if (!teacher.getTeacherName().equals(updateTeacher.getTeacherName()))
			teacher.setTeacherName(updateTeacher.getTeacherName());

		String hashedPassword = ShareHelper.hashingMd5(updateTeacher
				.getTeacherPassword()); // md5でハッシュ化
		if (!teacher.getTeacherPassword().equals(hashedPassword))
			teacher.setTeacherPassword(hashedPassword);

		if (!teacher.getTeacherAddress().equals(
				updateTeacher.getTeacherAddress()))
			teacher.setTeacherAddress(updateTeacher.getTeacherAddress());

		if (!teacher.getTeacherTel().equals(updateTeacher.getTeacherTel()))
			teacher.setTeacherTel(updateTeacher.getTeacherTel());

		return teacherRepository.saveAndFlush(teacher) != null ? true : false; // 更新できたらtrue、できなければfalse
	}

}
