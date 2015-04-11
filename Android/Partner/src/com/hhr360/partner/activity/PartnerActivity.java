package com.hhr360.partner.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.hhr360.partner.PartnerApp;
import com.hhr360.partner.R;
import com.hhr360.partner.bean.User;
import com.hhr360.partner.observer.IPartnerObserver;
import com.hhr360.partner.utils.NetworkStateUtil;
import com.hhr360.partner.utils.PartnerUtils;
import com.hhr360.partner.utils.PreferenceUtils;

public class PartnerActivity extends Activity implements OnClickListener,
		IPartnerObserver {
	private TextView mIdTextView;
	private TextView mAccountNameTextView;
	private RelativeLayout[] mRelBtn = new RelativeLayout[12];
	private TextView mTv4;
	private TextView mTv5;
	private TextView mTv6;
	private TextView mTv7;
	private TextView mTv8;
	private final static int ISLOGOUT = 1024;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.partner);
		mRelBtn[0] = (RelativeLayout) findViewById(R.id.rel01);
		mRelBtn[1] = (RelativeLayout) findViewById(R.id.rel02);
		mRelBtn[2] = (RelativeLayout) findViewById(R.id.rel03);
		mRelBtn[3] = (RelativeLayout) findViewById(R.id.rel04);
		mRelBtn[4] = (RelativeLayout) findViewById(R.id.rel05);
		mRelBtn[5] = (RelativeLayout) findViewById(R.id.rel06);
		mRelBtn[6] = (RelativeLayout) findViewById(R.id.rel07);
		mRelBtn[7] = (RelativeLayout) findViewById(R.id.rel08);
		mRelBtn[8] = (RelativeLayout) findViewById(R.id.rel09);
		mRelBtn[9] = (RelativeLayout) findViewById(R.id.rel10);
		mRelBtn[10] = (RelativeLayout) findViewById(R.id.rel11);
		mRelBtn[11] = (RelativeLayout) findViewById(R.id.rel12);
		mIdTextView = (TextView) findViewById(R.id.id);
		mAccountNameTextView = (TextView) findViewById(R.id.account_name);
		mTv4 = (TextView) findViewById(R.id.tv4);
		mTv5 = (TextView) findViewById(R.id.tv5);
		mTv6 = (TextView) findViewById(R.id.tv6);
		mTv7 = (TextView) findViewById(R.id.tv7);
		mTv8 = (TextView) findViewById(R.id.tv8);

		// 注册点击监听事件
		for (int i = 0; i < 12; i++) {
			mRelBtn[i].setOnClickListener(this);
		}
		NetworkStateUtil.init(this);
		User user = new User();
		user.setId(PreferenceUtils.getID());
		user.setPhone(PreferenceUtils.getPhone());
		user.setInvitationCode(PreferenceUtils.getInviteCode());
		PartnerUtils.getInstance().getUser(this, this, user);
	}

	@Override
	public void onClick(View v) {
		Intent intent;
		switch (v.getId()) {
		case R.id.rel01:
			intent = new Intent();
			intent.setClass(this, SubMyInviteActivity.class);
			startActivity(intent);
			break;
		case R.id.rel02:
			Toast.makeText(this, "2", Toast.LENGTH_SHORT).show();
			break;
		case R.id.rel03:
			Toast.makeText(this, "3", Toast.LENGTH_SHORT).show();
			break;
		case R.id.rel04:
			intent = new Intent();
			intent.setClass(this, SubMyPartnerActivity.class);
			startActivity(intent);
			break;
		case R.id.rel05:
			Toast.makeText(this, "5", Toast.LENGTH_SHORT).show();
			break;
		case R.id.rel06:
			Toast.makeText(this, "6", Toast.LENGTH_SHORT).show();
			break;
		case R.id.rel07:
			Toast.makeText(this, "7", Toast.LENGTH_SHORT).show();
			break;
		case R.id.rel08:
			Toast.makeText(this, "8", Toast.LENGTH_SHORT).show();
			break;
		case R.id.rel09:
			Toast.makeText(this, "9", Toast.LENGTH_SHORT).show();
			break;
		case R.id.rel10:
			Toast.makeText(this, "10", Toast.LENGTH_SHORT).show();
			break;
		case R.id.rel11:
			Toast.makeText(this, "11", Toast.LENGTH_SHORT).show();
			break;
		case R.id.rel12:
			intent = new Intent();
			intent.setClass(this, SettingsActivity.class);
			startActivityForResult(intent, ISLOGOUT);
			break;

		default:
			break;
		}
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		if (requestCode == ISLOGOUT) {
			if (resultCode == 1) {
				finish();
			}
		}
	}

	@Override
	public void IPartnerObserver_success() {
		mIdTextView.setText(PartnerApp.USER.getId() + "");
		mAccountNameTextView.setText(PartnerApp.USER.getAccountName());
		mTv4.setText((PartnerApp.USER.getFirstlyPartnerNum() + PartnerApp.USER
				.getSecondlyPartnerNum()) + "");
		mTv5.setText(PartnerApp.USER.getInterestReturnCoefficient());
		mTv6.setText(PartnerApp.USER.getChargeReturnCoefficient());
		mTv7.setText("日增收益:" + PartnerApp.USER.getDailyIncome());
		mTv8.setText("本月收益:" + PartnerApp.USER.getMonthlyIncome());

	}

	@Override
	public void IPartnerObserver_failed(String errorMsg) {

	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
		NetworkStateUtil.release(this);
	}
}
