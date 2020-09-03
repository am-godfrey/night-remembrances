package com.mammoth.journal.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class AboutUsController {
    @RequestMapping("/AboutUs")
    public String getAboutUsResponse(){
        return "aboutus";
    }
}
