package com.KeyBoard;

import com.Main.WayMove;

public interface KeyboardHandle {

    void update();

    WayMove lastDirectionKeyPressed();

    boolean wasEscPressed();

}
