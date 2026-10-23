package src.controller.core;

import src.model.repository.SaveManager;
import src.model.repository.StoryBuilder;
import src.model.service.Chapter;
import src.model.service.GameState;
import src.model.service.SaveState;
import src.model.service.Scene;
import src.model.service.Settings;
import src.view.GameInterface;

public class Game {
    private GameState state;
    private int currentSaveSlot;
    private int textSpeed;

    /**
     * Método construtor. Cria um novo GameState adicionando os personagens
     * e os capítulos.
     */
    public Game(int saveSlot, SaveState loadedData) {
        this.currentSaveSlot = saveSlot;

        this.state = new GameState(
                loadedData.orfeu,
                loadedData.npcs,
                StoryBuilder.getChapters());

        // Avança silenciosamente até o capítulo salvo
        for (int i = 0; i < loadedData.chapterIndex; i++) {
            this.state.advanceChapter();
        }

        // Pula para a cena salva
        Chapter currentChapter = this.state.getCurrentChapter();
        if (currentChapter != null && loadedData.sceneId != null) {
            currentChapter.changeCurrentScene(loadedData.sceneId);
        }
    }

    /**
     * É o Core do jogo, é a lógica de funcionamento.
     * Mostra falas, passa cenas e capítulos de acordo com decisões
     */
    public void gameRunning() {
        Settings settings = SaveManager.loadSettings();
        while (state.isRunning()) {
            Chapter currentChapter = state.getCurrentChapter();
            GameInterface.showChapterTitle(currentChapter.getTitle());

            while (!state.isCurrentScenesFinished()) {

                Scene currentScene = currentChapter.getCurrentScene();

                while (!currentScene.isFinishedAllDialogues()) {
                    GameInterface.showDialogue(currentScene.nextDialogue());
                    littleStop(textSpeed);
                }

                GameInterface.showTextLn("");

                if (currentScene.isEnding()) {
                    state.setRunning(false);
                    break;
                }

                if (currentScene.choicesSize() != 0) {
                    GameInterface.showTextLn(currentScene.getChoicesText());
                    GameInterface.showPlayerStats(state.getOrfeu().toString());
                    GameInterface.showPromptChoice();

                    // 3. Passe a currentSceneId para o getNumberInput poder salvar a cena certa
                    int choiceId = getInput(currentScene.choicesSize(), currentScene.getSceneId());

                    String currentSceneId = ApplyConsequences.applyConsequences(
                            state.getOrfeu(),
                            state.getNpcs(),
                            currentScene.getChoice(choiceId));
                    currentChapter.changeCurrentScene(currentSceneId);
                    GameInterface.clearConsole();
                } else {
                    if (currentScene.getCondition() != null) {
                        String currentSceneId = StatVerify.selectScene(state.getOrfeu(), state.getNpcs(), currentScene);
                        if (currentSceneId != null) {
                            currentChapter.changeCurrentScene(currentSceneId);
                        } else if (currentScene.getNextSceneId() != null) {
                            currentChapter.changeCurrentScene(currentScene.getNextSceneId());
                        } else {
                            currentChapter.nextScene();
                        }
                    } else if (currentScene.getNextSceneId() != null) {
                        currentChapter.changeCurrentScene(currentScene.getNextSceneId());
                    } else {
                        currentChapter.nextScene();
                    }
                }
            }
            if (state.isRunning()) {
                GameInterface.waitForEnter("\n[Fim do Capítulo. Pressione ENTER para avançar...]");
                GameInterface.clearConsole();

                // Avança para o próximo capítulo no GameState
                state.advanceChapter();

                // AUTO-SAVE: Salva o jogo no início do novo capítulo
                if (state.isRunning() && settings.isAutoSave()) {
                    // Passamos "null" para a cena, assim o jogo carrega a primeira cena padrão do
                    // capítulo
                    saveCurrentProgress(state.getCurrentChapter().getCurrentScene().getNextSceneId(), -1);
                    GameInterface.printWarning("[!] Jogo salvo automaticamente no Slot " + this.currentSaveSlot + ".");
                }
            }
        }
        GameInterface.clearConsole();
        GameInterface.showGameFinished();
        GameInterface.waitForEnter("\nPressione ENTER para voltar ao Menu Principal...");
        GameInterface.clearConsole();
    }

    /**
     * Recebe um valor máximo e recebe uma entrada do usuário.
     * Faz a conversão em inteiro e retorna um valor dentro do máximo.
     * 
     * @param maxChoices int
     * @return escolha int
     */
    private int getInput(int maxChoices, String currentSceneId) {
        int choice;
        while (true) {
            String userInput = GameInterface.getTextInput().trim();

            // Lógica interceptadora do Save
            if (userInput.equalsIgnoreCase("salvar")) {
                boolean control = true;
                while (control) {
                    GameInterface.showText("Escolha o slot: ");
                    int userSlot = getNumericInput(8); // NUMERO MAXIMO DE SLOTS! - Simeony
                    SaveState temp = SaveManager.loadGame(userSlot);
                    if (temp.sceneId == null) {
                        saveCurrentProgress(currentSceneId);
                        GameInterface.showTextLn("\n[!] Jogo salvo no Slot " + this.currentSaveSlot + " com sucesso!");
                        GameInterface.showPromptChoice(); // Mostra o prompt de novo para o jogador poder continuar
                        control = false;
                        continue; // Volta ao início do while(true) sem avançar a história
                    } else {
                        GameInterface.showOverwriteQuestion();
                        String confirmation = GameInterface.getTextInput().trim();
                        if (confirmation.equalsIgnoreCase("S")) {
                            saveCurrentProgress(currentSceneId);
                            GameInterface
                                    .showTextLn("\n[!] Jogo salvo no Slot " + this.currentSaveSlot + " com sucesso!");
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

    private int getNumericInput(int maxChoices) {
        int choice;
        while (true) {
            String userInput = GameInterface.getTextInput();
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

    private void littleStop(int milissegundos) {
        try {
            Thread.sleep(milissegundos);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    private void saveCurrentProgress(String currentSceneId) {
        SaveState saveInfo = new SaveState(
                this.state.getOrfeu(),
                this.state.getNpcs(),
                this.state.getCurrentChapterIndex(),
                currentSceneId);
        SaveManager.saveGame(saveInfo, this.currentSaveSlot);
    }

    private void saveCurrentProgress(String currentSceneId, int slot) {
        SaveState saveInfo = new SaveState(
                this.state.getOrfeu(),
                this.state.getNpcs(),
                this.state.getCurrentChapterIndex(),
                currentSceneId);
        SaveManager.saveGame(saveInfo, slot);
    }


    public void setTextSpeed(int speed) {
        textSpeed = speed;
    }

}