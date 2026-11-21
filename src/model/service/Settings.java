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
            case 2500:
                textSpeed = 1000;
                break;
            case 1000:
                textSpeed = 100;
                break;
            case 100:
                textSpeed = 0;
                break;
            case 0:
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
            case 2500:
                return "Lento";
            case 1000:
                return "Médio";
            case 100:
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
