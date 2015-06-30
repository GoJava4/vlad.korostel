package com.morkva.services;

import com.morkva.entities.Category;
import com.morkva.model.dao_v2.CategoryDAO;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * Created by koros on 30.06.2015.
 */
public class CategoryService {

    @Autowired
    CategoryDAO categoryDAO;

    public List<Category> getAll() {
        return categoryDAO.getAll();
    }

    public Category getById(Integer categoryId) {
        return categoryDAO.getById(categoryId);
    }
}
