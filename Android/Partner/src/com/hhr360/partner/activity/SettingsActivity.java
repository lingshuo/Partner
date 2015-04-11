package com.hhr360.partner.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;

import com.hhr360.partner.BaseActivity;
import com.hhr360.partner.R;
import com.hhr360.partner.utils.PreferenceUtils;

public class SettingsActivity extends BaseActivity implements OnClickListener {

	private Button mLogoutBtn;
	private TextView mChangePwdTV;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.settings);
		// 设置head的文字
		setHeaderTextName("设置");

		mLogoutBtn = (Button) findViewById(R.id.logout_btn);
		mLogoutBtn.setOnClickListener(this);
		mChangePwdTV = (TextView) findViewById(R.id.change_pwd_tv);
		mChangePwdTV.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.logout_btn:
			PreferenceUtils.clearData();
			setResult(1);
			finish();
			break;
		case R.id.change_pwd_tv:
			Intent intent = new Intent(this, FindPasswordActivity.class);
			intent.putExtra("isfromsettings", true);
			startActivity(intent);
			break;
		}
	}
}
