package scr.model.service;

import java.util.ArrayList;

public class GameState {
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