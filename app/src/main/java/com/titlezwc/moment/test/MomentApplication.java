package com.titlezwc.moment.test;

import android.app.Application;

import com.enai.app.database.DataBaseManager;

/**
 * Created by zhaowenchao on 16/6/18.
 */
public class MomentApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        DataBaseManager.getInstance().init(this);
    }
}
