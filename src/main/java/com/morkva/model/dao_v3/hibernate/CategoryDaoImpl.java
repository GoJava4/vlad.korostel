package com.morkva.model.dao_v3.hibernate;

import com.morkva.entities.Category;
import com.morkva.model.dao_v3.CategoryDao;
import org.hibernate.criterion.Order;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by koros on 06.07.2015.
 */
@Repository("categoryDao")
@Transactional
public class CategoryDaoImpl extends AbstractDao<Category> implements CategoryDao {

    public CategoryDaoImpl() {
        super(Category.class);
    }

    @Override
    public List<Category> getAll() {
        return sessionFactory.getCurrentSession().createCriteria(Category.class).addOrder(Order.asc("name")).list();
    }
}
