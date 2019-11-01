package com.openjava.mvc.controller;
import com.openjava.mvc.model.UserModel;
import com.openjava.mvc.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class UserController {

    private final UserService service;

    @Autowired
    public UserController(UserService service) {
        this.service = service;
    }

    @GetMapping("/user")
    public ModelAndView User() {

        ModelAndView mv = new ModelAndView("User"); // 对应 demo.jsp 路径
        mv.addObject("value", "test");
        return mv;
    }

    @GetMapping("/list")
    @ResponseBody
    public List<UserModel> List() {
        return service.selectAll();
    }
}
