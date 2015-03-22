package cn.edu.bjtu.partner.activity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.TextUtils;
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
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import cn.edu.bjtu.partner.R;
import cn.edu.bjtu.partner.observer.ILoginObserver;
import cn.edu.bjtu.partner.utils.UIUtils;

public class LoginActivity extends Activity implements OnClickListener,
		OnTouchListener, ILoginObserver {

	private EditText mPhoneEditText;
	private EditText mPasswordEditText;
	private EditText mSecurityEditText;
	private Button mFindPasswordBtn;
	private ImageView mSecurityImageView;
	private Button mLoginBtn;
	private TextView mRegisterTv;
	private static final int REQUEST_CODE_REGIST = 1024;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.login);
		mPhoneEditText = (EditText) findViewById(R.id.login_tel);
		mPasswordEditText = (EditText) findViewById(R.id.login_password);
		mSecurityEditText = (EditText) findViewById(R.id.login_security);
		mSecurityImageView = (ImageView) findViewById(R.id.login_security_img);
		mSecurityImageView.setOnClickListener(this);
		mLoginBtn = (Button) findViewById(R.id.login_btn);
		mLoginBtn.setOnClickListener(this);
		mRegisterTv = (TextView) findViewById(R.id.login_register_tv);
		mRegisterTv.setOnClickListener(this);
		mRegisterTv.setOnTouchListener(this);
		mFindPasswordBtn = (Button) findViewById(R.id.find_password);
		mFindPasswordBtn.setOnClickListener(this);
	}

	@Override
	protected void onResume() {
		super.onResume();
		UIUtils.setSecurityBitmap(mSecurityImageView);
		mSecurityEditText.setText("");
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

			if (TextUtils.isEmpty(mPhoneEditText.getText().toString())
					|| TextUtils
							.isEmpty(mPasswordEditText.getText().toString())) {
				Toast.makeText(this, "账号密码不能为空", Toast.LENGTH_SHORT).show();
				return;
			}
			intent = new Intent(this, PartnerActivity.class);
			startActivity(intent);
			finish();
			break;
		case R.id.login_security_img:
			UIUtils.setSecurityBitmap(mSecurityImageView);
			break;
		}

	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		if (requestCode == REQUEST_CODE_REGIST) {

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
	public void ILoginObserver_succeed(int userId, int phoneNum, int inviteCode) {
		// TODO Auto-generated method stub

	}

	@Override
	public void ILoginObaserver_failed(String errorMsg) {
		// TODO Auto-generated method stub

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
