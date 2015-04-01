package com.hhr360.partner.utils;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

/**
 * HttpUtils 网络加载数据和缓存数据工具类
 * 
 * @author Tans
 */
public class HttpUtils {

	private static AsyncHttpClient mClient = new AsyncHttpClient();

	/**
	 * http get 请求不带参数
	 * 
	 * @param url
	 * @param res
	 */
	public static void get(String url, AsyncHttpResponseHandler res) {
		mClient.get(url, res);
	}

	/**
	 * http get 请求带参数
	 * 
	 * @param url
	 * @param params
	 * @param res
	 */
	public static void get(String url, RequestParams params,
			AsyncHttpResponseHandler res) {
		mClient.get(url, params, res);
	}

	/**
	 * post 不带参数
	 * 
	 * @param url
	 * @param res
	 */
	public static void post(String url, AsyncHttpResponseHandler res) {
		mClient.post(url, res);
	}

	/**
	 * http post 请求带参数
	 * 
	 * @param url
	 * @param params
	 * @param res
	 */
	public static void post(String url, RequestParams params,
			AsyncHttpResponseHandler res) {
		mClient.post(url, params, res);
	}

}
