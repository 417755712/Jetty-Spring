package com.zz.rest.demo.model.entity;

import com.zz.rest.demo.utility.JsonUtility;

public abstract class EntityBase {

	public String toJSONStr() {
		return JsonUtility.obj2Json(this);
	}
}