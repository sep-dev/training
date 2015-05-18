package com.attendance.helper;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import org.springframework.util.DigestUtils;

public class ShareHelper {

	// ログインユーザタイプ
	public static final int TYPE_MANAGER = 0; // 管理者
	public static final int TYPE_STUDENT = 1; // 生徒

	// 出席状態
	public static final int ATTENDANCE_OK = 0; // 出席済み
	public static final int ATTENDANCE_NO = 1; // 未登録
	public static final int ATTENDANCE_BAT = 2; // 出席不許可
	public static final int ATTENDANCE_KESSEKI = 3; // 欠席

	// 引数の文字列をMD5でハッシュ化する
	public static String hashingMd5(String target) {
		return DigestUtils.md5DigestAsHex(target.getBytes());
	}

	// 引数に渡されたDateを、yyyy-MM-ddに変換して返す;
	public static String formatHyphenDate(Date date) {
		return formatDate("yyyy-MM-dd", date);
	}

	// 引数に渡された日付を、yyyy年MM月dd日 (E)にして返す
	public static String formatJapaneseDate(Date date) {
		return formatDate("yyyy年MM月dd日 (E)", date);
	}

	public static String getToday() {
		return formatJapaneseDate(new Date());
	}

	private static String formatDate(String formatStr, Date date) {
		return new SimpleDateFormat(formatStr, Locale.JAPAN).format(date);
	}
}
