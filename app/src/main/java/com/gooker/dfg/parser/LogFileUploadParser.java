package com.gooker.dfg.parser;

import org.json.JSONException;
import org.json.JSONObject;

import android.text.TextUtils;

import com.alibaba.fastjson.JSON;
import com.aoeng.degu.domain.LogFileUploadResult;
import com.aoeng.degu.parser.BaseParser;

public class LogFileUploadParser extends com.gooker.dfg.parser.BaseParser {

	@Override
	public Object parseJSON(String paramString) throws JSONException {
		// TODO Auto-generated method stub
		if (!TextUtils.isEmpty(checkResponse(paramString))) {
			JSONObject jsonObject = new JSONObject(paramString);
			String json = jsonObject.getString("result");
			return JSON.parseObject(json, com.gooker.dfg.domain.LogFileUploadResult.class);
		}
		return null;
	}

}
