package com.hhr360.partner.utils;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

public class NetworkStateUtil extends BroadcastReceiver {

	public static void init(final Context ctx) {
		IntentFilter filter = new IntentFilter();
		filter.addAction(ConnectivityManager.CONNECTIVITY_ACTION);
		try {
			ctx.registerReceiver(instance, filter);
		} catch (Exception e) {
		}
		getNetworkInfo(ctx);
	}

	public static void release(final Context ctx) {
		try {
			ctx.unregisterReceiver(instance);
		} catch (Exception e) {

		}
	}

	public static boolean isAvaliable() {
		return isNetworkAvaliable;
	}

	public static boolean isWifi() {
		return isAvaliable() && isWifiAvaliable;
	}

	public static boolean isMobile() {
		return isAvaliable() && !isWifi();
	}

	public static boolean is3G() {
		return isAvaliable() && getNetWorkType() == TYPE_3G;
	}

	public static boolean isWifiOr3G() {
		return isWifi() || is3G();
	}

	public static final int TYPE_UNKNOWN = 0, TYPE_2G = 1, TYPE_3G = 2;

	public static int getNetWorkType() {
		return networkTypeID;
	}

	// "WIFI"、"2G"、"3G"、"UNKNOWN"
	public static String getNetworkTypeName() {
		return networkTypeName;
	}

	public static final int OPERATOR_UNKNOWN = 0, OPERATOR_CMCC = 1,
			OPERATOR_CUCC = 2, OPERATOR_CT = 3;

	public static int getOperatorType() {
		return networkOperatorID;
	}

	public static String getAccessPoint() {
		return accessPointName;
	}

	@Override
	public final void onReceive(final Context context, final Intent intent) {
		new Thread() {
			@Override
			public void run() {
				getNetworkInfo(context);
			}
		}.start();
	}

	public static void getNetworkInfo(final Context context) {
		ConnectivityManager connectivity = (ConnectivityManager) context
				.getSystemService(Context.CONNECTIVITY_SERVICE);
		if (connectivity == null) {
			return;
		}

		NetworkInfo[] info = null;
		try {
			info = connectivity.getAllNetworkInfo();
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (info != null) {
			isNetworkAvaliable = false;
			isWifiAvaliable = false;

			for (int i = 0; i < info.length; i++) {
				if (info[i].isConnected()) {
					isNetworkAvaliable = true;
					accessPointName = info[i].getTypeName();
					if (info[i].getType() == ConnectivityManager.TYPE_WIFI) {
						isWifiAvaliable = true;
						networkTypeName = "WIFI";
					} else if (info[i].getType() == ConnectivityManager.TYPE_MOBILE) {
						int typeID = info[i].getSubtype();
						if (typeID < NETWORK_TYPES.length) {
							accessPointName = info[i].getExtraInfo();
							networkTypeID = NETWORK_TYPES[typeID][0];
							networkOperatorID = NETWORK_TYPES[typeID][1];
							networkTypeName = TYPE_NAME[networkTypeID];
						} else {
							networkTypeID = TYPE_3G;
							networkTypeName = "3G";
						}
					} else {
						networkTypeName = "UNKNOWN";
					}
					break;
				}
			}
		}
	}

	private static final String[] TYPE_NAME = { "UNKNOWN", "2G", "3G", "3G" }; // 服务器暂时把LTE当作3G对待

	private static final int[][] NETWORK_TYPES = {
	/* 0 */{ TYPE_UNKNOWN, OPERATOR_UNKNOWN },
	/* 1 */{ TYPE_2G, OPERATOR_CUCC },
	/* 2 */{ TYPE_2G, OPERATOR_CMCC },
	/* 3 */{ TYPE_3G, OPERATOR_CUCC },
	/* 4 */{ TYPE_2G, OPERATOR_CT },
	/* 5 */{ TYPE_3G, OPERATOR_CT },
	/* 6 */{ TYPE_3G, OPERATOR_CT },
	/* 7 */{ TYPE_UNKNOWN, OPERATOR_UNKNOWN },
	/* 8 */{ TYPE_3G, OPERATOR_CUCC },
	/* 9 */{ TYPE_UNKNOWN, OPERATOR_UNKNOWN },
	/* 10 */{ TYPE_UNKNOWN, OPERATOR_UNKNOWN },
	/* 11 */{ TYPE_UNKNOWN, OPERATOR_UNKNOWN },
	/* 12 */{ TYPE_3G, OPERATOR_CT },
	/* 13 */{ TYPE_3G/* 其实是TYPE_LTE */, OPERATOR_UNKNOWN },
	/* 14 */{ TYPE_UNKNOWN, OPERATOR_UNKNOWN },
	/* 15 */{ TYPE_UNKNOWN, OPERATOR_UNKNOWN } };

	private static NetworkStateUtil instance = new NetworkStateUtil();
	private static volatile boolean isNetworkAvaliable;
	private static volatile boolean isWifiAvaliable;
	private static volatile int networkTypeID;
	private static volatile String networkTypeName = "UNKNOWN";
	private static volatile int networkOperatorID;
	private static volatile String accessPointName = "None";
}
