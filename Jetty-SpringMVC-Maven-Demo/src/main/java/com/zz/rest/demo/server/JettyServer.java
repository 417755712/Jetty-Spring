package com.zz.rest.demo.server;

import java.util.EnumSet;

import javax.servlet.DispatcherType;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

import com.zz.rest.demo.config.spring.SpringInitConfig;
import com.zz.rest.demo.config.spring.mvc.SpringMvcInitConfig;
import com.zz.rest.demo.filter.ApplicationFilter;
import com.zz.rest.demo.filter.LoginFilter;
import com.zz.rest.demo.listener.InitListener;
import com.zz.rest.demo.utility.ConfigUtility;
import com.zz.rest.demo.utility.IdGenerator;
import com.zz.rest.demo.utility.LogUtility;

/**
 * http服务启动器
 * 
 * @author ZZ
 *
 */
public class JettyServer {
	private final int DEFAULT_PORT = 80;
	private final String CONTEXT_PATH = "/";
	private final String MAPPING_URL = "/*";

	public static void main(String[] args) throws Exception {
		new JettyServer().run();
	}

	private void run() throws Exception {
		// 初始化配置文件
		ConfigUtility.init("config/server.properties");
		LogUtility.businessLog().info("Init config...");
		// 初始化数据中心
		initDatacenter();
		// 初始化Spring配置文件
		ApplicationContext ctx = SpringInitConfig.initSpringXml();
		LogUtility.businessLog().info("Spring Init Success!");
		// 初始化SpringMvc配置文件
		WebApplicationContext webApplicationContext = SpringMvcInitConfig.webApplicationContext(ctx);
		LogUtility.businessLog().info("SpringMvc Init Success!");
		// 启动jetty
		int port = ConfigUtility.getPropertyAsInt("port", DEFAULT_PORT);
		Server server = new Server(port);
		ServletContextHandler servletContextHandler = servletContextHandler(webApplicationContext);
		server.setHandler(servletContextHandler);
		server.start();
		LogUtility.businessLog().info("Start Success! Listen port:{}", port);
		server.join();
	}

	/**
	 * 初始化数据中心设置
	 */
	private void initDatacenter() {
		long datacenterId = ConfigUtility.getPropertyAsLong("datacenter.id", 1);
		long workerId = ConfigUtility.getPropertyAsLong("worker.id", 1);
		IdGenerator.initSnowflake(workerId, datacenterId);
		LogUtility.businessLog().info("当前数据中心编号为{},机器编号为{}", datacenterId, workerId);
	}

	/**
	 * 设置jettyServlet
	 * 
	 * @param context
	 * @return
	 */
	private ServletContextHandler servletContextHandler(WebApplicationContext context) {
		ServletContextHandler handler = new ServletContextHandler();
		handler.setContextPath(CONTEXT_PATH);

		ServletHolder servletHolder = new ServletHolder(new DispatcherServlet(context));
		handler.addServlet((servletHolder), MAPPING_URL);
		// 配置监听器
		handler.addEventListener(new ContextLoaderListener(context));
		handler.addEventListener(new InitListener());
		// 配置过滤器
		handler.addFilter(ApplicationFilter.class, "/*", EnumSet.of(DispatcherType.REQUEST));
		handler.addFilter(LoginFilter.class, "/*", EnumSet.of(DispatcherType.REQUEST));

		return handler;
	}

}
