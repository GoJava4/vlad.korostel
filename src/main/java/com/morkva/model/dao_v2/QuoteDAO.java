package com.morkva.model.dao_v2;

import com.morkva.entities.Quote;

/**
 * Created by koros on 30.06.2015.
 */
public interface QuoteDao extends Dao<Quote> {

    Quote getRandom();
}
