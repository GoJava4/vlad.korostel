package com.morkva.model.dao_v3;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by koros on 06.07.2015.
 */
public class AbstractDao<T> implements Dao<T> {

    @Autowired
    protected SessionFactory sessionFactory;

    Class<T> type;

    public AbstractDao(Class<T> type) {
        this.type = type;
    }

    @Override
    public void create(T entity) {
        sessionFactory.getCurrentSession().save(entity);
    }

    @Override
    public T getById(Integer id) {
        return (T) sessionFactory.getCurrentSession().get(type, id);
    }

    @Override
    public void update(T entity) {
        sessionFactory.getCurrentSession().update(entity);
    }

    @Override
    public void delete(T entity) {
        sessionFactory.getCurrentSession().delete(entity);
    }
}
