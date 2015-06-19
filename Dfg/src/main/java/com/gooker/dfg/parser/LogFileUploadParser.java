package com.gooker.dfg.parser;

import android.text.TextUtils;

import com.alibaba.fastjson.JSON;

import org.json.JSONException;
import org.json.JSONObject;

public class LogFileUploadParser extends BaseParser {

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
