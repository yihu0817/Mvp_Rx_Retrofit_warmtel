package com.warmtel.mvprr.Utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

/**
 * 016/9/2.
 */
public class HttpNetUtil {
    /**
     * 判断网络连接是否可用
     * @param context
     * @return
     */
    public static boolean isNetworkConnected(Context context) {
        ConnectivityManager connectivity = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        if (isGpsConnection(connectivity) || isWiFiConnection(connectivity)) {
            //网络连接可用
            return true;
        } else {
            //网络连接不可用!
            return false;
        }
    }

    /**
     * 检查网络连接是否可用
     */
    public static boolean isGpsConnection( ConnectivityManager connectivity) {
        NetworkInfo networkInfo = connectivity.getActiveNetworkInfo();
        if (networkInfo != null && networkInfo.isConnected()) {
            //网络连接可用
            return true;
        } else {
            //网络连接不可用!
            return false;
        }
    }

    public static boolean isWiFiConnection( ConnectivityManager connectivity) {
        if (connectivity != null) {
            NetworkInfo[] info = connectivity.getAllNetworkInfo();
            if (info != null) {
                for (int i = 0; i < info.length; i++) {
                    if (info[i].getTypeName().equals("WIFI") && info[i].isConnected()) {
                        return true;
                    }
                }
            }
        }
        return false;
    }
}
