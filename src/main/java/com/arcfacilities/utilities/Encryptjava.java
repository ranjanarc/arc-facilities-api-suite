package com.arcfacilities.utilities;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

public class Encryptjava {
	private static String salt = "AES";

	public static String HexMD5ForString(String message) {
		String digest = null;

		try {
			MessageDigest md = MessageDigest.getInstance("MD5");

			byte[] hash = md.digest(message.getBytes("UTF-8"));
			// converting byte array to Hexadecimal
			StringBuilder sb = new StringBuilder(2 * hash.length);
			for (byte b : hash) {
				sb.append(String.format("%02x", b & 0xff));
			}
			digest = sb.toString();
		} catch (UnsupportedEncodingException ex) {
			System.out.println(ex.getMessage());
			// Logger.info( ex.getMessage());
		} catch (NoSuchAlgorithmException ex) {
			System.out.println(ex.getMessage());
			// Logger.info(ex.getMessage());
		}

		return digest;
	}

	public static String AESEncrypt(String strToEncrypt, String secret) {
		try {
			byte[] iv = { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 };
			IvParameterSpec ivspec = new IvParameterSpec(iv);

			// SecretKeySpec secretKey = new
			// SecretKeySpec(HexMD5ForString(secret).getBytes(), "AES");
			byte[] secretKey = getMD5("LOG").getBytes();
			SecretKeySpec mKey = new SecretKeySpec(secretKey, "AES");
			Cipher cipher = Cipher.getInstance("AES/CBC/PKCS7Padding");
			cipher.init(Cipher.ENCRYPT_MODE, mKey, ivspec);
			return Base64.getEncoder().encodeToString(
					cipher.doFinal(strToEncrypt
							.getBytes(StandardCharsets.US_ASCII)));

		} catch (Exception e) {
			System.out.println("Error while encrypting: " + e.toString());
		}
		return null;
	}

	public static String AESDecrypt(String strToDecrypt, String secret) {
		try {
			byte[] iv = { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 };
			IvParameterSpec ivspec = new IvParameterSpec(iv);

			SecretKeySpec secretKey = new SecretKeySpec(HexMD5ForString(secret)
					.getBytes(), "AES");

			Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5PADDING");
			cipher.init(Cipher.DECRYPT_MODE, secretKey, ivspec);
			return new String(cipher.doFinal(Base64.getDecoder().decode(
					strToDecrypt)));
		} catch (Exception e) {
			System.out.println("Error while decrypting: " + e.toString());
		}
		return null;
	}

	private static String getMD5(String strKey) {
		String key = strKey;
		String result = null;
		try {
			MessageDigest algorithm = MessageDigest.getInstance("MD5");
			algorithm.reset();
			algorithm.update(key.getBytes("UTF-8"));
			byte messageDigest[] = algorithm.digest();
			StringBuilder hexString = new StringBuilder();
			for (int count = 0; count < messageDigest.length; count++) {
				String hexaDecimal = Integer
						.toHexString(0xFF & messageDigest[count]);
				while (hexaDecimal.length() < 2)
					hexaDecimal = new StringBuilder("0").append(hexaDecimal)
							.toString();
				hexString.append(hexaDecimal);
			}
			result = hexString.toString();
		} catch (NoSuchAlgorithmException e) {
		} catch (UnsupportedEncodingException e) {
		}
		return result;
	}

}
