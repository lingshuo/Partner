package cn.edu.bjtu.partner.utils;

import android.app.Activity;
import android.content.Context;
import android.util.DisplayMetrics;
import android.view.WindowManager;

public final class DeviceInfo {

	// 屏幕宽度（像素）
	public static int WIDTH;

	// 屏幕高度（像素）
	public static int HEIGHT;
	public static float DENSITY;
	public static void initScreenInfo(Activity activity) {
		if (activity == null)
			return;
		if (WIDTH == 0) {
			try {
				DisplayMetrics dm = new DisplayMetrics();
				activity.getWindowManager().getDefaultDisplay().getMetrics(dm);
				WIDTH = Math.min(dm.widthPixels, dm.heightPixels);
				HEIGHT = Math.max(dm.widthPixels, dm.heightPixels);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	public static void initScreenInfo(Context context) {
		if (WIDTH == 0) {
			try {
				DisplayMetrics dm = new DisplayMetrics();
				((WindowManager) context
						.getSystemService(Context.WINDOW_SERVICE))
						.getDefaultDisplay().getMetrics(dm);
				WIDTH = Math.min(dm.widthPixels, dm.heightPixels);
				HEIGHT = Math.max(dm.widthPixels, dm.heightPixels);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

}
