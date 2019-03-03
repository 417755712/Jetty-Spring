package com.zz.rest.demo.utility;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Field;
import java.nio.ByteBuffer;

public class StringUtility {
	private final static char[] zeroArr = new char[] { '0', '0', '0', '0', '0', '0', '0', '0', '0', '0' };
	private final static int KEYVALUELEN = 10;

	private StringUtility() {
	}

	public static boolean isNullOrEmpty(String str) {
		return str == null ? true : str.isEmpty();
	}

	public static String o2s(Object o) {
		if (o == null) {
			return null;
		} else {
			if (o instanceof String) {
				return (String) o;
			} else {
				return o.toString();
			}
		}
	}

	public static String alignRight(String data, int len) {
		StringBuilder sb = new StringBuilder(data);
		while (sb.length() < len) {
			sb.insert(0, ' ');
		}
		return sb.toString();
	}

	public static String alignLeft(String data, int len) {
		StringBuilder sb = new StringBuilder(data);
		while (sb.length() < len) {
			sb.append(' ');
		}
		return sb.toString();
	}

	/**
	 * 对应一个int数字，生成长度为10的固定长字符串
	 * 
	 * @param value
	 * @return
	 */
	public static String generateFixLengthNumString(int value) {
		String key = String.valueOf(value);
		String s = new String(zeroArr, 0, KEYVALUELEN - key.length());
		return s + key;
	}

	/**
	 * 替换指定对象 String类型为NULL的属性成空字符串 ""
	 * 
	 * @param obj
	 */
	public static void replaceAllNull(Object obj) {
		if (obj == null)
			return;

		Class<?> cls = obj.getClass();
		if (cls == null)
			return;

		Field[] flds = cls.getDeclaredFields();
		if (flds == null)
			return;

		Field field;
		for (int i = 0; i < flds.length; i++) {
			field = flds[i];
			field.setAccessible(true);

			// String类型的属性
			if (field.getGenericType().toString().equals("class java.lang.String")) {
				try {
					if (field.get(obj) == null) {
						field.set(obj, "");
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
	}

	/**
	 * 将Exception.printStackTrace()转换为String输出
	 * 
	 * @param e
	 * @return
	 */
	public static String getErrorInfoFromException(Exception e) {
		try {
			StringWriter sw = new StringWriter();
			PrintWriter pw = new PrintWriter(sw);
			e.printStackTrace(pw);
			return "\r\n" + sw.toString() + "\r\n";
		} catch (Exception e2) {
			return "bad getErrorInfoFromException";
		}
	}

	/**
	 * 过滤掉UTF-8字符串中占4个字节的大字符(解决类似emoji表情 插入当前utf8编码的数据库报异常问题)
	 * 
	 * @param text
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	public static String filterOffUtf8Mb4(String text) throws UnsupportedEncodingException {
		byte[] bytes = text.getBytes("utf-8");
		ByteBuffer buffer = ByteBuffer.allocate(bytes.length);
		int i = 0;
		while (i < bytes.length) {
			short b = bytes[i];
			if (b > 0) {
				buffer.put(bytes[i++]);
				continue;
			}

			b += 256; // 去掉符号位

			if (((b >> 5) ^ 0x6) == 0) {
				buffer.put(bytes, i, 2);
				i += 2;
			} else if (((b >> 4) ^ 0xE) == 0) {
				buffer.put(bytes, i, 3);
				i += 3;
			} else if (((b >> 3) ^ 0x1E) == 0) {
				i += 4;
			} else if (((b >> 2) ^ 0x3E) == 0) {
				i += 5;
			} else if (((b >> 1) ^ 0x7E) == 0) {
				i += 6;
			} else {
				buffer.put(bytes[i++]);
			}
		}
		buffer.flip();
		return new String(buffer.array(), "utf-8").trim();
	}
}
