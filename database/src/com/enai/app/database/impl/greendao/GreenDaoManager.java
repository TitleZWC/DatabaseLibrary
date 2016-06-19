package com.enai.app.database.impl.greendao;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.enai.app.database.base.BaseManager;
import com.enai.app.database.base.BaseOperator;
import com.enai.app.database.base.DataBaseConfig;
import com.enai.app.database.base.DatabaseContext;
import com.xiaoenai.app.database.bean.DaoMaster;
import com.xiaoenai.app.database.bean.DaoMaster.DevOpenHelper;

import java.util.Collection;

import de.greenrobot.dao.AbstractDao;

/**
 * Created by zhaowenchao on 16/5/17.
 */
public class GreenDaoManager implements BaseManager {
    //TODO 支持多DB 根据读写操作使用对应的readable/writeAble database,测试多线程访问是否有问题 by title

    private static GreenDaoManager instance;
    private SQLiteDatabase db;
    private static Context mContext;
    private DatabaseContext mDatabaseContext;
    private DaoMaster daoMaster;

    public static GreenDaoManager getInstance() {

        if (null == instance) {
            synchronized (GreenDaoManager.class) {
                if (null == instance) {
                    instance = new GreenDaoManager();
                }
            }
        }

        return instance;

    }

    private GreenDaoManager() {
    }

    private AbstractDao<?, ?> getDao(Class<? extends Object> entityClass) {
        return daoMaster.newSession().getDao(entityClass);
    }

    private DataBaseConfig config;
    private boolean isIniting = false;
    private boolean isInited = false;

    @Override
    public void setConfig(DataBaseConfig config) {
        isIniting = true;
        this.config = config;
        mDatabaseContext = new DatabaseContext(mContext, config.path);
        UpgradeDevOpenHelper helper = new UpgradeDevOpenHelper(mDatabaseContext, config.name, null);

        db = helper.getWritableDatabase();
        daoMaster = new DaoMaster(db);
        isIniting = false;
    }

    @Override
    public void init(Context context) {
        mContext = context.getApplicationContext();
        isInited = true;
    }

    @Override
    public boolean isIniting() {
        return isIniting;
    }

    @Override
    public boolean isInited() {
        return isInited;
    }

    @Override
    public DataBaseConfig getConfig() {
        return config;
    }


    @Override
    public BaseOperator getOperator(Class<? extends Object> entityClass) {
        return new GreenDaoOperator(daoMaster.newSession());
    }

    private class UpgradeDevOpenHelper extends DevOpenHelper {

        public UpgradeDevOpenHelper(Context context, String name, SQLiteDatabase.CursorFactory factory) {
            super(context, name, factory);
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            Collection<AbstractDao<?, ?>> Daos = daoMaster.newSession().getAllDaos();
            int size = Daos.size();
            Class[] array = new Class[size];
            for (int i = 0; i < size; i++) {
                array[i] = Daos.toArray()[i].getClass();
            }
            MigrationHelper.migrate(db, array);
        }
    }
}
