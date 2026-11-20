package src.controller;

import src.model.repository.SaveManager;
import src.model.service.Collection;
import src.model.service.SaveState;
import src.model.service.Settings;
import src.model.service.logic.Npc;
import src.model.service.logic.Orfeu;
import src.view.Game.GameInterfaceJFX;
import src.model.Observer;

import java.util.ArrayList;
import java.util.Map;

import src.controller.legacy.core.Game;

// FACADE ENTRE O GAME E O VIEWJFX 
public class ControllerFX implements Observer {

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
        game.addObservers(this);
    }

    // public void loader(int index, Collection collection) {
    // SaveState savestate = SaveManager.loadGame(index);
    // Settings settings = SaveManager.loadSettings();
    // game = new Game(savestate, collection, settings);
    // }

    public void startCurrentScene() {
        ArrayList<Map.Entry<String, String>> dialogos = DialoguesParser
                .dialogueSeparator(game.allSceneDialoguesQueue());
        System.out.println(dialogos); // TODO: Remover quando tudo tiver certo
        String nomeImagem = game.getCurrentChapterBgName();
        view.setChapterBackground(nomeImagem);
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

    @Override
    public void modelUpdated(Orfeu orfeu, ArrayList<Npc> npcs, Orfeu oldOrfeu, ArrayList<Npc> oldNpcs) {
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

}
