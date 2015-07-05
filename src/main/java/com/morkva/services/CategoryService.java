package com.morkva.services;

import com.morkva.entities.Category;
import com.morkva.model.dao_v3.CategoryDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by koros on 30.06.2015.
 */
@Service("categoryService")
public class CategoryService {

    @Autowired
    CategoryDao categoryDAO;

    public List<Category> getAll() {
        return categoryDAO.getAll();
    }

    public Category getById(Integer categoryId) {
        return categoryDAO.getById(categoryId);
    }
}
