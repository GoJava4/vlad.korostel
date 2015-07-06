package com.morkva.model.dao_v3.hibernate;

import com.github.springtestdbunit.DbUnitTestExecutionListener;
import com.github.springtestdbunit.annotation.DatabaseOperation;
import com.github.springtestdbunit.annotation.DatabaseSetup;
import com.github.springtestdbunit.annotation.ExpectedDatabase;
import com.github.springtestdbunit.assertion.DatabaseAssertionMode;
import com.morkva.entities.PaymentOption;
import com.morkva.entities.Project;
import com.morkva.model.dao_v3.PaymentOptionDao;
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
@DatabaseSetup(value = "classpath:sampleData.xml", type = DatabaseOperation.CLEAN_INSERT)
public class PaymentOptionDaoImplTest {

    @Autowired
    PaymentOptionDao paymentOptionDao;

    @Autowired
    ProjectDao projectDao;

    @Test
    public void testGetPaymentOptionsOfProject() throws Exception {
        Project project = projectDao.getById(1);
        List<PaymentOption> paymentOptionsOfProject = paymentOptionDao.getPaymentOptionsOfProject(project);
        Assert.assertTrue(paymentOptionsOfProject.size() == 3);
    }

    @Test
    @ExpectedDatabase(
            value = "classpath:paymentOptionTest/expectedCreateData.xml",
            assertionMode = DatabaseAssertionMode.NON_STRICT,
            table = "payment_options"
    )
    public void testCreate() throws Exception {
        Project project = projectDao.getById(2);

        PaymentOption paymentOption = new PaymentOption();
        paymentOption.setValue(500);
        paymentOption.setDescription("New Description");
        paymentOption.setProject(project);

        paymentOptionDao.create(paymentOption);
    }

    @Test
    public void testGetById() throws Exception {
        PaymentOption paymentOption = paymentOptionDao.getById(2);
        Assert.assertNotNull(paymentOption);
        Assert.assertEquals(new Integer(200), paymentOption.getValue());
    }

    @Test
    @ExpectedDatabase(
            value = "classpath:paymentOptionTest/expectedUpdateData.xml",
            assertionMode = DatabaseAssertionMode.NON_STRICT,
            table = "payment_options"
    )
    public void testUpdate() throws Exception {
        PaymentOption paymentOption = paymentOptionDao.getById(1);
        paymentOption.setDescription("New Description");
        paymentOptionDao.update(paymentOption);
    }

    @Test
    @ExpectedDatabase(
            value = "classpath:paymentOptionTest/expectedDeleteData.xml",
            assertionMode = DatabaseAssertionMode.NON_STRICT,
            table = "payment_options"
    )
    public void testDelete() throws Exception {
        PaymentOption paymentOption = paymentOptionDao.getById(4);
        paymentOptionDao.delete(paymentOption);
    }
}