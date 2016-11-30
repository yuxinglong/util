package com.tydic.bcp.common.utils;

import java.util.Iterator;
import java.util.Random;
import java.util.Set;
import java.util.regex.Pattern;

/**
 *
 * @author lee
 * @date 2006-12-13
 * @version 1.0
 */
public class MacUtil {
	/**
	 * 将字符串的首字符变成大写字符
	 *
	 * @param s
	 * @return
	 */
	public static String firstCharUpper(String s) {
		String result = s.substring(0, 1).toUpperCase() + s.substring(1);
		return result;
	}

	/**
	 * 将字符串的首字符变成小写字符
	 *
	 * @param s
	 * @return
	 */
	public static String firstCharLower(String s) {
		String result = s.substring(0, 1).toLowerCase() + s.substring(1);
		return result;
	}

	/**
	 * 生成指定长度的随机字符串（0--9,A--Z,a--Z）
	 *
	 * @param length
	 * @return
	 */
	public static String genRandom(int length) {
		StringBuffer buffer = new StringBuffer();
		Random r = new Random();
		int i = 0;
		int c;
		while (i < length) {
			c = r.nextInt(122);
			if ((48 <= c && c <= 57) || (65 <= c && c <= 90)
					|| (97 <= c && c <= 122)) {
				buffer.append((char) c);
				i++;
			}
		}
		return buffer.toString();
	}

	/**
	 * 字符串左边补零
	 *
	 *
	 *
	 *
	 * 例：将字符串"ABC"用"0"补足8位，结果为"00000ABC"
	 *
	 * @param orgStr
	 *            原始字符串
	 *
	 *
	 *
	 *
	 * @param fillWith
	 *            用来填充的字符
	 *
	 *
	 *
	 *
	 * @param fixLen
	 *            固定长度
	 * @return
	 */
	public static String fillLeft(String orgStr, char fillWith, int fixLen) {

		return fillStr(orgStr, fillWith, fixLen, true);

	}

	/**
	 * 字符串右边补零
	 *
	 *
	 *
	 *
	 *
	 * @param orgStr
	 * @param fillWith
	 * @param fixLen
	 * @return
	 */
	public static String fillRight(String orgStr, char fillWith, int fixLen) {

		return fillStr(orgStr, fillWith, fixLen, false);

	}

	private static String fillStr(String orgStr, char fillWith, int fixLen,
			boolean isLeft) {

		int toFill = fixLen - orgStr.length();

		if (toFill <= 0) {
			return orgStr;
		}

		StringBuilder sb = new StringBuilder(orgStr);
		for (; toFill > 0; toFill--) {
			if (isLeft) {
				sb.insert(0, fillWith);
			} else {
				sb.append(fillWith);
			}
		}

		return sb.toString();

	}

	/**
	 * toTrim
	 *
	 * @param str
	 * @return
	 */
	public static String toTrim(String str) {
		if (str == null) {
			return "";
		}
		return str.trim();
	}

	public static String convertToString(int length, int value) {
		StringBuffer buffer = new StringBuffer();
		for (int i = 0; i < length - ("" + value).length(); i++) {
			buffer.append("0");
		}
		buffer.append(value);
		return buffer.toString();
	}

	public static String arrayToString(Object[] array, String split) {
		StringBuffer buffer = new StringBuffer();
		for (int i = 0; i < array.length; i++) {
			buffer.append(array[i].toString()).append(split);
		}
		if (buffer.length() != 0) {
			return buffer.substring(0, buffer.length() - split.length());
		} else {
			return "";
		}
	}

	public static String arrayToString(Set set, String split) {
		StringBuffer buffer = new StringBuffer();
		for (Iterator i = set.iterator(); i.hasNext();) {
			buffer.append(i.next().toString()).append(split);
		}
		if (buffer.length() != 0) {
			return buffer.substring(0, buffer.length() - split.length());
		} else {
			return "";
		}
	}

	public static String trimLeft(String s, char c) {
		if (s == null) {
			return "";
		} else {
			StringBuffer b = new StringBuffer();
			char[] cc = s.toCharArray();
			int i = 0;
			for (i = 0; i < cc.length; i++) {
				if (cc[i] != c) {
					break;
				}
			}
			for (int n = i; n < cc.length; n++) {
				b.append(cc[n]);
			}
			return b.toString();
		}
	}

	public static String repNull(Object o) {
		if (o == null) {
			return "";
		} else {
			return o.toString().trim();
		}
	}

	public static String generateRandomString(int len) {
		final char[] mm = new char[] { '0', '1', '2', '3', '4', '5', '6', '7',
				'8', '9' };

		StringBuffer sb = new StringBuffer();
		Random random = new Random();

		for (int i = 0; i < len; i++) {
			sb.append(mm[random.nextInt(mm.length)]);
		}
		return sb.toString();

	}

	public static String toColumnName(String attributeName) {
		StringBuffer buffer = new StringBuffer();
		for (int i = 0; i < attributeName.length(); i++) {
			char temp = attributeName.charAt(i);
			if (temp >= 'a' && temp <= 'z') {
				buffer.append((char) (temp - 32));
			}
			if (temp >= 'A' && temp <= 'Z') {
				buffer.append("_").append(temp);
			}
		}
		return buffer.toString();
	}

	public static String toPropertyName(String columnName) {
		StringBuffer buffer = new StringBuffer();
		boolean b = false;
		for (int i = 0; i < columnName.length(); i++) {
			char temp = columnName.charAt(i);
			if (temp >= '0' && temp <= '9') {
				buffer.append((char) (temp));
			} else if (temp == '_') {
				b = true;
			} else {
				if (!b) {
					buffer.append((char) (temp + 32));
				} else {
					buffer.append((char) (temp));
				}
				b = false;
			}
		}
		return buffer.toString();
	}

	/**
	 *
	 * Convert byte[] to hex
	 * string.这里我们可以将byte转换成int，然后利用Integer.toHexString(int)来转换成16进制字符串。
	 *
	 * @param src
	 *            byte[] data
	 * @return hex string
	 */

	public static String bytesToHexString(byte[] src) {

		StringBuilder stringBuilder = new StringBuilder("");
		if (src == null || src.length <= 0) {
			return null;
		}
		for (int i = 0; i < src.length; i++) {
			int v = src[i] & 0xFF;
			String hv = Integer.toHexString(v);
			if (hv.length() < 2) {
				stringBuilder.append(0);
			}
			stringBuilder.append(hv);
		}
		return stringBuilder.toString();

	}

	/**
	 * Convert hex string to byte[]
	 *
	 * @param hexString
	 *            the hex string
	 * @return byte[]
	 */
	public static byte[] hexStringToBytes(String hexString) {
		if (hexString == null || hexString.equals("")) {
			return null;
		}
		hexString = hexString.toUpperCase();
		int length = hexString.length() / 2;
		char[] hexChars = hexString.toCharArray();
		byte[] d = new byte[length];
		for (int i = 0; i < length; i++) {
			int pos = i * 2;
			d[i] = (byte) (charToByte(hexChars[pos]) << 4 | charToByte(hexChars[pos + 1]));
		}
		return d;
	}

	/**
	 * Convert char to byte
	 *
	 * @param c
	 *            char
	 * @return byte
	 */

	private static byte charToByte(char c) {
		return (byte) "0123456789ABCDEF".indexOf(c);
	}

	public static String xor(String s1, String s2) {
		int c = Integer.parseInt(s1);
		int d = Integer.parseInt(s2);
		int e = c ^ d;
		String f = Integer.toString(e);
		return f;
	}

	// XSS过滤
	public static boolean CheckXSS(String inputString) {

		String htmlStr = inputString; // 含html标签的字符串

		String textStr = "";
		java.util.regex.Pattern p_script;

		java.util.regex.Matcher m_script;

		java.util.regex.Pattern p_style;

		java.util.regex.Matcher m_style;

		java.util.regex.Pattern p_iframe;

		java.util.regex.Matcher m_iframe;

		java.util.regex.Pattern p_img;

		java.util.regex.Matcher m_img;

		java.util.regex.Pattern p_image;

		java.util.regex.Matcher m_image;

		java.util.regex.Pattern p_html;

		java.util.regex.Matcher m_html;

		try {
			// 定义script的正则表达式{或<script[^>]*?>[\\s\\S]*?<\\/script> }
			String regEx_script = "<[\\s]*?script[^>]*?>[\\s\\S]*?<[\\s]*?\\/[\\s]*?script[\\s]*?>";

			// 定义style的正则表达式{或<style[^>]*?>[\\s\\S]*?<\\/style> }
			String regEx_style = "<[\\s]*?style[^>]*?>[\\s\\S]*?<[\\s]*?\\/[\\s]*?style[\\s]*?>";

			// 定义iframe的正则表达式{或<iframe[^>]*?>[\\s\\S]*?<\\/iframe> }
			String regEx_iframe = "<[\\s]*?iframe[^>]*?>[\\s\\S]*?<[\\s]*?\\/[\\s]*?iframe[\\s]*?>";

			// 定义img的正则表达式{或<img[^>]*?>[\\s\\S]*?<\\/img> }
			String regEx_img = "<[\\s]*?img[^>]*?>[\\s\\S]*?<[\\s]*?\\/[\\s]*?img[\\s]*?>";

			// 定义image的正则表达式{或<image[^>]*?>[\\s\\S]*?<\\/image> }
			String regEx_image = "<[\\s]*?image[^>]*?>[\\s\\S]*?<[\\s]*?\\/[\\s]*?image[\\s]*?>";

			// 定义HTML标签的正则表达式
			String regEx_html = "<[^>]+>";

			p_script = Pattern.compile(regEx_script, Pattern.CASE_INSENSITIVE);
			m_script = p_script.matcher(htmlStr);
			htmlStr = m_script.replaceAll("-"); // 过滤script标签

			p_style = Pattern.compile(regEx_style, Pattern.CASE_INSENSITIVE);
			m_style = p_style.matcher(htmlStr);
			htmlStr = m_style.replaceAll("-"); // 过滤style标签

			p_iframe = Pattern.compile(regEx_iframe, Pattern.CASE_INSENSITIVE);
			m_iframe = p_iframe.matcher(htmlStr);
			htmlStr = m_iframe.replaceAll("-"); // 过滤style标签

			p_img = Pattern.compile(regEx_img, Pattern.CASE_INSENSITIVE);
			m_img = p_img.matcher(htmlStr);
			htmlStr = m_img.replaceAll("-"); // 过滤img标签

			p_image = Pattern.compile(regEx_image, Pattern.CASE_INSENSITIVE);
			m_image = p_image.matcher(htmlStr);
			htmlStr = m_image.replaceAll("-"); // 过滤image标签

			p_html = Pattern.compile(regEx_html, Pattern.CASE_INSENSITIVE);
			m_html = p_html.matcher(htmlStr);
			htmlStr = m_html.replaceAll("-"); // 过滤html标签

			textStr = htmlStr;
		} catch (Exception e) {
			System.err.println("Html2Text: " + e.getMessage());
		}
		if ("-".equals(textStr)) {
			return true;
		} else {
			return false;// 返回文本字符串
		}
	}

	public static String converStringToDate(String textStr) {
		if (textStr == null) {
			return "";
		} else {
			return textStr.substring(0, 4) + "-" + textStr.substring(4, 6)
					+ "-" + textStr.substring(6, 8) + " "
					+ textStr.substring(8, 10) + ":"
					+ textStr.substring(10, 12) + ":"
					+ textStr.substring(12, 14);
		}
	}

	public static String nvl(String str, String repal) {
		if (null == str || "".equals(str)) {
			return repal;
		}
		return "";
	}

	/**
	 * 验证一个字符串是否是数字组成
	 *
	 * @param s
	 *            要验证的字符串
	 * @return 如果字符串是数字组成的则返回true,否则返回false
	 */
	public static boolean isNumber(String s) {
		if (s == null || s.equals("")) {
			return false;
		}
		String num = "0123456789";
		for (int i = 0; i < s.length(); i++) {
			if (num.indexOf(s.charAt(i)) < 0) {
				return false;
			}
		}
		return true;
	}

	public static int length(String s) {
		if (s == null) {
			return 0;
		} else {
			return s.length();
		}
	}

	public static String[] splitToArray(String s, int length) {
		if (s == null || s.length() == 0) {
			return null;
		}
		int segs = (s.length() - 1) / length + 1;
		String[] arr = new String[segs];
		int i = 0;
		int n = 0;
		StringBuffer b = new StringBuffer();
		while (i < s.length()) {
			b.append(s.charAt(i));
			i++;
			if (b.length() == length) {
				arr[n] = b.toString();
				b = new StringBuffer();
				n++;
			}
		}
		if (b.length() != 0) {
			arr[segs - 1] = b.toString();
		}
		return arr;
	}
}
