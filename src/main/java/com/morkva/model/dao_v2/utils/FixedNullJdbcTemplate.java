package com.morkva.model.dao_v2.utils;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.RowMapperResultSetExtractor;

import javax.sql.DataSource;
import java.util.List;

/**
 * Created by koros on 01.07.2015.
 */
public class FixedNullJdbcTemplate extends JdbcTemplate {

    public FixedNullJdbcTemplate(DataSource dataSource) {
        super(dataSource);
    }

    @Override
    public <T> T queryForObject(String sql, Object[] args, RowMapper<T> rowMapper) throws DataAccessException {
        List query = (List)super.query(sql, args, (new RowMapperResultSetExtractor(rowMapper, 1)));
        return (T) FixedNullDataAccessUtils.requiredSingleResult(query);
    }
}
