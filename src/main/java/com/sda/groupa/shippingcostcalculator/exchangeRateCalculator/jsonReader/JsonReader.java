package com.sda.groupa.shippingcostcalculator.exchangeRateCalculator.jsonReader;

import java.io.*;

public class JsonReader {

    public String readAll(Reader reader) throws IOException {
        StringBuilder stringBuilder = new StringBuilder();
        int cp;
        while ((cp = reader.read()) != -1) {
            stringBuilder.append((char) cp);
        }
        return stringBuilder.toString();
    }

}
