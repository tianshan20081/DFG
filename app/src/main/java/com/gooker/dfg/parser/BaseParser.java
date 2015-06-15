/**
 * 
 */
package com.gooker.dfg.parser;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * May 21, 2014 5:02:39 PM
 * 
 */
public abstract class BaseParser<T> {
	String TAG = com.aoeng.degu.parser.BaseParser.class.getName();

	public abstract T parseJSON(String paramString) throws JSONException;

	/**
	 * 
	 * @param res
	 * @throws JSONException
	 */
	public String checkResponse(String paramString) throws JSONException {
		if (paramString == null) {
			return null;
		} else {
			JSONObject jsonObject = new JSONObject(paramString);
			String result = jsonObject.optString("result");

			if (result != null && !result.equals("0")) {
				return result;
			} else {
				return null;
			}

		}
	}

}
