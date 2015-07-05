package com.morkva.entities;

import com.morkva.model.dao_v2.Identified;

import javax.persistence.Entity;

/**
 * Created by vladyslav on 30.04.15.
 */
@Entity
public class Quote implements Identified<Integer> {

    private Integer id;
    private String value;
    private String author;

    public Quote(String value, String author) {
        this.value = value;
        this.author = author;
    }

    public Quote() {

    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    @Override
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
