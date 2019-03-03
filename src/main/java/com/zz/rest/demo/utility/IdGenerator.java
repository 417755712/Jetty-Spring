package com.zz.rest.demo.utility;

public class IdGenerator {

	private static SnowflakeIdWorker snowflake;

	public static synchronized void initSnowflake(long workerId, long datacenterId) {
		snowflake = new SnowflakeIdWorker(workerId, datacenterId);
	}

	public static long nextId() {
		return snowflake.nextId();
	}
}
