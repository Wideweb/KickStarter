package com.kickstarter.logic.domain;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Project extends EntityBase {
    private String name;
    private User owner;
    private Country country;
    private ProjectType projectType;
    private String description;
    private Date startDate;
    private Date endDate;
    private Integer fundingGoal;
    private Integer fundingDuration;
    private List<Reward> rewards;
    private Boolean isApproved;

    public Project(){
        rewards = new ArrayList<Reward>();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public User getOwner() {
        return owner;
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }

    public Country getCountry() {
        return country;
    }

    public void setCountry(Country country) {
        this.country = country;
    }

    public ProjectType getProjectType() {
        return projectType;
    }

    public void setProjectType(ProjectType projectType) {
        this.projectType = projectType;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
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

    /*
    public Integer getDaysToGo() {
        long diff = new Date().getTime() - getStartDate().getTime();
        int daysToGo = getFundingDuration() - (int)diff / (24 * 60 * 60 * 1000);
        return daysToGo;
    }
    */
    public List<Reward> getRewards() {
        return rewards;
    }

    public void setRewards(List<Reward> rewards) {
        this.rewards = rewards;
    }

    public Boolean getApproved() {
        return isApproved;
    }

    public void setApproved(Boolean approved) {
        isApproved = approved;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }
}
