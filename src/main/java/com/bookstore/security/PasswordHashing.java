package com.bookstore.security;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class PasswordHashing {

	public static String generateHashString(String str) throws Exception{
		try {
			MessageDigest digest = MessageDigest.getInstance("SHA-256");
			byte[] hashedBytes = digest.digest(str.getBytes("UTF-8"));
			
			return convertByteArrayToHexString(hashedBytes);
		}catch(NoSuchAlgorithmException | UnsupportedEncodingException ex) {
			throw new Exception("Could not generate Hash String", ex);
		}
	}

	private static String convertByteArrayToHexString(byte[] hashedBytes) {
		StringBuffer stringBuffer = new StringBuffer();
		for (int i = 0; i < hashedBytes.length; i++) {
			stringBuffer.append(Integer.toString((hashedBytes[i] & 0xff) + 0x100, 16).substring(1));
			
			}

		return stringBuffer.toString();
	}
	
	
}
