/**
 * 
 */
package me.hello.backend.crypto.keys;

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
public class KeyValue {

	/** 키제공자 유형 enum */
	public static enum Provider{ PROP };
	
	/** 키ID */
	private final String id;

	/** 키제공자 */
	private final Provider provider;
	
	/**
	 * 키 형식
	 * @return 키 형식
	 */
	private final Class<?> type;
	
	/**
	 * 키
	 */
	private final Object key;
	
	/**
	 * IV
	 */
	private final Object iv;
	
	/**
	 * 키 정보 
	 * @param id 키ID
	 * @param provider 키제공자
	 * @param type 키형식
	 */
	public KeyValue(String id, Provider provider, Class<?> type) {
		this.id = id;
		this.provider = provider;
		this.type = type;
		this.key = null;
		this.iv = null;
	}

	/**
	 * 키 정보 
	 * @param id 키ID
	 * @param provider 키제공자
	 * @param type 키 형식
	 * @param key 키
	 */
	public KeyValue(String id, Provider provider, Class<?> type, Object key) {
		this.id = id;
		this.provider = provider;
		this.type = type;
		this.key = key;
		this.iv = null;
	}

	/**
	 * 키 정보 
	 * @param id 키ID
	 * @param provider 키제공자
	 * @param type 키 형식
	 * @param key 키
	 * @param iv IV값
	 */
	public KeyValue(String id, Provider provider, Class<?> type, Object key, Object iv) {
		this.id = id;
		this.provider = provider;
		this.type = type;
		this.key = key;
		this.iv = iv;
	}

	/**
	 * 키ID
	 * @return 키ID
	 */
	public String getId() {
		return this.id;
	}
	
	/**
	 * 키제공자
	 * @return 키제공자
	 */
	public Provider getProvider() {
		return this.provider;
	}
	
	/**
	 * 키 형식
	 * @return 키 형식
	 */
	public Class<?> getType() {
		return this.type;
	}
	
	/**
	 * 키 형식
	 * @return 키 형식
	 */
	public Object getKey() {
		return getKey(this.type);
	}
	
	/**
	 * IV값
	 * @return IV값
	 */
	public Object getIv() {
		return getIv(this.type);
	}
	
	/**
	 * 키
	 * @param <T>
	 * @param clazz 형식
	 * @return 키
	 */
	@SuppressWarnings("unchecked")
	public <T>T getKey(Class<T> clazz) {
		return (T) this.key;
	}
	
	/**
	 * IV값
	 * @param <T> 
	 * @param clazz 형식
	 * @return IV값
	 */
	@SuppressWarnings("unchecked")
	public <T>T getIv(Class<T> clazz) {
		return (T) this.iv;
	}
	
	@Override	
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(this.id).append(": ").append(key);
		if ( this.iv != null ) sb.append("(").append(iv).append(")");
		return sb.toString();
	}
}
