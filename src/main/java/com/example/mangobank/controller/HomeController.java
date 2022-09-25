package com.example.mangobank.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/")
public class HomeController {

    @RequestMapping("/homepage/success/")
    public ModelAndView homePage(){
        return new ModelAndView("homepage");
    }
}