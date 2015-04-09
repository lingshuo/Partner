package com.hhr360.partner.utils;

import org.apache.http.Header;
import org.json.JSONException;
import org.json.JSONObject;

import android.text.TextUtils;

import com.hhr360.partner.PartnerApp;
import com.hhr360.partner.bean.User;
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

	public void getPartner(final IPartnerObserver observer, User partner) {
		String url = UrlManagerUtils.getUserMsgUrl();
		RequestParams params = new RequestParams();
		params.put("user_id", partner.getId());
		params.put("invitation_code", partner.getInvitationCode());
		params.put("phone", partner.getPhone());
		HttpUtils.post(url, params, new AsyncHttpResponseHandler() {

			@Override
			public void onSuccess(int code, Header[] head, byte[] result) {
				// {"id":"1","account_name":"test","password":"test123",
				// "user_name":null,"card_number":null,"is_check_card":null,
				// "phone":"18888888888","email":null,"is_bind_email":null,
				// "is_marriage":null,"live_address":null,"max_degree":null,
				// "business_type":null,"position_name":null,"salary":null,
				// "draw_password":null,"card_before_pic":null,"card_behind_pic":null,
				// "card_hand_pic":null,"account_balance":null,"account_total_money":null,
				// "match_money":null,"safe_money":null,"freeze_money":null,"safe_level":null,
				// "register_time":"2015-03-26 16:35:35","register_ip":"221.130.41.149",
				// "extend_person_num":null,"borrow_person_num":null,"commission_total":null,
				// "exchange_money":null,"visit_ip":null,"visit_num":null,"phone_code":null,
				// "email_code":null,"login_token":null,"state":null,"province_id":null,
				// "city_id":null,"province_name":null,"city_name":null,"gender":null,
				// "recommend_id":null,"login_time":null,"fee_total":null,"recharge_money":null,
				// "draw_money":null,"phone_code_time":null,"hhr_level":null,"hhr_parentid":null,
				// "invitation_code":"123456789012"}
				String json = new String(result);
				try {
					JSONObject jsonObj = new JSONObject(json);
					int status = jsonObj.getInt("is_success");
					if (status == 1) {
						String userJson = jsonObj.getString("user");
						JSONObject userJsonObj = new JSONObject(userJson);
						String accountName = userJsonObj
								.getString("account_name");
						PartnerApp.USER.setAccountName(accountName);
						String statJson = jsonObj.getString("stat");
						if (!TextUtils.isEmpty(statJson)
								&& !statJson.equals("null")) {

							JSONObject statJonObj = new JSONObject(statJson);
							PartnerApp.USER.setHhrLevel(statJonObj
									.getString("hhr_level"));
							PartnerApp.USER.setFirstlyPartnerNum(statJonObj
									.getString("firstly_partner_num"));
							PartnerApp.USER.setSecondlyPartnerNum(statJonObj
									.getString("secondary_partner_num"));
							PartnerApp.USER.setExtendPartnerNum(statJonObj
									.getString("extend_person_new"));
							PartnerApp.USER.setInterestReturnCoefficient(statJonObj
									.getString("interest_return_coefficient"));
							PartnerApp.USER.setChargeReturnCoefficient(statJonObj
									.getString("charges_return_coefficient"));
							PartnerApp.USER.setDailyIncome(statJonObj
									.getString("daily_income"));
							PartnerApp.USER.setMonthlyIncome(statJonObj
									.getString("monthly_income"));
						}
						observer.IPartnerObserver_success();
					} else {
						observer.IPartnerObserver_failed("请求失败");
					}
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
