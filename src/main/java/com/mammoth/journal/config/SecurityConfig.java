package com.mammoth.journal.config;

import com.mammoth.journal.services.MyUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    MyUserDetailsService myUserDetailsService;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication()
                .withUser("user1").password((getPasswordEncoder()).encode("password")).roles("USER");
        auth.userDetailsService(myUserDetailsService);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception{
        http.csrf().disable()
                .authorizeRequests()
                    .antMatchers("/","/AboutUs", "/Communities", "/SignIn", "/Register" , "/css/**").permitAll()
                    .antMatchers("/Journal").hasAuthority("USER")
                .and()
                    .formLogin().defaultSuccessUrl("/Journal", true)
                .and()
                    .logout().logoutUrl("/perform_logout")
                    .logoutSuccessUrl("/SignIn");
    }

    @Bean
    public PasswordEncoder getPasswordEncoder(){
        BCryptPasswordEncoder passwordEncoder= new BCryptPasswordEncoder();
        return passwordEncoder;
    }





}