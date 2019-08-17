package com.xn.util;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class SHA256Util {

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static List getPasswordSHA256(String str) {
		List sha = new ArrayList();
		String Salt = getRandomString(20);
		String pwd = getSHA256(str + Salt);
		sha.add(Salt);
		sha.add(pwd);
		return sha;
	}

	public static boolean getPasswordSHA256(String str, String Salt, String pwd) {
		String pwd2 = getSHA256(str + Salt);
		if (pwd.equals(pwd2)) {
			return true;
		}
		return false;
	}

	// public static String getPasswordSHA256(String str) {
	// String Salt = "KEVcukVGYJSGDEICdcjvcdsgj84";
	// return getSHA256(str + Salt);
	// }

	public static String getRandomString(int length) {
		String str = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
		Random random = new Random();
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < length; i++) {
			int number = random.nextInt(62);
			sb.append(str.charAt(number));
		}
		return sb.toString();
	}

	public static String getSHA256(String str) {
		MessageDigest messageDigest;
		String encodestr = "";
		try {
			messageDigest = MessageDigest.getInstance("SHA-256");
			messageDigest.update(str.getBytes("UTF-8"));
			encodestr = byte2Hex(messageDigest.digest());
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return encodestr;
	}

	private static String byte2Hex(byte[] bytes) {
		StringBuffer stringBuffer = new StringBuffer();
		String temp = null;
		for (int i = 0; i < bytes.length; i++) {
			temp = Integer.toHexString(bytes[i] & 0xFF);
			if (temp.length() == 1) {
				// 1得到一位的进行补0操作
				stringBuffer.append("0");
			}
			stringBuffer.append(temp);
		}
		return stringBuffer.toString();
	}

}