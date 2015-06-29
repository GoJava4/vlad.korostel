package com.morkva.model.dao_v2.mappers;

import com.morkva.entities.Project;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by koros on 29.06.2015.
 */
public class ProjectMapper implements RowMapper<Project> {

    private class PersistedProject extends Project {
        @Override
        public void setId(Integer id) {
            super.setId(id);
        }
    }
    @Override
    public Project mapRow(ResultSet resultSet, int i) throws SQLException {
        PersistedProject project = new PersistedProject();
        project.setId(resultSet.getInt("id"));
        project.setCategoryId(resultSet.getInt("category_id"));
        project.setName(resultSet.getString("name"));
        project.setShortDescr(resultSet.getString("short_description"));
        project.setCurrentMoney(resultSet.getInt("current_money"));
        project.setNeedMoney(resultSet.getInt("need_money"));
        project.setDaysLeft(resultSet.getInt("days_left"));
        project.setHistory(resultSet.getString("history"));
        project.setUrlVideo(resultSet.getString("video_url"));
        return project;
    }
}
