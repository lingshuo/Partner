package cn.edu.bjtu.partner;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import cn.edu.bjtu.partner.activity.LoginActivity;
import cn.edu.bjtu.partner.activity.PartnerManagerActivity;
import cn.edu.bjtu.partner.utils.PreferenceUtils;

/**
 * @author Tans
 */
public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		PreferenceUtils.init(this);
		Intent intent = new Intent();
		// 根据登录状态进行跳转
		if (PreferenceUtils.getLoginStatus()) {
			intent.setClass(this, PartnerManagerActivity.class);
		} else {
			intent.setClass(this, LoginActivity.class);
		}
		startActivity(intent);
	}

}
