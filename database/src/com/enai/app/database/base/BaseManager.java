package com.enai.app.database.base;

import android.content.Context;

/**
 * Created by zhaowenchao on 16/5/18.
 * 范型中，T为操作表对应的实体类，K为改表的主键
 */
public interface BaseManager {
    /**
     * 初始化，要加线程同步锁
     *
     * @param config
     */
    void setConfig(DataBaseConfig config);

    void init(Context context);

    boolean isIniting();

    boolean isInited();

    DataBaseConfig getConfig();

    BaseOperator getOperator(Class<? extends Object> entityClass);

}
