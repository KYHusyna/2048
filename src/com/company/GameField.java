package com.company;

public class GameField {
    //create field array
    private int theField[][];

    // fill array by zero

    public GameField() {
        theField = new int[4][4];

        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                theField[i][j] = 0;
            }
        }

    }

    //get value
    public int getValue(int x, int y) {
        return theField[x][y];
    }

    //setValue
    public void setValue(int x, int y, int state) {
        theField[x][y] = state;
    }

    //setColumn
    public void setColumn(int i, int[] newColumn) {
        theField[i] = newColumn;
    }

    //getColumn
    public int[] getColumn(int i) {
        return theField[i];
    }

    //setLine
    public void setLine(int i, int[] newline) {
        for (int j = 0; j < 4; j++) {
            theField[j][i] = newline[j];
        }
    }

    //getLine
    public int[] getLine(int i) {
        int[] ret = new int[4];

        for (int j = 0; j < 4; j++) {
            ret[j] = theField[j][i];
        }
        return ret;
    }
}
