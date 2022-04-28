package main.game.logic;

import java.util.Random;

public class Main {

    public static int score;

    public static void main(String[] args) {
        // initial fields
        //create initial cells

      /*while(!endGame){
          input();
          logic();

          draw fields
      }
   destroy fields
      */

    }

    public static void createInitialCells() {
        for (int i = 0; i < 2; i++) {
            generateNewCell();
        }
    }

    //spawn number
    private static void generateNewCell() {
        //chance to spawn cell with value 4 - 17%
        int valueSpawn = (new Random().nextInt(100) <= 17) ? 4 : 2;

        int randX, randY,
                currentX, currentY;

        GameField gameField = new GameField();

        randX = new Random().nextInt(4);
        currentX = randX;
        randY = new Random().nextInt(4);
        currentY = randY;


        boolean placed = false;

        while (!placed) {
            if (gameField.getValue(randX, randY) == 0) {
                gameField.setValue(randX, randY, valueSpawn);
                placed = true;
            } else {
                if (currentX + 1 <= 4) {
                    currentX++;
                } else {
                    currentX = 0;
                    if (currentY + 1 <= 4) {
                        currentY++;
                    } else {
                        currentY = 0;
                    }
                }
            }
        }
        score += valueSpawn;
    }
}
