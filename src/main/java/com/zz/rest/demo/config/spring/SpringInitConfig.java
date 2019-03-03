package com.zz.rest.demo.config.spring;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class SpringInitConfig {

	public static ApplicationContext initSpringXml() {
		String[] ctxList = { "classpath*:/config/applicationContext-datasource.xml",
				"classpath*:/config/applicationContext-mybatis.xml",
				"classpath*:/config/applicationContext-transaction.xml",
				"classpath*:/config/applicationContext-service.xml", "classpath*:/config/applicationContext-aop.xml",
				// "classpath*:/config/applicationContext-task.xml" };
		};
		return new ClassPathXmlApplicationContext(ctxList);
	}
}
