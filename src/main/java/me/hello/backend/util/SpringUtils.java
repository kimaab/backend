package me.hello.backend.util;

public class SpringUtils {

	public static final String PROFILE_LOCAL = "local";
	public static final String PROFILE_DEV = "dev";;
	public static final String PROFILE_STAGE = "stg";
	public static final String PROFILE_PROD = "prod";
	
	/**
	 * 활성화 profile을 가져온다.
	 * 
	 * @return String
	 */
	public static String getActiveProfile() {
		return System.getProperty("spring.profiles.active");
	}
	
	/**
	 * 활성화 profile이 로컬인지 여부 판단
	 * 
	 * @return boolean 로컬여부 
	 */
	public static boolean isLocal() {
		return PROFILE_LOCAL.equals(getActiveProfile());
	}
	
	/**
	 * 활성화 profile이 개발인지 여부 판단
	 * 
	 * @return boolean 개발여부
	 */
	public static boolean isDev() {
		return PROFILE_DEV.equals(getActiveProfile());
	}

	/**
	 * 활성화 profile이 스테이징인지 여부 판단
	 * 
	 * @return boolean 스테이징여부
	 */
	public static boolean isStage() {
		return PROFILE_STAGE.equals(getActiveProfile());
	}

	/**
	 * 활성화 profile이 운영인지 여부 판단
	 * 
	 * @return boolean 운영여부
	 */
	public static boolean isProc() {
		return PROFILE_PROD.equals(getActiveProfile());
	}
}
