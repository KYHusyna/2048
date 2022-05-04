/*
 * @LwjglKeyboardHandleModule.java
 *
 * Version 1.0 (7.07.2016)
 *
 * Распространяется под копилефтной лицензией GNU GPL v3
 */
package com.KeyBoard.lwjglmodule;

import com.KeyBoard.KeyboardHandle;
import com.Main.WayMove;
import org.lwjgl.input.Keyboard;

/**
 * Реализует считывание необходимых игре параметров с клавиатуры средствами LWJGL
 *
 * @author DoKel
 * @version 1.0
 */
public class LwjglKeyboardHandleModule implements KeyboardHandle {

    /* Данные о вводе за последнюю итераци. */
    private boolean wasEscPressed;
    private WayMove lastDirectionKeyPressed;

    /**
     * Считывание последних данных из стека событий
     */
    @Override
    public void update() {
        resetValues();
        lastDirectionKeyPressed = WayMove.AWAIT;

        while (Keyboard.next()) {
            if (Keyboard.getEventKeyState()) {
                switch(Keyboard.getEventKey()){
                    case Keyboard.KEY_ESCAPE:
                        wasEscPressed = true;
                        break;
                    case Keyboard.KEY_UP:
                        lastDirectionKeyPressed = WayMove.UP;
                        break;
                    case Keyboard.KEY_RIGHT:
                        lastDirectionKeyPressed = WayMove.RIGHT;
                        break;
                    case Keyboard.KEY_DOWN:
                        lastDirectionKeyPressed = WayMove.DOWN;
                        break;
                    case Keyboard.KEY_LEFT:
                        lastDirectionKeyPressed = WayMove.LEFT;
                        break;
                }
            }
        }
    }

    /**
     * Обнуление данных, полученых в при предыдущих запросах
     */
    private void resetValues() {
        lastDirectionKeyPressed = WayMove.AWAIT;
        wasEscPressed = false;
    }

    /**
     * @return Возвращает направление последней нажатой "стрелочки",
     * либо AWAITING, если не было нажато ни одной
     */
    @Override
    public WayMove lastDirectionKeyPressed() {
        return lastDirectionKeyPressed;
    }

    /**
     * @return Возвращает информацию о том, был ли нажат ESCAPE за последнюю итерацию
     */
    @Override
    public boolean wasEscPressed() {
        return wasEscPressed;
    }


}
