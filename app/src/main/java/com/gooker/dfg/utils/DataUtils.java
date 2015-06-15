package com.gooker.dfg.utils;

import com.aoeng.degu.services.BaseTask;
import com.aoeng.degu.services.DataCallback;
import com.aoeng.degu.utils.BaseHandler;
import com.aoeng.degu.utils.RequestVO;
import com.aoeng.degu.utils.common.UIUtils;

public class DataUtils {

	public static void getDateFromServer(RequestVO reqVo, DataCallback callBack) {
		// TODO Auto-generated method stub
		BaseHandler handler = new BaseHandler(callBack, reqVo);
		BaseTask taskThread = new BaseTask(reqVo, handler);
		UIUtils.getThreadPoolManager().addTask(taskThread);
	}

}
