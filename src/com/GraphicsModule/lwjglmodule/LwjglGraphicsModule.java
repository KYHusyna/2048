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
            /* Задаём размер будущего окна */
            Display.setDisplayMode(new DisplayMode(256, 256));

            /* Задаём имя будущего окна */
            Display.setTitle("2048");

            /* Создаём окно */
            Display.create();
        } catch (Exception e) {
            ErrorCatcher.graphicsFailure(e);
        }

        glMatrixMode(GL_PROJECTION);
        glLoadIdentity();
        glOrtho(0, 256,0, 256,1,-1);
        glMatrixMode(GL_MODELVIEW);

		/* Для поддержки текстур */
        glEnable(GL_TEXTURE_2D);

		/* Для поддержки прозрачности */
        glEnable(GL_BLEND);
        glBlendFunc(GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA);

		/* Белый фоновый цвет */
        glClearColor(1,1,1,1);
    }

    /**
     * Отрисовывает переданное игровое поле
     *
     * @param field Игровое поле, которое необходимо отрисовать
     */
    @Override
    public void draw(GameField field) {
        glClear(GL_COLOR_BUFFER_BIT);

        for(int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                drawCell(64*i, 6*j, field.getValue(i,j));
            }
        }

        Display.update();
        Display.sync(60);
    }

    /**
     * Отрисовывает отдельную ячейку
     *
     * @param x Координата отрисовки X
     * @param y Координата отрисовки Y
     * @param state Состояние ячейки
     */
    private void drawCell(int x, int y, int state) {
        spriteSystem.getSpriteByNumber(state).getTexture().bind();

        glBegin(GL_QUADS);
        glTexCoord2f(0,0);
        glVertex2f(x,y+ 64);
        glTexCoord2f(1,0);
        glVertex2f(x+ 64,y+ 64);
        glTexCoord2f(1,1);
        glVertex2f(x+ 64, y);
        glTexCoord2f(0,1);
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
