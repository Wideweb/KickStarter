package com.kickstarter.logic.services;

import com.kickstarter.models.DonationModel;

import java.util.List;

public interface IDonationService {
    List<DonationModel> getDonationsByProjectId(Integer projectId);
}
