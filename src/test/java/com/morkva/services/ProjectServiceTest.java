package com.morkva.services;

import com.github.springtestdbunit.DbUnitTestExecutionListener;
import com.github.springtestdbunit.annotation.DatabaseOperation;
import com.github.springtestdbunit.annotation.DatabaseSetup;
import com.morkva.entities.Category;
import com.morkva.entities.Project;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;

import java.util.List;

/**
 * Created by koros on 30.06.2015.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:application-context-test.xml"})
@TestExecutionListeners({
        DependencyInjectionTestExecutionListener.class,
        DbUnitTestExecutionListener.class
})
@DatabaseSetup(value = "classpath:sampleData.xml", type = DatabaseOperation.CLEAN_INSERT)
public class ProjectServiceTest {

    @Autowired
    ProjectService projectService;

    @Autowired
    CategoryService categoryService;

    @Test
    public void testGetById() {
        Project project = projectService.getById(1);
        Assert.assertEquals(new Integer(1), project.getId());
        Assert.assertEquals("name 1", project.getName());
    }


    @Test
    public void testGetProjectsOfCategory() throws Exception {
        Category category = categoryService.getById(1);
        List<Project> projects = projectService.getProjectsOfCategory(category);
        Assert.assertEquals(2, projects.size());
    }

    @Test
    public void testUpdate() throws Exception {
        Project project = projectService.getById(1);
        project.setName("New name");
        projectService.update(project);
        project = projectService.getById(1);
        Assert.assertEquals("New name", project.getName());
    }
}
