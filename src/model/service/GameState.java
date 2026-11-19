package src.model.service;

import java.util.ArrayList;

import src.model.Observer;
import src.model.service.logic.Chapter;
import src.model.service.logic.Npc;
import src.model.service.logic.Orfeu;

public class GameState {
    private ArrayList<Observer> observers = new ArrayList<>();
    private Orfeu orfeu;
    private ArrayList<Npc> npcs;
    private ArrayList<Chapter> chapters;
    private int currentChapterIndex;
    private boolean isRunning;

    // O Construtor recebe os dados iniciais do jogo
    public GameState(Orfeu orfeu, ArrayList<Npc> npcs, ArrayList<Chapter> chapters) {
        this.orfeu = orfeu;
        this.npcs = npcs;
        this.chapters = chapters;
        this.currentChapterIndex = 0;
        this.isRunning = true;
    }

    public void addObservers(Observer newObs) {
        observers.add(newObs);
    }

    public void removeObservers(Observer obs) {
        observers.remove(obs);
    }

    public void notifyObservers() {
        for (Observer obs : observers) {
            obs.modelUpdated(orfeu);
        }
    }

    public Orfeu getOrfeu() {
        return orfeu;
    }

    public ArrayList<Npc> getNpcs() {
        return npcs;
    }

    public Chapter getCurrentChapter() {
        if (currentChapterIndex < chapters.size()) {
            return chapters.get(currentChapterIndex);
        }
        return null;
    }

    public int getCurrentChapterIndex() {
        return currentChapterIndex;
    }

    public void advanceChapter() {
        this.currentChapterIndex++;
    }

    public boolean isRunning() {
        return isRunning && currentChapterIndex < chapters.size();
    }

    public void setRunning(boolean running) {
        this.isRunning = running;
    }

    public boolean isCurrentScenesFinished() {
        if (!isRunning) {
            return true;
        }
        Chapter current = getCurrentChapter();
        return current == null || current.isScenesFinished();
    }

}