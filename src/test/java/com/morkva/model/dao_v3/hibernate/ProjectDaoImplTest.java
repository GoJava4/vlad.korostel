package com.morkva.model.dao_v3.hibernate;

import com.github.springtestdbunit.DbUnitTestExecutionListener;
import com.github.springtestdbunit.annotation.DatabaseOperation;
import com.github.springtestdbunit.annotation.DatabaseSetup;
import com.github.springtestdbunit.annotation.ExpectedDatabase;
import com.github.springtestdbunit.assertion.DatabaseAssertionMode;
import com.morkva.entities.Category;
import com.morkva.entities.Project;
import com.morkva.model.dao_v3.CategoryDao;
import com.morkva.model.dao_v3.ProjectDao;
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
 * Created by koros on 06.07.2015.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:application-context-test.xml"})
@TestExecutionListeners({
        DependencyInjectionTestExecutionListener.class,
        DbUnitTestExecutionListener.class
})
@DatabaseSetup(value = "classpath:projectTest/sampleData.xml", type = DatabaseOperation.CLEAN_INSERT)
public class ProjectDaoImplTest {

    @Autowired
    ProjectDao projectDao;

    @Autowired
    CategoryDao categoryDao;

    @Test
    @ExpectedDatabase(
            value = "classpath:projectTest/expectedCreateData.xml",
            assertionMode = DatabaseAssertionMode.NON_STRICT,
            table = "projects"
    )
    public void testCreate() throws Exception {
        Category category = categoryDao.getById(1);

        Project project = new Project();
        project.setName("New Name");
        project.setShortDescr("New Short Description");
        project.setCurrentMoney(1000);
        project.setNeedMoney(16000);
        project.setDaysLeft(45);
        project.setHistory("New History");
        project.setUrlVideo("New Video Url");
        project.setCategory(category);
        projectDao.create(project);
    }
    @Test
    public void testGetById() throws Exception {
        Project project = projectDao.getById(1);
        Assert.assertNotNull(project);
        Assert.assertEquals("name 1", project.getName());
    }

    @Test
    @ExpectedDatabase(
            value = "classpath:projectTest/expectedUpdateData.xml",
            assertionMode = DatabaseAssertionMode.NON_STRICT,
            table = "projects"
    )
    public void testUpdate() throws Exception {
        Project project = projectDao.getById(4);
        project.setName("Updated Name");
        projectDao.update(project);
    }

    @Test
    @ExpectedDatabase(
            value = "classpath:projectTest/expectedDeleteData.xml",
            assertionMode = DatabaseAssertionMode.NON_STRICT,
            table = "projects"
    )
    public void testDelete() throws Exception {
        Project project = projectDao.getById(1);
        projectDao.delete(project);
    }

    @Test
    public void testGetProjectsOfCategory() throws Exception {
        Category category = categoryDao.getById(1);
        List<Project> projectsOfCategory = projectDao.getProjectsOfCategory(category);
        Assert.assertTrue(projectsOfCategory.size() == 2);
    }
}