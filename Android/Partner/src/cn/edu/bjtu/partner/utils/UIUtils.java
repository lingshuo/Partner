package cn.edu.bjtu.partner.utils;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.ImageView;

/**
 * @author Tans
 */
public class UIUtils {

	// 设置验证码
	public static void setSecurityBitmap(ImageView imageView) {
		Bitmap bitmap = SecurityCodeUtil.getInstance().createBitmap();
		if (bitmap != null) {
			imageView.setImageBitmap(bitmap);
		}
	}

	// 隐藏键盘
	public static void hideKeyboard(Context context) {
		if (context == null) {
			return;
		}

		InputMethodManager imm = (InputMethodManager) context
				.getSystemService(Context.INPUT_METHOD_SERVICE);
		if (imm.isActive()) {
			imm.toggleSoftInput(InputMethodManager.SHOW_IMPLICIT,
					InputMethodManager.HIDE_NOT_ALWAYS);
		}
	}

	// 显示键盘
	public static boolean showKeyboard(final View windowView) {
		if (windowView == null) {
			return false;
		}

		if (!windowView.isFocused()) {
			windowView.requestFocus();
		}

		Context context = windowView.getContext();
		InputMethodManager imm = (InputMethodManager) context
				.getSystemService(Context.INPUT_METHOD_SERVICE);
		return imm.showSoftInput(windowView, InputMethodManager.SHOW_IMPLICIT);
	}

}
