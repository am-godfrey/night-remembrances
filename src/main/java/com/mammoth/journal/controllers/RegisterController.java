package com.mammoth.journal.controllers;

import com.mammoth.journal.model.JournalUser;
import com.mammoth.journal.model.MyUserDetails;
import com.mammoth.journal.services.MyUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import com.mammoth.journal.model.JournalUser;

import javax.validation.Valid;

@Controller
public class RegisterController {

    @Autowired
    private MyUserDetailsService myUserDetailsService;

    @RequestMapping(value="/Register", method= RequestMethod.GET)
    public String getRegisterMapping(Model model){
        model.addAttribute("newUserDetails", new MyUserDetails());
        return "register";
    }

    @RequestMapping(value="/Register", method=RequestMethod.POST)
    public String userRegistration(@Valid @ModelAttribute("newUserDetails") MyUserDetails newUserDetails, BindingResult result){
        //Check if User exists
        System.out.println("YEllo");
        try{
            MyUserDetails retrievedUser = myUserDetailsService.loadUserByUsername(newUserDetails.getUsername());
        }
        catch(UsernameNotFoundException e){
            JournalUser journalUser = new JournalUser();
            System.out.println("Username: " + newUserDetails.getUsername());
            journalUser.setUsername(newUserDetails.getUsername());
            journalUser.setPassword(newUserDetails.getPassword());
            journalUser.setFirstName(newUserDetails.getFirstName());
            journalUser.setLastName(newUserDetails.getLastName());
            journalUser.setActive(true);
            if(newUserDetails.getEmail() != null){
                journalUser.setEmail(newUserDetails.getEmail());
            }
            System.out.println("We Are about to save the User");
            myUserDetailsService.save(journalUser);
            return "redirect:/Journal";
        }
        result.rejectValue("username",null, "Username is already taken");

        return "register";

    }
}
