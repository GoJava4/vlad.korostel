package com.morkva.model.dao_v3;

import com.morkva.entities.Category;
import org.hibernate.criterion.Order;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by koros on 06.07.2015.
 */
@Repository("categoryDao")
public class CategoryDaoImpl extends AbstractDao<Category> implements CategoryDao {

    public CategoryDaoImpl() {
        super(Category.class);
    }

    @Override
    public List<Category> getAll() {
        return sessionFactory.getCurrentSession().createCriteria(Category.class).addOrder(Order.asc("name")).list();
    }
}
