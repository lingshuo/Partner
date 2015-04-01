package com.hhr360.partner.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import com.hhr360.partner.R;
import com.hhr360.partner.observer.IRegistObserver;
import com.hhr360.partner.utils.RegistUtil;


public class RegisterActivity extends Activity implements OnClickListener,
		IRegistObserver {

	private EditText mPhoneEditText;
	private EditText mAccountEditText;
	private EditText mPasswordEditText;
	private EditText mPasswordAgainEditText;
	private EditText mRegistInviteEditText;
	private Button mRegistBtn;
	private Button mQrBtn;
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
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.regist_btn:
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
		}
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
		Toast.makeText(this,
				errorMsg,
				Toast.LENGTH_SHORT).show();
	}
}
