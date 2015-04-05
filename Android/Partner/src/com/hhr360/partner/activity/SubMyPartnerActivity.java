package com.hhr360.partner.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.hhr360.partner.BaseActivity;
import com.hhr360.partner.PartnerApp;
import com.hhr360.partner.R;

public class SubMyPartnerActivity extends BaseActivity implements
		OnClickListener {
	private TextView mUserIdTv;
	private TextView mUserNameTv;
	private TextView mRegistTimeTv;
	private TextView mLastLoginTimeTv;
	private TextView mPartnerNumTv;
	private TextView mMonthProfitTv;
	private TextView mLevelUpTv;
	private RelativeLayout mRelBtn1;
	private RelativeLayout mRelBtn2;
	private RelativeLayout mRelBtn3;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.sub_my_partner);
		mUserIdTv = (TextView) findViewById(R.id.textView2);
		mUserNameTv = (TextView) findViewById(R.id.textView4);
		mRegistTimeTv = (TextView) findViewById(R.id.register_time);
		mLastLoginTimeTv = (TextView) findViewById(R.id.lastlogin_time);
		mPartnerNumTv = (TextView) findViewById(R.id.partner_num_tv);
		mMonthProfitTv = (TextView) findViewById(R.id.month_profit_tv);
		mLevelUpTv = (TextView) findViewById(R.id.level_up_tv);

		mRelBtn1 = (RelativeLayout) findViewById(R.id.rel_btn1);
		mRelBtn2 = (RelativeLayout) findViewById(R.id.rel_btn2);
		mRelBtn3 = (RelativeLayout) findViewById(R.id.rel_btn3);
		mRelBtn1.setOnClickListener(this);
		mRelBtn2.setOnClickListener(this);
		mRelBtn3.setOnClickListener(this);

		mUserIdTv.setText(PartnerApp.PARTNER.getId() + "");
		mUserNameTv.setText(PartnerApp.PARTNER.getAccountName());
		mPartnerNumTv.setText(Integer.parseInt(PartnerApp.PARTNER
				.getFirstlyPartnerNum())
				+ Integer.parseInt(PartnerApp.PARTNER.getSecondlyPartnerNum())
				+ "");
		mMonthProfitTv.setText(PartnerApp.PARTNER.getMonthlyIncome());
		// mLevelUpTv.setText(PartnerApp.PARTNER.get);
		// mRegistTimeTv.setText(PartnerApp.PARTNER.get)

		// 设置head的文字
		setHeaderTextName(getResources().getString(R.string.partner_my_partner));
	}

	@Override
	public void onClick(View v) {
		Intent intent = new Intent();
		switch (v.getId()) {
		case R.id.rel_btn1:
			intent.setClass(this, SubMyPartnerDetailFirstActivity.class);
			startActivity(intent);
			break;
		case R.id.rel_btn2:
			break;
		case R.id.rel_btn3:
			break;
		}
	}
}
