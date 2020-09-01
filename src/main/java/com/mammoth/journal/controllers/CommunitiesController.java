package com.mammoth.journal.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class CommunitiesController {

    @RequestMapping({"/Communities","/communities","/Communities.html","/communities.html"})
    public String getCommunitiesResponse(){
        return "communities";
    }
}
