package cn.edu.bjtu.partner.utils;

import org.apache.http.Header;

import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import cn.edu.bjtu.partner.observer.ILoginObserver;

/**
 * @author Tans
 */
public class LoginUtil {

	public static void login(final ILoginObserver observer, String phoneNum,
			String password, String inviteCode) {
		RequestParams params = new RequestParams();
		params.put("username", phoneNum);
		params.put("password", password);
		HttpUtils.post(UrlManagerUtils.getLoginUrl(), params,
				new AsyncHttpResponseHandler() {

					@Override
					public void onSuccess(int code, Header[] head, byte[] result) {
						int userId = 0, phoneNum = 0, inviteCode = 0;
						//解析json数据
						// 登录成功回调
						observer.ILoginObserver_succeed(userId, phoneNum,
								inviteCode);
					}

					@Override
					public void onFailure(int code, Header[] head,
							byte[] result, Throwable throwable) {
						String errorMsg = "";
						observer.ILoginObaserver_failed(errorMsg);
					}
				});
	}
}
