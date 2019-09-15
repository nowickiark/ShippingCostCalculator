package com.sda.groupa.shippingcostcalculator.login.strategy;

import com.sda.groupa.shippingcostcalculator.login.model.UserAuthority;
import org.springframework.stereotype.Component;

@Component
public class SpedytorLoginStrategy implements LoginStrategy {
    @Override
    public String getLoggingView() {
        return "spedytorHome";
    }

    @Override
    public UserAuthority getRoleType() {
        return UserAuthority.SPEDYTOR;
    }
}
