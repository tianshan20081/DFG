/**
 * 
 */
package com.gooker.dfg.utils;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Mar 23, 2014 11:32:19 AM
 * 
 */
public class ThreadPoolManager {
	private ExecutorService service;
	private static com.aoeng.degu.utils.ThreadPoolManager manager = new com.aoeng.degu.utils.ThreadPoolManager();

	private ThreadPoolManager() {
		int num = Runtime.getRuntime().availableProcessors();
		service = Executors.newFixedThreadPool(num * 4);
	}

	/**
	 * @return
	 */
	public static com.aoeng.degu.utils.ThreadPoolManager getInstance() {
		// TODO Auto-generated method stub
		return manager;
	}
	public void addTask(Runnable runnable) {
		service.execute(runnable);
	}
}
