package com.sda.groupa.shippingcostcalculator.login.service;

import com.sda.groupa.shippingcostcalculator.driver.driverModel.Driver;
import com.sda.groupa.shippingcostcalculator.driver.driverService.DriverService;
import com.sda.groupa.shippingcostcalculator.login.model.User;
import com.sda.groupa.shippingcostcalculator.login.model.UserAuthority;
import com.sda.groupa.shippingcostcalculator.login.workerStrategy.WorkerStrategy;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Component
public class SteerRole {

    private final List<WorkerStrategy> workerStrategies;
    private UserDetailsService userDetailsService;

    public SteerRole(List<WorkerStrategy> workerStrategies, UserDetailsService userDetailsService) {
        this.workerStrategies = workerStrategies;
        this.userDetailsService = userDetailsService;
    }

    private WorkerStrategy getWorkingStrategy(UserAuthority userAuthority){
        return workerStrategies.stream().filter(strategy -> strategy.getUserAuthority().
                                        equals(userAuthority)).
                                        findFirst().orElseThrow(() -> new RuntimeException("Not supported strategy type"));
    }

    public ModelAndView runStrategy(String username){

        User user = userDetailsService.findUserByUsername(username).orElseThrow(()->new RuntimeException("Unavailable"));

        WorkerStrategy workerStrategy = getWorkingStrategy(user.getRole().getUserAuthority());

        return workerStrategy.setUpSession(user);

    }



}
