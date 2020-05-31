package com.jier.admin.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @program: admin
 * @description:
 * @author: Mr.liu
 * @create: 2020-05-28 17:51
 **/
@Controller
public class PageController {
    @GetMapping("/index")
    public String index(){
        return "/index";
    }
    @GetMapping("/login")
    public String login(){
        return "login";
    }
    @GetMapping("/menu")
    public String menu2(){
        return "/menu/menu";
    }
    @GetMapping("/show")
    public String show(){
        return "/show";
    }

}
