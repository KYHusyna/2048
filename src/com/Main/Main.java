package com.Main;

import java.util.Random;

import com.GraphicsModule.lwjglmodule.LwjglGraphicsModule;
import com.KeyBoard.KeyboardHandle;
import com.GraphicsModule.GraphicsModule;
import com.KeyBoard.lwjglmodule.LwjglKeyboardHandleModule;

public class Main {

    private static int score;
    private static boolean endOfGame = false;
    private static boolean isThere2048;
    private static WayMove wayMove;
    private static GraphicsModule graphicsModule;
    private static LwjglKeyboardHandleModule keyboardHandle;
    private static GameField gameField = new GameField();
    private static GameField gameField1;

    private static void initFields() {
        score = 0;
        endOfGame = false;
        isThere2048 = false;
        wayMove = WayMove.AWAIT;
        graphicsModule = new LwjglGraphicsModule();
        keyboardHandle = new LwjglKeyboardHandleModule();
        gameField = new com.Main.GameField();
    }

    private static void createInitialCells() {
        for (int i = 0; i < 4; i++) {
            generateCell();
        }
    }

    public static void main(String[] args) {

        initFields();
        createInitialCells();

        gameField.getField();
        while (!endOfGame) {

            input();
            logic();

            graphicsModule.draw(gameField);

        }
        gameField.getField();
        graphicsModule.destroy();

    }

    public static void logic() {
        if (wayMove != WayMove.AWAIT) {
            if (shift(wayMove))
                generateCell();

            System.out.println();

            if (wayMove != WayMove.AWAIT)
                gameField.getField();

            wayMove = WayMove.AWAIT;
        }
    }

    public static void input() {
        keyboardHandle.update();

        wayMove = keyboardHandle.lastDirectionKeyPressed();
        switch (wayMove) {
            case UP:
                System.out.println("up");
                break;
            case DOWN:
                System.out.println("down");
                break;
            case LEFT:
                System.out.println("left");
                break;
            case RIGHT:
                System.out.println("right");
                break;
        }

        endOfGame = endOfGame || graphicsModule.isCloseRequested() || keyboardHandle.wasEscPressed();
    }

    public static boolean shift(WayMove wayMove) {
        boolean ret = false;

        switch (wayMove) {
            case UP:
            case DOWN:
                for (int i = 0; i < 4; i++) {
                    int arg[] = gameField.getColumn(i);

                    if (wayMove == WayMove.DOWN) {
                        arg = gameField.reverse(arg);
                    }

                    ShiftRowResult result = shiftRow(arg);

                    if (wayMove == WayMove.DOWN) {
                        result.shiftedRow = gameField.reverse(result.shiftedRow);
                    }

                    gameField.setColumn(i, result.shiftedRow);

                    ret = ret || result.didAnythingMove;
                }
                break;

            case RIGHT:
            case LEFT:

                for (int i = 0; i < 4; i++) {
                    int arg[] = gameField.getLine(i);

                    if (wayMove == WayMove.RIGHT) {
                        arg = gameField.reverse(arg);
                    }

                    ShiftRowResult result = shiftRow(arg);

                    if (wayMove == WayMove.RIGHT) {
                        result.shiftedRow = gameField.reverse(result.shiftedRow);
                    }

                    gameField.setLine(i, result.shiftedRow);

                    ret = ret || result.didAnythingMove;
                }
                break;

            default:
                ErrorCatcher.shiftFailureWrongParam();
                break;

        }

        return ret;
    }

    private static ShiftRowResult shiftRow(int[] oldRow) {
        ShiftRowResult ret = new ShiftRowResult();

        int[] oldRowWithoutZeroes = new int[oldRow.length];

        int q = 0;
        for (int i = 0; i < oldRow.length; i++) {
            if (oldRow[i] != 0) {
                if (q != i) {
                    ret.didAnythingMove = true;
                }

                oldRowWithoutZeroes[q] = oldRow[i];
                q++;
            }
        }

        for (int i = q; i < oldRowWithoutZeroes.length; i++) {
            oldRowWithoutZeroes[i] = 0;
        }

        ret.shiftedRow = new int[oldRowWithoutZeroes.length];

        q = 0;
        int i = 0;

        while (i < oldRowWithoutZeroes.length) {
            if ((i + 1 < oldRowWithoutZeroes.length) && (oldRowWithoutZeroes[i] == oldRowWithoutZeroes[i + 1])
                    && oldRowWithoutZeroes[i] != 0) {
                ret.didAnythingMove = true;
                ret.shiftedRow[q] = oldRowWithoutZeroes[i] * 2;
                if (ret.shiftedRow[q] == 2048) merged2048();
                i++;
            } else {
                ret.shiftedRow[q] = oldRowWithoutZeroes[i];
            }

            q++;
            i++;
        }
        for (int j = q; j < ret.shiftedRow.length; j++) {
            ret.shiftedRow[j] = 0;
        }

        return ret;
    }

    private static class ShiftRowResult {
        boolean didAnythingMove;
        int[] shiftedRow;
    }

    private static void merged2048() {
        endOfGame = true;
        isThere2048 = true;
    }

    public static void generateCell() {
        int valueSpawn = (new Random().nextInt(100) <= 17) ? 4 : 2,
                randX, randY,
                currentX, currentY;

        randX = new Random().nextInt(4);
        currentX = randX;
        randY = new Random().nextInt(4);
        currentY = randY;

        boolean placed = false;

        while (!placed) {
            if (gameField.getValue(currentX, currentY) == 0) {
                gameField.setValue(currentX, currentY, valueSpawn);
                placed = true;
            } else {
                if ((currentX + 1) < 4) {
                    currentX++;
                } else {
                    currentX = 0;
                    if ((currentY + 1) < 4) {
                        currentY++;
                    } else {
                        currentY = 0;
                    }
                }

                if ((currentX == randX) && (currentY == randY)) {
                    ErrorCatcher.cellCreationFailure();
                }
            }
        }
        score += valueSpawn;
    }


}

