package com.morkva.entities;

import javax.persistence.*;

/**
 * Created by vladyslav on 29.05.15.
 */
@Entity
@Table(name = "payment_options")
public class PaymentOption {


    @Id
    @GeneratedValue
    private Integer id;
    private String description;
    private Integer value;

    @ManyToOne
    @JoinColumn(name = "project_id")
    private Project project;

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

    public Project getProject() {
        return project;
    }

    public void setProject(Project project) {
        this.project = project;
    }

    public Integer getId() {
        return id;
    }
}
