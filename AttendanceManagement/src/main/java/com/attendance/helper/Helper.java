package com.attendance.helper;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import org.springframework.util.DigestUtils;

public class Helper {

	// ログインユーザタイプ
	public static final int TYPE_MANAGER = 0; // 管理者
	public static final int TYPE_STUDENT = 1; // 生徒

	// 引数の文字列をMD5でハッシュ化する
	public static String hashingMd5(String target) {
		return DigestUtils.md5DigestAsHex(target.getBytes());
	}

	// 引数に渡されたDateを、yyyy-MM-ddに変換して返す;
	public static String formatHyphenDate(Date date) {
		return formatDate("yyyy-MM-dd", date);
	}

	// 引数に渡された日付を、yyyy年MM月dd日にして返す
	public static String formatJapaneseDate(Date date) {
		return formatDate("yyyy年MM月dd日 (E)", date);
	}

	private static String formatDate(String formatStr, Date date) {
		return new SimpleDateFormat(formatStr, Locale.JAPAN).format(date);
	}
}
