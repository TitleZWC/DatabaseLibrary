package com.enai.app.database;

import android.content.Context;
import android.util.Log;

import com.enai.app.database.base.BaseManager;
import com.enai.app.database.base.BaseOperator;
import com.enai.app.database.base.DataBaseConfig;
import com.enai.app.database.impl.greendao.GreenDaoManager;

/**
 * Created by zhaowenchao on 16/5/19.
 */
public class DataBaseManager {

    private BaseManager manager;
    private final Object mLock = new Object();
    private static DataBaseManager instance;

    private DataBaseManager() {
        manager = GreenDaoManager.getInstance();
    }

    public static DataBaseManager getInstance() {
        if (null == instance) {
            synchronized (DataBaseManager.class) {
                if (null == instance) {
                    instance = new DataBaseManager();
                }
            }
        }
        return instance;
    }

    /**
     * 根据配置将初始化DB，如果在使用过程中需要跟换数据库，可以通过传入新的config来重新进行初始化
     *
     * @param config 数据库配置
     */
    public DataBaseManager setConfig(DataBaseConfig config) {
        if (null != config && !isIniting()) {
            synchronized (mLock) {
                if (!isIniting()) {
                    manager.setConfig(config);
                }
            }
        }
        return this;
    }

    public void init(Context context) {
        manager.init(context);
    }


    private boolean isIniting() {
        return manager.isIniting();
    }

    public DataBaseConfig getConfig() {
        return manager.getConfig();
    }

    public BaseOperator getOperator(Class<? extends Object> entityClass) {
        if (manager.isInited()) {
            return manager.getOperator(entityClass);
        } else {
            Log.e("DataBaseManager", "the dataBaseManager is never inited!");
            return null;
        }
    }
}
