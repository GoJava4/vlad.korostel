package com.morkva.services;

import com.github.springtestdbunit.DbUnitTestExecutionListener;
import com.github.springtestdbunit.annotation.DatabaseOperation;
import com.github.springtestdbunit.annotation.DatabaseSetup;
import com.morkva.entities.Category;
import com.morkva.model.dao_v2.CategoryDAO;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;

import java.util.LinkedList;

import static org.mockito.Mockito.*;

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
public class CategoryServiceTest {

    @Mock
    CategoryDAO categoryDAO;

    @InjectMocks
    CategoryService categoryService;

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testGetAll() throws Exception {
        when(categoryDAO.getAll()).thenReturn(new LinkedList<Category>());
        Assert.assertTrue(categoryService.getAll().size() == 0);
    }

    @Test
    public void testGetById() throws Exception {
        Category category = new Category("Mocked category");
        when(categoryDAO.getById(1)).thenReturn(category);
        Assert.assertEquals(category, categoryService.getById(1));
    }
}