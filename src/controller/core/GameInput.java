package src.controller.core;

import src.model.repository.SaveManager;
import src.model.service.SaveState;
import src.view.GameInterface;
import src.model.service.GameState;

public class GameInput {
    private GameInput(){}

    public static int getInputGame(GameState state, int maxChoices, String currentSceneId) {
        int choice;
        while (true) {
            String userInput = GameInterface.getTextInput().trim();

            // Lógica interceptadora do Save
            if (userInput.equalsIgnoreCase("salvar")) {
                boolean control = true;

                while (control) {
                    GameInterface.showText("Escolha o slot: ");
                    int userSlot = getNumericInput(3);
                    SaveState temp = SaveManager.loadGame(userSlot);
                    if (temp.sceneId == null) {
                        GameSave.saveCurrentProgress(state, currentSceneId, userSlot);
                        GameInterface.clearConsoleSave(3);
                        GameInterface.showTextLn("\n[!] Jogo salvo no Slot " + userSlot + " com sucesso!");
                        GameInterface.showPromptChoice(); // Mostra o prompt de novo para o jogador poder continuar
                        control = false;
                        continue; // Volta ao início do while(true) sem avançar a história
                    } else {
                        GameInterface.showOverwriteQuestion();
                        String confirmation = GameInterface.getTextInput().trim();
                        if (confirmation.equalsIgnoreCase("S")) {
                            GameSave.saveCurrentProgress(state, currentSceneId, userSlot);
                            GameInterface.clearConsoleSave(5);
                            GameInterface.showTextLn("\n[!] Jogo salvo no Slot " + userSlot + " com sucesso!");
                            GameInterface.showPromptChoice();
                            control = false;
                            continue;
                        } else {
                            GameInterface.showTextLn("Escolha outro Slot!");
                        }
                    }
                }
                userInput = GameInterface.getTextInput().trim();
            }

            // Lógica normal das escolhas
            try {
                choice = Integer.parseInt(userInput);
                if (choice > 0 && choice <= maxChoices) {
                    return choice - 1;
                } else {
                    GameInterface.showGameInvalidOption();
                }
            } catch (NumberFormatException e) {
                GameInterface.showGameInvalidInput();
            }
        }
    }

    private static int getNumericInput(int maxChoices) {
        int choice;
        while (true) {
            String userInput = GameInterface.getTextInput();
            try {
                choice = Integer.parseInt(userInput);
                if (choice > 0 && choice <= maxChoices) {
                    return choice;
                } else {
                    GameInterface.showGameInvalidOption();
                }
            } catch (NumberFormatException e) {
                GameInterface.showGameInvalidInput();
            }
        }
    }

}
