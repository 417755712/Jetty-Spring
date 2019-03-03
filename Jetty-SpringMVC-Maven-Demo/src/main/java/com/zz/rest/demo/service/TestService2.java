package com.zz.rest.demo.service;

import org.springframework.stereotype.Service;

import com.zz.rest.demo.annotation.GrabAbnormal;

@Service
public class TestService2 {

	@GrabAbnormal
	public void test() {
		int i[] = new int[2];
		i[3] = 5;
	}
}
