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

    public void iniciar() {
        int opcao = 0;
        while (opcao != 4) {
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
                if (number > 0 && number <= 4) {
                    return number;
                } else {
                    GameInterface.showMenuInvalidOption();
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
                if (number >= 1 && number <= 3) {
                    return number;
                } else {
                    System.out.println("\n[!] Slot inválido! Digite 1, 2 ou 3.\n");
                    System.out.print("Slot: ");
                }
            } catch (NumberFormatException e) {
                System.out.println("\n[!] Entrada inválida! Digite apenas números.\n");
                System.out.print("Slot: ");
            }
        }
    }

    public void optionScanner(int optionNumber) {
        GameInterface.clearConsole();
        switch (optionNumber) {
            case 1:
                // Atualizado para perguntar o slot e carregar os dados

                GameInterface.showLoadMenu(getAllSavesInformation());
                int slot = getSlotInput();
                SaveState loadedState = SaveManager.loadGame(slot); // Carrega o JSON

                game = new Game(slot, loadedState); // Passa os dados para o jogo
                game.gameRunning();
                break;
            case 2:
                GameInterface.showInstructions();
                GameInterface.getTextInput();
                break;
            case 3:
                GameInterface.showCredits();
                GameInterface.getTextInput();
                break;
            case 4:
                GameInterface.showExitMessage();
                break;
            default:
                GameInterface.showTextLn("Erro inesperado...");
                break;
        }
        GameInterface.clearConsole();
    }

    public Map<Integer, String> getAllSavesInformation() {
        SaveManager tempSVM = new SaveManager();
        Map<Integer, String> tempDic = new HashMap<>();

        // 1. Saves manuais (1 a 3)
        for (int i = 1; i <= 3; i++) {
            SaveState j = tempSVM.loadGame(i);
            if (j != null) {
                String time = tempSVM.getSaveDateTime(i);
                String tempStr = "Slot " + i + " - Capítulo: " + j.chapterIndex + " | Cena: " + j.sceneId
                        + " | Data e Hora: " + time;
                tempDic.put(i, tempStr);
            } else {
                tempDic.put(i, "Slot " + i + ": [Vazio]");
            }
        }

        // 2. Autosaves (1 a 5 -> mapeados para as chaves 4 a 8)
        for (int i = 1; i <= 5; i++) {
            SaveState j = tempSVM.loadAutoSave(i);
            int chave = i + 3;
            if (j != null) {
                String time = tempSVM.getAutoSaveDateTime(i);
                String tempStr = "Autosave " + i + " - Capítulo: " + j.chapterIndex + " | Cena: " + j.sceneId
                        + " | Data e Hora: " + time;
                tempDic.put(chave, tempStr);
            } else {
                tempDic.put(chave, "Autosave " + i + ": [Vazio]");
            }
        }

        return tempDic;
    }
}