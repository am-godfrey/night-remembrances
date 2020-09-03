package com.mammoth.journal.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class SignInController {
    @GetMapping("/SignIn")
    public String getSignIn(Model model){
        return "signin";
    }

}
