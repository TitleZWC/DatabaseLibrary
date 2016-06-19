package com.enai.app.database.impl.greendao;

import com.enai.app.database.base.BaseOperator;
import com.titlezwc.moment.DaoSession;

import java.util.List;

import de.greenrobot.dao.async.AsyncOperation;
import de.greenrobot.dao.async.AsyncSession;
import de.greenrobot.dao.query.Query;

/**
 * Created by zhaowenchao on 16/5/17.
 */
public class GreenDaoOperator<T> implements BaseOperator<T> {
    private DaoSession mOperator;

    private AsyncSession mAsyncOperrator;

    public GreenDaoOperator(DaoSession dao) {
        mOperator = dao;
    }


    /**
     * 插入一个实体类
     *
     * @param entity 要插入的实体类
     * @return 新插入实体类的行ID （row Id）
     */
    @Override
    public long insert(T entity) {
        return mOperator.insert(entity);
    }


    public AsyncOperation AsyncInsert(T entuty) {
        AsyncOperation a = mOperator.startAsyncSession().insert(entuty);
        return null;
    }

    /**
     * 插入一个list
     *
     * @param entities 要插入的list
     */
    @Override
    public void insertList(List entities) {

        if (null == entities || entities.size() <= 0) {
            return;
        }
        mOperator.getDao(entities.get(0).getClass()).insertInTx(entities);
    }


    public AsyncOperation AsyncInsertList(List entities) {
        if (null == entities || entities.size() <= 0) {
            return null;
        }
        return mOperator.startAsyncSession().insertInTx(entities.get(0).getClass(), entities);
    }

    /**
     * 替换（如果没有就插入）一条记录
     *
     * @param entity 要插入的记录
     * @return 新插入记录的row id
     */
    @Override
    public long insertOrUpdate(T entity) {
        return mOperator.insertOrReplace(entity);
    }

    public AsyncOperation AsyncInsertOrUpdate(T entity) {
        return mOperator.startAsyncSession().insertOrReplace(entity);
    }


    @Override
    public void insertOrUpdate(List entities) {
        if (null == entities || entities.size() <= 0) {
            return;
        }
        mOperator.getDao(entities.get(0).getClass()).insertOrReplaceInTx(entities);
    }


    public AsyncOperation AsyncInsertOrUpdate(List entities) {
        if (null == entities || entities.size() <= 0) {
            return null;
        }
        return mOperator.startAsyncSession().insertOrReplaceInTx(entities.get(0).getClass(), entities);
    }

    @Override
    public void update(T entity) {
        mOperator.update(entity);
    }

    public AsyncOperation AsyncUpdate(T entity) {
        return mOperator.startAsyncSession().update(entity);
    }

    @Override
    public void updateList(List entities) {

        if (null == entities || entities.size() <= 0) {
            return;
        }
        mOperator.getDao(entities.get(0).getClass()).updateInTx(entities);
    }

    public AsyncOperation AsyncUpdateList(List entities) {
        if (null == entities || entities.size() <= 0) {
            return null;
        }
        return mOperator.startAsyncSession().updateInTx(entities.get(0).getClass(), entities);
    }

    @Override
    public void delete(T entity) {
        mOperator.delete(entity);
    }

    public AsyncOperation AsyncDelete(T entity) {
        return mOperator.startAsyncSession().delete(entity);
    }

    @Override
    public void deleteList(List entities) {

        if (null == entities || entities.size() <= 0) {
            return;
        }
        mOperator.getDao(entities.get(0).getClass()).deleteInTx(entities);
    }

    public AsyncOperation AsyncDeleteList(List entities) {
        if (null == entities || entities.size() <= 0) {
            return null;
        }
        return mOperator.startAsyncSession().deleteInTx(entities.get(0).getClass(), entities);
    }

    @Override
    public void deleteAll(Class<? extends Object> entityClass) {
        mOperator.deleteAll(entityClass);
    }

    public AsyncOperation AsyncDeleteAll(Class<? extends Object> entityClass) {
        return mOperator.startAsyncSession().deleteAll(entityClass);
    }

    @Override
    public List<T> qureyAll(Class<? extends Object> entityClass) {
        Query query = mOperator.queryBuilder(entityClass)
                .build();
        return query.list();
    }

    public AsyncOperation AsyncQureyAll(Class<? extends Object> entityClass) {
        Query query = mOperator.queryBuilder(entityClass)
                .build();
        return mOperator.startAsyncSession().queryList(query);
    }

}
