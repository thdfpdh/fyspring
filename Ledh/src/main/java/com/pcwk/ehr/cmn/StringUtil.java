package com.pcwk.ehr.cmn;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

/**
 * 
 * @author user
 *
 */
public class StringUtil {
	
	/**
	 * 파일에 확장자 구하기
	 * @param fileName
	 * @return "": 확장자 없음, 그렇치 않으면 확장자 return 
	 */
	public static String getExt(String fileName) {
		String ext = "";
		String tmpFileName = fileName;
		if(tmpFileName.lastIndexOf(".")>-1) {
			int lastIdx = tmpFileName.lastIndexOf(".");
			ext=tmpFileName.substring(lastIdx+1);
		}		
		return ext;
	}
	
	
	/**
	 * 45byte pk return
	 * @return
	 */
	public static String getPK() {
		return getCurrentDate("yyyyMMdd")+"_"+getUUID();
	}
	
	/**
	 * 
	 * @return 36byte 범용 고유 식별자(Universally Unique Identifiers)
	 */
	public static String getUUID() {
		UUID  uuid=UUID.randomUUID();
		return uuid.toString();
	}
	/**
	 * 현재날짜, 시간 패턴
	 * @param pattern
	 * @return 문자열
	 */
	public static String getCurrentDate(String pattern) {
		if(null == pattern || "".equals(pattern)) {
			pattern = "yyyyMMdd";
		}
		return LocalDateTime.now().format(DateTimeFormatter.ofPattern(pattern));
	}

	/**
	 * 문자열 치환
	 * @param strTarget(원본 문자열)
	 * @param strReplace(치환문자열
	 * @return String
	 */
	public static String nvl(final String strTarget, final String strReplace) {
		if(null == strTarget || "".equals(strTarget)) {
			return strReplace.trim();
		}
		
		return strTarget.trim();
	}
	
	/**
	 * 문자열이 null인 경우 ""으로 치환
	 * @param strTarget
	 * @return String
	 */
	public static String nvl(final String strTarget) {
		return nvl(strTarget,"");
	}
	
	
}
