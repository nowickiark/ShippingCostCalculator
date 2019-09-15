package com.sda.groupa.shippingcostcalculator.login.model;


import com.sda.groupa.shippingcostcalculator.login.service.UserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;

@Component
@Scope(value = "session",  proxyMode = ScopedProxyMode.TARGET_CLASS)
public class UserProvider {

    private User user;

    private UserDetailsService userDetailsService;

    @Autowired
    public UserProvider(UserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

    public UserProvider(){};

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void saveLoggedUser(String username){
        user = userDetailsService.findUserByUsername(username).orElseThrow(()->new RuntimeException("Unavailable"));
    }

}
