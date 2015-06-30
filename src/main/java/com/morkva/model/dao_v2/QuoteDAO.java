package com.morkva.model.dao_v2;

import com.morkva.entities.Quote;

/**
 * Created by koros on 30.06.2015.
 */
public interface QuoteDAO extends DAO<Quote> {

    Quote getRandom();
}
