package com.kickstarter.logic.services;

import com.kickstarter.models.RewardModel;

import java.util.List;

public interface IRewardService {
    List<RewardModel> getRewardsByProjectId(Integer projectId);
}
