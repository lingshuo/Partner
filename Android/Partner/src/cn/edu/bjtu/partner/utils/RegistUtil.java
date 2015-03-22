package cn.edu.bjtu.partner.utils;

import org.apache.http.Header;

import android.content.Context;
import android.text.TextUtils;
import android.widget.Toast;
import cn.edu.bjtu.partner.R;
import cn.edu.bjtu.partner.observer.IRegistObserver;

import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

/**
 * @author Tans
 */
public class RegistUtil {
	private Context mContext;

	public void clickRegist(Context context, final IRegistObserver observer,
			String phoneNum, String passWord, String passWordAgain,
			String inviteCode) {
		mContext = context;
		if (!judgePhoneNumberLegal(phoneNum)) {
			return;
		} else if (!judgePasswordLegal(passWord, passWordAgain)) {
			return;
		} else if (!judgeInviteCodeLegal(inviteCode)) {
			return;
		}
		// 发起注册请求
		regist(observer, phoneNum, passWord, inviteCode);

	}

	// 判断手机号是否符合要求
	private boolean judgePhoneNumberLegal(String phoneNum) {
		if (phoneNum.length() != 11) {
			Toast.makeText(
					mContext,
					mContext.getResources().getString(
							R.string.register_phone_too_short),
					Toast.LENGTH_SHORT).show();
			return false;
		}
		return true;
	}

	// 判断邀请码是否符合要求
	private boolean judgeInviteCodeLegal(String inviteCode) {
		if (TextUtils.isEmpty(inviteCode)) {
			Toast.makeText(
					mContext,
					mContext.getResources().getString(
							R.string.register_invite_not_null),
					Toast.LENGTH_SHORT).show();
			return false;
		} else {
			return true;
		}
	}

	// 判断密码是否符合要求
	private boolean judgePasswordLegal(String firstPassword,
			String secondPassword) {
		String toastText = null;
		if (firstPassword.length() < 6) {
			// 密码长度不符合要求
			toastText = mContext.getResources().getString(
					R.string.register_password_too_short);
		} else if (!firstPassword.equals(secondPassword)) {
			// 两次密码输入不一致
			toastText = mContext.getResources().getString(
					R.string.register_password_not_same);
		}
		if (!TextUtils.isEmpty(toastText)) {
			Toast.makeText(mContext, toastText, Toast.LENGTH_SHORT).show();
			return false;
		}
		return true;
	}

	private void regist(final IRegistObserver observer, String phoneNum,
			String password, String inviteCode) {
		RequestParams params = new RequestParams();
		params.put("phoneNum", phoneNum);
		params.put("password", password);
		params.put("invite", inviteCode);
		HttpUtils.post(UrlManagerUtils.getRegisterUrl(), params,
				new AsyncHttpResponseHandler() {

					@Override
					public void onSuccess(int code, Header[] header,
							byte[] result) {
						// 解析json数据

						int userId = 0, phoneNum = 0, inviteCode = 0;
						// 注册成功回调
						observer.IRegistObserver_succeed(userId, phoneNum,
								inviteCode);
					}

					@Override
					public void onFailure(int code, Header[] header,
							byte[] result, Throwable throwable) {
						String errorMsg = "";
						// 注册失败回调
						observer.IRegistObaserver_failed(errorMsg);
					}
				});
	}
}
