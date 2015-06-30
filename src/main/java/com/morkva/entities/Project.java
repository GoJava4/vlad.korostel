package com.morkva.entities;

import com.morkva.model.dao_v2.Identified;

/**
 * Created by vladyslav on 02.05.15.
 */
public class Project implements Identified<Integer> {

    private Integer id;
    private Integer categoryId;
    private String name;
    private String shortDescr;
    private int needMoney;
    private int currentMoney;
    private int daysLeft;
    private String history;
    private String urlVideo;

    public Project(
            String name,
            String shortDescr,
            int needMoney,
            int currentMoney,
            int daysLeft,
            String history,
            String urlVideo,
            Integer categoryId
    ) {
        this.name = name;
        this.shortDescr = shortDescr;
        this.needMoney = needMoney;
        this.currentMoney = currentMoney;
        this.daysLeft = daysLeft;
        this.history = history;
        this.urlVideo = urlVideo;
        this.categoryId = categoryId;
    }

    public Project() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }

    public String getShortDescr() {
        return shortDescr;
    }

    public void setShortDescr(String shortDescr) {
        this.shortDescr = shortDescr;
    }

    public int getNeedMoney() {
        return needMoney;
    }

    public void setNeedMoney(int needMoney) {
        this.needMoney = needMoney;
        if (getNeedMoney() < 0) {
            this.needMoney = 0;
        }
    }

    public int getCurrentMoney() {
        return currentMoney;
    }

    public void setCurrentMoney(int currentMoney) {
        this.currentMoney = currentMoney;
    }

    public int getDaysLeft() {
        return daysLeft;
    }

    public void setDaysLeft(int daysLeft) {
        this.daysLeft = daysLeft;
    }

    public String getHistory() {
        return history;
    }

    public void setHistory(String history) {
        this.history = history;
    }

    public String getUrlVideo() {
        return urlVideo;
    }

    public void setUrlVideo(String urlVideo) {
        this.urlVideo = urlVideo;
    }

    @Override
    public Integer getId() {
        return id;
    }

    protected void setId(Integer id) {
        this.id = id;
    }
}
