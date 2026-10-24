package src.model.service;

public class Settings {
    private int colorNum;
    private boolean autoSave;
    private int autoSaveCounter;
    private int textSpeed;

    public Settings() {
        colorNum = 1;
        autoSave = true;
        autoSaveCounter = 1;
        textSpeed = 1000;
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
        switch (colorNum) {
            case 1:
                colorNum = 2;
                break;
            case 2:
                colorNum = 3;
                break;
            case 3:
                colorNum = 4;
                break;
            case 4:
                colorNum = 1;
                break;
            default:
                colorNum = 1;
                break;
        }
    }

    public void changeTextSpeed() {
        switch (textSpeed) {
            case 2500:
                textSpeed = 1000;
                break;
            case 1000:
                textSpeed = 100;
                break;
            case 100:
                textSpeed = 2500;
                break;
            default:
                textSpeed = 1000;
                break;
        }
    }

    public void addAutoSaveCounter() {
        autoSaveCounter++;
    }

    public int getColorNum() {
        return colorNum;
    }

    public boolean isAutoSave() {
        return autoSave;
    }

    public int getAutoSaveCounter() {
        return autoSaveCounter;
    }

    public int getTextSpeed() {
        return textSpeed;
    }

    public void setAutoSaveInOne() {
        autoSaveCounter = 1;
    }

}
