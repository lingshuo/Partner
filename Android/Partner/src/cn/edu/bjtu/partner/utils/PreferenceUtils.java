package cn.edu.bjtu.partner.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

/**
 * @author Tans
 */
public class PreferenceUtils {
	private static final String SHARED_PREFF_LOGIN = "isLogin";// 判断是否登录

	private static SharedPreferences mSharedPreferences;
	private static Editor mEditor;

	public static void init(Context context) {
		mSharedPreferences = context.getSharedPreferences("partner",
				Context.MODE_PRIVATE);
		mEditor = mSharedPreferences.edit();
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

	// 清除所有存储的数据
	public static void clearData() {
		setLoginStatus(false);
	}
}
