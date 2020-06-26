package dev.el.atm;

import dev.el.atm.engine.Atm;

import java.math.BigDecimal;
import java.util.List;

public class MainClass {

    public static void main(String[] args) {
        Atm atm = Atm.getInstance();
        atm.putAmount(new BigDecimal(1100.00));
        List<Double> doubleList = atm.takeAmount(new BigDecimal(1100.00));
        System.out.println(doubleList.toString());
    }
}
