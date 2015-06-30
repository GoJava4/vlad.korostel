package com.morkva.model.dao_v2;

import com.morkva.entities.Category;
import com.morkva.entities.Project;

import java.util.List;

/**
 * Created by koros on 30.06.2015.
 */
public interface ProjectDAO extends DAO<Project> {



    List<Project> getProjectsOfCategory(Category category);
}
