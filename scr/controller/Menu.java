package scr.controller;

import scr.view.GameInterface;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Dictionary;
import java.util.HashMap;
import java.util.Map;
import java.nio.file.attribute.FileTime;

import scr.model.repository.SaveManager; // Importe o seu SaveManager
import scr.model.service.SaveState; // Importe o seu SaveState

public class Menu {
    private Game game;
    private Settings settings;

    public void iniciar() {
        settings = SaveManager.loadSettings();
        GameInterface.changeColor(settings.collorNum);
        int opcao = 0;
        while (opcao != 5) {
            opcao = inputOptions();
            optionScanner(opcao);
        }
    }

    public int inputOptions() {
        while (true) {
            try {
                GameInterface.showMainMenu();
                String userInput = GameInterface.getTextInput();
                int number = Integer.parseInt(userInput.trim());
                if (number > 0 && number <= 5) {
                    return number;
                } else {
                    GameInterface.showMenuInvalidOption();
                }
            } catch (NumberFormatException e) {
                GameInterface.showMenuInvalidInput();
            }
        }
    }

    public int getSettingsMenuOption() {
        while (true) {
            try {
                GameInterface.showDefinitions(settings.autoSave, settings.collorNum);
                String userInput = GameInterface.getTextInput();
                int number = Integer.parseInt(userInput.trim());
                if (number > 0 && number <= 3) {
                    return number;
                } else {
                    GameInterface.showTextLn("Digite números entre 1 e 3!");
                }
            } catch (NumberFormatException e) {
                GameInterface.showMenuInvalidInput();
            }
        }
    }

    // Método novo para validar a escolha do Slot (1 a 3)
    public int getSlotInput() {
        while (true) {
            try {
                String userInput = GameInterface.getTextInput();
                int number = Integer.parseInt(userInput.trim());
                if (number >= 1 && number <= 8) {
                    return number;
                } else {
                    GameInterface.showText("\n[!] Slot inválido! Digite um número de 1 até 8.\n\n");
                    GameInterface.showText("Slot: ");
                }
            } catch (NumberFormatException e) {
                GameInterface.showText("\n[!] Entrada inválida! Digite apenas números.\n\n");
                GameInterface.showText("Slot: ");
            }
        }
    }

    public void optionScanner(int optionNumber) {
        int choice = 0;
        GameInterface.clearConsole();
        switch (optionNumber) { // PRECISAMOS ADICIONAR O SOBRESCREVER E APAGAR SAVE!
            case 1: // Acho que uma nova aba pra "Saves" é uma boa!
                    // Acho q uma das configs pode ser a língua tbm - Simeony

                // Atualizado para perguntar o slot e carregar os dados
                GameInterface.showLoadMenu(getAllSavesInformation());
                int slot = getSlotInput();
                SaveState loadedState = SaveManager.loadGame(slot); // Carrega o JSON

                game = new Game(slot, loadedState); // Passa os dados para o jogo
                game.gameRunning();
                break;
            case 2:
                GameInterface.showLoadMenu(getAllSavesInformation());
                // SaveManager.deleteSave(); // ADICIONAR AQUI O SLOT - Simeony
                break;
            case 3:
                GameInterface.showInstructions();
                GameInterface.getTextInput();
                break;
            case 4:
                GameInterface.showCredits();
                GameInterface.getTextInput();
                break;
            case 5:
                while (choice != 3) {
                    choice = getSettingsMenuOption();
                    if (choice == 1) {
                        settings.setAutosave();
                    } else if (choice == 2) {
                        settings.changeColorNumber();
                    }
                }
                // Forma de mudar as settings quase pronta - Simeony
                SaveManager.saveSettings(settings);
                GameInterface.changeColor(settings.collorNum);
                // Falta o autosave verificar a variavel autosave tbm - Simeony
                break;
            case 6:
                GameInterface.showExitMessage();
            default:
                GameInterface.showTextLn("Erro inesperado...");
                break;
        }
        GameInterface.clearConsole();
    }

    public Map<Integer, String> getAllSavesInformation() {
        Map<Integer, String> tempDic = new HashMap<>();

        // 1. Saves manuais (1 a 3)
        for (int i = 1; i <= 3; i++) {
            SaveState j = SaveManager.loadGame(i);
            if (j != null && SaveManager.getSaveDateTime(i) != null) {
                String time = SaveManager.getSaveDateTime(i);
                String tempStr = "Slot " + i + " - Capítulo: " + (j.chapterIndex + 1) + " | Cena: " + j.sceneId
                        + " | Data e Hora: " + time;
                tempDic.put(i, tempStr);
            } else {
                tempDic.put(i, "Slot " + i + ": [Vazio]");
            }
        }

        // 2. Autosaves (1 a 5 -> mapeados para as chaves 4 a 8)
        for (int i = 1; i <= 5; i++) {
            int chave = i + 3;
            SaveState j = SaveManager.loadGame(chave);
            if (j != null && SaveManager.getAutoSaveDateTime(i) != null) {
                String time = SaveManager.getAutoSaveDateTime(i);
                String tempStr = "Autosave " + i + " - Capítulo: " + (j.chapterIndex + 1) + " | Cena: " + j.sceneId
                        + " | Data e Hora: " + time;
                tempDic.put(chave, tempStr);
            } else {
                tempDic.put(chave, "Autosave " + i + ": [Vazio]");
            }
        }

        return tempDic;
    }
}