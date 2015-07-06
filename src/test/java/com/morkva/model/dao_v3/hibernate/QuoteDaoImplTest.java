package com.morkva.model.dao_v3.hibernate;

import com.github.springtestdbunit.DbUnitTestExecutionListener;
import com.github.springtestdbunit.annotation.DatabaseOperation;
import com.github.springtestdbunit.annotation.DatabaseSetup;
import com.github.springtestdbunit.annotation.ExpectedDatabase;
import com.github.springtestdbunit.assertion.DatabaseAssertionMode;
import com.morkva.entities.Quote;
import com.morkva.model.dao_v3.QuoteDao;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;

/**
 * Created by koros on 05.07.2015.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:application-context-test.xml"})
@TestExecutionListeners({
        DependencyInjectionTestExecutionListener.class,
        DbUnitTestExecutionListener.class
})
@DatabaseSetup(value = "classpath:sampleData.xml", type = DatabaseOperation.CLEAN_INSERT)
public class QuoteDaoImplTest {

    @Autowired
    QuoteDao quoteDao;

    @Test
    public void testGetRandom() throws Exception {
        Quote random = quoteDao.getRandom();
        Assert.assertNotNull(random);
    }


    @Test
    @ExpectedDatabase(
            value = "classpath:quoteTest/expectedCreateData.xml",
            assertionMode = DatabaseAssertionMode.NON_STRICT,
            table = "quotes"
    )
    public void testCreate() throws Exception {
        Quote quote = new Quote("New Value", "New Author");
        quoteDao.create(quote);
    }

    @Test
    public void testGetById() throws Exception {
        Quote byId = quoteDao.getById(1);
        Assert.assertNotNull(byId);
        Assert.assertEquals("value 1", byId.getValue());
        Assert.assertEquals("author 1", byId.getAuthor());
    }

    @Test
    @ExpectedDatabase(
            value = "classpath:quoteTest/expectedUpdateData.xml",
            assertionMode = DatabaseAssertionMode.NON_STRICT,
            table = "quotes"
    )
    public void testUpdate() throws Exception {
        Quote quoteToUpdate = quoteDao.getById(3);
        quoteToUpdate.setValue("Updated Value");
        quoteToUpdate.setAuthor("Updated Author");
        quoteDao.update(quoteToUpdate);
    }

    @Test
    @ExpectedDatabase(
            value = "classpath:quoteTest/expectedDeleteData.xml",
            assertionMode = DatabaseAssertionMode.NON_STRICT,
            table = "quotes"
    )
    public void testDelete() throws Exception {
        Quote quoteToDelete = quoteDao.getById(3);
        quoteDao.delete(quoteToDelete);
    }
}