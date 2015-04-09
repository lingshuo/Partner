package com.hhr360.partner;

import com.hhr360.partner.bean.Partner;
import com.hhr360.partner.bean.User;

import android.app.Application;

public class PartnerApp extends Application {

	// 设置登录的user为static
	public static User USER = new User();
	// 找回密码验证码
	public static String PHONE_CODE = "";
	// 注册验证码
	public static String REGIST_CODE = "";
	// 合伙人数据
	public static Partner PARTNER = new Partner();

}
