package com.alic.util;

import java.io.IOException;
import java.io.StringReader;
import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

import org.wltea.analyzer.core.IKSegmenter;
import org.wltea.analyzer.core.Lexeme;

/**
 * 字符串操作工具类
 * @author rui.wang
 * @since 2013年9月5日
 */
public class StringUtils extends org.apache.commons.lang3.StringUtils {

	/**
	 * 序列化字符串
	 * @param string 字符串
	 * @return 序列化后的字节数组
	 */
	public static byte[] serialize(final String string, final Charset charset) {
		return (string == null ? null : string.getBytes(charset));
	}

	/**
	 * 反序列化字符串
	 * @param bytes 序列化字节数组
	 * @return 反序列化后的字符串
	 */
	public static String deserialize(final byte[] bytes, final Charset charset) {
		return (bytes == null ? null : new String(bytes, charset));
	}

	/**
	 * 判断str不为null或不为""
	 * @param str
	 * @return
	 */
	public static boolean notEmpty(String str) {
		return !isEmpty(str);
	}

	/**
	 * 判断str不为null或不为""或trim之后不为""
	 * @param str
	 * @return
	 */
	public static boolean notBlank(String str) {
		return !isBlank(str);
	}

	/**
	 * 使用StringBuilder进行拼接
	 * @param strings
	 * @return
	 */
	public static StringBuilder concatToSB(String... strings) {
		StringBuilder builder = new StringBuilder();
		if (strings != null) {
			for (String str : strings) {
				builder.append(str);
			}
		}
		return builder;
	}

	/**
	 * 去掉为NULL的情况
	 * @param str
	 * @return
	 */
	public static String safeValue(String str) {
		if (str == null) return "";
		return str;
	}

	/**
	 * object to string
	 * @param r
	 * @return
	 */
	public static String objectToStr(Object obj) {
		if (obj == null) return "";
		return String.valueOf(obj);
	}

	/**
	 * 分词
	 * @param str 要分词的字符串
	 * @param smartFlag 为true，使用智能分词策略 非智能分词：细粒度输出所有可能的切分结果 智能分词： 合并数词和量词，对分词结果进行歧义判断
	 * @return 拆分好的词组
	 */
	public static String[] splitByWord(String str, boolean smartFlag) {
		List<String> words = new ArrayList<>();
		try {
			IKSegmenter ikSegmenter = new IKSegmenter(new StringReader(str), smartFlag);
			for (Lexeme Lexeme = ikSegmenter.next(); Lexeme != null; Lexeme = ikSegmenter.next()) {
				words.add(Lexeme.getLexemeText());
			}
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
		String[] wordsStrArray = new String[words.size()];
		return words.toArray(wordsStrArray);
	}

	/**
	 * 截短字符串，返回长度不大于maxLen的子串.
	 * 如果所给源字符串长度过大，则大于maxLen的后面部分用“…”替换
	 * @param str 源字符
	 * @param maxLen 截短后的最大长度(按字节计算，一个汉字或全角标点长度2，一个英文、数字或半角标点长度1)
	 * @return 截短后的字符串
	 */
	public static String getLimitLengthString(String str, int maxLen) {
		return getLimitLengthString(str, maxLen, Charset.defaultCharset().name());
	}

	/**
	 * 截短字符串，返回长度不大于maxLen的子串.
	 * 如果所给源字符串长度过大，则大于maxLen的后面部分用symbol替换。
	 * 如果为空(null)则返回""。
	 * @param str 源字符
	 * @param maxLen 截短后的最大长度(按字节计算，一个汉字或全角标点长度2，一个英文、数字或半角标点长度1)
	 * @param charsetName 字符集
	 * @return 截短后的字符串
	 */
	public static String getLimitLengthString(String str, int maxLen, String charsetName) {
		if (str == null) {
			return "";
		}
		try {
			byte b[] = str.getBytes(charsetName);
			if (b.length <= maxLen) {
				return str;
			}

			// 返回字符串的长度应小于或等于此长度
			int len = maxLen;

			// 使用二分法查找算法
			int index = 0;
			// 记录第一个元素
			int lower = 0;
			// 记录最后一个元素
			int higher = str.length() - 1;
			while (lower <= higher) {
				// 记录中间元素，用两边之和除2
				index = (lower + higher) / 2;
				int tmpLen = str.substring(0, index).getBytes(charsetName).length;
				if (tmpLen == len) {
					// 如果得到的与要查找的相等，则break退出
					break;
				} else if (tmpLen < len) {
					// 如果得到的要小于查找的，就用下标加1
					lower = index + 1;
				} else {
					// 如果得到的要大于查找的，就用下标减1
					higher = index - 1;
				}
			}
			if (lower > higher) {
				index = higher;
			}

			// 调用String构造方法以避免substring导致的内存泄露
			return new String(str.substring(0, index));
		} catch (UnsupportedEncodingException e) {
			throw new IllegalStateException(e);
		}
	}

	/**
	 * 去掉字符串两端的全角空格和半角空格.
	 * 如果为空(null)则返回""
	 * @param str 字符串
	 * @return 无左右空格的字符串
	 */
	public static String trim(String str) {
		if (str == null || str.equals("")) {
			return "";
		} else {
			return str.replaceAll("^[　 ]+|[　 ]+$", "");
		}
	}

	/**
	 * 去除字符串首尾空格以及中间的所有空格，包括空白符、换行符、段落符、全角空格等
	 * 如果为空(null)则返回""
	 * @param str 源字符
	 * @return 不包含空格的字符串
	 * @see java.lang.Character#isWhitespace(char)
	 */
	public static String trimAll(String str) {
		if (str == null) {
			return "";
		}

		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < str.length(); i++) {
			char ch = str.charAt(i);
			// 过滤掉各种空白符
			if (!Character.isWhitespace(ch)) {
				sb.append(ch);
			}
		}

		return sb.toString();
	}
}
