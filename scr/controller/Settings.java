package scr.controller;

import scr.view.GameInterface;

public class Settings {
    public int collorNum;
    public boolean autoSave;
    public int autoSaveCounter;

    public Settings() {
        collorNum = 1;
        autoSave = true;
        autoSaveCounter = 1;
    }

    public void incrementCounter() {
        autoSaveCounter++;
    }

    public void setAutosave() {
        if (autoSave) {
            autoSave = false;
        } else {
            autoSave = true;
        }
    }

    public void changeColorNumber() {
        if (collorNum <= GameInterface.MAX_COLLORS) {
            collorNum += 1;
        } else {
            collorNum = 1;
        }
    }

    public void addAutoSaveCounter() {
        autoSaveCounter++;
    }

}
