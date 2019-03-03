package com.zz.rest.demo.helper;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import com.zz.rest.demo.constant.CommonErrorId;
import com.zz.rest.demo.exception.ApplicationException;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * 所有http调用方式,都采用okhttp3 封装在这个类
 * 
 * @author ZZ
 *
 */
public final class HttpHelper {
	private HttpHelper() {
	}

	/**
	 * application/json的post请求
	 * 
	 * @param url
	 * @param jsonParam
	 * @return
	 * @throws IOException
	 */
	public static String doJsonPost(String url, String contentType, String jsonParam) throws ApplicationException {
		OkHttpClient client = new OkHttpClient().newBuilder().connectTimeout(10, TimeUnit.SECONDS)
				.readTimeout(30, TimeUnit.SECONDS).build();
		RequestBody body = RequestBody.create(MediaType.get(contentType), jsonParam);
		Request request = new Request.Builder().url(url).post(body).build();
		try {
			Response response = client.newCall(request).execute();
			return response.body().string();
		} catch (Exception e) {
			throw new ApplicationException(CommonErrorId.ERROR_REMOTE_CALL, e.getMessage());
		}
	}

}
