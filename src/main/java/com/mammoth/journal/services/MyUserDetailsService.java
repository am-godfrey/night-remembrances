package com.mammoth.journal.services;

import com.mammoth.journal.model.JournalUser;
import com.mammoth.journal.model.MyUserDetails;
import com.mammoth.journal.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class MyUserDetailsService implements UserDetailsService {

    @Autowired
    UserRepository userRepository;

    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<JournalUser> journalUser = userRepository.findByUsername(username);
        journalUser.orElseThrow(() -> new UsernameNotFoundException("Not Found:" + username));
        return journalUser.map(MyUserDetails::new).get();
    }

    private void generatePasswordEncoder(){
        bCryptPasswordEncoder = new BCryptPasswordEncoder();
    }


    public void save(JournalUser user) {
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        user.setRoles("USER");
        userRepository.save(user);
    }


}
