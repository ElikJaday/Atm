package dev.el.atm.engine;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Atm {
    private static volatile Atm instance = null;
    private volatile Double moneyStorage = 100.00;
    private List<Note> noteList = Arrays.asList(
            Note.ONE,
            Note.FIVE,
            Note.TEN,
            Note.TWENTY_FIVE,
            Note.FIFTY,
            Note.ONE_HUNDRED);

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
    public void putAmount(List<Note> noteList) {
        synchronized (this.moneyStorage) {
            for (Note note : noteList) {
                moneyStorage += note.getNoteValue();
            }
        }
    }

    public List<Note> takeAmount(Double amount) {
        synchronized (this.moneyStorage) {
            if (checkHasAmount(amount)) {
                return calculateMoney(amount);
            } else {
                throw new IllegalStateException("Не достаточно средств");
            }
        }
    }

    public synchronized Double checkBalance() {
        return moneyStorage;
    }

    private List<Note> calculateMoney(Double amount) {
        List<Note> list = new ArrayList<>();
        double summary = amount.doubleValue();
        int i = noteList.size() - 1;
        while (i >= 0)
            if (noteList.get(i).getNoteValue() > summary)
                i--;
            else {
                list.add(noteList.get(i));
                summary -= noteList.get(i).getNoteValue();
            }
        return list;
    }

    private boolean checkHasAmount(Double amount) {
        if (amount > moneyStorage) {
            return false;
        }
        return true;
    }

}
