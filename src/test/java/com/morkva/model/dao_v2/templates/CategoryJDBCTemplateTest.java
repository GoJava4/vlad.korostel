package com.morkva.model.dao_v2.templates;

import com.github.springtestdbunit.DbUnitTestExecutionListener;
import com.github.springtestdbunit.annotation.DatabaseOperation;
import com.github.springtestdbunit.annotation.DatabaseSetup;
import com.github.springtestdbunit.annotation.ExpectedDatabase;
import com.github.springtestdbunit.assertion.DatabaseAssertionMode;
import com.morkva.entities.Category;
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
 * Created by koros on 01.07.2015.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:application-context-test.xml"})
@TestExecutionListeners({
        DependencyInjectionTestExecutionListener.class,
        DbUnitTestExecutionListener.class
})
@DatabaseSetup(value = "classpath:categoryTest/sampleData.xml", type = DatabaseOperation.CLEAN_INSERT)
public class CategoryJDBCTemplateTest {

    @Autowired
    CategoryJDBCTemplate categoryJDBCTemplate;

    @Test
    @ExpectedDatabase(
            value = "classpath:categoryTest/expectedCreateData.xml",
            assertionMode = DatabaseAssertionMode.NON_STRICT,
            table = "categories")
    public void testCreate() throws Exception {
        Category category = new Category("New Name");
        categoryJDBCTemplate.create(category);
    }

    @Test
    public void testGetById() throws Exception {
        Category category = categoryJDBCTemplate.getById(1);
        Assert.assertEquals("name 1", category.getName());
    }

    @Test
    public void testUpdate() throws Exception {
        Category category = categoryJDBCTemplate.getById(2);
        String newName = "New Name";
        category.setName(newName);
        categoryJDBCTemplate.update(category);
        category = categoryJDBCTemplate.getById(2);
        Assert.assertEquals(newName, category.getName());
    }

    @Test
    public void testDelete() throws Exception {
        List<Category> all = categoryJDBCTemplate.getAll();
        int oldSize = all.size();

        Category category = categoryJDBCTemplate.getById(3);
        categoryJDBCTemplate.delete(category);

        all = categoryJDBCTemplate.getAll();
        int newSize = all.size();

        Assert.assertEquals(oldSize - 1, newSize);
    }

    @Test
    public void testGetAll() throws Exception {
        List<Category> all = categoryJDBCTemplate.getAll();
        Assert.assertEquals(3, all.size());
    }
}