package com.sda.groupa.shippingcostcalculator.login.workerStrategy;

import com.sda.groupa.shippingcostcalculator.login.model.User;
import com.sda.groupa.shippingcostcalculator.login.model.UserAuthority;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.web.servlet.ModelAndView;

public interface WorkerStrategy {

    ModelAndView setUpSession(User user);
    UserAuthority getUserAuthority();
    User getUser();

}
