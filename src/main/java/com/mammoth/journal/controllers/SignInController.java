package com.mammoth.journal.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class SignInController {
    @GetMapping({"/Signin.html", "/SignIn.html","/signin","/SignIn","/Signin"})
    public String getSignIn(Model model){
        return "signin";
    }

}
