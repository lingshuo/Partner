package com.hhr360.partner.activity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.hhr360.partner.BaseActivity;
import com.hhr360.partner.PartnerApp;
import com.hhr360.partner.R;
import com.hhr360.partner.observer.IFindPasswordObserver;
import com.hhr360.partner.utils.FindPasswordUtil;


public class FindPasswordActivity extends BaseActivity implements
		OnClickListener, IFindPasswordObserver {

	private EditText mPhoneEt;
	private EditText mSecurityEt;
	private Button mSendBtn;
	private Button mButton;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.find_password);
		mPhoneEt = (EditText) findViewById(R.id.findpassword_tel);
		mSecurityEt = (EditText) findViewById(R.id.findpassword_security);
		mSendBtn = (Button) findViewById(R.id.find_password);
		mSendBtn.setOnClickListener(this);
		mButton = (Button) findViewById(R.id.next);
		mButton.setOnClickListener(this);
		setHeaderTextName(getResources().getString(R.string.forget_password));
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.find_password:
			if (mPhoneEt.getText().toString().length() != 11
					|| !mPhoneEt.getText().toString().startsWith("1")) {
				Toast.makeText(this, "手机号码不正确", Toast.LENGTH_SHORT).show();
			} else {
				FindPasswordUtil.sendSmsCode(this, mPhoneEt.getText()
						.toString());
			}
			break;
		case R.id.next:
			if (!TextUtils.isEmpty(PartnerApp.PHONE_CODE)
					&& PartnerApp.PHONE_CODE.equals(mSecurityEt.getText()
							.toString())) {
				Intent intent = new Intent(this, ResetPasswordActivity.class);
				intent.putExtra("phone", mPhoneEt.getText().toString());
				startActivity(intent);
			}
			break;
		}
	}

	@Override
	public void IFindPasswordObserver_onSuccess(String phoneCode) {
		Toast.makeText(this, "发送成功", Toast.LENGTH_SHORT).show();
		PartnerApp.PHONE_CODE = phoneCode;
	}

	@Override
	public void IFindPasswordObserver_onFailed(String msg) {
		Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
	}
}
