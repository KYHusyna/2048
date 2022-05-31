package com.GraphicsModule.lwjglmodule;

import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import com.GraphicsModule.GraphicsModule;
import com.Main.ErrorCatcher;
import com.Main.GameField;

import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL11.glClearColor;

public class LwjglGraphicsModule implements GraphicsModule {

    private com.GraphicsModule.lwjglmodule.LwjglSpriteSystem spriteSystem;

    public LwjglGraphicsModule() {
        initOpengl();
        spriteSystem = new com.GraphicsModule.lwjglmodule.LwjglSpriteSystem();
    }

    private void initOpengl() {
        try {
            // window size
            Display.setDisplayMode(new DisplayMode(256, 256));

            // window name
            Display.setTitle("2048");

            // create window
            Display.create();
        } catch (Exception e) {
            ErrorCatcher.graphicsFailure(e);
        }

        glMatrixMode(GL_PROJECTION);
        glLoadIdentity();
        glOrtho(0, 256, 0, 256, 1, -1);
        glMatrixMode(GL_MODELVIEW);

        // texture support
        glEnable(GL_TEXTURE_2D);

        // transparency
        glEnable(GL_BLEND);
        glBlendFunc(GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA);

        // white background
        glClearColor(1, 1, 1, 1);
    }

    /**
     * Show playing field
     *
     * @param field Playing field that need to create
     */
    @Override
    public void draw(GameField field) {
        glClear(GL_COLOR_BUFFER_BIT);

        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {

                drawCell(64 * i, 64 * j, field.getValue(i,j));
            }
        }

        Display.update();
        Display.sync(60);
    }

    /**
     * Display cell
     *
     * @param x     Coordinate X
     * @param y     Coordinate Y
     * @param state states of cell
     */
    private void drawCell(int x, int y, int state) {
        spriteSystem.getSpriteByNumber(state).getTexture().bind();

        glBegin(GL_QUADS);
        glTexCoord2f(0, 0);
        glVertex2f(x, y + 64);
        glTexCoord2f(1, 0);
        glVertex2f(x + 64, y + 64);
        glTexCoord2f(1, 1);
        glVertex2f(x + 64, y);
        glTexCoord2f(0, 1);
        glVertex2f(x, y);
        glEnd();
    }

    /**
     * @return Возвращает true, если в окне нажат "крестик"
     */
    @Override
    public boolean isCloseRequested() {
        return Display.isCloseRequested();
    }

    /**
     * Заключительные действия.
     * Принудительно уничтожает окно.
     */
    @Override
    public void destroy() {
        Display.destroy();
    }
}
