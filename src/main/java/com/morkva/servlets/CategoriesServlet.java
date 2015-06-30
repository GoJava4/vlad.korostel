package com.morkva.servlets;

import com.morkva.model.dao_v2.templates.CategoryJDBCTemplate;
import com.morkva.services.QuoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by koros on 19.06.2015.
 */
@Controller
public class CategoriesServlet extends HttpServlet {

    @Autowired
    QuoteService quoteService;

    @Autowired
    CategoryJDBCTemplate categoryJDBCTemplate;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        req.setAttribute("quote", quoteService.getRandom());
        req.setAttribute("list", categoryJDBCTemplate.getAll());
        req.getRequestDispatcher("jsp/categories.jsp").forward(req, resp);
    }

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        SpringBeanAutowiringSupport.processInjectionBasedOnServletContext(this, config.getServletContext());
    }
}
