package src.controller.menu;

import src.model.repository.SaveManager;
import src.model.service.Settings;
import src.view.MenuInterface;

public class MenuSettings {
    Settings settings;

    public MenuSettings(Settings settings) {
        this.settings = settings;
        iniciar();
    }

    private void iniciar() {
        int choice = 0;
        while (choice != 4) {
            choice = getSettingsMenuOption();
            if (choice == 1) {
                settings.setAutosave();
            } else if (choice == 2) {
                settings.changeColorNumber();
                MenuInterface.changeColor(settings.getColorNum());
            } else if (choice == 3) {
                settings.changeTextSpeed();
            }
            MenuInterface.clearConsole();
        }
        SaveManager.saveSettings(settings);
        MenuInterface.clearConsole();
    }

    private int getSettingsMenuOption() {
        while (true) {
            try {
                MenuInterface.showSettings(settings.isAutoSave(), settings.getColorNum(), settings.getTextSpeed());
                String userInput = MenuInterface.getTextInput();
                int number = Integer.parseInt(userInput.trim());
                if (number > 0 && number <= 4) {
                    return number;
                } else {
                    MenuInterface.clearConsole();
                    MenuInterface.showTextLn("Digite números entre 1 e 4");
                }
            } catch (NumberFormatException e) {
                MenuInterface.showMenuInvalidInput();
            }
        }
    }
}