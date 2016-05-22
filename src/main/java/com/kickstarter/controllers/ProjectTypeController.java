package com.kickstarter.controllers;

import com.kickstarter.controllers.tools.CustomJsonResult;
import com.kickstarter.logic.services.IProjectService;
import com.kickstarter.logic.services.IProjectTypeService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/projectType")
public class ProjectTypeController {

    @Resource(name = "projectTypeService")
    private IProjectTypeService projectTypeService;

    @RequestMapping(value = "getAll", method = RequestMethod.GET)
    public
    @ResponseBody
    CustomJsonResult GetAll(HttpServletRequest request) {
        return CustomJsonResult.TryGetJsonResult(() ->
                projectTypeService.getAll());
    }
}
