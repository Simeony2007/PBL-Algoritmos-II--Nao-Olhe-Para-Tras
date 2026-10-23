package src.controller.menu;

import src.model.repository.SaveManager;
import src.model.service.Settings;
import src.model.service.Collection;
import src.view.GameInterface;

public class Menu {
    private Settings settings;
    private Collection collection;

    public Menu() {
        iniciar();
    }

    private void iniciar() {
        settings = SaveManager.loadSettings();
        collection = SaveManager.loadCollection();
        GameInterface.changeColor(settings.getColorNum());
        int opcao = 0;
        while (opcao != 6) {
            opcao = inputOptions();
            optionScanner(opcao);
        }
    }

    private int inputOptions() {
        while (true) {
            try {
                GameInterface.showMainMenu();
                String userInput = GameInterface.getTextInput();
                int number = Integer.parseInt(userInput.trim());
                if (number > 0 && number <= 6) {
                    return number;
                } else {
                    GameInterface.showMenuInvalidOption();
                }
            } catch (NumberFormatException e) {
                GameInterface.showMenuInvalidInput();
            }
        }
    }

    private void optionScanner(int optionNumber) {
        GameInterface.clearConsole();
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
                GameInterface.showInstructions();
                GameInterface.getTextInput();
                break;
            case 5:
                GameInterface.showCredits();
                GameInterface.getTextInput();
                break;
            case 6:
                GameInterface.showExitMessage();
            default:
                GameInterface.showTextLn("Erro inesperado...");
                break;
        }
        GameInterface.clearConsole();
    }

}