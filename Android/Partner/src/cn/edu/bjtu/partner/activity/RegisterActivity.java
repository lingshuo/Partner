package cn.edu.bjtu.partner.activity;

import android.app.Activity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.TextureView;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import cn.edu.bjtu.partner.R;
import cn.edu.bjtu.partner.observer.IRegistObserver;
import cn.edu.bjtu.partner.utils.RegistUtil;

public class RegisterActivity extends Activity implements OnClickListener,
		IRegistObserver {

	private EditText mPhoneEditText;
	private EditText mPasswordEditText;
	private EditText mPasswordAgainEditText;
	private EditText mRegistInviteEditText;
	private Button mRegistBtn;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.register);
		mPhoneEditText = (EditText) findViewById(R.id.regist_tel);
		mPasswordEditText = (EditText) findViewById(R.id.regist_password);
		mPasswordAgainEditText = (EditText) findViewById(R.id.regist_password_again);
		mRegistInviteEditText = (EditText) findViewById(R.id.regist_invite_code);
		mRegistBtn = (Button) findViewById(R.id.regist_btn);
		mRegistBtn.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.regist_btn:
			new RegistUtil().clickRegist(this, this, mPhoneEditText.getText()
					.toString(), mPasswordEditText.getText().toString(),
					mPasswordAgainEditText.getText().toString(),
					mRegistInviteEditText.getText().toString());
			break;
		}

	}

	@Override
	public void IRegistObserver_succeed(int userId, int phoneNum, int inviteCode) {
		finish();
	}

	@Override
	public void IRegistObaserver_failed(String errorMsg) {
		// 网络发送注册请求失败回调
		Toast.makeText(this, errorMsg, Toast.LENGTH_SHORT).show();
	}
}
