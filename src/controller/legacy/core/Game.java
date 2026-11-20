package src.controller.legacy.core;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import src.model.repository.SaveManager;
import src.model.repository.StoryBuilder;
import src.model.service.Collection;
import src.model.service.GameState;
import src.model.service.SaveState;
import src.model.service.Settings;
import src.model.service.logic.Chapter;
import src.model.service.logic.Choice;
import src.model.service.logic.Npc;
import src.model.service.logic.Orfeu;
import src.model.service.logic.Scene;
import src.view.legacy.GameInterface;
import src.model.Observer;

public class Game {
    private GameState state;
    private ArrayList<Observer> observers = new ArrayList<>();
    private int textSpeed;
    private Collection collection;

    /**
     * Método construtor. Cria um novo GameState adicionando os personagens
     * e os capítulos.
     */
    public Game(SaveState loadedData, Collection collection, Settings settings) {
        this.collection = collection;

        try {
            this.state = new GameState(
                    loadedData.orfeu,
                    loadedData.npcs,
                    StoryBuilder.getChapters());
        } catch (Exception e) {
            GameInterface.showTextLn("História do jogo indisponível, não é possível jogar. Reinstale o jogo completo.");
        }

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
     * 
     * public void gameRunning() {
     * Settings settings = SaveManager.loadSettings();
     * while (state.isRunning()) {
     * Chapter currentChapter = state.getCurrentChapter();
     * GameInterface.showChapterTitle(currentChapter.getTitle());
     * 
     * while (!state.isCurrentScenesFinished()) {
     * Scene currentScene = currentChapter.getCurrentScene();
     * 
     * // Passa um dialogo até o outro
     * while (!currentScene.isFinishedAllDialogues()) {
     * GameInterface.showDialogue(currentScene.nextDialogue());
     * littleStop(textSpeed);
     * }
     * 
     * GameInterface.showTextLn("");
     * 
     * // Se chegar a um dos finais
     * if (currentScene.isEnding()) {
     * collection.onFinalReached(currentChapter.getCurrentScene().getSceneId());
     * state.setRunning(false);
     * break;
     * }
     * 
     * // Passa as opções de dialogo
     * if (currentScene.choicesSize() != 0) {
     * GameInterface.showTextLn(currentScene.getChoicesText());
     * GameInterface.showPlayerStats(state.getOrfeu().toString());
     * GameInterface.showPromptChoice();
     * 
     * // 3. Passe a currentSceneId para o getInputGame poder salvar a cena certa
     * int choiceId = GameInput.getInputGame(state, currentScene.choicesSize(),
     * currentScene.getSceneId());
     * 
     * String currentSceneId = ApplyConsequences.applyConsequences(
     * state.getOrfeu(),
     * state.getNpcs(),
     * currentScene.getChoice(choiceId));
     * currentChapter.changeCurrentScene(currentSceneId);
     * GameInterface.clearConsole();
     * 
     * } else {
     * // Verifica condições
     * if (currentScene.getCondition() != null) {
     * String currentSceneId = StatVerify.selectScene(state.getOrfeu(),
     * state.getNpcs(), currentScene);
     * if (currentSceneId != null) {
     * currentChapter.changeCurrentScene(currentSceneId);
     * } else if (currentScene.getNextSceneId() != null) {
     * currentChapter.changeCurrentScene(currentScene.getNextSceneId());
     * } else {
     * currentChapter.nextScene();
     * }
     * } else if (currentScene.getNextSceneId() != null) {
     * currentChapter.changeCurrentScene(currentScene.getNextSceneId());
     * } else {
     * currentChapter.nextScene();
     * }
     * }
     * }
     * // Fim de capítulo
     * if (state.isRunning()) {
     * GameInterface.waitForEnter("\n[Fim do Capítulo. Pressione ENTER para
     * avançar...]");
     * GameInterface.clearConsole();
     * 
     * int chapterNumber = state.getCurrentChapterIndex();
     * collection.onChapterReached(chapterNumber);
     * 
     * state.advanceChapter();
     * 
     * // AUTO-SAVE: Salva o jogo no início do novo capítulo
     * if (state.isRunning() && settings.isAutoSave()) {
     * // Passamos "null" para a cena, assim o jogo carrega a primeira cena padrão
     * do
     * // capítulo
     * GameSave.saveCurrentProgress(state,
     * state.getCurrentChapter().getCurrentScene().getNextSceneId(),
     * -1);
     * GameInterface.printWarning(
     * "[!] Jogo salvo automaticamente em autosave " +
     * (settings.getAutoSaveCounter()) + ".");
     * }
     * }
     * }
     * 
     * GameInterface.clearConsole();
     * GameInterface.showGameFinished();
     * GameInterface.waitForEnter("\nPressione ENTER para voltar ao Menu
     * Principal...");
     * GameInterface.clearConsole();
     * }
     * 
     * private void littleStop(int milissegundos) {
     * try {
     * Thread.sleep(milissegundos);
     * } catch (InterruptedException e) {
     * Thread.currentThread().interrupt();
     * }
     * }
     */
    public void setTextSpeed(int speed) {
        textSpeed = speed;
    }

    public String getCurrentSceneId() {
        Chapter currentChapter = state.getCurrentChapter();
        Scene currentScene = currentChapter.getCurrentScene();
        return currentScene.getSceneId();
    }

    public ArrayList<Map.Entry<String, String>> allSceneDialoguesQueue() {
        if (!state.isCurrentScenesFinished()) {
            Chapter currentChapter = state.getCurrentChapter();
            Scene currentScene = currentChapter.getCurrentScene();
            ArrayList<Map.Entry<String, String>> queue = new ArrayList<>();
            // Passa um dialogo até o outro
            while (!currentScene.isFinishedAllDialogues()) {
                queue.add(currentScene.nextDialogue());
            }
            return queue;
        }
        return null;
    }

    public String getCurrentChapterBgName() {
        Chapter currenChapter = state.getCurrentChapter();
        switch (currenChapter.getTitle()) {
            case "Capítulo 1 - A Entrada do Submundo":
                return "asphodel.png"; // só pra teste - Simeony
            case "Capítulo 2 - Campos de Asfódelos":
                return "asphodel.png";
            case "Capítulo 3 - Campos Elíseos":
                return "elysium.jpg";
            case "Capítulo 4 - O Tártaro":
                return "tartarus.jpg";
            case "Capítulo 6 - A volta pelo Tártaro":
                return "tartarus.jpg";
            case "Capítulo 7 - A volta por Elíseos":
                return "elysium.jpg";
            case "Capítulo 8 - A volta por Asfódelo":
                return "asphodel.png";

            default:
                return "";
        }
    }

    public ArrayList<String> allSceneChoices() {
        Chapter currentChapter = state.getCurrentChapter();
        Scene currentScene = currentChapter.getCurrentScene();
        ArrayList<String> choicesQueue = new ArrayList<>();
        if (currentScene.choicesSize() != 0) {
            for (String texto : currentScene.getChoicesText()) {
                choicesQueue.add(texto);
            }
        }
        return choicesQueue;
    }

    public void makeAChoice(int index) {
        Chapter currentChapter = state.getCurrentChapter();
        Scene currentScene = currentChapter.getCurrentScene();
        Orfeu oldOrfeu = state.getOrfeu().clone();
        ArrayList<Npc> oldNpcs = state.cloneNpcsList();
        String currentSceneId = ApplyConsequences.applyConsequences(
                state.getOrfeu(),
                state.getNpcs(),
                currentScene.getChoice(index));
        notifyObserversModelChange(oldOrfeu, oldNpcs);
        currentChapter.changeCurrentScene(currentSceneId);
    }

    public void goToNextScene() {
        Chapter currentChapter = state.getCurrentChapter();
        Scene currentScene = currentChapter.getCurrentScene();
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
            if (currentChapter.getCurrentScene() == null) {
                nextChapter();
                // currentChapter.nextScene();
            }
        }
    }

    public boolean isTheEndOfChapter() {
        return state.isCurrentScenesFinished();
    }

    public void autoSave(Settings settings) {
        if (state.isRunning() && settings.isAutoSave()) {
            GameSave.saveCurrentProgress(state, state.getCurrentChapter().getCurrentScene().getNextSceneId(), -1);
            notifyObserversSave();
        }
    }

    public boolean isTheEndOfGame() {
        Chapter currentChapter = state.getCurrentChapter();
        Scene currentScene = currentChapter.getCurrentScene();
        if (currentScene.isEnding()) {
            collection.onFinalReached(currentChapter.getCurrentScene().getSceneId());
            state.setRunning(false);
            return true;
        }
        return false;
    }

    public String getCurrentChapterTitle() {
        return state.getCurrentChapter().getTitle();
    }

    public String nextChapter() {
        int chapterNumber = state.getCurrentChapterIndex();
        collection.onChapterReached(chapterNumber);
        state.advanceChapter();
        Settings settings = SaveManager.loadSettings();
        autoSave(settings);

        return null;
    }

    public int getTextSpeed() {
        return textSpeed;
    }

    public void addObservers(Observer newObs) {
        observers.add(newObs);
    }

    public void removeObservers(Observer obs) {
        observers.remove(obs);
    }

    public void notifyObserversModelChange(Orfeu oldStatus, ArrayList<Npc> oldNpcsStatus) {
        for (Observer obs : observers) {
            obs.modelUpdated(state.getOrfeu(), state.getNpcs(), oldStatus, oldNpcsStatus);
        }
    }

    public void notifyObserversSave() {
        for (Observer obs : observers) {
            obs.saveWasMade();
        }
    }

}