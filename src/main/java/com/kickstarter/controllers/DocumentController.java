package com.kickstarter.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import com.kickstarter.logic.domain.User;

@Controller
public class DocumentController {

    @RequestMapping(value = "/downloadPDF", method = RequestMethod.GET)
    public ModelAndView downloadPDF() {
        List<User> listProject = new ArrayList<User>();
        return new ModelAndView("pdfView", "project", listProject);
    }

    @RequestMapping(value = "/downloadExcel", method = RequestMethod.GET)
    public ModelAndView downloadExcel() {
        List<User> listProject = new ArrayList<User>();
        return new ModelAndView("excelView", "projectList", listProject);
    }
}