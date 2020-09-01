package com.mammoth.journal.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class JournalController {

    @RequestMapping({"/Journal", "/journal.html", "/Jounral.html", "/journal"})
    public String getJournalController(){
        return "journal";
    }
}
