package com.morkva.model.dao_v2.templates;

import com.github.springtestdbunit.DbUnitTestExecutionListener;
import com.github.springtestdbunit.annotation.DatabaseOperation;
import com.github.springtestdbunit.annotation.DatabaseSetup;
import com.github.springtestdbunit.annotation.ExpectedDatabase;
import com.github.springtestdbunit.assertion.DatabaseAssertionMode;
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
 * Created by koros on 02.07.2015.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:application-context-test.xml"})
@TestExecutionListeners({
        DependencyInjectionTestExecutionListener.class,
        DbUnitTestExecutionListener.class
})
@DatabaseSetup(value = "classpath:projectTest/sampleData.xml", type = DatabaseOperation.CLEAN_INSERT)
public class ProjectJDBCTemplateTest {

    @Autowired
    ProjectJDBCTemplate projectJDBCTemplate;

    @Autowired
    CategoryJDBCTemplate categoryJDBCTemplate;

    @Test
    @ExpectedDatabase(
            value = "classpath:projectTest/expectedCreateData.xml",
            assertionMode = DatabaseAssertionMode.NON_STRICT,
            table = "projects"
    )
    public void testCreate() throws Exception {
        Project project = new Project();
        project.setCategoryId(1);
        project.setName("New Name");
        project.setShortDescr("New Short Description");
        project.setCurrentMoney(1000);
        project.setNeedMoney(16000);
        project.setDaysLeft(45);
        project.setHistory("New History");
        project.setUrlVideo("New Video Url");
        projectJDBCTemplate.create(project);
    }

    @Test
    public void testGetById() throws Exception {
        Project project = projectJDBCTemplate.getById(1);
        Assert.assertEquals("name 1", project.getName());
    }

    @Test
    public void testUpdate() throws Exception {
        Project project = projectJDBCTemplate.getById(1);
        String newName = "New Name";
        project.setName(newName);
        projectJDBCTemplate.update(project);
        project = projectJDBCTemplate.getById(1);
        Assert.assertEquals(new Integer(1), project.getId());
        Assert.assertEquals(newName, project.getName());
    }

    @Test
    @ExpectedDatabase(
            value = "classpath:projectTest/expectedDeleteData.xml",
            assertionMode = DatabaseAssertionMode.NON_STRICT,
            table = "projects"
    )
    public void testDelete() throws Exception {
        Project project = projectJDBCTemplate.getById(1);
        projectJDBCTemplate.delete(project);
    }

    @Test
    public void testGetProjectsOfCategory() throws Exception {
        Category category = categoryJDBCTemplate.getById(1);
        List<Project> projectsOfCategory = projectJDBCTemplate.getProjectsOfCategory(category);
        Assert.assertEquals(2, projectsOfCategory.size());
    }
}