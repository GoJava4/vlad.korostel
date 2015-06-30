package com.morkva.model.dao_v2.templates;

import com.morkva.entities.Quote;
import com.morkva.model.dao_v2.QuoteDAO;
import com.morkva.model.dao_v2.mappers.QuoteMapper;
import com.morkva.model.dao_v2.utils.FixedNullJdbcTemplate;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;

/**
 * Created by koros on 29.06.2015.
 */
public class QuoteJDBCTemplate implements QuoteDAO {

    private DataSource dataSource;
    private JdbcTemplate jdbcTemplate;

    @Override
    public void setDataSource(DataSource ds) {
        this.dataSource = ds;
//        this.jdbcTemplate = new JdbcTemplate(ds);
        this.jdbcTemplate = new FixedNullJdbcTemplate(ds);
    }

    @Override
    public void create(Quote entity) {
        String sql = "INSERT INTO quotes (`value`, `author`) VALUE (?,?);";
        jdbcTemplate.update(sql, entity.getValue(), entity.getAuthor());
        return;
    }

    @Override
    public Quote getById(Integer id) {
        String sql = "SELECT * FROM quotes WHERE id = ?;";

//        //WTF ???? WHY ???? O_o (01.07.2015 - 00:58)
//        try {
//            quote = jdbcTemplate.queryForObject(sql, new Object[]{id}, new QuoteMapper());
//        } catch (EmptyResultDataAccessException ignored) {
//            //TODO Решить эту ***** правильно!
//        }
//        return quote;

        Quote quote = jdbcTemplate.queryForObject(sql, new Object[]{id}, new QuoteMapper());

        return quote;
    }

    @Override
    public void update(Quote entity) {
        String sql = "UPDATE quotes SET `value` = ?, `author` = ? WHERE id = ?;";
        jdbcTemplate.update(sql, entity.getValue(), entity.getAuthor(), entity.getId());
        return;
    }

    @Override
    public void delete(Quote entity) {
        String sql = "DELETE FROM quotes WHERE id = ?;";
        jdbcTemplate.update(sql, entity.getId());
        return;
    }

    public Quote getRandom() {
        String sql = "SELECT * FROM quotes ORDER BY RAND() LIMIT 1;";
        Quote quote = jdbcTemplate.queryForObject(sql, new QuoteMapper());
        return quote;
    }
}
