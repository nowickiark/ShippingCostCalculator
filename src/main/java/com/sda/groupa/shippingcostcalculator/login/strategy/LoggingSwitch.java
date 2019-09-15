package com.sda.groupa.shippingcostcalculator.login.strategy;

import com.sda.groupa.shippingcostcalculator.login.model.UserAuthority;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Component
public class LoggingSwitch {

    List<LoginStrategy> loginStrategyList;

    public LoggingSwitch(List<LoginStrategy> loginStrategyList) {
        this.loginStrategyList = loginStrategyList;
    }

    private LoginStrategy getLogingStrategy(UserAuthority userAuthority){
       return loginStrategyList.stream().filter(strategy->strategy.getRoleType()
                                  .equals(userAuthority))
                                  .findFirst()
                                  .orElseThrow(() -> new RuntimeException("Not supported strategy type"));
    }

    public String getLogindView(UserAuthority userAuthority){
        LoginStrategy loginStrategy = getLogingStrategy(userAuthority);
        return loginStrategy.getLoggingView();
    }

}
