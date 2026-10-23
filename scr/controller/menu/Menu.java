package scr.controller;

import scr.view.GameInterface;

import scr.model.repository.SaveManager; // Importe o seu SaveManager
import scr.model.service.Settings;

public class Menu {
    private Settings settings;

    public Menu() {
        iniciar();
    }

    private void iniciar() {
        settings = SaveManager.loadSettings();
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
                loadMenuSlots().manageSaves();
                break;
            case 2:
                new MenuSettings(settings);
                break;
            case 3:
                // COLEÇÃO AINDA NÃO IMPLEMENTADA
                GameInterface.showTextLn("\n[!] Coleção ainda não implementada.\n");
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

    private MenuSlots loadMenuSlots() {
        return new MenuSlots(settings);
    }
}