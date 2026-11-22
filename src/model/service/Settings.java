package src.model.service;

public class Settings {
    private boolean autoSave;
    private int autoSaveCounter;
    private int textSpeed;
    private int volume;

    public Settings() {
        autoSave = true;
        autoSaveCounter = 1;
        textSpeed = 1000;
        volume = 50;
    }

    public void incrementCounter() {
        autoSaveCounter++;
    }

    public void toggleAutoSave() {
        autoSave = !autoSave;
    }

    public void changeTextSpeed() {
        switch (textSpeed) {
            case 70:
                textSpeed = 35;
                break;
            case 35:
                textSpeed = 15;
                break;
            case 15:
                textSpeed = 0;
                break;
            case 0:
                textSpeed = 70;
                break;
            default:
                textSpeed = 35;
                break;
        }
    }

    public void addAutoSaveCounter() {
        autoSaveCounter++;
    }

    public boolean isAutoSave() {
        return autoSave;
    }

    public String getAutoSaveString() {
        return autoSave ? "Ativado" : "Desativado";
    }

    public int getAutoSaveCounter() {
        return autoSaveCounter;
    }

    public int getTextSpeed() {
        return textSpeed;
    }

    public String getTextSpeedString() {
        switch (textSpeed) {
            case 70:
                return "Lento";
            case 35:
                return "Médio";
            case 15:
                return "Rápido";
            case 0:
                return "Instantâneo";
            default:
                return "Desconhecido";
        }
    }

    public void setAutoSaveInOne() {
        autoSaveCounter = 1;
    }

    public int getVolume() {
        return volume;
    }

    public void setVolume(int value) {
        this.volume = value;
    }

}
