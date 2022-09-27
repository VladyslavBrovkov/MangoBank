package com.example.mangobank.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping(value = "/homepage")
public class HomeController {
    @RequestMapping
    public ModelAndView homePage(){
        return new ModelAndView("homepage");
    }
}
