package com.hhr360.partner.activity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.hhr360.partner.PartnerApp;
import com.hhr360.partner.R;
import com.hhr360.partner.observer.ILoginObserver;
import com.hhr360.partner.utils.LoginUtil;
import com.hhr360.partner.utils.PreferenceUtils;

public class LoginActivity extends Activity implements OnClickListener,
		OnTouchListener, ILoginObserver {

	private EditText mPhoneEditText;
	private EditText mPasswordEditText;
	private Button mFindPasswordBtn;
	private Button mLoginBtn;
	private TextView mRegisterTv;
	private static final int REQUEST_CODE_REGIST = 5678;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.login);
		mPhoneEditText = (EditText) findViewById(R.id.login_tel);
		mPasswordEditText = (EditText) findViewById(R.id.login_password);
		mLoginBtn = (Button) findViewById(R.id.login_btn);
		mLoginBtn.setOnClickListener(this);
		mRegisterTv = (TextView) findViewById(R.id.login_register_tv);
		mRegisterTv.setOnClickListener(this);
		mRegisterTv.setOnTouchListener(this);
		mFindPasswordBtn = (Button) findViewById(R.id.find_password);
		mFindPasswordBtn.setOnClickListener(this);
		Log.d("tanshuai", "onCreate");
	}

	@Override
	protected void onNewIntent(Intent intent) {
		super.onNewIntent(intent);
		setIntent(intent);
		// 判断是否是从找回密码跳转的自动登录
		boolean isAutoLogin = getIntent().getBooleanExtra("isautologin", false);
		if (isAutoLogin) {
			isAutoLogin = false;
			Intent t = new Intent(this, PartnerActivity.class);
			startActivity(t);
			finish();
		}
		Log.d("tanshuai", "onNewIntent");
	}

	@Override
	protected void onResume() {
		super.onResume();
		Log.d("tanshuai", "onResume");

	}

	@Override
	public void onClick(View v) {
		Intent intent;
		switch (v.getId()) {
		case R.id.login_register_tv:
			// 用户点击了注册
			intent = new Intent(this, RegisterActivity.class);
			startActivityForResult(intent, REQUEST_CODE_REGIST);
			break;
		case R.id.find_password:
			// 用户点击了找回密码
			intent = new Intent(this, FindPasswordActivity.class);
			startActivity(intent);
			break;
		case R.id.login_btn:
			// 用户点击了登录
			if (TextUtils.isEmpty(mPhoneEditText.getText().toString())
					|| TextUtils
							.isEmpty(mPasswordEditText.getText().toString())) {
				Toast.makeText(this, "账号密码不能为空", Toast.LENGTH_SHORT).show();
				return;
			} else if (mPhoneEditText.getText().toString().length() != 11
					|| !mPhoneEditText.getText().toString().startsWith("1")) {
				Toast.makeText(this, "手机号码不符合规则", Toast.LENGTH_SHORT).show();
				return;
			}
			LoginUtil.login(this, mPhoneEditText.getText().toString(),
					mPasswordEditText.getText().toString());
			mLoginBtn.setClickable(false);
			mLoginBtn.setText("登录中...");
			break;
		}

	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		if (requestCode == REQUEST_CODE_REGIST) {
			// 注册结果返回
		}
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		if (item.getItemId() == R.id.action_register) {
			// 用户点击了注册
			Intent intent = new Intent(this, RegisterActivity.class);
			startActivityForResult(intent, REQUEST_CODE_REGIST);
		}
		return super.onOptionsItemSelected(item);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		MenuInflater inflater = new MenuInflater(this);
		inflater.inflate(R.menu.main, menu);
		return super.onCreateOptionsMenu(menu);
	}

	@Override
	public void ILoginObserver_succeed() {

		// 存储登录状态
		PreferenceUtils.setLoginStatus(true);
		PreferenceUtils.setID(PartnerApp.USER.getId());
		PreferenceUtils.setInviteCode(PartnerApp.USER.getInvitationCode());
		PreferenceUtils.setPhone(PartnerApp.USER.getPhone());
		Intent intent = new Intent(this, PartnerActivity.class);
		startActivity(intent);
		finish();
	}

	@Override
	public void ILoginObaserver_failed(String errorMsg) {
		Toast.makeText(this, errorMsg, Toast.LENGTH_SHORT).show();
		mLoginBtn.setClickable(true);
		mLoginBtn.setText("登录");
	}

	@Override
	public boolean onTouch(View v, MotionEvent event) {
		switch (v.getId()) {
		case R.id.login_register_tv:
			if (event.getAction() == MotionEvent.ACTION_DOWN) {
				mRegisterTv.setTextColor(Color.parseColor("#cccccc"));
			} else if (event.getAction() == MotionEvent.ACTION_UP) {
				mRegisterTv.setTextColor(Color.parseColor("#60aeee"));
				v.performClick();
			}
			break;
		}
		return true;
	}
}
