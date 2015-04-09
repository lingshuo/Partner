package com.hhr360.partner.activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.hhr360.partner.BaseActivity;
import com.hhr360.partner.PartnerApp;
import com.hhr360.partner.R;
import com.hhr360.partner.utils.QrUtil;

public class SubMyInviteActivity extends BaseActivity implements
		OnClickListener {
	private TextView mIdTextView;
	private TextView mAccountTextView;
	private TextView mInviteTextView;
	private ImageView mQrImageView;
	private Button mButton1;
	private Button mButton2;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.sub_my_invite);
		// 设置head的文字
		setHeaderTextName(getResources().getString(R.string.partner_my_invite));
		mIdTextView = (TextView) findViewById(R.id.id_tv);
		mAccountTextView = (TextView) findViewById(R.id.account_tv);
		mInviteTextView = (TextView) findViewById(R.id.invitation_tv);
		mQrImageView = (ImageView) findViewById(R.id.qr_img);
		mIdTextView.setText(PartnerApp.USER.getId() + "");
		mAccountTextView.setText(PartnerApp.USER.getAccountName());
		mInviteTextView.setText(PartnerApp.USER.getInvitationCode());
		Bitmap bitmap = QrUtil.create2DCoderBitmap(PartnerApp.USER
				.getInvitationCode());
		mQrImageView.setImageBitmap(bitmap);
		mButton1 = (Button) findViewById(R.id.myinvite_btn1);
		mButton2 = (Button) findViewById(R.id.myinvite_btn2);
		mButton1.setOnClickListener(this);
		mButton2.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		StringBuilder sb = new StringBuilder("您的朋友“");
		switch (v.getId()) {
		case R.id.myinvite_btn1:
			sb.append(PartnerApp.USER.getAccountName());
			sb.append("”邀请您成为他的超级合伙人，注册邀请码是：");
			sb.append(PartnerApp.USER.getInvitationCode());
			sb.append("，超级合伙人APP下载地址：http://app.hhr360.com");
			sendSMS(sb.toString());
			break;
		case R.id.myinvite_btn2:
			sb.append(PartnerApp.USER.getAccountName());
			sb.append("”邀请您配资，现在就配资请访问：https://www.kunzhoudade.com/812345678910");
			sendSMS(sb.toString());
			break;
		}
	}

	private void sendSMS(String smsBody) {
		Uri smsToUri = Uri.parse("smsto:");
		Intent intent = new Intent(Intent.ACTION_SENDTO, smsToUri);
		intent.putExtra("sms_body", smsBody);
		startActivity(intent);

	}
}
