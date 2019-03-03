package com.zz.rest.demo.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import com.zz.rest.demo.utility.LogUtility;

public class LoginFilter implements Filter {

	@Override
	public void destroy() {
	}

	@Override
	public void doFilter(ServletRequest req, ServletResponse rsp, FilterChain chain)
			throws IOException, ServletException {
		chain.doFilter(req, rsp);
		return;
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		LogUtility.businessLog().info("身份认证过滤器已启动!!");
	}

}
