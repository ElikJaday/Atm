package dev.el.atm.engine;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class Atm {
    private static volatile Atm instance = null;
    private volatile BigDecimal moneyStorage = new BigDecimal(100.00);
    private double note[] = {1.00,5.00, 10.00, 20.00, 50.00, 100.00, 200.00, 500.00};

    private Atm() {
    }

    public static Atm getInstance() {
        if (instance == null) {
            synchronized (Atm.class) {
                if (instance == null) {
                    instance = new Atm();
                }
            }
        }
        System.out.println("ATM started work");
        return instance;
    }

    //Просто волатайл здесь не поможет так как операция не атомарная
    public void putAmount(BigDecimal amount) {
        synchronized (this.moneyStorage){
            moneyStorage = moneyStorage.add(amount);
        }
    }

    public List<Double> takeAmount(BigDecimal amount) {
        synchronized (this.moneyStorage){
            if (checkHasAmount(amount)) {
                moneyStorage = moneyStorage.subtract(amount);
                return calculateMoney(amount);
            } else {
                throw new IllegalStateException("Не достаточно средств");
            }
        }
    }

    private List<Double> calculateMoney(BigDecimal amount) {
        List<Double> list = new ArrayList<>();
        double summary = amount.doubleValue();
        int i = note.length - 1;
        while (i >= 0)
            if (note[i] > summary)
                i--;
            else {
                list.add(note[i]);
                summary -= note[i];
            }
        return list;
    }

    private boolean checkHasAmount(BigDecimal amount) {
        if (moneyStorage.compareTo(amount) == 0) {
            return true;
        } else if (moneyStorage.compareTo(amount) == 1) {
            return true;
        } else {
            return false;
        }
    }

}
