package com.zz.rest.demo.config.spring.mvc;

import org.springframework.context.ApplicationContext;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;

public class SpringMvcInitConfig {
	/**
	 * 用注解方式加载springmvc配置(为了大众化,此处可以改为配置文件的方式)
	 * 
	 * @param ctx
	 * @return
	 */
	public static WebApplicationContext webApplicationContext(ApplicationContext ctx) {
		AnnotationConfigWebApplicationContext context = new AnnotationConfigWebApplicationContext();
		context.setParent(ctx);
		context.register(AppConfiguration.class);
		return context;
	}
}
