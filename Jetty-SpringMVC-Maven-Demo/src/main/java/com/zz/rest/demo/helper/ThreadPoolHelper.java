package com.zz.rest.demo.helper;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * 所有业务线程池必须存放于此,并且带有自己的业务命名
 * 
 * @author ZZ
 *
 */
public final class ThreadPoolHelper {
	// 统一项目初始化的时候加载
	private static final ThreadPoolExecutor API_LOG_THREADPOOL = initGeneralThreadPool(1, 5000, "apiLogThread");

	public static ThreadPoolExecutor getApiLogThreadpool() {
		return API_LOG_THREADPOOL;
	}

	private ThreadPoolHelper() {
	}

	/**
	 * 通用线程池初始化方法,三个属性均不能为空(业务自行保证)
	 * 
	 * @param poolSize
	 * @param queueSize
	 * @param threadName
	 */
	private static ThreadPoolExecutor initGeneralThreadPool(int poolSize, int queueSize, String threadName) {
		AppThreadFactory threadFactory = new AppThreadFactory();
		threadFactory.name = threadName;
		return new ThreadPoolExecutor(poolSize, poolSize, 1, TimeUnit.SECONDS,
				new LinkedBlockingQueue<Runnable>(queueSize), threadFactory);
	}

	/**
	 * 自定义线程工厂,设置线程名称格式为 name + number(同一业务递增)
	 * 
	 * @author ZZ
	 *
	 */
	static class AppThreadFactory implements ThreadFactory {
		String name;
		int number = 0;

		@Override
		public Thread newThread(Runnable r) {
			Thread thread = new Thread(r);
			thread.setName(name + "-" + (number++));
			return thread;
		}
	}
}
