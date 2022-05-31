package com.Main;

public class GameField {
    private int[][] field;

    public int[][] getField() {
        for (int i = 0; i < field.length; i++) {
            for (int j = 0; j < field.length; j++) {
                System.out.print(field[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println("-----------------");
        return field;
    }

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
        int[] column=new int[4];

        for (int i = 0; i < 4; i++)
            column[i] = field[i][columnNumber];

        return column;
    }

    public void setColumn(int columnNumber, int[] newColumn) {
        for (int i = 0; i < 4; i++)
        field[i][columnNumber] = newColumn[i];
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

    public int[] reverse(int arguments[]) {

        int[] temp = new int[arguments.length];
        for (int j = 0; j < temp.length; j++)
            temp[j] = arguments[arguments.length - j - 1];

        return temp;
    }
}
