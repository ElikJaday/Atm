package dev.el.atm.engine;

public enum Note {
    ONE(1.00),
    FIVE(5.00),
    TEN(10.00),
    TWENTY_FIVE(10.00),
    FIFTY(50.00),
    ONE_HUNDRED(100.00);

    private Double noteValue;

    Note(Double noteValue) {
        this.noteValue = noteValue;
    }

    public Double getNoteValue() {
        return noteValue;
    }
}
