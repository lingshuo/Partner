package cn.edu.bjtu.partner.utils;

/**
 * @author Tans
 */
public class UrlManagerUtils {
	private static final String DOMAIN = "http://XXXXXXX/";
	private static final String LOGIN = "login";
	private static final String REGISTER = "register";

	// 注册的url
	public static String getRegisterUrl() {
		String url = DOMAIN + REGISTER;
		return url;
	}

	// 登录的url
	public static String getLoginUrl() {
		String url = DOMAIN + LOGIN;
		return url;
	}
}
