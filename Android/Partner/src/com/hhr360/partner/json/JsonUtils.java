package com.hhr360.partner.json;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.text.TextUtils;

import com.hhr360.partner.PartnerApp;
import com.hhr360.partner.bean.User;
import com.hhr360.partner.observer.IPartnerObserver;

/**
 * @author Tans
 */
public class JsonUtils {

	// 解析登录返回的json数据
	// 解析注册返回的json数据

	/**
	 * 获取登录用户信息
	 * 
	 * @param jsonObj
	 * @param user
	 * @param observer
	 * @throws JSONException
	 */
	public static void parseUser(JSONObject jsonObj, User user,
			IPartnerObserver observer) throws JSONException {
		int status = jsonObj.getInt("is_success");
		if (status == 1) {
			String userJson = jsonObj.getString("user");
			JSONObject userJsonObj = new JSONObject(userJson);
			String accountName = userJsonObj.getString("account_name");
			PartnerApp.USER.setAccountName(accountName);
			PartnerApp.USER.setId(user.getId());
			PartnerApp.USER.setInvitationCode(user.getInvitationCode());
			String statJson = jsonObj.getString("stat");
			if (!TextUtils.isEmpty(statJson) && !statJson.equals("null")) {

				JSONObject statJonObj = new JSONObject(statJson);
				PartnerApp.USER.setHhrLevel(statJonObj.getString("hhr_level"));
				PartnerApp.USER.setFirstlyPartnerNum(statJonObj
						.getInt("firstly_partner_num"));
				PartnerApp.USER.setSecondlyPartnerNum(statJonObj
						.getInt("secondary_partner_num"));
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
	}

	/**
	 * 解析获取一二级合伙人的信息
	 * 
	 * @param jsonObj
	 * @param observer
	 * @throws JSONException
	 */
	public static void parsePartner(JSONObject jsonObj,
			IPartnerObserver observer) throws JSONException {
		String userInfo = jsonObj.getString("userinfo");
		JSONObject userInfoJsonObj = new JSONObject(userInfo);
		PartnerApp.PARTNER.setRegisterTime(userInfoJsonObj
				.getString("register_time"));
		PartnerApp.PARTNER.setLastLoginTime(userInfoJsonObj
				.getString("last_login_time"));

		String statInfo = jsonObj.getString("statinfo");
		JSONObject statInfoJsonObj = new JSONObject(statInfo);
		PartnerApp.PARTNER.setFirstPartnerNum(statInfoJsonObj
				.getInt("firstly_partner_num"));
		PartnerApp.PARTNER.setSecondPartnerNum(statInfoJsonObj
				.getInt("secondary_partner_num"));
		PartnerApp.PARTNER.setMonthIncome(statInfoJsonObj
				.getDouble("monthly_income"));
		PartnerApp.PARTNER.setSecStockEndow(statInfoJsonObj
				.getDouble("secondary_stock_endowment"));
		PartnerApp.PARTNER.setSecFutEndow(statInfoJsonObj
				.getDouble("secondary_futures_endowment"));
		PartnerApp.PARTNER.setSecStockComm(statInfoJsonObj
				.getDouble("secondary_stock_commission"));
		PartnerApp.PARTNER.setSecFutComm(statInfoJsonObj
				.getDouble("secondary_futures_commission"));
		PartnerApp.PARTNER.setSecContrIncome(statInfoJsonObj
				.getDouble("secondary_contribution_income"));
		PartnerApp.PARTNER.setFirStockEndow(statInfoJsonObj
				.getDouble("firstly_stock_endowment"));
		PartnerApp.PARTNER.setFirFutEndow(statInfoJsonObj
				.getDouble("firstly_futures_endowment"));
		PartnerApp.PARTNER.setFirStockComm(statInfoJsonObj
				.getDouble("firstly_stock_commission"));
		PartnerApp.PARTNER.setFirFutComm(statInfoJsonObj
				.getDouble("firstly_futures_commission"));
		PartnerApp.PARTNER.setFirContrIncome(statInfoJsonObj
				.getDouble("firstly_contribution_income"));

		String partnersJson = jsonObj.getString("partners");
		JSONArray partnerJsonArray = new JSONArray(partnersJson);
		User user;
		PartnerApp.PARTNER.mPartnerList.clear();
		for (int i = 0; i < partnerJsonArray.length(); i++) {
			user = new User();
			JSONObject partnerJsonObj = partnerJsonArray.getJSONObject(i);
			user.setId(partnerJsonObj.getInt("id"));
			user.setAccountName(partnerJsonObj.getString("account_name"));
			user.setFirstlyPartnerNum(partnerJsonObj
					.getInt("firstly_partner_num"));
			user.setSecondlyPartnerNum(partnerJsonObj
					.getInt("secondary_partner_num"));
			user.setMonthlyIncome(partnerJsonObj
					.getDouble("monthly_stock_endowment")
					+ partnerJsonObj.getDouble("monthly_futures_endowment")
					+ "");
			PartnerApp.PARTNER.mPartnerList.add(user);

		}
		observer.IPartnerObserver_success();
	}
}
