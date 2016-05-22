package com.kickstarter.logic.services;

import com.kickstarter.entitiesRepositories.IRepository;
import com.kickstarter.logic.domain.Country;
import com.kickstarter.logic.domain.ProjectType;
import com.kickstarter.models.LookupModel;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

public class LookupService implements ILookupService {
    @Resource(name = "projectTypeRepository")
    private IRepository<ProjectType> projectTypeRepository;

    @Resource(name = "countryRepository")
    private IRepository<Country> countryRepository;

    public List<LookupModel> getProjectTypeLookups(){
        List<LookupModel> lookups = new ArrayList<>();
        List<ProjectType> projectTypes = projectTypeRepository.getAll();

        for(int i = 0; i < projectTypes.size(); i++){
            LookupModel lookup = new LookupModel();
            ProjectType projectType = projectTypes.get(i);

            lookup.setId(projectType.getId());
            lookup.setText(projectType.getName());

            lookups.add(lookup);
        }
        return lookups;
    }

    public List<LookupModel> getCountriesLookups(){
        List<LookupModel> lookups = new ArrayList<>();
        List<Country> countries = countryRepository.getAll();

        for(int i = 0; i < countries.size(); i++){
            LookupModel lookup = new LookupModel();
            Country country = countries.get(i);

            lookup.setId(country.getId());
            lookup.setText(country.getName());

            lookups.add(lookup);
        }
        return lookups;
    }
}
