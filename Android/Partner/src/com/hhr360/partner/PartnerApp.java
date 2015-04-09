package com.hhr360.partner;

import com.hhr360.partner.bean.Partner;

import android.app.Application;

public class PartnerApp extends Application {

	// 设置登录的Partner为static
	public static Partner PARTNER = new Partner();
	// 找回密码验证码
	public static String PHONE_CODE = "";
	// 注册验证码
	public static String REGIST_CODE = "";

}
