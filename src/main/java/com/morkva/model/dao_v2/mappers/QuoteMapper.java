package com.morkva.model.dao_v2.mappers;

import com.morkva.entities.Quote;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by koros on 29.06.2015.
 */
public class QuoteMapper implements RowMapper<Quote> {

    private class PersistedQuote extends Quote {
        @Override
        public void setId(Integer id) {
            super.setId(id);
        }
    }

    @Override
    public Quote mapRow(ResultSet resultSet, int i) throws SQLException {
        PersistedQuote quote = new PersistedQuote();
        quote.setId(resultSet.getInt("id"));
        quote.setValue(resultSet.getString("value"));
        quote.setAuthor(resultSet.getString("author"));
        return quote;
    }
}
