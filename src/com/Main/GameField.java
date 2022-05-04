package com.Main;

public class GameField {
    private int[][] field;

    public GameField() {
        field = new int[4][4];
    }

    public int getValue(int x, int y) {
        return field[x][y];
    }

    public void setValue(int x, int y, int value) {
        field[x][y] = value;
    }

    public int[] getColumn(int columnNumber) {
        return field[columnNumber];
    }

    public void setColumn(int columnNumber, int[] newColumn) {
        field[columnNumber] = newColumn;
    }

    public int[] getLine(int lineNumber) {
        int[] retNum = new int[4];

        for (int i = 0; i < 4; i++)
            retNum[i] = field[lineNumber][i];

        return retNum;
    }

    public void setLine(int lineNUmber, int[] newLine) {
        for (int i = 0; i < 4; i++)
          field[lineNUmber][i] = newLine[i];
    }
}
