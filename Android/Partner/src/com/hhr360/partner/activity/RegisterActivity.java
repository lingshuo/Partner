package com.hhr360.partner.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.hhr360.partner.PartnerApp;
import com.hhr360.partner.R;
import com.hhr360.partner.observer.IFindPasswordObserver;
import com.hhr360.partner.observer.IRegistObserver;
import com.hhr360.partner.utils.FindPasswordUtil;
import com.hhr360.partner.utils.RegistUtil;

public class RegisterActivity extends Activity implements OnClickListener,
		IRegistObserver, IFindPasswordObserver {

	private EditText mPhoneEditText;
	private EditText mAccountEditText;
	private EditText mPasswordEditText;
	private EditText mPasswordAgainEditText;
	private EditText mRegistInviteEditText;
	private Button mRegistBtn;
	private Button mQrBtn;
	private EditText mSecurityEt;
	private Button mSendBtn;
	private static final int REQUEST_RESULT_QRCODE = 1025;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.register);
		mPhoneEditText = (EditText) findViewById(R.id.regist_tel);
		mAccountEditText = (EditText) findViewById(R.id.account_name);
		mPasswordEditText = (EditText) findViewById(R.id.regist_password);
		mPasswordAgainEditText = (EditText) findViewById(R.id.regist_password_again);
		mRegistInviteEditText = (EditText) findViewById(R.id.regist_invite_code);
		mRegistBtn = (Button) findViewById(R.id.regist_btn);
		mRegistBtn.setOnClickListener(this);
		mQrBtn = (Button) findViewById(R.id.qrbtn);
		mQrBtn.setOnClickListener(this);
		mSecurityEt = (EditText) findViewById(R.id.findpassword_security);
		mSendBtn = (Button) findViewById(R.id.find_password);
		mSendBtn.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.regist_btn:
			if (TextUtils.isEmpty(PartnerApp.PHONE_CODE)
					|| !PartnerApp.REGIST_CODE.equals(mSecurityEt.getText()
							.toString())) {
				Toast.makeText(this, "短信验证码错误", Toast.LENGTH_SHORT).show();
				return;
			}
			new RegistUtil().clickRegist(this, this, mPhoneEditText.getText()
					.toString(), mAccountEditText.getText().toString(),
					mPasswordEditText.getText().toString(),
					mPasswordAgainEditText.getText().toString(),
					mRegistInviteEditText.getText().toString());
			break;
		case R.id.qrbtn:
			Intent intent = new Intent(this, CaptureActivity.class);
			startActivityForResult(intent, REQUEST_RESULT_QRCODE);
			break;
		case R.id.find_password:
			if (mPhoneEditText.getText().toString().length() != 11
					|| !mPhoneEditText.getText().toString().startsWith("1")) {
				Toast.makeText(this, "手机号码不正确", Toast.LENGTH_SHORT).show();
			} else {
				FindPasswordUtil.sendSmsCode(RegisterActivity.this,
						mPhoneEditText.getText().toString(), 1);
				mSendBtn.setEnabled(false);
				mSendBtn.setBackgroundResource(R.drawable.loginbutton_press);

				new Thread() {
					int resetTime = 60;

					public void run() {
						while (resetTime >= 0) {
							try {
								runOnUiThread(new Runnable() {

									@Override
									public void run() {
										if (resetTime != 0) {
											mSendBtn.setText(resetTime + "s后重试");
										} else {
											mSendBtn.setText("获取短信验证码");
											mSendBtn.setEnabled(true);
											mSendBtn.setBackgroundResource(R.drawable.getphonesecurity_background);

										}
									}
								});
								sleep(1000);
								resetTime--;
							} catch (InterruptedException e) {
								e.printStackTrace();
							}
						}
					};
				}.start();
			}
			break;
		}
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
		PartnerApp.REGIST_CODE = "";
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		// 扫码后返回的邀请码
		if (requestCode == REQUEST_RESULT_QRCODE) {
			if (resultCode == Activity.RESULT_OK && data != null) {
				mRegistInviteEditText.setText(data.getStringExtra("qr_result"));
			}
		}
	}

	@Override
	public void IRegistObserver_succeed() {
		Toast.makeText(this,
				getResources().getString(R.string.register_success),
				Toast.LENGTH_SHORT).show();
		finish();
	}

	@Override
	public void IRegistObaserver_failed(String errorMsg) {
		// 网络发送注册请求失败回调
		Toast.makeText(this, errorMsg, Toast.LENGTH_SHORT).show();
	}

	@Override
	public void IFindPasswordObserver_onSuccess(String phoneCode) {
		View toastRoot = getLayoutInflater().inflate(
				R.layout.dialog_findpwd_success, null);

		Toast toastStart = new Toast(this);
		toastStart.setGravity(Gravity.CENTER, 0, 0);
		toastStart.setDuration(Toast.LENGTH_LONG);
		toastStart.setView(toastRoot);
		toastStart.show();
		PartnerApp.REGIST_CODE = phoneCode;
		mPhoneEditText.setEnabled(false);
	}

	@Override
	public void IFindPasswordObserver_onFailed(String msg) {
		Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
	}
}
