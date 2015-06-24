package com.morkva.servlets;

import com.morkva.entities.Category;
import com.morkva.model.*;
import com.morkva.model.dao.DAO;
import com.morkva.model.dao.DAOFactory;
import com.morkva.model.dao.PersistException;
import com.morkva.model.dao.jdbc.mysql.MySQLDaoFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.context.ContextLoader;
import org.springframework.web.context.WebApplicationContext;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.Connection;
import java.util.List;

/**
 * Created by koros on 19.06.2015.
 */
public class MainServlet extends HttpServlet {


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        try {
            WebApplicationContext context = ContextLoader.getCurrentWebApplicationContext();
            DAOFactory<Connection> daoFactory = (DAOFactory<Connection>) context.getBean("daoFactory");
            if (daoFactory != null) {
                Connection connection = daoFactory.getContext();
                CategoryRepository repository = new CategoryRepositoryImpl(daoFactory.getDao(connection, Category.class));
                req.setAttribute("list", repository.getAll());
            }
        } catch (PersistException e) {
            e.printStackTrace();
        }
        req.getRequestDispatcher("index.jsp").forward(req, resp);
    }
}
