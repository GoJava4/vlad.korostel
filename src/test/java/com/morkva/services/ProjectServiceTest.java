package com.morkva.services;

import com.github.springtestdbunit.DbUnitTestExecutionListener;
import com.github.springtestdbunit.annotation.DatabaseOperation;
import com.github.springtestdbunit.annotation.DatabaseSetup;
import com.morkva.entities.Category;
import com.morkva.entities.Project;
import com.morkva.model.dao_v2.ProjectDAO;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;

import java.util.Collections;
import java.util.LinkedList;

/**
 * Created by koros on 30.06.2015.
 */
@RunWith(MockitoJUnitRunner.class)
@ContextConfiguration(locations = {"classpath:application-context-test.xml"})
@TestExecutionListeners({
        DependencyInjectionTestExecutionListener.class,
        DbUnitTestExecutionListener.class
})
@DatabaseSetup(value = "classpath:sampleData.xml", type = DatabaseOperation.CLEAN_INSERT)
public class ProjectServiceTest {

    @Mock
    ProjectDAO projectDAO;

    @InjectMocks
    ProjectService projectService;

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testGetById() {
        Project project = new Project();
        Mockito.when(projectDAO.getById(1)).thenReturn(project);
        Assert.assertEquals(project, projectService.getById(1));
    }


    @Test
    public void testGetProjectsOfCategory() {
        Category category = new Category();
        Mockito.when(projectDAO.getProjectsOfCategory(category)).thenReturn(new LinkedList<>(Collections.singletonList(new Project())));
        Assert.assertTrue(projectService.getProjectsOfCategory(category).size() == 1);
    }

    @Test
    public void testUpdate() {
        Project project = new Project();
        projectService.update(project);
        Mockito.verify(projectDAO).update(project);
    }
}
