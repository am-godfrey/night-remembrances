package com.mammoth.journal.controllers;

import com.mammoth.journal.model.Entry;
import com.mammoth.journal.model.JournalUser;
import com.mammoth.journal.repositories.EntryRepository;
import com.mammoth.journal.repositories.UserRepository;
import com.mammoth.journal.services.MyUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;
import java.sql.Date;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Controller
public class JournalController {

    @Autowired
    EntryRepository entryRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    private MyUserDetailsService myUserDetailsService;

    @RequestMapping("/Journal")
    public String getJournalController(Model model){
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if(principal instanceof UserDetails) {
            JournalUser journalUser = myUserDetailsService.loadJournalUserByUsername(((UserDetails) principal).getUsername());
            model.addAttribute("currentUser", journalUser);

            try{
                List<Entry> userEntries = journalUser.getEntries();
                System.out.println(userEntries);
                Collections.sort(userEntries, Collections.reverseOrder());
                System.out.println(userEntries);
                List<Entry> first5ElementsList = userEntries.stream().limit(4).collect(Collectors.toList());
                model.addAttribute("listOfEntries", first5ElementsList);
            }
            catch(UsernameNotFoundException e){
            }
        }
        return "journal";
    }

    @RequestMapping("/Journal/Entry")
    public String getJournalEntryController(Model model){
        model.addAttribute("newEntry", new Entry());
        return "entry";
    }

    @PostMapping("/Journal/Entry")
    public String postJournalEntry(@ModelAttribute("newEntry") @Validated Entry newEntry,
                                   BindingResult result){
        java.util.Date utilDate = new java.util.Date();
        Date sqlDate = new java.sql.Date(utilDate.getTime());
        newEntry.setDate(sqlDate);
        Timestamp time = new Timestamp(utilDate.getTime());
        newEntry.setTime(time);

        //Set the User
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if(principal instanceof UserDetails){
            JournalUser journalUser = myUserDetailsService.loadJournalUserByUsername(((UserDetails) principal).getUsername());
            List<Entry> userEntries = journalUser.getEntries();
            int previousSize = userEntries.size();
            if(!userEntries.add(newEntry)){
                result.addError(new ObjectError("adding-entry-to-user",
                        "Cannot add the entry to this user"));
            }
            if(userEntries.size() != previousSize +1){
                result.addError(new ObjectError("adding-entry-to-user",
                        "Did not add the entry to this user"));
            }
            journalUser.setEntries(userEntries);
            newEntry.setJournalUser(journalUser);
            myUserDetailsService.save(newEntry);
            myUserDetailsService.save(journalUser);
        }
        else{
            result.addError(new ObjectError("submission-user-principle",
                    "Unable to submit response as this user"));
        }
        return "redirect:/Journal";
    }

    @RequestMapping("/Journal/AllEntries")
    public String getAllEntries(Model model){
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if(principal instanceof UserDetails) {
            JournalUser journalUser = myUserDetailsService.loadJournalUserByUsername(((UserDetails) principal).getUsername());
            model.addAttribute("currentUser", journalUser);

            try {
                List<Entry> userEntries = journalUser.getEntries();
                model.addAttribute("listOfEntries", userEntries);
            } catch (UsernameNotFoundException e) {
            }
        }
        return "allEntries";
    }
}
