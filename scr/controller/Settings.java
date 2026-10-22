package scr.controller;

public class Settings {
    public int collorNum = 1;
    public boolean autoSave = true;
    public int autoSaveCounter = 1;

    public Settings() {
        collorNum = 1;
        autoSave = true;
        autoSaveCounter = 1;
    }

    public void incrementCounter() {
        autoSaveCounter++;
    }

    public void setAutosave(boolean state) {
        autoSave = state;
    }

    public void changeColorNumber(int number) {
        collorNum = number;
    }

}
