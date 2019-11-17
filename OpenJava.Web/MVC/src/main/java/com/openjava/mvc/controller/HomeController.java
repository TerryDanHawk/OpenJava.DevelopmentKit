package com.openjava.mvc.controller;

import com.openjava.mvc.model.UserModel;
import com.openjava.mvc.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@Controller
public class HomeController {

    private final UserService service;

    @Autowired
    public HomeController(UserService service) {
        this.service = service;
    }

    @RequestMapping("/")
    public String Login(Map<String, Object> paramMap) {
//        UserModel user=this.service.select(1);
//        paramMap.put("username", user.getUserName());
//        paramMap.put("realname", user.getRealName());
        return "login";
    }


    @RequestMapping("/home/index")
    public String Index(Map<String, Object> paramMap) {
        return "home/index";
    }


    @RequestMapping("/user")
    public String User(Model model) {

        UserModel user=this.service.select(1);
        model.addAttribute("user",user);
        return "user";
    }

    @RequestMapping(value = "/api")//,method = RequestMethod.POST
    @ResponseBody
    public String GetData(@RequestParam("name") String name)
    {
        return "Hello!"+name;
    }


}
