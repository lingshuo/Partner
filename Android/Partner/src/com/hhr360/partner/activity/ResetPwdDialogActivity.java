package com.hhr360.partner.activity;

import com.hhr360.partner.R;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Window;

public class ResetPwdDialogActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.dialog_resetpwd_success);
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
		Intent intent = new Intent(this, LoginActivity.class);
		startActivity(intent);
	}
}
