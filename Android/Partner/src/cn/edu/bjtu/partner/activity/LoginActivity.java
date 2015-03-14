package cn.edu.bjtu.partner.activity;

import java.util.Locale;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import cn.edu.bjtu.partner.R;
import cn.edu.bjtu.partner.utils.SecurityCodeUtil;

public class LoginActivity extends Activity implements OnClickListener,
		TextWatcher {

	private EditText mPhoneEditText;
	private EditText mPasswordEditText;
	private EditText mSecurityEditText;
	private ImageView mSecurityImageView;
	private Button mLoginBtn;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.login);
		mPhoneEditText = (EditText) findViewById(R.id.login_tel);
		mPhoneEditText.addTextChangedListener(this);
		mPasswordEditText = (EditText) findViewById(R.id.login_password);
		mPasswordEditText.addTextChangedListener(this);
		mSecurityEditText = (EditText) findViewById(R.id.login_security);
		mSecurityEditText.addTextChangedListener(this);
		mSecurityImageView = (ImageView) findViewById(R.id.login_security_img);
		setSecurityBitmap();
		mSecurityImageView.setOnClickListener(this);
		mLoginBtn = (Button) findViewById(R.id.login_btn);
		mLoginBtn.setOnClickListener(this);
	}

	// 设置验证码
	private void setSecurityBitmap() {
		Bitmap bitmap = SecurityCodeUtil.getInstance().createBitmap();
		if (bitmap != null) {
			mSecurityImageView.setImageBitmap(bitmap);
		}
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.login_btn:
			// 首先判断验证码是否正确
			if (!mSecurityEditText
					.getText()
					.toString()
					.toLowerCase(Locale.ENGLISH)
					.equals(SecurityCodeUtil.getInstance().getCode()
							.toLowerCase(Locale.ENGLISH))) {
				return;
			}

			break;
		case R.id.login_security_img:
			setSecurityBitmap();
			break;
		}

	}

	@Override
	public void beforeTextChanged(CharSequence s, int start, int count,
			int after) {

	}

	@Override
	public void onTextChanged(CharSequence s, int start, int before, int count) {
		if (!TextUtils.isEmpty(mPhoneEditText.getText())
				&& !TextUtils.isEmpty(mPasswordEditText.getText())
				&& !TextUtils.isEmpty(mSecurityEditText.getText())) {
			mLoginBtn.setEnabled(true);
		} else {
			mLoginBtn.setEnabled(false);
		}
	}

	@Override
	public void afterTextChanged(Editable s) {

	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		if (item.getItemId() == R.id.action_register) {
			// 用户点击了注册
			Intent intent = new Intent(this, RegisterActivity.class);
			startActivity(intent);
		}
		return super.onOptionsItemSelected(item);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		MenuInflater inflater = new MenuInflater(this);
		inflater.inflate(R.menu.main, menu);
		return super.onCreateOptionsMenu(menu);
	}
}
