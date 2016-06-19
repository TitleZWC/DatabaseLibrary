package com.enai.app.database.base;

import java.util.List;

/**
 * Created by zhaowenchao on 16/5/18.
 * 范型中，T为操作表对应的实体类，K为改表的主键
 */
public interface BaseOperator<T> {
    /**
     * 插入一个实体类
     *
     * @param entity 要插入的实体类
     * @return 新插入实体类的行ID （row Id）
     */
    long insert(T entity);

    /**
     * 插入一个list
     *
     * @param entities 要插入的list
     */
    void insertList(List entities);

    /**
     * 替换（如果没有就插入）一条记录
     *
     * @param entity 要插入的记录
     * @return 新插入记录的row id
     */
    long insertOrUpdate(T entity);

    void insertOrUpdate(List entities);

    void update(T entity);

    void updateList(List entities);

    void delete(T entity);

    void deleteList(List entities);

    void deleteAll(Class<? extends Object> entityClass);

    List<T> qureyAll(Class<? extends Object> entityClass);
}
