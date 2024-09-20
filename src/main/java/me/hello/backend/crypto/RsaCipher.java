/**
 *
 */
package me.hello.backend.crypto;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

import me.hello.backend.crypto.keys.KeyValue;

/**
 * @packageName : net.e4.common.crypto
 * @fileName    : RsaCipher.java
 * @author      : hslee
 * @date        : 2022.11.25
 * @description :
 * =====================================================
 * DATE					AUTHOR				NOTE
 * =====================================================
 * 2022.11.25			hslee
 */
public class RsaCipher {

	//private static String DEAFULT_TRANSFORMATION = "RSA/ECB/OAEPWithSHA1AndMGF1Padding";
	//private static String DEAFULT_TRANSFORMATION = "RSA/ECB/PKCS1Padding";
	private static String DEAFULT_TRANSFORMATION = "RSA";
	private static Charset DEFAULT_CHARSET = StandardCharsets.UTF_8;

	private RsaCipher() {}

	/**
	 * Transformation 설정
	 *
	 * @param transformation
	 */
	public static void setDefaultTransformation(String transformation) {
		DEAFULT_TRANSFORMATION = transformation;
	}

	/**
	 * Charset 설정
	 *
	 * @param charset
	 */
	public static void setDefaultCharset(String charset) {
		DEFAULT_CHARSET = Charset.forName(charset);
	}

	/**
	 * Charset 설정
	 *
	 * @param charset
	 */
	public static void setDefaultCharset(Charset charset) {
		DEFAULT_CHARSET = charset;
	}

	/**
	 * KeyPair 생성
	 *
	 * @return
	 * @throws NoSuchAlgorithmException
	 */
	public static KeyPair generateKeyPair() throws NoSuchAlgorithmException {
		KeyPairGenerator generator = KeyPairGenerator.getInstance("RSA");
		generator.initialize(2048);
		return generator.genKeyPair();
	}

	/**
	 * PrivateKey 객체 생성
	 *
	 * @param key
	 * @return
	 * @throws NoSuchAlgorithmException
	 * @throws InvalidKeySpecException
	 */
	public static PrivateKey getPrivateKey(byte[] key) throws NoSuchAlgorithmException, InvalidKeySpecException {
		KeyFactory keyFactory = KeyFactory.getInstance("RSA");
		PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(key, "RSA");
		return keyFactory.generatePrivate(keySpec);
	}

	/**
	 * PrivateKey 객체 생성
	 *
	 * @param key
	 * @return
	 * @throws NoSuchAlgorithmException
	 * @throws InvalidKeySpecException
	 */
	public static PrivateKey getPrivateKeyFromBase64String(String key) throws NoSuchAlgorithmException, InvalidKeySpecException {
		return getPrivateKey(Base64.getDecoder().decode(key));
	}

	/**
	 * PublicKey 객체 생성
	 *
	 * @param key
	 * @return
	 * @throws NoSuchAlgorithmException
	 * @throws InvalidKeySpecException
	 */
	public static PublicKey getPublicKey(byte[] key) throws NoSuchAlgorithmException, InvalidKeySpecException {
		KeyFactory keyFactory = KeyFactory.getInstance("RSA");
		X509EncodedKeySpec keySpec = new X509EncodedKeySpec(key, "RSA");
		return keyFactory.generatePublic(keySpec);
	}

	/**
	 * PublicKey 객체 생성
	 *
	 * @param key
	 * @return
	 * @throws NoSuchAlgorithmException
	 * @throws InvalidKeySpecException
	 */
	public static PublicKey getPublicKeyFromBase64String(String key) throws NoSuchAlgorithmException, InvalidKeySpecException {
		return getPublicKey(Base64.getDecoder().decode(key));
	}

	/**
	 * RSA 암호화 (PublicKey 사용)
	 *
	 * @param key
	 * @param data
	 * @return
	 * @throws NoSuchAlgorithmException
	 * @throws NoSuchPaddingException
	 * @throws InvalidKeyException
	 * @throws IllegalBlockSizeException
	 * @throws BadPaddingException
	 * @throws InvalidAlgorithmParameterException
	 */
	public static byte[] encrypt(PublicKey key, byte[] data) throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException {
		Cipher cipher = Cipher.getInstance(DEAFULT_TRANSFORMATION);
		cipher.init(Cipher.ENCRYPT_MODE, key);
		return cipher.doFinal(data);
	}

	/**
	 * RSA 암호화 (PrivateKey 사용)
	 *
	 * @param key
	 * @param data
	 * @return
	 * @throws NoSuchAlgorithmException
	 * @throws NoSuchPaddingException
	 * @throws InvalidKeyException
	 * @throws IllegalBlockSizeException
	 * @throws BadPaddingException
	 * @throws InvalidAlgorithmParameterException
	 */
	public static byte[] encrypt(PrivateKey key, byte[] data) throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException {
		Cipher cipher = Cipher.getInstance(DEAFULT_TRANSFORMATION);
		cipher.init(Cipher.ENCRYPT_MODE, key);
		return cipher.doFinal(data);
	}

	/**
	 * RSA 암호화 (PublicKey 사용)
	 *
	 * @param keyValue
	 * @param data
	 * @return
	 * @throws InvalidKeyException
	 * @throws NoSuchAlgorithmException
	 * @throws NoSuchPaddingException
	 * @throws IllegalBlockSizeException
	 * @throws BadPaddingException
	 * @throws InvalidKeySpecException
	 * @throws InvalidAlgorithmParameterException
	 */
	public static String encryptToBase64String(KeyValue keyValue, String data) throws InvalidKeyException, NoSuchAlgorithmException, NoSuchPaddingException, IllegalBlockSizeException, BadPaddingException, InvalidKeySpecException {
		return Base64.getEncoder().encodeToString(encrypt(getPublicKeyFromBase64String(keyValue.getKey(String.class)), data.getBytes(DEFAULT_CHARSET)));
	}

	/**
	 * RSA 복호화 (PrivateKey 사용)
	 *
	 * @param key
	 * @param data
	 * @return
	 * @throws NoSuchAlgorithmException
	 * @throws NoSuchPaddingException
	 * @throws InvalidKeyException
	 * @throws IllegalBlockSizeException
	 * @throws BadPaddingException
	 * @throws InvalidAlgorithmParameterException
	 */
	public static byte[] decrypt(PrivateKey key, byte[] data) throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException {
		Cipher cipher = Cipher.getInstance(DEAFULT_TRANSFORMATION);
		cipher.init(Cipher.DECRYPT_MODE, key);
		return cipher.doFinal(data);
	}

	public static byte[] decrypt(PublicKey key, byte[] data) throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException {
		Cipher cipher = Cipher.getInstance(DEAFULT_TRANSFORMATION);
		cipher.init(Cipher.DECRYPT_MODE, key);
		return cipher.doFinal(data);
	}

	/**
	 * RSA 복호화 (PrivateKey 사용)
	 *
	 * @param keyValue
	 * @param data
	 * @return
	 * @throws InvalidKeyException
	 * @throws NoSuchAlgorithmException
	 * @throws NoSuchPaddingException
	 * @throws IllegalBlockSizeException
	 * @throws BadPaddingException
	 * @throws InvalidKeySpecException
	 * @throws InvalidAlgorithmParameterException
	 */
	public static String decryptFromBase64String(KeyValue keyValue, String data) throws InvalidKeyException, NoSuchAlgorithmException, NoSuchPaddingException, IllegalBlockSizeException, BadPaddingException, InvalidKeySpecException {
		return new String(decrypt(getPrivateKeyFromBase64String(keyValue.getKey(String.class)), Base64.getDecoder().decode(data)), DEFAULT_CHARSET);
	}

}
