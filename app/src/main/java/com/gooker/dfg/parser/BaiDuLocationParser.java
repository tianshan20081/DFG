package com.gooker.dfg.parser;

import org.json.JSONException;

import android.text.TextUtils;

import com.alibaba.fastjson.JSON;
import com.aoeng.degu.domain.BaiDuLocation;
import com.aoeng.degu.parser.BaseParser;

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
