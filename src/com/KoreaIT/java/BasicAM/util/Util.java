package com.KoreaIT.java.BasicAM.util;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Util {
	/** ���� ��¥ �ð� String */
	public static String getNowDateStr() {

		SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

		Date now = new Date();

		return sdf1.format(now);
	}
}