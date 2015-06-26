package com.morkva.servlets;

import com.morkva.entities.Category;
import com.morkva.model.CategoryRepository;
import com.morkva.model.dao.DAOFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.AutowireCapableBeanFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.context.ContextLoader;
import org.springframework.web.context.WebApplicationContext;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;

/**
 * Created by koros on 26.06.2015.
 */
@Controller
public class CategoryServlet extends HttpServlet {

    @Autowired
    DAOFactory<Connection> daoFactory;

    @Autowired
    CategoryRepository categoryRepository;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //TODO Parse GET data
        String parameter = req.getParameter("category-id");
        req.setAttribute("asd", parameter);
        Integer categoryId = Integer.parseInt(parameter);

        Category category = categoryRepository.getById(categoryId);

        req.setAttribute("category_name", category.getName());
        req.setAttribute("category_id", categoryId);

        req.getRequestDispatcher("jsp/category.jsp").forward(req, resp);
    }

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        WebApplicationContext context = ContextLoader.getCurrentWebApplicationContext();
        final AutowireCapableBeanFactory beanFactory = context.getAutowireCapableBeanFactory();
        beanFactory.autowireBean(this);
    }
}
