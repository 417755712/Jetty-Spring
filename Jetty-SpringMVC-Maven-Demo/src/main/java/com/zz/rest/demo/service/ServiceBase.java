package com.zz.rest.demo.service;

import com.zz.rest.demo.constant.BusinessErrorId;
import com.zz.rest.demo.exception.ApplicationException;
import com.zz.rest.demo.utility.IdGenerator;
import com.zz.rest.demo.utility.StringUtility;

public abstract class ServiceBase implements BusinessErrorId {

	/**
	 * 检查参数是否是null
	 * 
	 * @param param
	 *            参数值
	 * @param name
	 *            参数名
	 * @throws InvalidParamException
	 */
	protected void checkParamNotNull(String param, String name) throws ApplicationException {
		if (StringUtility.isNullOrEmpty(param)) {
			throw new ApplicationException(ERROR_PARAMETER, name);
		}
	}

	/**
	 * 检查参数是否是null
	 * 
	 * @param param
	 *            参数值
	 * @param name
	 *            参数名
	 * @throws InvalidParamException
	 */
	protected void checkParamNotNull(Object param, String name) throws ApplicationException {
		if (param == null) {
			throw new ApplicationException(ERROR_PARAMETER, name);
		}
	}

	/**
	 * 属性copy
	 * 
	 * @param src
	 * @param des
	 */
	protected void copyProperties(Object src, Object des) {
		org.springframework.beans.BeanUtils.copyProperties(src, des);
	}

	/**
	 * 基于snowflake算法生成long型id
	 * 
	 * @param prefix
	 * @return
	 */
	protected long getSnowflakeNextId() {
		return IdGenerator.nextId();
	}

	protected int getOffset(int numberPerPage, int currentPage) {
		return numberPerPage * (currentPage - 1);
	}
}
