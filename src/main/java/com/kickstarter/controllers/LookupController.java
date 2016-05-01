package com.kickstarter.controllers;

import com.kickstarter.controllers.tools.CustomJsonResult;
import com.kickstarter.logic.domain.User;
import com.kickstarter.logic.services.ILookupService;
import com.kickstarter.models.UserRegistrationModel;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/lookups")
public class LookupController {

    @Resource(name = "lookupService")
    private ILookupService lookupService;

    @RequestMapping(value = "/countries", method = RequestMethod.GET)
    public @ResponseBody CustomJsonResult Countries() {
        return CustomJsonResult.TryGetJsonResult(() -> lookupService.getCountriesLookups());
    }

    @RequestMapping(value = "/projectTypes", method = RequestMethod.GET)
    public @ResponseBody CustomJsonResult ProjectTypes() {
        return CustomJsonResult.TryGetJsonResult(() -> lookupService.getProjectTypeLookups());
    }
}
