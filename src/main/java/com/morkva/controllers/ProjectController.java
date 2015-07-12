package com.morkva.controllers;

import com.morkva.entities.Project;
import com.morkva.services.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Created by koros on 27.06.2015.
 */
@Controller
@RequestMapping("/project")
public class ProjectController {

    @Autowired
    ProjectService projectService;

    @RequestMapping(method = RequestMethod.GET)
    public String showProject(ModelMap modelMap, @RequestParam int projectId) {
        Project project = projectService.getById(projectId);
        modelMap.addAttribute("project", project);

        return "project";
    }

    @RequestMapping(method = RequestMethod.POST)
    public String donateToProject(ModelMap modelMap, @RequestParam int donateCount, @RequestParam int projectId) {

        Project project = projectService.getById(projectId);

        donate(project, donateCount);

        modelMap.addAttribute("project", project);
        return "project";
    }

    private void donate(Project project, Integer donateCount) {
        if (donateCount != null) {
            project.setCurrentMoney(project.getCurrentMoney() + donateCount);
            projectService.update(project);
        }
    }
}
