package com.morkva.servlets;

import com.morkva.entities.Project;
import com.morkva.model.dao_v2.templates.ProjectJDBCTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Created by koros on 27.06.2015.
 */
@Controller
public class ProjectServlet extends HttpServlet {

    @Autowired
    ProjectJDBCTemplate projectJDBCTemplate;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();

        String parameter = req.getParameter("project-id");
        if (parameter == null) {
            req.getRequestDispatcher("jsp/404.jsp").forward(req, resp);
            return;
        }
        Integer projectId = Integer.parseInt(parameter);
        Project project = projectJDBCTemplate.getById(projectId);

        session.setAttribute("current_project", project);

        req.setAttribute("project", project);
        req.getRequestDispatcher("jsp/project.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String donateCount = req.getParameter("donate-count");

        Integer donateCountInt = Integer.parseInt(donateCount);

        HttpSession session = req.getSession();
        Project project = (Project) session.getAttribute("current_project");

        donate(project, donateCountInt);

        req.setAttribute("project", project);
        req.getRequestDispatcher("jsp/project.jsp").forward(req, resp);
    }

    private void donate(Project project, Integer donateCount) {
        if (donateCount != null) {
            project.setCurrentMoney(project.getCurrentMoney() + donateCount);
            projectJDBCTemplate.update(project);
        }
    }

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        SpringBeanAutowiringSupport.processInjectionBasedOnServletContext(this, config.getServletContext());
    }
}
