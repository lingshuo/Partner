package com.hhr360.partner.utils;

import org.apache.http.Header;
import org.json.JSONException;
import org.json.JSONObject;

import com.hhr360.partner.observer.IResetPasswordObserver;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;


/**
 * @author Tans
 * 
 */
public class ResetPasswordUtil {

	public static void resetPassword(final IResetPasswordObserver observer,
			String phone, String password, String phone_code) {
		RequestParams params = new RequestParams();
		params.put("newpwd", MD5.getMD5Str(password));
		params.put("phone", phone);
		params.put("phone_code", phone_code);
		HttpUtils.post(UrlManagerUtils.getResetPwdUrl(), params,
				new AsyncHttpResponseHandler() {

					@Override
					public void onSuccess(int code, Header[] head, byte[] result) {
						String json = new String(result);
						String msg = "";
						try {
							JSONObject jsonObj = new JSONObject(json);
							int status = jsonObj.getInt("is_success");
							msg = jsonObj.getString("message");
							if (status == 1) {
								observer.IResetPasswordObserver_onSuccess(msg);
							} else {
								observer.IResetPasswordObserver_onFailed(msg);
							}
						} catch (JSONException e) {
							observer.IResetPasswordObserver_onFailed(msg);
							e.printStackTrace();
						}

					}

					@Override
					public void onFailure(int code, Header[] head,
							byte[] result, Throwable throwable) {
						observer.IResetPasswordObserver_onFailed("请求失败");
					}
				});
	}
}
