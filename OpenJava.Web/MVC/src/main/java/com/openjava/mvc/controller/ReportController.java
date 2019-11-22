package com.openjava.mvc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ReportController {

    @RequestMapping("/Report/List")
    public String List(Model model) {
        //model.addAttribute("user",user);
        return "Report/List";
    }

    @RequestMapping("/Report/YTDTrend")
    public String YTDTrend(Model model) {
        //model.addAttribute("user",user);
        return "Report/YTDTrend";
    }

    @RequestMapping("/Report/MonthTrend")
    public String MonthTrend(Model model) {
        //model.addAttribute("user",user);
        return "Report/MonthTrend";
    }
}
