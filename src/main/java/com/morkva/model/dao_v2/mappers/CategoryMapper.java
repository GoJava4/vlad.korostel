package com.morkva.model.dao_v2.mappers;

import com.morkva.entities.Category;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by koros on 29.06.2015.
 */
public class CategoryMapper implements RowMapper<Category> {

    private class PersistedCategory extends Category {
        @Override
        public void setId(Integer id) {
            super.setId(id);
        }
    }
    @Override
    public Category mapRow(ResultSet resultSet, int i) throws SQLException {
        PersistedCategory category = new PersistedCategory();
        category.setId(resultSet.getInt("id"));
        category.setName(resultSet.getString("name"));
        return category;
    }
}
