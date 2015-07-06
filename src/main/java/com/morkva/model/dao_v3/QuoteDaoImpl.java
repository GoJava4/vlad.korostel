package com.morkva.model.dao_v3;

import com.morkva.entities.Quote;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by koros on 05.07.2015.
 */
@Repository("quoteDao")
@Transactional
public class QuoteDaoImpl extends AbstractDao<Quote> implements QuoteDao {


    public QuoteDaoImpl() {
        super(Quote.class);
    }

    @Override
    public Quote getRandom() {
        Session currentSession = sessionFactory.getCurrentSession();
        return (Quote) currentSession.createSQLQuery("SELECT * FROM quotes ORDER BY RAND() LIMIT 1").addEntity(Quote.class).uniqueResult();
    }
}
