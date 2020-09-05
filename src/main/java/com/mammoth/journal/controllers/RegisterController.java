package com.mammoth.journal.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class RegisterController {

    @GetMapping(path={"/Register"})
    public String getRegisterMapping(){
        return "register";
    }
}
