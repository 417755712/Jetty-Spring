package com.zz.rest.demo.service;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zz.rest.demo.annotation.ApplicationTransaction;
import com.zz.rest.demo.annotation.GrabAbnormal;
import com.zz.rest.demo.constant.CommonErrorId;
import com.zz.rest.demo.exception.ApplicationException;
import com.zz.rest.demo.mapper.inf.TestPoMapper;
import com.zz.rest.demo.model.dto.UserDto;
import com.zz.rest.demo.model.po.TestPo;

@Service
public class TestService {

	@Autowired
	private TestPoMapper testPoMapper;
	@Autowired
	private TestService2 testService2;

	@GrabAbnormal
	@ApplicationTransaction
	public void test() throws ApplicationException {
		TestPo record = new TestPo();
		record.setName("test");
		record.setSex("女");
		Date date = new Date();
		record.setCreateTime(date);
		testPoMapper.insert(record);
		System.out.println("I'm Service!");
	}

	@GrabAbnormal
	public void testAop(UserDto dto, String name, Integer count) throws ApplicationException {
		testService2.test();
		throw new ApplicationException(CommonErrorId.ERROR_UNKNOWN, "这是一个测试异常");
	}
}
