package src.controller;

import src.model.repository.SaveManager;
import src.model.service.Collection;
import src.model.service.SaveState;
import src.model.service.Settings;
import src.view.GameInterfaceJFX;

import java.util.ArrayList;
import java.util.Map;

import src.controller.legacy.core.Game;

// FACADE ENTRE O GAME E O VIEWJFX 
public class ControllerFX {

    private Game game;
    private GameInterfaceJFX view;

    public void setView(GameInterfaceJFX view) {
        this.view = view;
    }

    public void loader(int index) {
        SaveState savestate = SaveManager.loadGame(index);
        Collection collection = SaveManager.loadCollection();
        Settings settings = SaveManager.loadSettings();
        game = new Game(savestate, collection, settings);
    }

    // public void loader(int index, Collection collection) {
    // SaveState savestate = SaveManager.loadGame(index);
    // Settings settings = SaveManager.loadSettings();
    // game = new Game(savestate, collection, settings);
    // }

    public void startCurrentScene() {
        ArrayList<Map.Entry<String, String>> dialogos = DialoguesParser
                .dialogueSeparator(game.allSceneDialoguesQueue());
        System.out.println(dialogos);
        int speed = game.getTextSpeed();
        view.showDialogues(dialogos, speed);
    }

    public void endedDialogues() {
        // Verifica se é um final
        if (game.isTheEndOfGame()) {
            view.showEndGame(); // Fim do jogo, mostra tela final/sair
            return;
        }

        ArrayList<String> escolhas = game.allSceneChoices();
        if (!escolhas.isEmpty()) {
            view.showChoicesOptions(escolhas);
        } else {
            nextScene();
        }
    }

    public void choicedOption(int indexEscolha) {
        game.makeAChoice(indexEscolha);
        runChoice();
    }

    private void nextScene() {
        game.goToNextScene();
        runChoice();
    }

    private void runChoice() {
        if (game.isTheEndOfChapter()) {
            view.showEndChapter();
        } else {
            startCurrentScene();
        }
    }

    public void startNextChapter() {
        game.nextChapter();
        startCurrentScene();
    }

}
