package com.hhr360.partner.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;

import com.hhr360.partner.BaseActivity;
import com.hhr360.partner.PartnerApp;
import com.hhr360.partner.R;
import com.hhr360.partner.observer.IResetPasswordObserver;
import com.hhr360.partner.utils.RegistUtil;
import com.hhr360.partner.utils.ResetPasswordUtil;


public class ResetPasswordActivity extends BaseActivity implements
		OnClickListener, IResetPasswordObserver {
	private EditText mPasswordEditText;
	private EditText mPasswordAgainEditText;
	private Button mButton;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.reset_password);
		setHeaderTextName(getResources().getString(R.string.reset_password));
		mPasswordEditText = (EditText) findViewById(R.id.regist_password);
		mPasswordAgainEditText = (EditText) findViewById(R.id.regist_password_again);
		mButton = (Button) findViewById(R.id.next);
		mButton.setOnClickListener(this);
	}


	@Override
	public void IResetPasswordObserver_onSuccess(String msg) {
		Intent intent = new Intent(this, ResetPwdDialogActivity.class);
		startActivity(intent);
	}

	@Override
	public void IResetPasswordObserver_onFailed(String msg) {

	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.next:
			if (new RegistUtil().judgePasswordLegal(this, mPasswordEditText
					.getText().toString(), mPasswordAgainEditText.getText()
					.toString())) {
				Intent intent = getIntent();
				String phone = intent.getStringExtra("phone");
				ResetPasswordUtil.resetPassword(this, phone, mPasswordEditText
						.getText().toString(), PartnerApp.PHONE_CODE);
			}
			break;

		default:
			break;
		}
	}
}
