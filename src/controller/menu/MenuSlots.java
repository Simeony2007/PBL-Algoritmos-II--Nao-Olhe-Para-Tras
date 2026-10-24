package src.controller.menu;

import java.util.HashMap;
import java.util.Map;

import src.controller.core.Game;
import src.model.repository.SaveManager;
import src.model.service.SaveState;
import src.model.service.Settings;
import src.model.service.Collection;
import src.view.MenuInterface;

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
        MenuInterface.showLoadMenu(getAllSavesInformation());

        MenuInterface.showText("\nSelecione o número do Slot que deseja gerenciar (1 a 8): ");
        int slot = getSlotInput();

        if (slot > 3 || !SaveManager.doesSaveExist(slot)) {
            startGame(slot);
            return;
        }
        else{
            MenuInterface.showSlotSelection(slot);
        }

            int action = getOptionInput(1, 3);

            switch (action) {
                case 1:
                    SaveState loadedState = SaveManager.loadGame(slot);
                    game = new Game(loadedState, collection, settings);
                    game.setTextSpeed(settings.getTextSpeed());
                    game.gameRunning();
                    break;
                case 2:
                    SaveManager.deleteSave(slot);
                    MenuInterface.showTextLn("\n[!] Save do slot " + slot + " deletado com sucesso!\n");
                    break;
                case 3:
                    return; // Retorna para o menu anterior
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
                String userInput = MenuInterface.getTextInput();
                int number = Integer.parseInt(userInput.trim());
                if (number >= min && number <= max) {
                    return number;
                } else {
                    MenuInterface.showText("\n[!] Opção inválida! Digite um número de " + min + " a " + max + ".\nOpção: ");
                }
            } catch (NumberFormatException e) {
                MenuInterface.showMenuInvalidInput();            
            }
        }
    }

    private int getSlotInput() {
        while (true) {
            try {
                String userInput = MenuInterface.getTextInput();
                int number = Integer.parseInt(userInput.trim());
                if (number >= 1 && number <= 8) {
                    return number;
                } else {
                    MenuInterface.showText("\n[!] Slot inválido! Digite um número de 1 até 8.\n\nSlot: ");
                }
            } catch (NumberFormatException e) {
                MenuInterface.showMenuInvalidInput();
            }
        }
    }

    private void startGame(int slot) {
        SaveState loadedState = SaveManager.loadGame(slot);
        game = new Game(loadedState, collection, settings);
        game.setTextSpeed(settings.getTextSpeed());
        game.gameRunning();
    }
    
}