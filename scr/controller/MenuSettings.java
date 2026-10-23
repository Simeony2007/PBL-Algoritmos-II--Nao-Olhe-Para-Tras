package scr.controller;

import scr.model.repository.SaveManager;
import scr.model.service.Settings;
import scr.view.GameInterface;

public class MenuSettings {
    Settings settings;


    public MenuSettings(Settings settings) {
        this.settings = settings;
    }


    public void iniciar() {
        int choice = 0;
        while (choice != 4) {
            choice = getSettingsMenuOption();
            if (choice == 1) {
                settings.setAutosave();
            } else if (choice == 2) {
                settings.changeColorNumber();
                GameInterface.changeColor(settings.getColorNum());
            } else if (choice == 3) {
                settings.changeTextSpeed();
            }
            GameInterface.clearConsole();
        }
        SaveManager.saveSettings(settings);
        GameInterface.clearConsole();
        Menu menu = new Menu();
        menu.iniciar();
    }

    public int getSettingsMenuOption() {
        while (true) {
            try {
                GameInterface.showDefinitions(settings.isAutoSave(), settings.getColorNum(), settings.getTextSpeed());
                String userInput = GameInterface.getTextInput();
                int number = Integer.parseInt(userInput.trim());
                if (number > 0 && number <= 4) {
                    return number;
                } else {
                    GameInterface.clearConsole();
                    GameInterface.showTextLn("Digite números entre 1 e 4");
                }
            } catch (NumberFormatException e) {
                GameInterface.showMenuInvalidInput();
            }
        }
    }
}
