package scr.controller;

public class Settings {
    public static int collorNum = 1;
    public static boolean autoSave = true;
    public static int autoSaveCounter = 1;

    public static void incrementCounter() {
        autoSaveCounter++;
    }

    public static void setAutosave(boolean state) {
        autoSave = state;
    }

    public static void changeColorNumber(int number) {
        collorNum = number;
    }

}
