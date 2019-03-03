package com.zz.rest.demo.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zz.rest.demo.constant.CommonErrorId;
import com.zz.rest.demo.exception.ApplicationException;
import com.zz.rest.demo.model.entity.GeneralJsonResult;

/**
 * controller父类
 * 
 * @author zz
 *
 */
public abstract class BaseController {

	/**
	 * 基于ExceptionHandler的统一controller异常处理
	 * 
	 * @param request
	 * @param ex
	 * @return
	 */
	@ExceptionHandler
	@ResponseBody
	public final GeneralJsonResult exception(HttpServletRequest request, HttpServletResponse response, Exception ex) {
		if (ex instanceof ApplicationException) {
			return GeneralJsonResult.error(((ApplicationException) ex).getErrorId());
		} else {
			return GeneralJsonResult.error(CommonErrorId.ERROR_UNKNOWN);
		}
	}
}
