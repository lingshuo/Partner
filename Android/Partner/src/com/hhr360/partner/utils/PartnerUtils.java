package com.hhr360.partner.utils;

import org.apache.http.Header;
import org.json.JSONException;
import org.json.JSONObject;

import android.content.Context;
import android.widget.Toast;

import com.hhr360.partner.bean.User;
import com.hhr360.partner.json.JsonUtils;
import com.hhr360.partner.observer.IPartnerObserver;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

public class PartnerUtils {

	private static PartnerUtils instance;

	private PartnerUtils() {

	}

	// 单例模式
	public static PartnerUtils getInstance() {
		if (instance == null) {
			instance = new PartnerUtils();
		}
		return instance;
	}

	// 获取合伙人信息
	public void getPartner(final Context context,
			final IPartnerObserver observer, int userId) {

		final ACache aCache = ACache.get(context);
		final String url = UrlManagerUtils.getPartnerUrl(userId);
		if (!NetworkStateUtil.isAvaliable()) {
			// 网络不能用，使用本地缓存
			JSONObject cacheJObj = aCache.getAsJSONObject(url);
			if (cacheJObj != null) {
				try {
					Toast.makeText(context, "使用缓存", Toast.LENGTH_LONG).show();
					JsonUtils.parsePartner(cacheJObj, observer);
				} catch (JSONException e) {
					observer.IPartnerObserver_failed("解析本地缓存异常");
					e.printStackTrace();
				}
			} else {
				observer.IPartnerObserver_failed("获取缓存异常");
			}

			return;
		}
		HttpUtils.get(url, new AsyncHttpResponseHandler() {

			@Override
			public void onSuccess(int code, Header[] head, byte[] result) {
				String json = new String(result);
				try {
					JSONObject jsonObj = new JSONObject(json);
					// 缓存JSONObject对象
					aCache.put(url, jsonObj);
					JsonUtils.parsePartner(jsonObj, observer);
				} catch (JSONException e) {
					e.printStackTrace();
					observer.IPartnerObserver_failed("Json解析异常");
				}
			}

			@Override
			public void onFailure(int code, Header[] head, byte[] result,
					Throwable throwable) {
				String errorMsg = new String(result);
				observer.IPartnerObserver_failed(errorMsg);
			}
		});

	}

	// 获取登录用户信息
	public void getUser(Context context, final IPartnerObserver observer,
			final User user) {
		final String url = UrlManagerUtils.getUserMsgUrl();
		final ACache aCache = ACache.get(context);
		if (!NetworkStateUtil.isAvaliable()) {
			// 网络不能用，使用本地缓存
			JSONObject cacheJObj = aCache.getAsJSONObject(url);
			if (cacheJObj != null) {
				try {
					Toast.makeText(context, "使用缓存", Toast.LENGTH_LONG).show();
					JsonUtils.parseUser(cacheJObj, user, observer);
				} catch (JSONException e) {
					observer.IPartnerObserver_failed("解析本地缓存异常");
					e.printStackTrace();
				}
			} else {
				observer.IPartnerObserver_failed("获取缓存异常");
			}

			return;
		}

		RequestParams params = new RequestParams();
		params.put("user_id", user.getId());
		params.put("invitation_code", user.getInvitationCode());
		params.put("phone", user.getPhone());
		HttpUtils.post(url, params, new AsyncHttpResponseHandler() {

			@Override
			public void onSuccess(int code, Header[] head, byte[] result) {
				String json = new String(result);
				try {
					JSONObject jsonObj = new JSONObject(json);
					// 缓存JSONObject对象
					aCache.put(url, jsonObj);
					JsonUtils.parseUser(jsonObj, user, observer);
				} catch (JSONException e) {
					e.printStackTrace();
					observer.IPartnerObserver_failed("Json解析异常");
				}
			}

			@Override
			public void onFailure(int code, Header[] head, byte[] result,
					Throwable throwable) {
				String errorMsg = new String(result);
				observer.IPartnerObserver_failed(errorMsg);
			}
		});
	}
}
