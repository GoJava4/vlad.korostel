package com.morkva.model.dao_v2.templates;

import com.morkva.entities.Category;
import com.morkva.model.dao_v2.DAO;
import com.morkva.model.dao_v2.mappers.CategoryMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;
import java.util.List;

/**
 * Created by koros on 29.06.2015.
 */
public class CategoryJDBCTemplate implements DAO<Category> {

    private DataSource dataSource;
    private JdbcTemplate jdbcTemplate;

    @Override
    public void setDataSource(DataSource ds) {
        this.dataSource = ds;
        this.jdbcTemplate = new JdbcTemplate(ds);
    }

    @Override
    public void create(Category entity) {
        String sql = "INSERT INTO categories(name) VALUE (?);";

        jdbcTemplate.update(sql, entity.getName());
        return;
    }

    @Override
    public Category getById(Integer id) {
        String sql = "SELECT * FROM categories WHERE id = ?;";

        Category category = jdbcTemplate.queryForObject(sql, new Object[]{id}, new CategoryMapper());
        return category;
    }

    @Override
    public void update(Category entity) {
        String sql = "UPDATE categories SET `name` = ? WHERE id = ?;";
        jdbcTemplate.update(sql, entity.getName(), entity.getId());
        return;
    }

    @Override
    public void delete(Category entity) {
        String sql = "DELETE FROM categories WHERE id = ?;";
        jdbcTemplate.update(sql, entity.getId());
        return;
    }

    public List<Category> getAll() {
        String sql = "SELECT * FROM categories;";
        List<Category> categories = jdbcTemplate.query(sql, new CategoryMapper());
        return categories;
    }
}
