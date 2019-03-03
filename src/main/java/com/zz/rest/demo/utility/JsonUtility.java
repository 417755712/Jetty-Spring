package com.zz.rest.demo.utility;

import com.google.gson.Gson;

public final class JsonUtility {
	private static final Gson gson = new Gson();
	
	private JsonUtility() {
	}
	
	public static String obj2Json(Object o) {
		return gson.toJson(o);
	}
	
	public static <T> T json2Obj(String json, Class<T> clazz) {
		return gson.fromJson(json, clazz);
	}
}
