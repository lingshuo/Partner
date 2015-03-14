package cn.edu.bjtu.partner.activity;

import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import cn.edu.bjtu.partner.R;

public class LoginActivity extends Activity {

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
