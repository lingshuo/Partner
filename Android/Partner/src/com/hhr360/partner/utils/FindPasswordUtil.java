package com.hhr360.partner.utils;

import org.apache.http.Header;
import org.json.JSONException;
import org.json.JSONObject;

import com.hhr360.partner.observer.IFindPasswordObserver;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;


public class FindPasswordUtil implements IConstants {

	public static void sendSmsCode(final IFindPasswordObserver observer,
			String phone) {
		RequestParams params = new RequestParams();
		params.put(PHONE, phone);
		HttpUtils.post(UrlManagerUtils.getSendSmsCodeUrl(), params,
				new AsyncHttpResponseHandler() {

					@Override
					public void onSuccess(int code, Header[] head, byte[] result) {
						String json = new String(result);
						try {
							JSONObject jsonObj = new JSONObject(json);
							int status = jsonObj.getInt(IS_SUCCESS);
							String msg = jsonObj.getString(MESSAGE);
							if (status == 1) {
								String phoneCode = jsonObj
										.getString("phone_code");
								observer.IFindPasswordObserver_onSuccess(phoneCode);
							} else {
								observer.IFindPasswordObserver_onFailed(msg);
							}
						} catch (JSONException e) {
							e.printStackTrace();
							observer.IFindPasswordObserver_onFailed("请求失败");
						}
					}

					@Override
					public void onFailure(int code, Header[] head,
							byte[] result, Throwable throwable) {
						observer.IFindPasswordObserver_onFailed("请求失败");
					}
				});
	}
}
