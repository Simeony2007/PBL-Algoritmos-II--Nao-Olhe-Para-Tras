package src.model.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotSame;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import src.model.service.logic.Chapter;
import src.model.service.logic.Npc;
import src.model.service.logic.Orfeu;

public class GameStateTest {

    private GameState gameState;
    private Orfeu orfeu;
    private ArrayList<Npc> npcs;
    private ArrayList<Chapter> chapters;

    @Before 
    public void setUp() {
        orfeu = new Orfeu(10, 8, 5, 2);
        npcs = new ArrayList<>();
        chapters = new ArrayList<>();

        npcs.add(new Npc("NPC1", 10));
        npcs.add(new Npc("NPC2", 0));

        chapters.add(new Chapter("Capítulo 1"));
        chapters.add(new Chapter("Capítulo 2"));

        gameState = new GameState(orfeu, npcs, chapters);
    }

    @Test 
    public void startsAtFirstChapterAndAdvancesUntilFinished() {
        assertEquals(orfeu, gameState.getOrfeu());
        assertEquals(npcs.get(0), gameState.getNpcs().get(0));
        assertEquals(npcs.get(1), gameState.getNpcs().get(1));
        assertEquals(0, gameState.getCurrentChapterIndex());
        assertEquals("Capítulo 1", gameState.getCurrentChapter().getTitle());
        assertTrue(gameState.isRunning());

        gameState.advanceChapter();
        assertEquals("Capítulo 2", gameState.getCurrentChapter().getTitle());
        gameState.advanceChapter();
        assertFalse(gameState.isRunning());
        assertNull(gameState.getCurrentChapter());
    }

    @Test
    public void runningCanBeStopped() {
        assertTrue(gameState.isRunning());
        gameState.setRunning(false);
        assertFalse(gameState.isRunning());
        assertTrue(gameState.isCurrentScenesFinished());
    }

    @Test
    public void emptyCurrentChapterHasFinishedScenes() {
        assertTrue(gameState.isCurrentScenesFinished());
    }

    @Test
    public void cloneNpcListCopiesNpcObjects() {
        ArrayList<Npc> clonedNpcs = gameState.cloneNpcsList();

        assertEquals(npcs.size(), clonedNpcs.size());
        assertEquals(npcs.get(0).getName(), clonedNpcs.get(0).getName());
        assertEquals(npcs.get(0).getAffinity(), clonedNpcs.get(0).getAffinity());
        assertNotSame(npcs, clonedNpcs);
        assertNotSame(npcs.get(0), clonedNpcs.get(0));

        clonedNpcs.get(0).changeAffinity(5);
        assertEquals(10, npcs.get(0).getAffinity());
        assertEquals(15, clonedNpcs.get(0).getAffinity());
    }
}