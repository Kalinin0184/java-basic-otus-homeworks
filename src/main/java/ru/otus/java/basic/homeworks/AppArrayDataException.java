package ru.otus.java.basic.homeworks;

public class AppArrayDataException extends Exception {
    private final int row;
    private final int col;
    private final String value;

    public AppArrayDataException(String message, int row, int col, String value) {
        super(message);
        this.row = row;
        this.col = col;
        this.value = value;
    }

    public int getRow() {
        return row;
    }

    public int getCol() {
        return col;
    }

    public String getValue() {
        return value;
    }
}