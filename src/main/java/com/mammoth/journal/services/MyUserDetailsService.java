package com.mammoth.journal.services;

import com.mammoth.journal.model.JournalUser;
import com.mammoth.journal.model.MyUserDetails;
import com.mammoth.journal.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class MyUserDetailsService implements UserDetailsService {

    @Autowired
    UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<JournalUser> journalUser = userRepository.findByUsername(username);
        journalUser.orElseThrow(() -> new UsernameNotFoundException("Not Found:" + username));
        return journalUser.map(MyUserDetails::new).get();
    }

}
