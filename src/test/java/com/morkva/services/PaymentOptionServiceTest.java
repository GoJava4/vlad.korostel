package com.morkva.services;

import com.morkva.entities.PaymentOption;
import com.morkva.entities.Project;
import com.morkva.model.dao_v3.PaymentOptionDao;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by koros on 02.07.2015.
 */
public class PaymentOptionServiceTest {
    @Mock
    PaymentOptionDao paymentOptionDao;

    @InjectMocks
    PaymentOptionService paymentOptionService;

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testGetPaymentOptionsOfProject() throws Exception {
        Project project = new Project();
        Mockito.when(paymentOptionDao.getPaymentOptionsOfProject(project)).thenReturn(new LinkedList<PaymentOption>());
        List<PaymentOption> paymentOptionsOfProject = paymentOptionService.getPaymentOptionsOfProject(project);
        Assert.assertEquals(0, paymentOptionsOfProject.size());
    }
}
