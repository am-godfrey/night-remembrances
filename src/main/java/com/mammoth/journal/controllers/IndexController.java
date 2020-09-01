package com.mammoth.journal.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class IndexController {

    @RequestMapping({"","/", "/index", "/index.html", "/home", "/Home", "/Home.html", "/home.html"})
    public String getIndexResponse(){
        return "index";
    }
}
