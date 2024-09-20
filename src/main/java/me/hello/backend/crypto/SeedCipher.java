/**
 *
 */
package me.hello.backend.crypto;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.Security;
import java.security.spec.AlgorithmParameterSpec;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import org.bouncycastle.jce.provider.BouncyCastleProvider;

import me.hello.backend.crypto.keys.KeyValue;
import net.e4.common.util.StringUtils;

/**
 * @packageName : net.e4.common.crypto
 * @fileName    : SeedCipher.java
 * @author      : hslee
 * @date        : 2022.11.25
 * @description :
 * =====================================================
 * DATE					AUTHOR				NOTE
 * =====================================================
 * 2022.11.25			hslee
 */
public class SeedCipher {

	private static String DEAFULT_TRANSFORMATION = "SEED/ECB/ZeroBytePadding";
	private static Charset DEFAULT_CHARSET = StandardCharsets.UTF_8;

	private static byte[] SEED_IV;
	private static byte[] SEED_KEY;

	static {
		if (Security.getProvider(BouncyCastleProvider.PROVIDER_NAME) == null) {
			Security.addProvider(new BouncyCastleProvider());
		}
	}

	private SeedCipher() {}

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
	 * Initialization Vector 설정
	 *
	 * @param iv
	 */
	public static void setIv(byte[] iv) {
		SEED_IV = iv;
	}

	/**
	 * Key 설정
	 *
	 * @param key
	 */
	public static void setKey(byte[] key) {
		SEED_KEY = key;
	}

	/**
	 * SEED 암호화
	 *
	 * @param data
	 * @return
	 * @throws InvalidKeyException
	 * @throws NoSuchAlgorithmException
	 * @throws NoSuchProviderException
	 * @throws NoSuchPaddingException
	 * @throws IllegalBlockSizeException
	 * @throws BadPaddingException
	 * @throws InvalidAlgorithmParameterException
	 */
	public static String encryptToHexString(String data) throws InvalidKeyException, NoSuchAlgorithmException, NoSuchProviderException, NoSuchPaddingException, IllegalBlockSizeException, BadPaddingException, InvalidAlgorithmParameterException {
		SecretKeySpec keySpec = new SecretKeySpec(SEED_KEY, "SEED");
		AlgorithmParameterSpec ivSpec = SEED_IV == null ? null : new IvParameterSpec(SEED_IV);
		return StringUtils.byteArrayToHexString(encrypt(ivSpec, keySpec, data.getBytes(DEFAULT_CHARSET)));
	}

	/**
	 * SEED 암호화
	 *
	 * @param keyValue
	 * @param data
	 * @return
	 * @throws InvalidKeyException
	 * @throws NoSuchAlgorithmException
	 * @throws NoSuchProviderException
	 * @throws NoSuchPaddingException
	 * @throws IllegalBlockSizeException
	 * @throws BadPaddingException
	 * @throws InvalidAlgorithmParameterException
	 */
	public static String encryptToHexString(KeyValue keyValue, String data) throws InvalidKeyException, NoSuchAlgorithmException, NoSuchProviderException, NoSuchPaddingException, IllegalBlockSizeException, BadPaddingException, InvalidAlgorithmParameterException {
		SecretKeySpec keySpec = new SecretKeySpec(StringUtils.hexStringToByteArray(keyValue.getKey(String.class)), "SEED");
		return StringUtils.byteArrayToHexString(encrypt(null, keySpec, data.getBytes(DEFAULT_CHARSET)));
	}

	/**
	 * SEED 암호화
	 *
	 * @param ivSpec
	 * @param keySpec
	 * @param data
	 * @return
	 * @throws NoSuchAlgorithmException
	 * @throws NoSuchProviderException
	 * @throws NoSuchPaddingException
	 * @throws InvalidKeyException
	 * @throws IllegalBlockSizeException
	 * @throws BadPaddingException
	 * @throws InvalidAlgorithmParameterException
	 */
	public static byte[] encrypt(AlgorithmParameterSpec ivSpec, SecretKeySpec keySpec, byte[] data) throws NoSuchAlgorithmException, NoSuchProviderException, NoSuchPaddingException, InvalidKeyException, InvalidAlgorithmParameterException, IllegalBlockSizeException, BadPaddingException {
		Cipher cipher = Cipher.getInstance(DEAFULT_TRANSFORMATION, BouncyCastleProvider.PROVIDER_NAME);

		if (ivSpec != null) {
			cipher.init(Cipher.ENCRYPT_MODE, keySpec, ivSpec);
		}
		else {
			cipher.init(Cipher.ENCRYPT_MODE, keySpec);
		}

		return cipher.doFinal(data);
	}

	/**
	 * SEED 복호화
	 *
	 * @param data
	 * @return
	 * @throws InvalidKeyException
	 * @throws NoSuchAlgorithmException
	 * @throws NoSuchProviderException
	 * @throws NoSuchPaddingException
	 * @throws InvalidAlgorithmParameterException
	 * @throws IllegalBlockSizeException
	 * @throws BadPaddingException
	 */
	public static String decryptFromHexString(String data) throws InvalidKeyException, NoSuchAlgorithmException, NoSuchProviderException, NoSuchPaddingException, InvalidAlgorithmParameterException, IllegalBlockSizeException, BadPaddingException {
		SecretKeySpec keySpec = new SecretKeySpec(SEED_KEY, "SEED");
		AlgorithmParameterSpec ivSpec = SEED_IV == null ? null : new IvParameterSpec(SEED_IV);
		return new String(decrypt(ivSpec, keySpec, StringUtils.hexStringToByteArray(data)), DEFAULT_CHARSET);
	}

	/**
	 * SEED 복호화
	 *
	 * @param keyValue
	 * @param data
	 * @return
	 * @throws InvalidKeyException
	 * @throws NoSuchAlgorithmException
	 * @throws NoSuchProviderException
	 * @throws NoSuchPaddingException
	 * @throws InvalidAlgorithmParameterException
	 * @throws IllegalBlockSizeException
	 * @throws BadPaddingException
	 */
	public static String decryptFromHexString(KeyValue keyValue, String data) throws InvalidKeyException, NoSuchAlgorithmException, NoSuchProviderException, NoSuchPaddingException, InvalidAlgorithmParameterException, IllegalBlockSizeException, BadPaddingException {
		SecretKeySpec keySpec = new SecretKeySpec(StringUtils.hexStringToByteArray(keyValue.getKey(String.class)), "SEED");
		return new String(decrypt(null, keySpec, StringUtils.hexStringToByteArray(data)), DEFAULT_CHARSET);
	}

	/**
	 * SEED 복호화
	 *
	 * @param ivSpec
	 * @param keySpec
	 * @param data
	 * @return
	 * @throws NoSuchAlgorithmException
	 * @throws NoSuchProviderException
	 * @throws NoSuchPaddingException
	 * @throws InvalidKeyException
	 * @throws InvalidAlgorithmParameterException
	 * @throws IllegalBlockSizeException
	 * @throws BadPaddingException
	 */
	public static byte[] decrypt(AlgorithmParameterSpec ivSpec, SecretKeySpec keySpec, byte[] data) throws NoSuchAlgorithmException, NoSuchProviderException, NoSuchPaddingException, InvalidKeyException, InvalidAlgorithmParameterException, IllegalBlockSizeException, BadPaddingException {
		Cipher cipher = Cipher.getInstance(DEAFULT_TRANSFORMATION, BouncyCastleProvider.PROVIDER_NAME);

		if (ivSpec != null) {
			cipher.init(Cipher.DECRYPT_MODE, keySpec, ivSpec);
		}
		else {
			cipher.init(Cipher.DECRYPT_MODE, keySpec);
		}

		return cipher.doFinal(data);
	}
}
