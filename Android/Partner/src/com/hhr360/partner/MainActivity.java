package com.hhr360.partner;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import com.hhr360.partner.activity.LoginActivity;
import com.hhr360.partner.activity.PartnerActivity;
import com.hhr360.partner.utils.DeviceInfo;
import com.hhr360.partner.utils.PreferenceUtils;

/**
 * @author Tans
 */
public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		init();
		Intent intent = new Intent();
		// 根据登录状态进行跳转
		if (PreferenceUtils.getLoginStatus()) {
			intent.setClass(this, PartnerActivity.class);
		} else {
			intent.setClass(this, LoginActivity.class);
		}
		startActivity(intent);
		finish();
	}

	// 初始化方法
	private void init() {
		DeviceInfo.initScreenInfo(this);
		PreferenceUtils.init(this);
	}

}
