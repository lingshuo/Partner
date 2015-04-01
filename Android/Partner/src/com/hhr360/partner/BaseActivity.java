package com.hhr360.partner;

import android.app.Activity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.hhr360.partner.R;

public abstract class BaseActivity extends Activity {
	private TextView mTextView;
	private RelativeLayout mRelativeLayout;

	public void setHeaderTextName(String textName) {
		mTextView = (TextView) findViewById(R.id.head_tv);
		mRelativeLayout = (RelativeLayout) findViewById(R.id.back_rel);

		mRelativeLayout.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				BaseActivity.this.finish();
			}
		});
		mTextView.setText(textName);
	}

}
