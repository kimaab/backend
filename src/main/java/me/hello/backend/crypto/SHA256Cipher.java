/**
 * 
 */
package me.hello.backend.crypto;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Base64;

/**
 * @packageName : me.hello.backend.crypto
 * @fileName    : SHA256Cipher.java
 * @author      : doyoon.bae
 * @date        : 2024.09.20
 * @description : 
 * =====================================================
 * DATE					AUTHOR				NOTE
 * =====================================================
 * 2024.09.20			doyoon.bae                
 */
public class SHA256Cipher {

	private static Charset DEFAULT_CHARSET = StandardCharsets.UTF_8;

	private SHA256Cipher() {}

	public static void setDefaultCharset(String charset) {
		DEFAULT_CHARSET = Charset.forName(charset);
	}

	public static void setDefaultCharset(Charset charset) {
		DEFAULT_CHARSET = charset;
	}

	public static byte[] hash(byte[] data) throws NoSuchAlgorithmException {
		MessageDigest md = MessageDigest.getInstance("SHA-256");
		return md.digest(data);
	}

	public static byte[] hash(byte[] value, byte[] salt) throws Exception {
		MessageDigest digest = MessageDigest.getInstance("SHA-256");
		digest.reset();
		digest.update(salt);
		byte[] input = digest.digest(value);
		for (int ii = 0; ii < 1000; ++ii) {
			digest.reset();
			input = digest.digest(input);
		}
		return input;
	}

	public static String hashToString(String value) throws Exception {
		return bytesToHex(hash(value.getBytes(DEFAULT_CHARSET)));
	}

	public static String hashToString(String value, String salt) throws Exception {
		if(salt == null) {
			return hashToString(value);
		}
		return bytesToHex(hash(value.getBytes(DEFAULT_CHARSET), salt.getBytes()));
	}

	public static String hashToBase64String(String data) throws NoSuchAlgorithmException {
		return Base64.getEncoder().encodeToString(hash(data == null ? "".getBytes(DEFAULT_CHARSET) : data.getBytes(DEFAULT_CHARSET)));
	}

	public static String createSalt() {
		try {
			SecureRandom random = SecureRandom.getInstance("SHA1PRNG");
			byte[] bSalt = new byte[10];
			random.nextBytes(bSalt);
			return bytesToHex(bSalt);
		} catch (Throwable t) {
			throw new RuntimeException(t);
		}
	}
	/**
	 * byte를 헥사값으로 변환
	 * @param bytes
	 * @return 헥사 문자열
	 */
	public static String bytesToHex(byte[] bytes) {
		StringBuilder builder = new StringBuilder();

		for(byte b : bytes) {
			builder.append(String.format("%02x", b));
		}
		return builder.toString();
	}

}
