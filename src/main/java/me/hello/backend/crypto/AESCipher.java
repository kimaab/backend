/**
 * 
 */
package me.hello.backend.crypto;

import java.nio.charset.Charset;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.security.spec.AlgorithmParameterSpec;
import java.util.Base64;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import me.hello.backend.crypto.keys.KeyValue;
import me.hello.backend.crypto.keys.KeyValue.Provider;

/**
 * @packageName : me.hello.backend.crypto
 * @fileName    : AESCipher.java
 * @author      : doyoon.bae
 * @date        : 2024.09.20
 * @description : 
 * =====================================================
 * DATE					AUTHOR				NOTE
 * =====================================================
 * 2024.09.20			doyoon.bae                
 */
public class AESCipher {

	private static String DEAFULT_TRANSFORMATION = "AES/CBC/PKCS5Padding";
	private static Charset DEFAULT_CHARSET = Charset.forName("UTF-8");
	private static String SMPLE_TRANSFORMATION = "AES/CBC/PKCS7Padding";

	private static byte[] AES_IV;
	private static byte[] AES_KEY;

	private AESCipher() {}

	/**
	 * 키 생성
	 * @return Key
	 * @throws NoSuchAlgorithmException
	 */
	public static Key generateKey() throws NoSuchAlgorithmException {
		KeyGenerator keyGenerator = KeyGenerator.getInstance("AES");
		keyGenerator.init(256);
		return keyGenerator.generateKey();
	}

	/**
	 * Transformation 설정
	 * @param transformation
	 */
	public static void setDefaultTransformation(String transformation) {
		DEAFULT_TRANSFORMATION = transformation;
	}

	/**
	 * Charset 설정
	 * @param charset
	 */
	public static void setDefaultCharset(String charset) {
		DEFAULT_CHARSET = Charset.forName(charset);
	}

	/**
	 * Initialization Vector 설정
	 * @param iv
	 */
	public static void setIv(byte[] iv) {
		AES_IV = iv;
	}

	/**
	 * AES Key 설정
	 * @param key
	 */
	public static void setKey(byte[] key) {
		AES_KEY = key;
	}

	/**
	 * AES 암호화
	 *
	 * @param data (byte) 암호화 할 데이터
	 * @return (String) AES 암호화 데이터
	 * @throws NoSuchPaddingException
	 * @throws NoSuchAlgorithmException
	 * @throws InvalidAlgorithmParameterException
	 * @throws InvalidKeyException
	 * @throws BadPaddingException
	 * @throws IllegalBlockSizeException
	 */
	public static byte[] encrypt(byte[] data) throws NoSuchPaddingException, NoSuchAlgorithmException, InvalidAlgorithmParameterException, InvalidKeyException, BadPaddingException, IllegalBlockSizeException {
		return encrypt(AES_IV, AES_KEY, data);
	}

	/**
	 * AES 암호화
	 *
	 * @param data (String)암호화 할 데이터
	 * @return (String) AES 암호화 데이터
	 * @throws NoSuchPaddingException
	 * @throws NoSuchAlgorithmException
	 * @throws InvalidAlgorithmParameterException
	 * @throws InvalidKeyException
	 * @throws BadPaddingException
	 * @throws IllegalBlockSizeException
	 */
	public static String encryptToString(String data) throws NoSuchPaddingException, NoSuchAlgorithmException, InvalidAlgorithmParameterException, InvalidKeyException, BadPaddingException, IllegalBlockSizeException {
		if (data == null) {
			return null;
		}

		return encryptToBase64String(data.getBytes(DEFAULT_CHARSET));
	}

	/**
	 * AES 암호화(Base64 인코딩)
	 * @param keyValue
	 * @param data (String) 암호화 할 데이터
	 * @return (String) AES 암호화 데이터
	 * @throws NoSuchPaddingException
	 * @throws NoSuchAlgorithmException
	 * @throws InvalidAlgorithmParameterException
	 * @throws InvalidKeyException
	 * @throws BadPaddingException
	 * @throws IllegalBlockSizeException
	 */
	public static String encryptToString(KeyValue keyValue, String data) throws NoSuchPaddingException, NoSuchAlgorithmException, InvalidAlgorithmParameterException, InvalidKeyException, BadPaddingException, IllegalBlockSizeException {
		if (data == null) {
			return null;
		}

		return encryptToBase64String(keyValue, data.getBytes(DEFAULT_CHARSET));
	}

	/**
	 * AES 암호화(Base64 인코딩)
	 *
	 * @param iv (byte) iv 데이터
	 * @param key (byte) key 데이터
	 * @param data (String) 암호화 할 데이터
	 * @return (String) AES 암호화 데이터
	 * @throws NoSuchPaddingException
	 * @throws NoSuchAlgorithmException
	 * @throws InvalidAlgorithmParameterException
	 * @throws InvalidKeyException
	 * @throws BadPaddingException
	 * @throws IllegalBlockSizeException
	 */
	public static String encryptToString(byte[] iv, byte[] key, String data) throws NoSuchPaddingException, NoSuchAlgorithmException, InvalidAlgorithmParameterException, InvalidKeyException, BadPaddingException, IllegalBlockSizeException {
		if (data == null) {
			return null;
		}

		return encryptToBase64String(iv, key, data.getBytes(DEFAULT_CHARSET));
	}

	/**
	 * AES 암호화(Base64 인코딩)
	 *
	 * @param iv (String) iv 데이터
	 * @param key (Key) key 데이터
	 * @param data (String) 암호화 할 데이터
	 * @return (String) AES 암호화 데이터
	 * @throws InvalidKeyException
	 * @throws NoSuchAlgorithmException
	 * @throws NoSuchPaddingException
	 * @throws InvalidAlgorithmParameterException
	 * @throws IllegalBlockSizeException
	 * @throws BadPaddingException
	 */
	public static String encryptToString(String iv, Key key, String data) throws InvalidKeyException, NoSuchAlgorithmException, NoSuchPaddingException, InvalidAlgorithmParameterException, IllegalBlockSizeException, BadPaddingException {
		return encryptToBase64String(iv.getBytes(DEFAULT_CHARSET), key, data.getBytes(DEFAULT_CHARSET));
	}

	/**
	 * AES 암호화(Base64 인코딩)
	 *
	 * @param iv (String) iv 데이터
	 * @param key (String) key 데이터
	 * @param data (String) 암호화 할 데이터
	 * @return (String) AES 암호화 데이터
	 * @throws NoSuchPaddingException
	 * @throws InvalidKeyException
	 * @throws NoSuchAlgorithmException
	 * @throws IllegalBlockSizeException
	 * @throws BadPaddingException
	 * @throws InvalidAlgorithmParameterException
	 */
	public static String encryptToString(String iv, String key, String data) throws NoSuchPaddingException, InvalidKeyException, NoSuchAlgorithmException, IllegalBlockSizeException, BadPaddingException, InvalidAlgorithmParameterException {
		if (data == null) {
			return null;
		}

		return encryptToString(iv.getBytes(DEFAULT_CHARSET), key.getBytes(DEFAULT_CHARSET), data);
	}

	/**
	 * AES 암호화(Base64 인코딩)
	 *
	 * @param data (byte) 암호화 할 데이터
	 * @return (String) AES 암호화 데이터
	 * @throws NoSuchPaddingException
	 * @throws InvalidAlgorithmParameterException
	 * @throws NoSuchAlgorithmException
	 * @throws IllegalBlockSizeException
	 * @throws BadPaddingException
	 * @throws InvalidKeyException
	 */
	private static String encryptToBase64String(byte[] data) throws NoSuchPaddingException, InvalidAlgorithmParameterException, NoSuchAlgorithmException, IllegalBlockSizeException, BadPaddingException, InvalidKeyException {
		return Base64.getEncoder().encodeToString(encrypt(data));
	}

	/**
	 * AES 암호화(Base64 인코딩)
	 *
	 * @param iv (byte) iv 데이터
	 * @param key (byte) key 데이터
	 * @param data (byte) 암호화 할 데이터
	 * @return (String) AES 암호화 데이터
	 * @throws NoSuchPaddingException
	 * @throws InvalidAlgorithmParameterException
	 * @throws NoSuchAlgorithmException
	 * @throws IllegalBlockSizeException
	 * @throws BadPaddingException
	 * @throws InvalidKeyException
	 */
	private static String encryptToBase64String(byte[] iv, byte[] key, byte[] data) throws NoSuchPaddingException, InvalidAlgorithmParameterException, NoSuchAlgorithmException, IllegalBlockSizeException, BadPaddingException, InvalidKeyException {
		return Base64.getEncoder().encodeToString(encrypt(iv, key, data));
	}

	/**
	 * AES 암호화(Base64 인코딩)
	 *
	 * @param keyValue
	 * @param data (byte) 암호화 할 데이터
	 * @return (String) AES 암호화 데이터
	 * @throws NoSuchPaddingException
	 * @throws InvalidAlgorithmParameterException
	 * @throws NoSuchAlgorithmException
	 * @throws IllegalBlockSizeException
	 * @throws BadPaddingException
	 * @throws InvalidKeyException
	 */
	private static String encryptToBase64String(KeyValue keyValue, byte[] data) throws NoSuchPaddingException, InvalidAlgorithmParameterException, NoSuchAlgorithmException, IllegalBlockSizeException, BadPaddingException, InvalidKeyException {
		return Base64.getEncoder().encodeToString(encrypt(keyValue, data));
	}

	/**
	 * AES 암호화(Base64 인코딩)
	 *
	 * @param keyValue
	 * @param data (String) 암호화 할 데이터
	 * @return (String) AES 암호화 데이터
	 * @throws NoSuchPaddingException
	 * @throws NoSuchAlgorithmException
	 * @throws InvalidAlgorithmParameterException
	 * @throws InvalidKeyException
	 * @throws BadPaddingException
	 * @throws IllegalBlockSizeException
	 */
	public static String encryptToBase64String(KeyValue keyValue, String data) throws NoSuchPaddingException, NoSuchAlgorithmException, InvalidAlgorithmParameterException, InvalidKeyException, BadPaddingException, IllegalBlockSizeException {
		if (data == null) {
			return null;
		}

		if ( keyValue.getProvider() == Provider.PROP ) {
			return encryptToBase64String(
					 keyValue.getIv(String.class).getBytes(DEFAULT_CHARSET)
					,keyValue.getKey(String.class).getBytes(DEFAULT_CHARSET)
					,data.getBytes(DEFAULT_CHARSET)
			);
		} else {
			throw new InvalidKeyException("invalid KeyProvider '" + keyValue.getProvider() + "'");
		}
	}

	/**
	 * AES 암호화(Base64 인코딩) - 간편로그인 랜덤 key로 암호화데이터
	 *
	 * @param iv (byte) iv 데이터
	 * @param key (Key) key 데이터
	 * @param data (byte) 암호화 할 데이터
	 * @return (String) AES 암호화 데이터
	 * @throws InvalidKeyException
	 * @throws NoSuchAlgorithmException
	 * @throws NoSuchPaddingException
	 * @throws InvalidAlgorithmParameterException
	 * @throws IllegalBlockSizeException
	 * @throws BadPaddingException
	 */
	private static String encryptToBase64String(byte[] iv, Key key, byte[] data) throws InvalidKeyException, NoSuchAlgorithmException, NoSuchPaddingException, InvalidAlgorithmParameterException, IllegalBlockSizeException, BadPaddingException  {
		return Base64.getEncoder().encodeToString(encrypt(iv, key, data));
	}

	/**
	 * AES 암호화 - 간편로그인 랜덤 key로 암호화 요청
	 *
	 * @param iv (byte) iv 데이터
	 * @param key (Key) key 데이터
	 * @param data (byte) 암호화 할 데이터
	 * @return (byte) AES 암호화 데이터
	 * @throws InvalidKeyException
	 * @throws NoSuchAlgorithmException
	 * @throws NoSuchPaddingException
	 * @throws InvalidAlgorithmParameterException
	 * @throws IllegalBlockSizeException
	 * @throws BadPaddingException
	 */
	private static byte[] encrypt(byte[] iv, Key key, byte[] data) throws InvalidKeyException, NoSuchAlgorithmException, NoSuchPaddingException, InvalidAlgorithmParameterException, IllegalBlockSizeException, BadPaddingException {
		AlgorithmParameterSpec ivSpec = new IvParameterSpec(iv);
		return encrypt(ivSpec, key, data);
	}

	/**
	 * AES 암호화 - 간편로그인 랜덤 key로 암호화 처리(PKCS7Padding)
	 *
	 * @param ivSpec (AlgorithmParameterSpec) ivSpec 데이터
	 * @param key (Key) key 데이터
	 * @param data (byte) 데이터
	 * @return (byte) AES 암호화 데이터
	 * @throws NoSuchAlgorithmException
	 * @throws NoSuchPaddingException
	 * @throws InvalidKeyException
	 * @throws InvalidAlgorithmParameterException
	 * @throws IllegalBlockSizeException
	 * @throws BadPaddingException
	 */
	private static byte[] encrypt(AlgorithmParameterSpec ivSpec, Key key, byte[] data) throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, InvalidAlgorithmParameterException, IllegalBlockSizeException, BadPaddingException {
		Cipher cipher = Cipher.getInstance(SMPLE_TRANSFORMATION);
		cipher.init(Cipher.ENCRYPT_MODE, key, ivSpec);
		return cipher.doFinal(data);
	}

	/**
	 * AES 암호화
	 *
	 * @param iv (byte) iv 데이터
	 * @param key (byte) iv 데이터
	 * @param data (byte) 데이터
	 * @return (byte) AES 암호화 데이터
	 * @throws NoSuchPaddingException
	 * @throws NoSuchAlgorithmException
	 * @throws InvalidAlgorithmParameterException
	 * @throws InvalidKeyException
	 * @throws BadPaddingException
	 * @throws IllegalBlockSizeException
	 */
	public static byte[] encrypt(byte[] iv, byte[] key, byte[] data) throws NoSuchPaddingException, NoSuchAlgorithmException, InvalidAlgorithmParameterException, InvalidKeyException, BadPaddingException, IllegalBlockSizeException {
		AlgorithmParameterSpec ivSpec = new IvParameterSpec(iv);
		SecretKeySpec keySpec = new SecretKeySpec(key, "AES");
		return encrypt(ivSpec, keySpec, data);
	}

	/**
	 * AES 암호화
	 *
	 * @param keyValue (KeyValue) keyValue 데이터
	 * @param data (byte) 데이터
	 * @return (byte) AES 암호화 데이터
	 * @throws NoSuchPaddingException
	 * @throws NoSuchAlgorithmException
	 * @throws InvalidAlgorithmParameterException
	 * @throws InvalidKeyException
	 * @throws BadPaddingException
	 * @throws IllegalBlockSizeException
	 */
	private static byte[] encrypt(KeyValue keyValue, byte[] data) throws NoSuchPaddingException, NoSuchAlgorithmException, InvalidAlgorithmParameterException, InvalidKeyException, BadPaddingException, IllegalBlockSizeException {
		if ( keyValue.getProvider() == Provider.PROP ) {
			AlgorithmParameterSpec ivSpec = new IvParameterSpec(keyValue.getIv(String.class).getBytes(DEFAULT_CHARSET));
			SecretKeySpec keySpec = new SecretKeySpec(keyValue.getKey(String.class).getBytes(DEFAULT_CHARSET), "AES");
			return encrypt(ivSpec, keySpec, data);
		} else {
			throw new InvalidKeyException("invalid KeyProvider '" + keyValue.getProvider() + "'");
		}
	}

	/**
	 * AES 암호화
	 *
	 * @param ivSpec (AlgorithmParameterSpec) ivSpec 데이터
	 * @param keySpec (SecretKeySpec) keySpec 데이터
	 * @param data (byte) 데이터
	 * @return (byte) AES 암호화 데이터
	 * @throws NoSuchPaddingException
	 * @throws NoSuchAlgorithmException
	 * @throws InvalidAlgorithmParameterException
	 * @throws InvalidKeyException
	 * @throws BadPaddingException
	 * @throws IllegalBlockSizeException
	 */
	private static byte[] encrypt(AlgorithmParameterSpec ivSpec, SecretKeySpec keySpec, byte[] data) throws NoSuchPaddingException, NoSuchAlgorithmException, InvalidAlgorithmParameterException, InvalidKeyException, BadPaddingException, IllegalBlockSizeException {
		Cipher cipher = Cipher.getInstance(DEAFULT_TRANSFORMATION);
		cipher.init(Cipher.ENCRYPT_MODE, keySpec, ivSpec);
		return cipher.doFinal(data);
	}

	/**
	 * AES 복호화
	 *
	 * @param data (byte) AES 암호화 데이터
	 * @return (byte) 복호화 데이터
	 * @throws NoSuchPaddingException
	 * @throws NoSuchAlgorithmException
	 * @throws InvalidAlgorithmParameterException
	 * @throws InvalidKeyException
	 * @throws BadPaddingException
	 * @throws IllegalBlockSizeException
	 */
	public static byte[] decrypt(byte[] data) throws NoSuchPaddingException, NoSuchAlgorithmException, InvalidAlgorithmParameterException, InvalidKeyException, BadPaddingException, IllegalBlockSizeException {
		return decrypt(AES_IV, AES_KEY, data);
	}

	/**
	 * AES 복호화(Base64 디코딩)
	 *
	 * @param data (String) AES 암호화 데이터
	 * @return (String) 복호화 데이터
	 * @throws NoSuchPaddingException
	 * @throws InvalidKeyException
	 * @throws NoSuchAlgorithmException
	 * @throws IllegalBlockSizeException
	 * @throws BadPaddingException
	 * @throws InvalidAlgorithmParameterException
	 */
	public static String decryptFromString(String data) throws NoSuchPaddingException, InvalidKeyException, NoSuchAlgorithmException, IllegalBlockSizeException, BadPaddingException, InvalidAlgorithmParameterException {
		if (data == null) {
			return null;
		}

		return new String(decryptFromBase64String(data), DEFAULT_CHARSET);
	}

	/**
	 * AES 복호화(Base64 디코딩)
	 *
	 * @param iv (byte) iv 데이터
	 * @param key (byte) key 데이터
	 * @param data (String) AES 암호화 데이터
	 * @return (String) 복호화 데이터
	 * @throws NoSuchPaddingException
	 * @throws InvalidKeyException
	 * @throws NoSuchAlgorithmException
	 * @throws IllegalBlockSizeException
	 * @throws BadPaddingException
	 * @throws InvalidAlgorithmParameterException
	 */
	public static String decryptFromString(byte[] iv, byte[] key, String data) throws NoSuchPaddingException, InvalidKeyException, NoSuchAlgorithmException, IllegalBlockSizeException, BadPaddingException, InvalidAlgorithmParameterException {
		if (data == null) {
			return null;
		}

		return new String(decryptFromBase64String(iv, key, data), DEFAULT_CHARSET);
	}

	/**
	 * AES 복호화(Base64 디코딩)
	 *
	 * @param keyValue (KeyValue) keyValue 데이터
	 * @param data (String) AES 암호화 데이터
	 * @return (String) 복호화 데이터
	 * @throws NoSuchPaddingException
	 * @throws NoSuchAlgorithmException
	 * @throws InvalidAlgorithmParameterException
	 * @throws InvalidKeyException
	 * @throws BadPaddingException
	 * @throws IllegalBlockSizeException
	 */
	public static String decryptFromString(KeyValue keyValue, String data) throws NoSuchPaddingException, NoSuchAlgorithmException, InvalidAlgorithmParameterException, InvalidKeyException, BadPaddingException, IllegalBlockSizeException {
		if (data == null) {
			return null;
		}

		return new String(decryptFromBase64String(keyValue, data), DEFAULT_CHARSET);
	}

	/**
	 * AES 복호화(Base64 디코딩) - 간편로그인 복호화 스트링 요청
	 * @param iv (byte) iv 데이터
	 * @param key (Key) key 데이터
	 * @param data (String) AES 암호화 데이터
	 * @return (String) 복호화 데이터
	 * @throws NoSuchPaddingException
	 * @throws InvalidKeyException
	 * @throws NoSuchAlgorithmException
	 * @throws IllegalBlockSizeException
	 * @throws BadPaddingException
	 * @throws InvalidAlgorithmParameterException
	 */
	public static String decryptFromString(byte[] iv, Key key, String data) throws NoSuchPaddingException, InvalidKeyException, NoSuchAlgorithmException, IllegalBlockSizeException, BadPaddingException, InvalidAlgorithmParameterException {
		if (data == null) {
			return null;
		}

		return new String(decryptFromBase64String(iv, key, data), DEFAULT_CHARSET);
	}

	/**
	 * AES 복호화(Base64 디코딩)
	 *
	 * @param iv (String) iv 데이터
	 * @param key (String) key 데이터
	 * @param data (String) AES 암호화 데이터
	 * @return (String) 복호화 데이터
	 * @throws NoSuchPaddingException
	 * @throws InvalidKeyException
	 * @throws NoSuchAlgorithmException
	 * @throws IllegalBlockSizeException
	 * @throws BadPaddingException
	 * @throws InvalidAlgorithmParameterException
	 */
	public static String decryptFromString(String iv, String key, String data) throws NoSuchPaddingException, InvalidKeyException, NoSuchAlgorithmException, IllegalBlockSizeException, BadPaddingException, InvalidAlgorithmParameterException {
		if (data == null) {
			return null;
		}

		return decryptFromString(iv.getBytes(DEFAULT_CHARSET), key.getBytes(DEFAULT_CHARSET), data);
	}

	/**
	 * AES 복호화(Base64 디코딩) - 간편로그인 복호화 요청
	 *
	 * @param iv (String) iv 데이터
	 * @param key (Key) key 데이터
	 * @param data (String) AES 암호화 데이터
	 * @return (String) 복호화 데이터
	 * @throws NoSuchPaddingException
	 * @throws InvalidKeyException
	 * @throws NoSuchAlgorithmException
	 * @throws IllegalBlockSizeException
	 * @throws BadPaddingException
	 * @throws InvalidAlgorithmParameterException
	 */
	public static String decryptFromString(String iv, Key key, String data) throws NoSuchPaddingException, InvalidKeyException, NoSuchAlgorithmException, IllegalBlockSizeException, BadPaddingException, InvalidAlgorithmParameterException {
		if (data == null) {
			return null;
		}

		return decryptFromString(iv.getBytes(DEFAULT_CHARSET), key, data);
	}

	/**
	 * AES 복호화(Base64 디코딩)
	 *
	 * @param data (String) AES 암호화 데이터
	 * @return (byte) 복호화 데이터
	 * @throws NoSuchPaddingException
	 * @throws InvalidAlgorithmParameterException
	 * @throws NoSuchAlgorithmException
	 * @throws IllegalBlockSizeException
	 * @throws BadPaddingException
	 * @throws InvalidKeyException
	 */
	public static byte[] decryptFromBase64String(String data) throws NoSuchPaddingException, InvalidAlgorithmParameterException, NoSuchAlgorithmException, IllegalBlockSizeException, BadPaddingException, InvalidKeyException {
		return decrypt(Base64.getDecoder().decode(data));
	}

	/**
	 * AES 복호화(Base64 디코딩)
	 *
	 * @param iv (byte) iv 데이터
	 * @param key (byte) key 데이터
	 * @param data (String) AES 암호화 데이터
	 * @return (byte) 복호화 데이터
	 * @throws NoSuchPaddingException
	 * @throws InvalidAlgorithmParameterException
	 * @throws NoSuchAlgorithmException
	 * @throws IllegalBlockSizeException
	 * @throws BadPaddingException
	 * @throws InvalidKeyException
	 */
	public static byte[] decryptFromBase64String(byte[] iv, byte[] key, String data) throws NoSuchPaddingException, InvalidAlgorithmParameterException, NoSuchAlgorithmException, IllegalBlockSizeException, BadPaddingException, InvalidKeyException {
		return decrypt(iv, key, Base64.getDecoder().decode(data));
	}

	/**
	 * AES 복호화(Base64 디코딩)
	 *
	 * @param keyValue (KeyValue) keyValue 데이터
	 * @param data (String) AES 암호화 데이터
	 * @return (byte) 복호화 데이터
	 * @throws NoSuchPaddingException
	 * @throws InvalidAlgorithmParameterException
	 * @throws NoSuchAlgorithmException
	 * @throws IllegalBlockSizeException
	 * @throws BadPaddingException
	 * @throws InvalidKeyException
	 */
	public static byte[] decryptFromBase64String(KeyValue keyValue, String data) throws NoSuchPaddingException, InvalidAlgorithmParameterException, NoSuchAlgorithmException, IllegalBlockSizeException, BadPaddingException, InvalidKeyException {
		if ( keyValue.getProvider() == Provider.PROP ) {
			return decryptFromBase64String(
					 keyValue.getIv(String.class).getBytes(DEFAULT_CHARSET)
					,keyValue.getKey(String.class).getBytes(DEFAULT_CHARSET)
					,data
				);
		} else {
			throw new InvalidKeyException("invalid KeyProvider '" + keyValue.getProvider() + "'");
		}
	}

	/**
	 * AES 복호화 - 간편로그인 data Decoding하여 랜덤 key로 복호화 요청
	 *
	 * @param iv (byte) iv 데이터
	 * @param key (Key) key 데이터
	 * @param data (String) AES 암호화 데이터
	 * @return (byte) 복호화 데이터
	 * @throws NoSuchPaddingException
	 * @throws InvalidAlgorithmParameterException
	 * @throws NoSuchAlgorithmException
	 * @throws IllegalBlockSizeException
	 * @throws BadPaddingException
	 * @throws InvalidKeyException
	 */
	public static byte[] decryptFromBase64String(byte[] iv, Key key, String data) throws NoSuchPaddingException, InvalidAlgorithmParameterException, NoSuchAlgorithmException, IllegalBlockSizeException, BadPaddingException, InvalidKeyException {
		return decrypt(iv, key, Base64.getDecoder().decode(data));
	}

	/**
	 * AES 복호화
	 *
	 * @param iv (byte) iv 데이터
	 * @param key (byte) key 데이터
	 * @param data (byte) AES 암호화 데이터
	 * @return (byte) 복호화 데이터
	 * @throws NoSuchPaddingException
	 * @throws NoSuchAlgorithmException
	 * @throws InvalidAlgorithmParameterException
	 * @throws InvalidKeyException
	 * @throws BadPaddingException
	 * @throws IllegalBlockSizeException
	 */
	public static byte[] decrypt(byte[] iv, byte[] key, byte[] data) throws NoSuchPaddingException, NoSuchAlgorithmException, InvalidAlgorithmParameterException, InvalidKeyException, BadPaddingException, IllegalBlockSizeException {
		AlgorithmParameterSpec ivSpec = new IvParameterSpec(iv);
		SecretKeySpec keySpec = new SecretKeySpec(key, "AES");
		return decrypt(ivSpec, keySpec, data);
	}

	/**
	 * AES 복호화
	 *
	 * @param keyValue (KeyValue) keyValue 데이터
	 * @param data (byte) AES 암호화 데이터
	 * @return (byte) 복호화 데이터
	 * @throws NoSuchPaddingException
	 * @throws NoSuchAlgorithmException
	 * @throws InvalidAlgorithmParameterException
	 * @throws InvalidKeyException
	 * @throws BadPaddingException
	 * @throws IllegalBlockSizeException
	 */
	public static byte[] decrypt(KeyValue keyValue, byte[] data) throws NoSuchPaddingException, NoSuchAlgorithmException, InvalidAlgorithmParameterException, InvalidKeyException, BadPaddingException, IllegalBlockSizeException {
		if ( keyValue.getProvider() == Provider.PROP ) {
			AlgorithmParameterSpec ivSpec = new IvParameterSpec(keyValue.getIv(String.class).getBytes(DEFAULT_CHARSET));
			SecretKeySpec keySpec = new SecretKeySpec(keyValue.getKey(String.class).getBytes(DEFAULT_CHARSET), "AES");
			return decrypt(ivSpec, keySpec, data);
		} else {
			throw new InvalidKeyException("invalid KeyProvider '" + keyValue.getProvider() + "'");
		}
	}

	/**
	 * AES 복호화
	 *
	 * @param iv (byte) iv 데이터
	 * @param key (Key) key 데이터
	 * @param data (byte) AES 암호화 데이터
	 * @return
	 * @throws NoSuchPaddingException
	 * @throws NoSuchAlgorithmException
	 * @throws InvalidAlgorithmParameterException
	 * @throws InvalidKeyException
	 * @throws BadPaddingException
	 * @throws IllegalBlockSizeException
	 */
	public static byte[] decrypt(byte[] iv, Key key, byte[] data) throws NoSuchPaddingException, NoSuchAlgorithmException, InvalidAlgorithmParameterException, InvalidKeyException, BadPaddingException, IllegalBlockSizeException {
		AlgorithmParameterSpec ivSpec = new IvParameterSpec(iv);
		return decrypt(ivSpec, key, data);
	}

	/**
	 * AES 복호화
	 *
	 * @param ivSpec (AlgorithmParameterSpec) ivSpec 데이터
	 * @param keySpec (SecretKeySpec) keySpec 데이터
	 * @param data (byte) AES 암호화 데이터
	 * @return (byte) 복호화 데이터
	 * @throws NoSuchPaddingException
	 * @throws NoSuchAlgorithmException
	 * @throws InvalidAlgorithmParameterException
	 * @throws InvalidKeyException
	 * @throws BadPaddingException
	 * @throws IllegalBlockSizeException
	 */
	public static byte[] decrypt(AlgorithmParameterSpec ivSpec, SecretKeySpec keySpec, byte[] data) throws NoSuchPaddingException, NoSuchAlgorithmException, InvalidAlgorithmParameterException, InvalidKeyException, BadPaddingException, IllegalBlockSizeException {
		Cipher cipher = Cipher.getInstance(DEAFULT_TRANSFORMATION);
		cipher.init(Cipher.DECRYPT_MODE, keySpec, ivSpec);
		return cipher.doFinal(data);
	}

	/**
	 * AES 복호화 - 간편로그인 랜덤 key로 복호화 처리
	 *
	 * @param ivSpec (AlgorithmParameterSpec) ivSpec 데이터
	 * @param keySpec (Key) keySpec 데이터
	 * @param data (byte) AES 암호화 데이터
	 * @return (byte) 복호화 데이터
	 * @throws NoSuchPaddingException
	 * @throws NoSuchAlgorithmException
	 * @throws InvalidAlgorithmParameterException
	 * @throws InvalidKeyException
	 * @throws BadPaddingException
	 * @throws IllegalBlockSizeException
	 */
	public static byte[] decrypt(AlgorithmParameterSpec ivSpec, Key keySpec, byte[] data) throws NoSuchPaddingException, NoSuchAlgorithmException, InvalidAlgorithmParameterException, InvalidKeyException, BadPaddingException, IllegalBlockSizeException {
		Cipher cipher = Cipher.getInstance(DEAFULT_TRANSFORMATION);
		cipher.init(Cipher.DECRYPT_MODE, keySpec, ivSpec);
		return cipher.doFinal(data);
	}
}
