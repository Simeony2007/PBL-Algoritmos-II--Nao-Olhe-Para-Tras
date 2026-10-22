package scr.controller;

public class Settings {
    public static int collorNum;
    public static boolean autoSave;
    public static int autoSaveCounter;

    public Settings() {
        collorNum = 1;
        autoSave = true;
        autoSaveCounter = 1;
    }

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
