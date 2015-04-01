package com.hhr360.partner.utils;


public class ScreenUtility {

	/**
	 * 根据手机的分辨率从 dp 的单位 转成为 px(像素)
	 */
	public static int dip2px(float dpValue) {
		float scale = DeviceInfo.DENSITY;
		return (int) (dpValue * scale + 0.5f);
	}

	/**
	 * 根据手机的分辨率从 px(像素) 的单位 转成为 dp
	 */
	public static int px2dip(float pxValue) {
		float scale = DeviceInfo.DENSITY;
		return (int) (pxValue / scale + 0.5f);
	}

}
