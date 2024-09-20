/**
 * 
 */
package me.hello.backend.crypto.keys;

import java.util.Enumeration;

/**
 * @packageName : me.hello.backend.crypto
 * @fileName    : KeyProvider.java
 * @author      : doyoon.bae
 * @date        : 2024.09.20
 * @description : 
 * =====================================================
 * DATE					AUTHOR				NOTE
 * =====================================================
 * 2024.09.20			doyoon.bae                
 */
public interface KeyProvider {

	/**
	 * 키ID 존재 여부
	 * @param keyId 키ID
	 * @return boolean
	 */
	boolean contains(String keyId);
	
	/**
	 * 키 정보 획득
	 * @param keyId
	 * @return KeyValue
	 */
	KeyValue getKey(String keyId);
	
	/**
	 * 키 획득
	 * @param keyId 키ID
	 * @param clazz 리턴받을 키 형식
	 * @return T
	 */
	<T> T getKey(String keyId, Class<T> clazz);
	
	/**
	 * 키 획득
	 * @param keyId 키ID
	 * @param clazz 리턴받을 키 형식
	 * @param args 추가 속성
	 * @return T
	 */
	<T> T getKey(String keyId, Class<T> clazz, Object... args);
	
	/**
	 * 키ID 목록
	 * @return
	 */
	Enumeration<String> keys();
}
