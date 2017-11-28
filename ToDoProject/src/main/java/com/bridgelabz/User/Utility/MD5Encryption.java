package com.bridgelabz.User.Utility;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class MD5Encryption {
	public static String encrypt(String plainText) {
		String cypherText = null;
		
		try {
			MessageDigest md = MessageDigest.getInstance("SHA");
			md.update(plainText.getBytes());
			byte[] bytes = md.digest();

			StringBuilder sb = new StringBuilder();
			for (int i = 0; i < bytes.length; i++) {
				sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
			}
			cypherText = sb.toString();
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		System.out.println(cypherText);
		return cypherText;
	}
}
