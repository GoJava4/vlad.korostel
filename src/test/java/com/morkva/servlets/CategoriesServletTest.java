package com.morkva.servlets;

import org.junit.Test;
import org.mockito.Mockito;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by koros on 08.07.2015.
 */
public class CategoriesServletTest extends Mockito{

    @Test
    public void testDoGet() throws Exception {
        HttpServletResponse response = mock(HttpServletResponse.class);
        HttpServletRequest request = mock(HttpServletRequest.class);


    }
}