package src.controller.menu;

import src.model.repository.SaveManager;
import src.model.service.Settings;
import src.model.service.Collection;
import src.view.MenuInterface;

public class Menu {
    private Settings settings;
    private Collection collection;

    public Menu() {
        iniciar();
    }

    private void iniciar() {
        settings = SaveManager.loadSettings();
        collection = SaveManager.loadCollection();
        MenuInterface.changeColor(settings.getColorNum());
        int opcao = 0;
        while (opcao != 6) {
            opcao = inputOptions();
            optionScanner(opcao);
        }
    }

    private int inputOptions() {
        while (true) {
            try {
                MenuInterface.showMainMenu();
                String userInput = MenuInterface.getTextInput();
                int number = Integer.parseInt(userInput.trim());
                if (number > 0 && number <= 6) {
                    return number;
                } else {
                    MenuInterface.showMenuInvalidOption();
                }
            } catch (NumberFormatException e) {
                MenuInterface.showMenuInvalidInput();
            }
        }
    }

    private void optionScanner(int optionNumber) {
        MenuInterface.clearConsole();
        switch (optionNumber) {
            case 1:
                new MenuSlots(settings, collection);
                break;
            case 2:
                new MenuSettings(settings);
                break;
            case 3:
                new MenuCollection(collection);
                break;
            case 4:
                MenuInterface.showInstructions();
                MenuInterface.getTextInput();
                break;
            case 5:
                MenuInterface.showCredits();
                MenuInterface.getTextInput();
                break;
            case 6:
                MenuInterface.showExitMessage();
            default:
                MenuInterface.showTextLn("Erro inesperado...");
                break;
        }
        MenuInterface.clearConsole();
    }

}