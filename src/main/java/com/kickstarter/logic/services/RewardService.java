package com.kickstarter.logic.services;

import com.kickstarter.entitiesRepositories.IRepository;
import com.kickstarter.logic.domain.Reward;
import com.kickstarter.models.RewardModel;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;

public class RewardService implements IRewardService{

    @Resource(name = "rewardRepository")
    private IRepository<Reward> rewardRepository;

    public List<RewardModel> getRewardsByProjectId(Integer projectId) {
        return rewardRepository
                .getAll()
                .stream()
                .filter(r -> r.getProject().getId().equals(projectId))
                .map(this::MapRewardToModel)
                .collect(Collectors.toList());
    }

    private RewardModel MapRewardToModel(Reward reward){
        RewardModel rewardModel = new RewardModel();
        rewardModel.setId(reward.getId());
        rewardModel.setAmount(reward.getAmount());
        rewardModel.setDescription(reward.getDescription());
        rewardModel.setProjectId(reward.getProject().getId());

        return rewardModel;
    }
}
