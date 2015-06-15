package com.gooker.dfg.parser;

import android.text.TextUtils;

import com.alibaba.fastjson.JSON;
import com.gooker.dfg.domain.BaiDuLocation;

import org.json.JSONException;

public class BaiDuLocationParser extends BaseParser<BaiDuLocation> {

	@Override
	public BaiDuLocation parseJSON(String paramString) throws JSONException {
		// TODO Auto-generated method stub
		if (!TextUtils.isEmpty(checkResponse(paramString))) {
			return JSON.parseObject(paramString, BaiDuLocation.class);
		}
		return null;
	}


}
