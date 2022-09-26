package com.example.mangobank.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/home") //todo home
public class HomeController {

    @RequestMapping("/homepage/success/") //todo rename details, list, load
    public ModelAndView homePage(){
        return new ModelAndView("homepage");
    }
}
