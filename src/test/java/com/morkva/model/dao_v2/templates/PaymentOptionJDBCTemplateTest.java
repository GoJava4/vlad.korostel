package com.morkva.model.dao_v2.templates;

import com.github.springtestdbunit.DbUnitTestExecutionListener;
import com.github.springtestdbunit.annotation.DatabaseOperation;
import com.github.springtestdbunit.annotation.DatabaseSetup;
import com.github.springtestdbunit.annotation.ExpectedDatabase;
import com.github.springtestdbunit.assertion.DatabaseAssertionMode;
import com.morkva.entities.PaymentOption;
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
@DatabaseSetup(value = "classpath:paymentOptionTest/sampleData.xml", type = DatabaseOperation.CLEAN_INSERT)
public class PaymentOptionJDBCTemplateTest {

    @Autowired
    PaymentOptionJDBCTemplate paymentOptionJDBCTemplate;

    @Autowired
    ProjectJDBCTemplate projectJDBCTemplate;

    @Test
    @ExpectedDatabase(
            value = "classpath:paymentOptionTest/expectedCreateData.xml",
            assertionMode = DatabaseAssertionMode.NON_STRICT,
            table = "payment_options"
    )
    public void testCreate() throws Exception{
        PaymentOption paymentOption = new PaymentOption();
        paymentOption.setValue(500);
        paymentOption.setDescription("New Description");
        paymentOption.setProjectId(2);
        paymentOptionJDBCTemplate.create(paymentOption);
    }

    @Test
    public void testGetById() throws Exception {
        PaymentOption paymentOption = paymentOptionJDBCTemplate.getById(1);
        Assert.assertNotNull(paymentOption);
    }

    @Test
    @ExpectedDatabase(
            value = "classpath:paymentOptionTest/expectedUpdateData.xml",
            assertionMode = DatabaseAssertionMode.NON_STRICT,
            table = "payment_options"
    )
    public void testUpdate() throws Exception {
        PaymentOption paymentOption = paymentOptionJDBCTemplate.getById(1);
        paymentOption.setDescription("New Description");
        paymentOptionJDBCTemplate.update(paymentOption);
    }


    @Test
    @ExpectedDatabase(
            value = "classpath:paymentOptionTest/expectedDeleteData.xml",
            assertionMode = DatabaseAssertionMode.NON_STRICT,
            table = "payment_options"
    )
    public void testDelete() throws Exception {
        PaymentOption paymentOption = paymentOptionJDBCTemplate.getById(4);
        paymentOptionJDBCTemplate.delete(paymentOption);
    }

    @Test
    public void testGetPaymentOptionsOfProject() throws Exception {
        Project project = projectJDBCTemplate.getById(1);
        List<PaymentOption> paymentOptions = paymentOptionJDBCTemplate.getPaymentOptionsOfProject(project);
        Assert.assertEquals(3, paymentOptions.size());
    }
}
