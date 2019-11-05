package com.openjava.mvc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@Controller
public class HomeController {

    @RequestMapping("/")
    public String Index(Map<String, Object> paramMap) {

        /** 默认Map的内容会放大请求域中，页面可以直接取值*/
        paramMap.put("name", "zhangSan");
        paramMap.put("age", 28);

        /** 会自动跳转到默认的 classpath:/templates/index.html 页面*/
        return "index";
    }


}
