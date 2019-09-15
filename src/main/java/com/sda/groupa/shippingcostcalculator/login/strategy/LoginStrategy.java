package com.sda.groupa.shippingcostcalculator.login.strategy;

import com.sda.groupa.shippingcostcalculator.login.model.UserAuthority;
import org.springframework.web.servlet.ModelAndView;

public interface LoginStrategy {

    public String getLoggingView();
    public UserAuthority getRoleType();

}
