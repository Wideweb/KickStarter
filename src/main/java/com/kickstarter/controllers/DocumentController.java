package com.kickstarter.controllers;

import java.util.ArrayList;
import java.util.List;

import com.kickstarter.logic.domain.Project;
import com.kickstarter.logic.services.IProjectService;
import com.kickstarter.models.ProjectModel;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import com.kickstarter.logic.domain.User;

import javax.annotation.Resource;

@Controller
public class DocumentController {

    @Resource(name = "projectService")
    private IProjectService projectService;

    @RequestMapping(value = "document/downloadProjectPDF/{projectId}", method = RequestMethod.GET)
    public ModelAndView downloadPDF(@PathVariable(value="projectId") Integer id) {
        ProjectModel project = projectService.get(id);
        return new ModelAndView("pdfView", "project", project);
    }

    @RequestMapping(value = "document/downloadProjectsStatistic", method = RequestMethod.GET)
    public ModelAndView downloadExcel() {
        List<ProjectModel> listProject = projectService.getFinished();
        return new ModelAndView("excelView", "projectList", listProject);
    }

    @RequestMapping(value = "document/downloadApprovingStatistic", method = RequestMethod.GET)
    public ModelAndView downloadApprovingStatisticExcel() {
        List<ProjectModel> listProject = projectService.getAll();
        return new ModelAndView("approvingStatisticExcelView", "projectList", listProject);
    }
}