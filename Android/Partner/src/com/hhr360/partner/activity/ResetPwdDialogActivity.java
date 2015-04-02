package com.hhr360.partner.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.hhr360.partner.R;
import com.hhr360.partner.observer.ILoginObserver;
import com.hhr360.partner.utils.LoginUtil;

public class ResetPwdDialogActivity extends Activity implements ILoginObserver {
	private Button mLogin;
	private TextView mTimeTv;
	private String mPhone;
	private String mPassword;
	private boolean mIsClickBtn = false;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		Intent intent = getIntent();
		mPhone = intent.getStringExtra("phone");
		mPassword = intent.getStringExtra("password");
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.dialog_resetpwd_success);
		mLogin = (Button) findViewById(R.id.login);
		mLogin.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				autoLogin();
			}
		});
		mTimeTv = (TextView) findViewById(R.id.time_tv);
		new Thread() {
			int resetTime = 10;

			public void run() {
				while (resetTime >= 0 && !mIsClickBtn) {
					runOnUiThread(new Runnable() {

						@Override
						public void run() {
							if (resetTime != 0) {

								mTimeTv.setText("......" + resetTime + "s");
							} else {
								Intent intent = new Intent(
										ResetPwdDialogActivity.this,
										LoginActivity.class);
								startActivity(intent);
							}
						}
					});
					try {
						sleep(1000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					resetTime--;
				}
			};
		}.start();
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
		if (!mIsClickBtn) {
			mIsClickBtn = true;
			Intent intent = new Intent(this, LoginActivity.class);
			intent.putExtra("isautologin", false);
			startActivity(intent);
		}

	}

	private void autoLogin() {
		LoginUtil.login(this, mPhone, mPassword);
	}

	@Override
	public void ILoginObserver_succeed() {
		mIsClickBtn = true;
		Intent intent = new Intent(this, LoginActivity.class);
		intent.putExtra("isautologin", true);
		startActivity(intent);
	}

	@Override
	public void ILoginObaserver_failed(String errorMsg) {
		Toast.makeText(this, errorMsg, Toast.LENGTH_LONG).show();
	}
}
