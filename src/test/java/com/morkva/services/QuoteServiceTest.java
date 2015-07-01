package com.morkva.services;

import com.morkva.model.dao_v2.QuoteDAO;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;

/**
 * Created by koros on 30.06.2015.
 */
@RunWith(MockitoJUnitRunner.class)
@ContextConfiguration(locations = {"classpath:application-context-test.xml"})
@TestExecutionListeners({
        DependencyInjectionTestExecutionListener.class,
})
public class QuoteServiceTest {

    @Mock
    QuoteDAO quoteDAO;

    @InjectMocks
    QuoteService quoteService = new QuoteService();

    @Test
    public void testGetRandom() throws Exception {
        Mockito.when(quoteDAO.getRandom()).thenReturn(null);

        Assert.assertEquals(null, quoteService.getRandom());
    }
}