/*
 * @LwjglSpriteSystem.java
 *
 * Version 1.0 (7.07.2016)
 *
 * Распространяется под копилефтной лицензией GNU GPL v3
 */
package com.GraphicsModule.lwjglmodule;

import java.util.HashMap;

/**
 * Система загрузки и хранения спрайтов для LWJGL
 *
 * @author DoKel
 * @version 1.0
 */
class LwjglSpriteSystem {

    /**
     * Хранит в себе ссылки на все доступные в игре текстуры с ключом, равным изображённой на текстуре цифре
     */
    private HashMap<Integer, com.GraphicsModule.lwjglmodule.LwjglSprite> spriteByNumber = new HashMap<>();

    /**
     * Инициализирует HashMap spriteByNumber и записывает в него ссылки
     * на все доступные в игре текстуры с ключом, равным изображённой на текстуре цифре
     */
    LwjglSpriteSystem() {

        for (com.GraphicsModule.lwjglmodule.LwjglSprite sprite : com.GraphicsModule.lwjglmodule.LwjglSprite.values()) {
            if (sprite.getSpriteNumber() != null) {
                spriteByNumber.put(sprite.getSpriteNumber(), sprite);
            }
        }
    }

    /**
     * @param n Число, которое должна изображать текстура
     * @return Текстура, изображающее число, переданное в параметре. Если такого нет, возвращает EMPTY.
     */
    public com.GraphicsModule.lwjglmodule.LwjglSprite getSpriteByNumber(int n) {
        return spriteByNumber.getOrDefault(n, com.GraphicsModule.lwjglmodule.LwjglSprite.EMPTY);
    }

}
