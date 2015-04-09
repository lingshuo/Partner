package com.hhr360.partner.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

/**
 * @author Tans
 */
public class PreferenceUtils {
	private static final String SHARED_PREFF_LOGIN = "isLogin";// 判断是否登录
	private static final String SHARED_PREFF_ID = "ID";// 用户id
	private static final String SHARED_PREFF_PHONE = "phone";// 用户手机号
	private static final String SHARED_PREFF_INVITATION_CODE = "invitationCode";// 邀请码

	private static SharedPreferences mSharedPreferences;
	private static Editor mEditor;

	public static void init(Context context) {
		mSharedPreferences = context.getSharedPreferences("partner",
				Context.MODE_PRIVATE);
		mEditor = mSharedPreferences.edit();
	}

	// 从本地获取用户ID
	public static int getID() {
		return mSharedPreferences.getInt(SHARED_PREFF_ID, 0);
	}

	// 设置用户ID
	public static void setID(int id) {
		mEditor.putInt(SHARED_PREFF_ID, id);
		mEditor.commit();
	}

	// 从本地获取用户手机号
	public static String getPhone() {
		return mSharedPreferences.getString(SHARED_PREFF_PHONE, "");
	}

	// 设置用户手机号
	public static void setPhone(String phone) {
		mEditor.putString(SHARED_PREFF_PHONE, phone);
		mEditor.commit();
	}

	// 从本地获取登陆状态
	public static boolean getLoginStatus() {
		return mSharedPreferences.getBoolean(SHARED_PREFF_LOGIN, false);
	}

	// 设置本地登陆状态
	public static void setLoginStatus(boolean status) {
		mEditor.putBoolean(SHARED_PREFF_LOGIN, status);
		mEditor.commit();
	}

	// 获取存储在本地的邀请码
	public static String getInviteCode() {
		return mSharedPreferences.getString(SHARED_PREFF_INVITATION_CODE, "");
	}

	// 保存邀请码到本地
	public static void setInviteCode(String code) {
		mEditor.putString(SHARED_PREFF_INVITATION_CODE, code);
		mEditor.commit();
	}

	// 清除所有存储的数据
	public static void clearData() {
		setLoginStatus(false);
		setID(0);
		setInviteCode("");
		setPhone("");
	}
}
