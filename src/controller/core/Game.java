package src.controller.core;

import src.model.repository.SaveManager;
import src.model.repository.StoryBuilder;
import src.model.service.Collection;
import src.model.service.GameState;
import src.model.service.SaveState;
import src.model.service.Settings;
import src.model.service.logic.Chapter;
import src.model.service.logic.Scene;
import src.view.GameInterface;

public class Game {
    private GameState state;
    private int textSpeed;
    private Collection collection;

    /**
     * Método construtor. Cria um novo GameState adicionando os personagens
     * e os capítulos.
     */
    public Game(SaveState loadedData, Collection collection) {
        this.collection = collection;

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

                // Passa um dialogo até o outro
                while (!currentScene.isFinishedAllDialogues()) {
                    GameInterface.showDialogue(currentScene.nextDialogue());
                    littleStop(textSpeed);
                }

                GameInterface.showTextLn("");

                // Se chegar a um dos finais
                if (currentScene.isEnding()) {
                    collection.onFinalReached(currentChapter.getCurrentScene().getSceneId());
                    state.setRunning(false);
                    break;
                }

                // Passa as opções de dialogo
                if (currentScene.choicesSize() != 0) {
                    GameInterface.showTextLn(currentScene.getChoicesText());
                    GameInterface.showPlayerStats(state.getOrfeu().toString());
                    GameInterface.showPromptChoice();

                    // 3. Passe a currentSceneId para o getInputGame poder salvar a cena certa
                    int choiceId = GameInput.getInputGame(state, currentScene.choicesSize(), currentScene.getSceneId());

                    String currentSceneId = ApplyConsequences.applyConsequences(
                            state.getOrfeu(),
                            state.getNpcs(),
                            currentScene.getChoice(choiceId));
                    currentChapter.changeCurrentScene(currentSceneId);
                    GameInterface.clearConsole();

                } else {
                    // Verifica condições
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
            // Fim de capítulo
            if (state.isRunning()) {
                GameInterface.waitForEnter("\n[Fim do Capítulo. Pressione ENTER para avançar...]");
                GameInterface.clearConsole();

                int chapterNumber = state.getCurrentChapterIndex();
                collection.onChapterReached(chapterNumber);

                state.advanceChapter();

                // AUTO-SAVE: Salva o jogo no início do novo capítulo
                if (state.isRunning() && settings.isAutoSave()) {
                    // Passamos "null" para a cena, assim o jogo carrega a primeira cena padrão do
                    // capítulo
                    GameSave.saveCurrentProgress(state, state.getCurrentChapter().getCurrentScene().getNextSceneId(), -1);
                    GameInterface.printWarning("[!] Jogo salvo automaticamente.");
                }
            }
        }
        
        GameInterface.clearConsole();
        GameInterface.showGameFinished();
        GameInterface.waitForEnter("\nPressione ENTER para voltar ao Menu Principal...");
        GameInterface.clearConsole();
    }


    private void littleStop(int milissegundos) {
        try {
            Thread.sleep(milissegundos);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }


    public void setTextSpeed(int speed) {
        textSpeed = speed;
    }

}