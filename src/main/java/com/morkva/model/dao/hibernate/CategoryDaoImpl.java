package com.morkva.model.dao.hibernate;

import com.morkva.entities.Category;
import com.morkva.model.dao.CategoryDao;
import org.hibernate.Session;
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
        Session currentSession = sessionFactory.getCurrentSession();
        System.err.println("CURRENT SESSION = " + currentSession);
        return currentSession.createCriteria(Category.class).addOrder(Order.asc("name")).list();
    }
}
