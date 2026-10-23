package src.controller.menu;

import java.util.HashMap;
import java.util.Map;

import src.controller.core.Game;
import src.model.repository.SaveManager;
import src.model.service.SaveState;
import src.model.service.Settings;
import src.model.service.Collection;
import src.view.GameInterface;

public class MenuSlots {
    Game game;
    Settings settings;
    Collection collection;

    public MenuSlots(Settings settings, Collection collection) {
        this.settings = settings;
        this.collection = collection;
        manageSaves();
    }

    public void manageSaves() {
        GameInterface.showLoadMenu(getAllSavesInformation());

        GameInterface.showText("\nSelecione o número do Slot que deseja gerenciar (1 a 8): ");
        int slot = getSlotInput();

        if (slot > 3 || !SaveManager.doesSaveExist(slot)) {
            startGame(slot);
            return;
        }
        else{
            GameInterface.showText("\n--- SLOT " + slot + " SELECIONADO ---");
            GameInterface.showText("\n[1] Jogar");
            GameInterface.showText("\n[2] Apagar Save");
            GameInterface.showText("\n[3] Voltar ao Menu");
            GameInterface.showText("\nEscolha uma opção: ");

            int action = getOptionInput(1, 3);

            switch (action) {
                case 1:
                    SaveState loadedState = SaveManager.loadGame(slot);
                    game = new Game(slot, loadedState, collection);
                    game.setTextSpeed(settings.getTextSpeed());
                    game.gameRunning();
                    break;
                case 2:
                    SaveManager.deleteSave(slot);
                    GameInterface.showTextLn("\n[!] Save do slot " + slot + " deletado com sucesso!\n");
                    break;
                case 3:
                    return; // Retorna para o menu anterior
            }
        }
    }

    private Map<Integer, String> getAllSavesInformation() {
        Map<Integer, String> tempDic = new HashMap<>();

        // 1. Saves manuais (1 a 3)
        for (int i = 1; i <= 3; i++) {
            SaveState j = SaveManager.loadGame(i);
            if (j != null && SaveManager.getSaveDateTime(i) != null) {
                String time = SaveManager.getSaveDateTime(i);
                String tempStr = i + " - Slot " + i + " - Capítulo: " + (j.chapterIndex + 1) + " | Cena: " + j.sceneId
                        + " | Data e Hora: " + time;
                tempDic.put(i, tempStr);
            } else {
                tempDic.put(i, i + " - Slot " + i + ": [Vazio]");
            }
        }

        // 2. Autosaves (1 a 5 -> mapeados para as chaves 4 a 8)
        for (int i = 1; i <= 5; i++) {
            int chave = i + 3;
            SaveState j = SaveManager.loadGame(chave);
            if (j != null && SaveManager.getAutoSaveDateTime(i) != null) {
                String time = SaveManager.getAutoSaveDateTime(i);
                String tempStr = chave + " - Autosave " + i + " - Capítulo: " + (j.chapterIndex + 1) + " | Cena: " + j.sceneId
                        + " | Data e Hora: " + time;
                tempDic.put(chave, tempStr);
            } else {
                tempDic.put(chave, chave + " - Autosave " + i + ": [Vazio]");
            }
        }

        return tempDic;
    }

    private int getOptionInput(int min, int max) {
        while (true) {
            try {
                String userInput = GameInterface.getTextInput();
                int number = Integer.parseInt(userInput.trim());
                if (number >= min && number <= max) {
                    return number;
                } else {
                    GameInterface.showText("\n[!] Opção inválida! Digite um número de " + min + " a " + max + ".\nOpção: ");
                }
            } catch (NumberFormatException e) {
                GameInterface.showText("\n[!] Entrada inválida! Digite apenas números.\nOpção: ");
            }
        }
    }

    private int getSlotInput() {
        while (true) {
            try {
                String userInput = GameInterface.getTextInput();
                int number = Integer.parseInt(userInput.trim());
                if (number >= 1 && number <= 8) {
                    return number;
                } else {
                    GameInterface.showText("\n[!] Slot inválido! Digite um número de 1 até 8.\n\nSlot: ");
                }
            } catch (NumberFormatException e) {
                GameInterface.showText("\n[!] Entrada inválida! Digite apenas números.\n\nSlot: ");
            }
        }
    }

    private void startGame(int slot) {
        SaveState loadedState = SaveManager.loadGame(slot);
        game = new Game(slot, loadedState, collection);
        game.setTextSpeed(settings.getTextSpeed());
        game.gameRunning();
    }

}