package com.hhr360.partner.utils;

import org.apache.http.Header;
import org.json.JSONException;
import org.json.JSONObject;

import android.content.Context;
import android.text.TextUtils;
import android.widget.Toast;
import com.hhr360.partner.R;
import com.hhr360.partner.observer.IRegistObserver;

import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

/**
 * @author Tans
 */
public class RegistUtil implements IConstants {
	private Context mContext;

	public void clickRegist(Context context, final IRegistObserver observer,
			String phoneNum, String accountName, String passWord,
			String passWordAgain, String inviteCode) {
		mContext = context;
		if (TextUtils.isEmpty(accountName)) {
			Toast.makeText(mContext, "昵称不能为空", Toast.LENGTH_SHORT).show();
			return;
		} else if (!judgePhoneNumberLegal(phoneNum)) {
			return;
		} else if (!judgePasswordLegal(mContext, passWord, passWordAgain)) {
			return;
		} else if (!judgeInviteCodeLegal(inviteCode)) {
			return;
		}
		// 发起注册请求
		regist(observer, phoneNum, accountName, passWord, inviteCode);

	}

	// 判断手机号是否符合要求
	private boolean judgePhoneNumberLegal(String phoneNum) {
		if (phoneNum.length() != 11 || !phoneNum.startsWith("1")) {
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
	public boolean judgePasswordLegal(Context context, String firstPassword,
			String secondPassword) {
		String toastText = null;
		if (firstPassword.length() < 6) {
			// 密码长度不符合要求
			toastText = context.getResources().getString(
					R.string.register_password_too_short);
		} else if (!firstPassword.equals(secondPassword)) {
			// 两次密码输入不一致
			toastText = context.getResources().getString(
					R.string.register_password_not_same);
		}
		if (!TextUtils.isEmpty(toastText)) {
			Toast.makeText(context, toastText, Toast.LENGTH_SHORT).show();
			return false;
		}
		return true;
	}

	// 利用正则表达式判断邀请码是否正确
	public static boolean isInviteCodeLegal(String code) {
		String regex = "[0-9]{12}$";
		return code.matches(regex) ? true : false;
	}

	private void regist(final IRegistObserver observer, String phoneNum,
			String accountName, String password, String inviteCode) {
		RequestParams params = new RequestParams();
		params.put(PHONE, phoneNum);
		params.put(PASSWORD, MD5.getMD5Str(password));
		params.put(ACCOUNT_NAME, accountName);
		params.put(INVITATION_CODE, inviteCode);
		HttpUtils.post(UrlManagerUtils.getRegisterUrl(), params,
				new AsyncHttpResponseHandler() {

					@Override
					public void onSuccess(int code, Header[] header,
							byte[] result) {
						/**
						 * {"is_success":true,"message":"success"}
						 */
						int registStatus = 0;
						String message = null;
						// 解析json数据
						String json = new String(result);

						JSONObject jsonObj;
						try {
							jsonObj = new JSONObject(json);
							registStatus = jsonObj.getInt(IS_SUCCESS);
							message = jsonObj.getString(MESSAGE);
							if (registStatus != 0) {
								// 注册成功回调
								observer.IRegistObserver_succeed();
							} else {
								// 注册失败回调
								observer.IRegistObaserver_failed(message);
							}
						} catch (JSONException e) {
							e.printStackTrace();
							// 注册失败回调
							observer.IRegistObaserver_failed(message);
						}

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
