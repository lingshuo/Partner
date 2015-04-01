package com.hhr360.partner.activity;

import android.os.Bundle;
import android.view.Window;

import com.hhr360.partner.BaseActivity;
import com.hhr360.partner.R;

public class SubMyPartnerActivity extends BaseActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.sub_my_partner);
		// 设置head的文字
		setHeaderTextName(getResources().getString(R.string.partner_my_partner));
	}
}
