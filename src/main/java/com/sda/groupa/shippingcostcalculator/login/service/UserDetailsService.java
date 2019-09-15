package com.sda.groupa.shippingcostcalculator.login.service;

import com.sda.groupa.shippingcostcalculator.login.model.User;
import com.sda.groupa.shippingcostcalculator.login.repository.UserRepository;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserDetailsService implements org.springframework.security.core.userdetails.UserDetailsService {

    private final UserRepository userRepository;

    public UserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        return userRepository.findById(s).orElseThrow(() -> new UsernameNotFoundException("Could not find user with username: " + s));
    }

    public List<User> findAll() {
        return userRepository.findAll();
    }

    public Optional<User> findUserByUsername(String username){
        return userRepository.findById(username);
    }



}
