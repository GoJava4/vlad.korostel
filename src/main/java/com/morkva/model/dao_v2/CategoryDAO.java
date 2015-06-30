package com.morkva.model.dao_v2;

import com.morkva.entities.Category;

import java.util.List;

/**
 * Created by koros on 30.06.2015.
 */
public interface CategoryDAO extends DAO<Category> {
    List<Category> getAll();
}
