package com.openjava.mvc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ReportController {

    @RequestMapping("/report/output")
    public String input(Model model) {
        //model.addAttribute("user",user);
        return "/report/output";
    }
}
