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
 * System download and storage sprite for LWJGL
 *
 * @author DoKel
 * @version 1.0
 */
class LwjglSpriteSystem {

    /**
     * Storage links to all available texture in game, with a key equal to display number
     */
    private HashMap<Integer, com.GraphicsModule.lwjglmodule.LwjglSprite> spriteByNumber = new HashMap<>();

    /**
     * Initialization HashMap spriteByNumber and write link
     * to all available texture with key equals display number
     */
    LwjglSpriteSystem() {

        for (com.GraphicsModule.lwjglmodule.LwjglSprite sprite : com.GraphicsModule.lwjglmodule.LwjglSprite.values()) {
            if (sprite.getSpriteNumber() != null) {
                spriteByNumber.put(sprite.getSpriteNumber(), sprite);
            }
        }
    }

    /**
     * @param n Number that texture must show
     * @return Texture display number, transferred at parameter. If this number not present, return empty
     */
    public com.GraphicsModule.lwjglmodule.LwjglSprite getSpriteByNumber(int n) {
        return spriteByNumber.getOrDefault(n, com.GraphicsModule.lwjglmodule.LwjglSprite.EMPTY);
    }
}
