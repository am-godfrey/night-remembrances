package com.mammoth.journal.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class CommunitiesController {

    @RequestMapping("/Communities")
    public String getCommunitiesResponse(){
        return "communities";
    }
}
