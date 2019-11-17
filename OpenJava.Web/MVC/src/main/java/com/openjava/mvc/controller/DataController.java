package com.openjava.mvc.controller;

import com.openjava.mvc.model.UserModel;
import com.openjava.mvc.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;

@Controller
public class DataController {

    private final UserService service;

    @Autowired
    public DataController(UserService service) {
        this.service = service;
    }

    @RequestMapping("/data/input")
    public String input(Model model) {
        //model.addAttribute("user",user);
        return "data/input";
    }



}
