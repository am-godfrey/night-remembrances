package com.mammoth.journal.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class AboutUsController {
    @RequestMapping({"/AboutUs","/AboutUs.html","/Aboutus","/Aboutus.html","/aboutus.html","/aboutus"})
    public String getAboutUsResponse(){
        return "aboutus";
    }
}
