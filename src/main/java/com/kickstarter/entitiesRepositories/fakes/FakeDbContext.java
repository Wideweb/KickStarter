package com.kickstarter.entitiesRepositories.fakes;


import com.kickstarter.logic.domain.Role;
import com.kickstarter.logic.domain.User;

import java.util.ArrayList;
import java.util.List;

public class FakeDbContext {

    private List entities;

    public FakeDbContext(){
        entities = new ArrayList<>();

        initialize();
    }

    @SuppressWarnings("unchecked")
    public List getEntities(Class requestedClass){
        List result = new ArrayList<>();

        entities.stream()
                .filter(e -> e.getClass().equals(requestedClass))
                .forEach(result::add);

        return result;
    }

    @SuppressWarnings("unchecked")
    public Integer addEntity(Object entity){
        entities.add(entity);
        return entities.indexOf(entity);
    }

    private void initialize(){
        Role adminRole = new Role();
        adminRole.setRoleName(Role.AdminRoleName);

        Role userRole = new Role();
        userRole.setRoleName(Role.UserRoleName);

        entities.add(adminRole);
        entities.add(userRole);


        User admin = new User();
        admin.setUsername("admin");
        admin.setPassword("a665937d971a3ecebf5729222962e4aa8949b5fb"); //123456
        admin.setSalt("[B@2bfbac38");
        admin.setRole(adminRole);

        User appUser = new User();
        appUser.setUsername("user");
        appUser.setPassword("a665937d971a3ecebf5729222962e4aa8949b5fb"); //123456
        appUser.setSalt("[B@2bfbac38");
        appUser.setRole(userRole);

        entities.add(admin);
        entities.add(appUser);
    }
}
