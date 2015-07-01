package com.morkva.model.dao_v2.templates;

import com.github.springtestdbunit.DbUnitTestExecutionListener;
import com.github.springtestdbunit.annotation.DatabaseOperation;
import com.github.springtestdbunit.annotation.DatabaseSetup;
import com.github.springtestdbunit.annotation.ExpectedDatabase;
import com.github.springtestdbunit.assertion.DatabaseAssertionMode;
import com.morkva.entities.Quote;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;

/**
 * Created by koros on 01.07.2015.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:application-context-test.xml"})
@TestExecutionListeners({
        DependencyInjectionTestExecutionListener.class,
        DbUnitTestExecutionListener.class
})
@DatabaseSetup(value = "classpath:quoteTest/sampleData.xml", type = DatabaseOperation.CLEAN_INSERT)
public class QuoteJDBCTemplateTest {

    @Autowired
    QuoteJDBCTemplate quoteJDBCTemplate;


    @Test
    @ExpectedDatabase(
            value = "classpath:quoteTest/expectedCreateData.xml",
            assertionMode = DatabaseAssertionMode.NON_STRICT,
            table = "quotes")
    public void testCreate() throws Exception {
        Quote quote = new Quote("New Value", "New Author");
        quoteJDBCTemplate.create(quote);
    }

    @Test
    public void testGetById() throws Exception {
        Quote quote = quoteJDBCTemplate.getById(1);
        Assert.assertNotNull(quote);
    }

    @Test
    public void shouldNull_whenIdNotExist() {
        Quote quote = quoteJDBCTemplate.getById(5);
        Assert.assertNull(quote);
    }

    @Test
    public void testUpdate() throws Exception {
        Quote quote = quoteJDBCTemplate.getById(1);
        String newValue = "New value";
        quote.setValue(newValue);
        quoteJDBCTemplate.update(quote);
        quote = quoteJDBCTemplate.getById(1);
        Assert.assertEquals(newValue, quote.getValue());
    }

    @Test
    @ExpectedDatabase(
            value = "classpath:quoteTest/expectedDeleteData.xml",
            assertionMode = DatabaseAssertionMode.NON_STRICT,
            table = "quotes"
    )
    public void testDelete() throws Exception {
        Quote quote = quoteJDBCTemplate.getById(3);
        quoteJDBCTemplate.delete(quote);
    }

    @Test
    public void testGetRandom() throws Exception {
        Quote quote = quoteJDBCTemplate.getRandom();
        Assert.assertNotNull(quote);
    }
}