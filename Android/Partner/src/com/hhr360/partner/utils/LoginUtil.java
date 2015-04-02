package com.hhr360.partner.utils;

import org.apache.http.Header;
import org.json.JSONException;
import org.json.JSONObject;

import com.hhr360.partner.PartnerApp;
import com.hhr360.partner.observer.ILoginObserver;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

/**
 * @author Tans
 */
public class LoginUtil implements IConstants {

	public static void login(final ILoginObserver observer,
			final String phoneNum, String password) {
		RequestParams params = new RequestParams();
		params.put(PHONE, phoneNum);
		params.put(PASSWORD, MD5.getMD5Str(password));
		HttpUtils.post(UrlManagerUtils.getLoginUrl(), params,
				new AsyncHttpResponseHandler() {

					@Override
					public void onSuccess(int code, Header[] head, byte[] result) {
						// 此处回调为主线程，json数据量不大，解析不费事，为了方便所以放在主线程
						int userId = 0;
						String message = "";
						String inviteCode = null;
						int loginStatus = 0;
						// 解析json数据
						String json = new String(result);
						JSONObject jsonObj;
						try {
							jsonObj = new JSONObject(json);
							loginStatus = jsonObj.getInt(IS_SUCCESS);
							message = jsonObj.getString(MESSAGE);
							if (loginStatus != 0) {
								userId = jsonObj.getInt(USER_ID);
								inviteCode = jsonObj.getString(INVITATION_CODE);
								if (userId > 0) {
									PartnerApp.PARTNER.setId(userId);
									PartnerApp.PARTNER
											.setInvitationCode(inviteCode);
									PartnerApp.PARTNER.setPhone(phoneNum);
									// 登录成功回调
									observer.ILoginObserver_succeed();
								} else {
									// 登录失败
									observer.ILoginObaserver_failed(message);
								}
							} else {
								// 登录失败
								observer.ILoginObaserver_failed(message);
							}

						} catch (JSONException e) {
							e.printStackTrace();
							// 登录失败
							observer.ILoginObaserver_failed("字段解析异常");
						}

					}

					@Override
					public void onFailure(int code, Header[] head,
							byte[] result, Throwable throwable) {
						observer.ILoginObaserver_failed("网络异常");
					}
				});
	}
}
