package com.zz.rest.demo.model.dto;

import com.zz.rest.demo.model.entity.EntityBase;

public class UserDto extends EntityBase {
	private String username;
	private Integer age;

	public UserDto() {
	}

	public UserDto(String username, Integer age) {
		this.username = username;
		this.age = age;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

}