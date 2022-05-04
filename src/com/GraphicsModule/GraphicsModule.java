package com.GraphicsModule;

import com.Main.GameField;

public interface GraphicsModule {
    void draw(GameField field);

    boolean isCloseRequested();

    void destroy();
}
