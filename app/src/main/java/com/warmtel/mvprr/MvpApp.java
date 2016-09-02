package com.warmtel.mvprr;

import android.app.Application;

/**
 * 2016/9/2.
 */
public class MvpApp extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        RetrofitManager.init(this);
    }
}
