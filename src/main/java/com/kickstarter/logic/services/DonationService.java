package com.kickstarter.logic.services;

import com.kickstarter.entitiesRepositories.IRepository;
import com.kickstarter.logic.domain.Donation;
import com.kickstarter.models.DonationModel;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;

public class DonationService implements IDonationService {

    @Resource(name = "donationRepository")
    private IRepository<Donation> donationRepository;

    public List<DonationModel> getDonationsByProjectId(Integer projectId){
        return donationRepository
                .getAll()
                .stream()
                .filter(d -> d.getProject().getId().equals(projectId))
                .map(d -> MapDonationToModel(d, projectId))
                .collect(Collectors.toList());
    }

    private DonationModel MapDonationToModel(Donation donation, Integer projectId){
        DonationModel donationModel = new DonationModel();
        donationModel.setId(donation.getId());
        donationModel.setAmount(donation.getAmount());
        if(donation.getReward() != null){
            donationModel.setRewardId(donation.getReward().getId());
        }
        donationModel.setProjectId(projectId);

        return donationModel;
    }
}
