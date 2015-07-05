package com.morkva.model.dao_v3;

import com.morkva.entities.Category;
import com.morkva.entities.Project;

import java.util.List;

/**
 * Created by koros on 06.07.2015.
 */
public interface ProjectDao extends Dao<Project> {

    List getProjectsOfCategory(Category category);

}
