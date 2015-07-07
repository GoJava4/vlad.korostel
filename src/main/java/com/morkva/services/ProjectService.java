package com.morkva.services;

import com.morkva.entities.Category;
import com.morkva.entities.Project;
import com.morkva.model.dao.ProjectDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by koros on 30.06.2015.
 */
@Service("projectService")
@Transactional
public class ProjectService {

    @Autowired
    ProjectDao projectDAO;

    public List<Project> getProjectsOfCategory(Category category) {
        return projectDAO.getProjectsOfCategory(category);
    }

    public Project getById(Integer projectId) {
        return projectDAO.getById(projectId);
    }

    public void update(Project project) {
        projectDAO.update(project);
    }
}
