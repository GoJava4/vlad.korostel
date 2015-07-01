package com.morkva.services;

import com.morkva.entities.Quote;
import com.morkva.model.dao_v2.QuoteDAO;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by koros on 30.06.2015.
 */
public class QuoteService {

    @Autowired
    QuoteDAO quoteDAO;

    public Quote getRandom() {
        return quoteDAO.getRandom();
    }
}
