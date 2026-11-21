package src.controller;

import src.model.repository.SaveManager;
import src.model.service.Collection;
import src.model.service.GameState;
import src.model.service.SaveState;
import src.model.service.Settings;
import src.model.service.logic.Npc;
import src.model.service.logic.Orfeu;
import src.view.Game.GameInterfaceJFX;
import src.view.Game.NotificationOverlayJFX;
import src.model.Observer;
import src.model.customExceptions.SaveNotFoundException;

import java.util.ArrayList;
import java.util.Map;

import src.controller.legacy.core.Game;
import src.controller.legacy.core.GameSave;

// FACADE ENTRE O GAME E O VIEWJFX 
public class ControllerFX implements Observer {

    private Game game;
    private GameInterfaceJFX view;
    private Runnable onSaveDeleted;

    public ControllerFX() {
        SaveManager.addObservers(this);
    }

    public void setOnSaveDeleted(Runnable onSaveDeleted) {
        this.onSaveDeleted = onSaveDeleted;
    }

    public void setView(GameInterfaceJFX view) {
        this.view = view;
        if (game != null) {
            view.updateStatus(game.getState().getOrfeu(), game.getState().getNpcs());
        }
    }

    public void loader(int index) {
        SaveState savestate = SaveManager.loadGame(index);
        Collection collection = SaveManager.loadCollection();
        Settings settings = SaveManager.loadSettings();
        game = new Game(savestate, collection, settings);
        game.addObservers(this);
    }

    public void deleteSave(int index) throws SaveNotFoundException {
        SaveManager.deleteSave(index);
    }

    public void saveGame(int index) {
        GameSave.saveCurrentProgress(game.getState(), game.getCurrentSceneId(), index);
    }

    public void startCurrentScene() {
        ArrayList<Map.Entry<String, String>> dialogos = DialoguesParser
                .dialogueSeparator(game.allSceneDialoguesQueue());
        String nomeImagem = game.getCurrentChapterBgName();
        view.setChapterBackground(nomeImagem);
        int speed = game.getTextSpeed();
        view.showDialogues(dialogos, speed);
    }

    public void endedDialogues() {
        if (game.isTheEndOfGame()) {
            String sceneId = game.getCurrentSceneId();
            view.showEndGame(sceneId); // Mostra tela final/sair
            return;
        }

        ArrayList<String> escolhas = game.allSceneChoices();
        if (!escolhas.isEmpty()) {
            view.showChoicesOptions(escolhas);
        } else {
            if (game.isTheEndOfChapter()) {
                startNextChapter();
            }
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
            startNextChapter();
        } else {
            startCurrentScene();
        }
    }

    public void startNextChapter() {
        game.nextChapter();
        if (view.showStartNewChapter(game.getCurrentChapterTitle())) {
            startCurrentScene();
        }
    }

    @Override
    public void modelUpdated(Orfeu orfeu, ArrayList<Npc> npcs, Orfeu oldOrfeu, ArrayList<Npc> oldNpcs) {
        view.updateStatus(orfeu, npcs);
        for (int i = 0; i < npcs.size(); i++) {
            int diffAff = npcs.get(i).getAffinity() - oldNpcs.get(i).getAffinity();
            if (diffAff != 0) {
                view.showNotification(String.format("%s: %+d de afinidade", npcs.get(i).getName(), diffAff));
            }
        }

        int quantAngry = orfeu.getAngry() - oldOrfeu.getAngry();
        int quantObol = orfeu.getObol() - oldOrfeu.getObol();
        int quantLove = orfeu.getLove() - oldOrfeu.getLove();
        int quantSadness = orfeu.getSadness() - oldOrfeu.getSadness();

        if (quantAngry != 0) {
            view.showNotification(String.format("%+d de raiva para Orfeu", quantAngry));
        }
        if (quantObol != 0) {
            view.showNotification(String.format("%+d óbolo para Orfeu", quantObol));
        }
        if (quantLove != 0) {
            view.showNotification(String.format("%+d de amor para Orfeu", quantLove));
        }
        if (quantSadness != 0) {
            view.showNotification(String.format("%+d de tristeza para Orfeu", quantSadness));
        }
    }

    @Override
    public void saveWasMade() {
        view.showNotification("Jogo salvo!");
    }

    @Override
    public void saveWasDeleted() {
        onSaveDeleted.run();
    }
}
