package com.openjava.mvc.controller;

import com.openjava.mvc.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.io.UnsupportedEncodingException;
import java.util.Map;

@Controller
public class HomeController {

    private final UserService userService;

    @Autowired
    public HomeController(UserService _userservice) {
        this.userService = _userservice;
    }

    @RequestMapping("/")
    public String Login(Map<String, Object> paramMap) {
//        UserModel user=this.service.select(1);
//        paramMap.put("username", user.getUserName());
//        paramMap.put("realname", user.getRealName());
        return "login";
    }


    @RequestMapping("/Home/Index")
    public String Index(Map<String, Object> paramMap) {
        return "Home/Index";
    }

    @RequestMapping(value = "/api/login",method = RequestMethod.POST)
    @ResponseBody
    public String Login(@RequestParam("username") String username,@RequestParam("password") String password) throws UnsupportedEncodingException {
        return  userService.Login(username,password);
    }


//    @RequestMapping("/user")
//    public String User(Model model) {
//
//        UserModel user=this.service.select(1);
//        model.addAttribute("user",user);
//        return "user";
//    }

//    @RequestMapping(value = "/api")//,method = RequestMethod.POST
//    @ResponseBody
//    public String GetData(@RequestParam("name") String name)
//    {
//        return "Hello!"+name;
//    }


}
