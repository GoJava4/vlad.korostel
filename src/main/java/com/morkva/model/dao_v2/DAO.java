package com.morkva.model.dao_v2;

import org.springframework.jdbc.core.JdbcTemplate;

/**
 * Created by koros on 29.06.2015.
 */
public interface Dao<T extends Identified> {

    void setJdbcTemplate(JdbcTemplate jdbcTemplate);
    void create(T entity);
    T getById(Integer id);
    void update(T entity);
    void delete(T entity);
}
