/**
 * 
 */
package com.gooker.dfg.utils;

import com.gooker.dfg.parser.BaseParser;

import java.util.HashMap;


/**
 * Mar 23, 2014 11:51:00 AM
 * 
 */
public class RequestVO {
	public String requestUrl;
	public HashMap<String, String> requestDataMap;
	public BaseParser<?> jsonParser;

	public RequestVO() {
	}

	public RequestVO(String requestUrl, HashMap<String, String> requestDataMap, BaseParser<?> jsonParser) {
		super();
		this.requestUrl = requestUrl;
		this.requestDataMap = requestDataMap;
		this.jsonParser = jsonParser;
	}
}
