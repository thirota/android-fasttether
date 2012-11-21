package org.fasttether;

import java.lang.reflect.Method;

import android.app.Activity;
import android.content.Context;
import android.net.wifi.WifiConfiguration;
import android.net.wifi.WifiManager;
import android.util.Log;

/**
 * Switch for Wifi AP tethering.
 * 
 * <p>
 * uses-permission
 * <ul>
 * <li>android.permission.ACCESS_WIFI_STATE</li>
 * <li>android.permission.CHANGE_WIFI_STATE</li>
 * </ul>
 * </p>
 * 
 * @author takayuki hirota
 */
public class WifiApSwitch {
	private static final String TAG = "WifiApSwitch";

	private final Activity activity;

	public WifiApSwitch(final Activity activity) {
		this.activity = activity;
	}

	/**
	 * turn on/off wifiAp tethering.
	 * 
	 * @return current wifiAp status after toggled
	 */
	public boolean toggle() {
		boolean wifiApEnabled = false;
		try {
			WifiManager wifi = (WifiManager) activity
					.getSystemService(Context.WIFI_SERVICE);
			Method method = wifi.getClass().getMethod("isWifiApEnabled");
			wifiApEnabled = Boolean
					.parseBoolean(method.invoke(wifi).toString());
			Log.i(TAG, "wifiApEnabled: " + wifiApEnabled);
			setWifiApMode(wifi, !wifiApEnabled);
		} catch (Exception e) {
			Log.e(TAG, "error: ", e);
		}
		return !wifiApEnabled;
	}

	private void setWifiApMode(final WifiManager wifi, final boolean mode)
			throws Exception {
		Method method = wifi.getClass().getMethod("setWifiApEnabled",
				WifiConfiguration.class, boolean.class);
		Log.i(TAG, "setWifiApEnabled: "
				+ method.invoke(wifi, null, mode).toString());
	}

}
