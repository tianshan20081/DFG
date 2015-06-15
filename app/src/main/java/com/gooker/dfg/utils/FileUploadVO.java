package com.gooker.dfg.utils;

import java.io.File;
import java.util.HashMap;
import java.util.LinkedHashMap;

import com.aoeng.degu.parser.BaseParser;
import com.aoeng.degu.utils.RequestVO;

import android.content.Context;

public class FileUploadVO extends RequestVO{
	public HashMap<String, String> fileFieldMap;

	public FileUploadVO() {
		super();
	}

	public FileUploadVO(String requestUrl,HashMap<String, String> requestDataMap, HashMap<String, String> fileFieldMap, BaseParser<?> jsonParser) {
		super();
		this.requestUrl = requestUrl;
		this.requestDataMap = requestDataMap;
		this.fileFieldMap = fileFieldMap;
		this.jsonParser = jsonParser;
	}

}
