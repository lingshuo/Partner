package cn.edu.bjtu.partner.activity;

import cn.edu.bjtu.partner.R;
import android.app.Activity;
import android.os.Bundle;
import android.view.Window;

public class FindPasswordActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.find_password);
	}
}
