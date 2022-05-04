package com.Main;

public class ErrorCatcher {

    public static void cellCreationFailure() {
        System.err.println("Main class failed to create new cell.");
        System.exit(-1);
    }

    public static void shiftFailureWrongParam() {
        System.err.println("Main class failed to shift cells on field. Wrong parameter.");
        System.exit(-2);
    }

    public static void graphicsFailure(Exception e) {
        System.err.println("GraphicsModule failed.");
        e.printStackTrace();
        System.exit(-3);
    }
}
