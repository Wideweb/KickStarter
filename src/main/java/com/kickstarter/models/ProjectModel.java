package com.kickstarter.models;

import java.util.Calendar;
import java.util.Date;

public class ProjectModel {
    private Integer id;
    private String name;
    private String owner;
    private Integer countryId;
    private Integer projectTypeId;
    private String description;
    private Integer daysToGo;
    private Integer backers;
    private Integer pledged;
    private Integer fundingGoal;
    private Integer fundingDuration;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getCountryId() {
        return countryId;
    }

    public void setCountryId(Integer countryId) {
        this.countryId = countryId;
    }

    public Integer getProjectTypeId() {
        return projectTypeId;
    }

    public void setProjectTypeId(Integer projectTypeId) {
        this.projectTypeId = projectTypeId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getFundingGoal() {
        return fundingGoal;
    }

    public void setFundingGoal(Integer fundingGoal) {
        this.fundingGoal = fundingGoal;
    }

    public Integer getFundingDuration() {
        return fundingDuration;
    }

    public void setFundingDuration(Integer fundingDuration) {
        this.fundingDuration = fundingDuration;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public Integer getDaysToGo() {
        return daysToGo;
    }

    public void setDaysToGo(Integer daysToGo) {
        this.daysToGo = daysToGo;
    }

    public Integer getBackers() {
        return backers;
    }

    public void setBackers(Integer backers) {
        this.backers = backers;
    }

    public Integer getPledged() {
        return pledged;
    }

    public void setPledged(Integer pledged) {
        this.pledged = pledged;
    }
}
