package com.morkva.services;

import com.morkva.entities.Category;
import com.morkva.entities.Project;
import com.morkva.model.dao_v2.ProjectDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by koros on 30.06.2015.
 */
@Service("projectService")
public class ProjectService {

    @Autowired
    ProjectDAO projectDAO;


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
