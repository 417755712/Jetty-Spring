package com.zz.rest.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.zz.rest.demo.exception.ApplicationException;
import com.zz.rest.demo.model.dto.UserDto;
import com.zz.rest.demo.service.TestService;
import com.zz.rest.demo.utility.LogUtility;

@RestController
public class HelloController extends BaseController {

	@Autowired
	private TestService service;

	@RequestMapping("/hello")
	public String hello() throws ApplicationException {
		service.test();
		return "Hello World! Welcome to visit waylau.com!";
	}

	@RequestMapping("/hello/{way}")
	public UserDto helloWay(@PathVariable("way") String way) throws ApplicationException {
		UserDto userDto = new UserDto("Way 张智", 30);
		service.testAop(userDto, "哈哈哈", 333);
		return userDto;
	}

	@RequestMapping(path = "/testJSON", consumes = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.POST)
	public UserDto testBody(@RequestBody UserDto dto) throws ApplicationException {
		System.out.println(dto);
		return dto;
	}

	@RequestMapping(path = "/{appId}/callback", consumes = { MediaType.TEXT_XML_VALUE, MediaType.TEXT_PLAIN_VALUE,
			MediaType.APPLICATION_JSON_VALUE })
	public String helloWay(@PathVariable("appId") String appId, String token, String key) throws ApplicationException {
		LogUtility.businessLog().info("appId");
		LogUtility.businessLog().info("token");
		LogUtility.businessLog().info("key");
		return "success";
	}
}