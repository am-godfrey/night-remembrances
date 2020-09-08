package com.mammoth.journal.services;

import com.mammoth.journal.model.Entry;
import com.mammoth.journal.model.JournalUser;
import com.mammoth.journal.model.MyUserDetails;
import com.mammoth.journal.repositories.EntryRepository;
import com.mammoth.journal.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class MyUserDetailsService implements UserDetailsService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    EntryRepository entryRepository;


    private BCryptPasswordEncoder bCryptPasswordEncoder;

    public MyUserDetailsService(){
        this.bCryptPasswordEncoder = new BCryptPasswordEncoder();
    }

    @Override
    public MyUserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<JournalUser> journalUser = userRepository.findByUsername(username);
        journalUser.orElseThrow(() -> new UsernameNotFoundException("Not Found:" + username));
        return journalUser.map(MyUserDetails::new).get();
    }


    public JournalUser loadJournalUserByUsername(String username) throws UsernameNotFoundException {
        Optional<JournalUser> journalUser = userRepository.findByUsername(username);
        journalUser.orElseThrow(() -> new UsernameNotFoundException("Not Found:" + username));
        return journalUser.get();
    }

    public void save(JournalUser user) {
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        user.setRoles("USER");
        userRepository.save(user);
    }

    public void save(Entry entry){
        entryRepository.save(entry);
    }

    public List<Entry> findAllEntries(JournalUser user){
        Optional<List<Entry>> entries = entryRepository.findAllByJournalUser(user);
        entries.orElseThrow(() -> new UsernameNotFoundException("Not entries were found for: " + user.getUsername()));
        return entries.get();
    }





}
