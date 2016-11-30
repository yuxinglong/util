package com.tydic.bcp.common.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * ip解析器
 * @author Leo
 *
 */
public class IPParser {
	
	/**
	 * 从指定字符串中解析出ip地址
	 * @param s 字符串
	 * @return ip地址
	 */
	public static String parseIP(String s) {
		Pattern p = Pattern.compile("\\d{1,3}\\.\\d{1,3}\\.\\d{1,3}\\.\\d{1,3}");
		Matcher m = p.matcher(s);
		if(m.find()){
			s = m.group();
		}
		return s;
	}
	
}
