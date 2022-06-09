package kr.or.ddit.utils;

import java.security.MessageDigest;
import java.util.Base64;

/**
 * Password 암호화 
 * @author 고성식
 * @since 2022. 4. 20.
 * @version 1.0
 * @see javax.servlet.http.HttpServlet
 * <pre>
 * [[개정이력(Modification Information)]]
 * 수정일                          수정자               수정내용
 * --------     --------    ----------------------
 * 2022. 4. 20.   고성식     	 최초작성 
 * Copyright (c) 2022 by DDIT All right reserved
 * </pre>
 */
public class PasswordUtils {
	public static String encodePassword(String plain){
		try {
			MessageDigest md = MessageDigest.getInstance("SHA-512");
			byte[] encrypted = md.digest(plain.getBytes());
			String encoded = Base64.getEncoder().encodeToString(encrypted);
			return encoded;
		}catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	public static boolean passwordMatcher(String plain, String encoded) {
		boolean matched = encodePassword(plain).equals(encoded);
		return matched;
	}
}
