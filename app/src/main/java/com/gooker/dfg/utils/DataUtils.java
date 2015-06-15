package com.gooker.dfg.utils;


import com.gooker.dfg.services.BaseTask;
import com.gooker.dfg.services.DataCallback;
import com.gooker.dfg.utils.common.UIUtils;

public class DataUtils {

	public static void getDateFromServer(RequestVO reqVo, DataCallback callBack) {
		// TODO Auto-generated method stub
		BaseHandler handler = new BaseHandler(callBack, reqVo);
		BaseTask taskThread = new BaseTask(reqVo, handler);
		UIUtils.getThreadPoolManager().addTask(taskThread);
	}

}
