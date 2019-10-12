package com.sda.groupa.shippingcostcalculator.freightRate.exception;

public class FreightNotFoundException extends RuntimeException {
    public FreightNotFoundException(String message){
        super(message);
    }
}
