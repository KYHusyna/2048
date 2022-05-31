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
 * Implements reading the parameters necessary for the game from the keyboard using LWJGL
 *
 * @author DoKel
 * @version 1.0
 */
public class LwjglKeyboardHandleModule implements KeyboardHandle {

    /* Данные о вводе за последнюю итераци. */
    private boolean wasEscPressed;
    private WayMove lastDirectionKeyPressed;

    /**
     * Read last data from event stack
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
     * Resetting data received from previous requests
     */
    private void resetValues() {
        lastDirectionKeyPressed = WayMove.AWAIT;
        wasEscPressed = false;
    }

    /**
     * @return Returns the direction of the last clicked "arrow",
     * or AWAITING,if none has been pressed
     */
    @Override
    public WayMove lastDirectionKeyPressed() {
        return lastDirectionKeyPressed;
    }

    /**
     * @return Returns information about whether ESCAPE was pressed during the last iteration
     */
    @Override
    public boolean wasEscPressed() {
        return wasEscPressed;
    }


}
