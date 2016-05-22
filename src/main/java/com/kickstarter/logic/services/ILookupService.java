package com.kickstarter.logic.services;

import com.kickstarter.models.LookupModel;

import java.util.List;

public interface ILookupService {
    List<LookupModel> getProjectTypeLookups();
    List<LookupModel> getCountriesLookups();
}
