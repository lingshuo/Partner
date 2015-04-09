package com.hhr360.partner.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

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
	private String mPhone;
	private boolean mIsFromSettings = false;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		Intent intent = getIntent();
		mPhone = intent.getStringExtra("phone");
		mIsFromSettings = intent.getBooleanExtra("isfromsettings", false);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.reset_password);
		if (mIsFromSettings) {
			setHeaderTextName("修改密码");
		} else {
			setHeaderTextName(getResources().getString(R.string.reset_password));

		}
		mPasswordEditText = (EditText) findViewById(R.id.regist_password);
		mPasswordAgainEditText = (EditText) findViewById(R.id.regist_password_again);
		mButton = (Button) findViewById(R.id.next);
		mButton.setOnClickListener(this);
	}

	@Override
	public void IResetPasswordObserver_onSuccess(String msg) {
		if (mIsFromSettings) {
			Toast.makeText(this, "密码修改成功", Toast.LENGTH_SHORT).show();
			finish();
			return;
		}
		Intent intent = new Intent(this, ResetPwdDialogActivity.class);
		intent.putExtra("phone", mPhone);
		intent.putExtra("password", mPasswordEditText.getText().toString());
		startActivity(intent);
	}

	@Override
	public void IResetPasswordObserver_onFailed(String msg) {
		Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.next:
			if (new RegistUtil().judgePasswordLegal(this, mPasswordEditText
					.getText().toString(), mPasswordAgainEditText.getText()
					.toString())) {
				ResetPasswordUtil.resetPassword(this, mPhone, mPasswordEditText
						.getText().toString(), PartnerApp.PHONE_CODE);
			}
			break;

		default:
			break;
		}
	}
}
