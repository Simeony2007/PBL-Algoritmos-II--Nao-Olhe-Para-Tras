package src.controller;

import src.model.repository.SaveManager;
import src.model.service.Settings;

public class SettingsMenuJFX {
    private Settings settings = SaveManager.loadSettings();

    public void saveSettings() {
        SaveManager.saveSettings(settings);
    }

    public Settings getSettings() {
        return settings;
    }

    public String getTextSpeedString() {
        return settings.getTextSpeedString();
    }

    public void changeTextSpeed() {
        settings.changeTextSpeed();
    }

    public String getAutoSaveString() {
        return settings.getAutoSaveString();
    }

    public void toggleAutoSave() {
        settings.toggleAutoSave();
    }

    public void setVolume(int volume) {
        settings.setVolume(volume);
    }

    public int getVolume() {
        return settings.getVolume();
    }
}
