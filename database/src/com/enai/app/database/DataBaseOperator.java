package com.enai.app.database;

import com.enai.app.database.base.BaseOperator;
import com.enai.app.database.base.DataBaseConfig;

import java.util.List;

/**
 * Created by zhaowenchao on 16/5/18.
 */
public class DataBaseOperator<T> {

    private BaseOperator<T> Operator;

    public DataBaseOperator(Class entityClass, String path) {
        DataBaseConfig config = new DataBaseConfig.Builder().setDatabaseName(path).build();
        DataBaseManager dbm = DataBaseManager.getInstance().setConfig(config);
        Operator = dbm.getOperator(entityClass);
    }

    /**
     * 插入一个实体类
     *
     * @param entity 要插入的实体类
     * @return 新插入实体类的行ID （row Id）
     */
    public long insert(T entity) {
        return Operator.insert(entity);
    }

    /**
     * 插入一个list
     *
     * @param entities 要插入的list
     */
    public void insertList(List entities) {
        Operator.insertList(entities);
    }

    /**
     * 替换（如果没有就插入）一条记录
     *
     * @param entity 要插入的记录
     * @return 新插入记录的row id
     */
    public long insertOrUpdate(T entity) {
        return Operator.insertOrUpdate(entity);
    }

    public void insertOrUpdate(List entities) {
        Operator.insertOrUpdate(entities);
    }

    public void update(T entity) {
        Operator.update(entity);
    }

    public void updateList(List entities) {
        Operator.updateList(entities);
    }

    public void delete(T entity) {
        Operator.delete(entity);
    }

    public void deleteList(List entities) {
        Operator.deleteList(entities);
    }

    public void deleteAll(Class<? extends Object> entityClass) {
        Operator.deleteAll(entityClass);
    }

    public List qureyAll(Class<? extends Object> entityClass) {
        return Operator.qureyAll(entityClass);
    }

}
