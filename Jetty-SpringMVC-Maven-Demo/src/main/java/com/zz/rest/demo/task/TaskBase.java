package com.zz.rest.demo.task;

import com.zz.rest.demo.utility.LogUtility;

/**
 * 定时器父类
 *
 */
public abstract class TaskBase {

	public final void execute() {
		try {
			doTask();
		} catch (Exception e) {
			LogUtility.businessLog().warn("TASK ERROR-->" + e.getMessage());
		}
	}

	protected abstract void doTask() throws Exception;
}
