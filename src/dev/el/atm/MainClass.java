package dev.el.atm;

import dev.el.atm.engine.Atm;
import dev.el.atm.engine.Note;

import java.util.Arrays;
import java.util.List;

public class MainClass {

    public static void main(String[] args) {
        Atm atm = Atm.getInstance();
        atm.putAmount(Arrays.asList(Note.ONE_HUNDRED, Note.ONE_HUNDRED, Note.ONE_HUNDRED));
        List<Note> noteList = atm.takeAmount(127.00);
        System.out.println(noteList);
    }
}
