package com.morkva.services;

import com.morkva.entities.Quote;
import com.morkva.model.dao_v3.QuoteDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by koros on 30.06.2015.
 */
@Service("quoteService")
public class QuoteService {

    @Autowired
    QuoteDao quoteDAO;

    public Quote getRandom() {
        return quoteDAO.getRandom();
    }
}
