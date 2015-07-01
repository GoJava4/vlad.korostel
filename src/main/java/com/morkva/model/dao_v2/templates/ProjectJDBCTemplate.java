package com.morkva.model.dao_v2.templates;

import com.morkva.entities.Category;
import com.morkva.entities.Project;
import com.morkva.model.dao_v2.ProjectDAO;
import com.morkva.model.dao_v2.mappers.ProjectMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

/**
 * Created by koros on 29.06.2015.
 */
public class ProjectJDBCTemplate implements ProjectDAO {

    private JdbcTemplate jdbcTemplate;

    @Override
    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public void create(Project entity) {
        String sql = "INSERT INTO projects " +
                "(`name`, short_description, current_money, need_money, days_left, history, video_url, category_id) " +
                "VALUE (?, ?, ?, ?, ?, ?, ?, ?);";

        Object[] args = new Object[] {
                entity.getName(),
                entity.getShortDescr(),
                entity.getCurrentMoney(),
                entity.getNeedMoney(),
                entity.getDaysLeft(),
                entity.getHistory(),
                entity.getUrlVideo(),
                entity.getCategoryId()
        };

        jdbcTemplate.update(sql, args);
        return;
    }

    @Override
    public Project getById(Integer id) {
        String sql = "SELECT * FROM projects WHERE id = ?;";
        Project project = jdbcTemplate.queryForObject(sql, new Object[]{id}, new ProjectMapper());
        return project;
    }

    @Override
    public void update(Project entity) {
        String sql = "UPDATE projects SET `name`=?, short_description=?, current_money=?, need_money=?, days_left=?, history=?, video_url=?, category_id=? " +
                "WHERE id = ?;";

        Object[] args = new Object[] {
                entity.getName(),
                entity.getShortDescr(),
                entity.getCurrentMoney(),
                entity.getNeedMoney(),
                entity.getDaysLeft(),
                entity.getHistory(),
                entity.getUrlVideo(),
                entity.getCategoryId(),
                entity.getId()
        };

        jdbcTemplate.update(sql, args);
        return;
    }

    @Override
    public void delete(Project entity) {
        String sql = "DELETE FROM projects WHERE id = ?;";
        jdbcTemplate.update(sql, entity.getId());
        return;
    }

    @Override
    public List<Project> getProjectsOfCategory(Category category) {
        String sql = "SELECT * FROM projects WHERE category_id = ?;";
        List<Project> projects = jdbcTemplate.query(sql, new Object[]{category.getId()}, new ProjectMapper());
        return projects;
    }
}
