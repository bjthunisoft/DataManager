package com.talk.util;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;

/**
 * URL ±àÂë
 * 
 * @author gaoqiang01
 *
 */
public class URLCode {
	public static String encode(String word) throws UnsupportedEncodingException {
		return URLEncoder.encode(word, Constant.ENCODE_TYPE);
	}

	public static String encode(String word, String code) throws UnsupportedEncodingException {
		return URLEncoder.encode(word, code);
	}

	public static String decode(String word, String code) throws UnsupportedEncodingException {
		return URLDecoder.decode(word, code);
	}
}
