/**
 * 
 */
package me.hello.backend.crypto;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

import me.hello.backend.crypto.keys.KeyValue;

/**
 * @packageName : net.e4.common.crypto
 * @fileName    : HmacCipher.java
 * @author      : hslee
 * @date        : 2022.11.25
 * @description : 
 * =====================================================
 * DATE					AUTHOR				NOTE
 * =====================================================
 * 2022.11.25			hslee                
 */
public class HmacCipher {

	private static String DEAFULT_MAC_ALGORITHM = "HmacSHA256";
	private static String DEAFULT_KEY_ALGORITHM = "HmacSHA256";
	private static Charset DEFAULT_CHARSET = StandardCharsets.UTF_8;

	private static byte[] KEY = null;

	private HmacCipher() {}

	/**
	 * 기본 Mac Algorithm 설정
	 *
	 * @param algorithm
	 */
	public static void setDefaultMacAlgorithm(String algorithm) {
		DEAFULT_MAC_ALGORITHM = algorithm;
	}

	/**
	 * 기본 Key Algorithm 설정
	 *
	 * @param algorithm
	 */
	public static void setDefaultKeyAlgorithm(String algorithm) {
		DEAFULT_KEY_ALGORITHM = algorithm;
	}

	/**
	 * 기본 Charset 설정
	 *
	 * @param charset
	 */
	public static void setDefaultCharset(String charset) {
		DEFAULT_CHARSET = Charset.forName(charset);
	}

	/**
	 * 기본 Charset 설정
	 *
	 * @param charset
	 */
	public static void setDefaultCharset(Charset charset) {
		DEFAULT_CHARSET = charset;
	}

	/**
	 * Mac Key 설정
	 *
	 * @param key
	 */
	public static void setKey(String key) {
		KEY = key.getBytes(DEFAULT_CHARSET);
	}

	/**
	 * Mac Key 설정
	 *
	 * @param key
	 */
	public static void setKey(byte[] key) {
		KEY = key;
	}

	/**
	 * Signature 생성
	 *
	 * @param algorithm
	 * @param key
	 * @param data
	 * @return
	 * @throws NoSuchAlgorithmException
	 * @throws InvalidKeyException
	 */
	public static byte[] signature(String algorithm, Key key, byte[] data) throws NoSuchAlgorithmException, InvalidKeyException {
		Mac mac = Mac.getInstance(algorithm);
		mac.init(key);
		return mac.doFinal(data);
	}

	/**
	 * Signature 생성
	 *
	 * @param data
	 * @return
	 * @throws NoSuchAlgorithmException
	 * @throws InvalidKeyException
	 */
	public static byte[] signature(byte[] data) throws NoSuchAlgorithmException, InvalidKeyException {
		return signature(KEY, data);
	}

	/**
	 * Signature 생성
	 *
	 * @param key
	 * @param data
	 * @return
	 * @throws NoSuchAlgorithmException
	 * @throws InvalidKeyException
	 */
	public static byte[] signature(byte[] key, byte[] data) throws NoSuchAlgorithmException, InvalidKeyException {
		Key keySpec = new SecretKeySpec(key, DEAFULT_KEY_ALGORITHM);
		return signature(DEAFULT_MAC_ALGORITHM, keySpec, data);
	}

	/**
	 * Signature 생성 (Base64 인코딩)
	 *
	 * @param data
	 * @return
	 * @throws NoSuchAlgorithmException
	 * @throws InvalidKeyException
	 */
	public static String signatureToBase64String(String data) throws NoSuchAlgorithmException, InvalidKeyException {
		return Base64.getEncoder().encodeToString(signature(data == null ? "".getBytes(DEFAULT_CHARSET) : data.getBytes(DEFAULT_CHARSET)));
	}

	/**
	 * Signature 생성 (Base64 인코딩)
	 *
	 * @param keyValue
	 * @param data
	 * @return
	 * @throws InvalidKeyException
	 * @throws NoSuchAlgorithmException
	 */
	public static String signatureToBase64String(String key, String data) throws InvalidKeyException, NoSuchAlgorithmException {
		return Base64.getEncoder().encodeToString(signature(key.getBytes(DEFAULT_CHARSET), data == null ? "".getBytes(DEFAULT_CHARSET) : data.getBytes(DEFAULT_CHARSET)));
	}

	/**
	 * Signature 생성 (Base64 인코딩)
	 *
	 * @param keyValue
	 * @param data
	 * @return
	 * @throws InvalidKeyException
	 * @throws NoSuchAlgorithmException
	 */
	public static String signatureToBase64String(KeyValue keyValue, String data) throws InvalidKeyException, NoSuchAlgorithmException {
		return Base64.getEncoder().encodeToString(signature(keyValue.getKey(String.class).getBytes(DEFAULT_CHARSET), data == null ? "".getBytes(DEFAULT_CHARSET) : data.getBytes(DEFAULT_CHARSET)));
	}
}
