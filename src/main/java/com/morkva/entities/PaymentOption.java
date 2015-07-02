package com.morkva.entities;

import com.morkva.model.dao_v2.Identified;

/**
 * Created by vladyslav on 29.05.15.
 */
public class PaymentOption implements Identified<Integer> {

    private Integer id;
    private String description;
    private Integer value;
    private Integer projectId;

    public PaymentOption() {
    }

    public String getDescription() {
        return description;
    }

    public Integer getValue() {
        return value;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setValue(Integer value) {
        this.value = value;
    }

    public Integer getProjectId() {
        return projectId;
    }

    public void setProjectId(Integer projectId) {
        this.projectId = projectId;
    }

    @Override
    public Integer getId() {
        return id;
    }

    protected void setId(Integer id) {
        this.id = id;
    }
}
