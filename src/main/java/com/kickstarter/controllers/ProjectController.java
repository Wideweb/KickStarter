package com.kickstarter.controllers;

import com.kickstarter.controllers.tools.CustomJsonResult;
import com.kickstarter.logic.services.IProjectService;
import com.kickstarter.models.DonationModel;
import com.kickstarter.models.ProjectModel;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/project")
public class ProjectController {

    @Resource(name = "projectService")
    private IProjectService projectService;

    @RequestMapping(value = "save", method = RequestMethod.POST)
    public
    @ResponseBody
    CustomJsonResult Save(@RequestBody ProjectModel model, HttpServletRequest request) {
        return CustomJsonResult.TryGetJsonResult(() ->
                projectService.save(model, request.getUserPrincipal().getName()));
    }

    @RequestMapping(value = "get", method = RequestMethod.GET)
    public
    @ResponseBody
    CustomJsonResult Get(@RequestParam Integer projectId, HttpServletRequest request) {
        return CustomJsonResult.TryGetJsonResult(() -> projectService.get(projectId));
    }

    @RequestMapping(value = "getUserProjects", method = RequestMethod.GET)
    public
    @ResponseBody
    CustomJsonResult GetUserProjects(HttpServletRequest request) {
        return CustomJsonResult.TryGetJsonResult(() ->
                projectService.getUserProjects(request.getUserPrincipal().getName()));
    }

    @RequestMapping(value = "getAll", method = RequestMethod.GET)
    public
    @ResponseBody
    CustomJsonResult GetAll(HttpServletRequest request) {
        return CustomJsonResult.TryGetJsonResult(() ->
                projectService.getAll());
    }

    @RequestMapping(value = "donate", method = RequestMethod.POST)
    public
    @ResponseBody
    CustomJsonResult DonateToProject(@RequestBody DonationModel model, HttpServletRequest request) {
        return CustomJsonResult.TryExecute(() ->
                projectService.donateToProject(model, request.getUserPrincipal().getName()));
    }

    @RequestMapping(value = "getAll/{categoryId}", method = RequestMethod.GET)
    public
    @ResponseBody
    CustomJsonResult GetAllByCategory(HttpServletRequest request, @PathVariable(value="categoryId") Integer id) {
        return CustomJsonResult.TryGetJsonResult(() ->
                projectService.getAllByCategory(id));
    }
}
