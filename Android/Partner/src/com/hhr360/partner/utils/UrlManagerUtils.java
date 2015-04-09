package com.hhr360.partner.utils;

/**
 * @author Tans
 */
public class UrlManagerUtils {
	private static final String DOMAIN = "http://123.57.204.123/partner/Home/";
	private static final String LOGIN = "login";
	private static final String REGISTER = "user";
	private static final String SENDSMSCODE = "phoneCode";
	private static final String CHANGEPWD = "changePwd";
	private static final String REGISTERSENDSMSCODE = "user/phoneCode";

	// 注册的url
	public static String getRegisterUrl() {
		String url = DOMAIN + REGISTER;
		return url;
	}

	// 登录的url
	public static String getLoginUrl() {
		String url = DOMAIN + LOGIN;
		return url;
	}

	// 获取用户基本信息
	public static String getUserMsgUrl() {
		String url = DOMAIN;
		return url;
	}

	// 发送短信验证码
	public static String getSendSmsCodeUrl(int type) {
		String url;
		if (type == 0) {// 找回密码
			url = DOMAIN + SENDSMSCODE;
		} else {// 注册
			url = DOMAIN + REGISTERSENDSMSCODE;
		}
		return url;
	}

	// 修改密码
	public static String getResetPwdUrl() {
		String url = DOMAIN + CHANGEPWD;
		return url;
	}
}
