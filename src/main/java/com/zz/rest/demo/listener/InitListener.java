package com.zz.rest.demo.listener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import com.zz.rest.demo.utility.LogUtility;

public class InitListener implements ServletContextListener {

	public void contextDestroyed(ServletContextEvent arg0) {

	}

	public void contextInitialized(ServletContextEvent arg0) {
		LogUtility.businessLog().info("监听器已启动");
	}

}
