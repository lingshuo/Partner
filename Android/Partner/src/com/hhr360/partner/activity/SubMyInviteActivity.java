package com.hhr360.partner.activity;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.Window;
import android.widget.ImageView;
import android.widget.TextView;

import com.hhr360.partner.BaseActivity;
import com.hhr360.partner.PartnerApp;
import com.hhr360.partner.R;
import com.hhr360.partner.utils.QrUtil;

public class SubMyInviteActivity extends BaseActivity {
	private TextView mIdTextView;
	private TextView mAccountTextView;
	private TextView mInviteTextView;
	private ImageView mQrImageView;

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
		mIdTextView.setText(PartnerApp.PARTNER.getId() + "");
		mAccountTextView.setText(PartnerApp.PARTNER.getAccountName());
		mInviteTextView.setText(PartnerApp.PARTNER.getInvitationCode());
		Bitmap bitmap = QrUtil.create2DCoderBitmap(PartnerApp.PARTNER
				.getInvitationCode());
		mQrImageView.setImageBitmap(bitmap);

	}

}
