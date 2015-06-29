package com.morkva.model.dao_v2;

import javax.sql.DataSource;

/**
 * Created by koros on 29.06.2015.
 */
public interface DAO<T extends Identified> {

    void setDataSource(DataSource ds);
    void create(T entity);
    T getById(Integer id);
    void update(T entity);
    void delete(T entity);
}
