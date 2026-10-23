package test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import scr.model.service.GameState;
import scr.model.service.Orfeu;
import scr.model.service.Npc;
import scr.model.service.Chapter;

public class GameStateTest {

    private GameState gameState;
    private Orfeu orfeu;
    private ArrayList<Npc> npcs;
    private ArrayList<Chapter> chapters;

    @Before 
    public void setUp(){
        // Instancia as listas e objetos auxiliares primeiro
        orfeu = new Orfeu(10, 8, 5, 2);
        npcs = new ArrayList<>();
        chapters = new ArrayList<>();

        npcs.add(new Npc("NPC1", 10));
        npcs.add(new Npc("NPC2", 0));
        
        chapters.add(new Chapter("Capítulo 1"));
        chapters.add(new Chapter("Capítulo 2"));

        // Inicializa o GameState com os dados válidos
        gameState = new GameState(orfeu, npcs, chapters);
    }

    @Test 
    public void testInitialState(){
        // Testa se os getters retornam os objetos corretos na inicialização
        assertEquals(orfeu, gameState.getOrfeu());
        assertEquals(npcs.get(0), gameState.getNpcs().get(0));
        assertEquals(npcs.get(1), gameState.getNpcs().get(1));
        assertEquals("Capítulo 1", gameState.getCurrentChapter().getTitle());
        assertTrue(gameState.isRunning());
        gameState.advanceChapter();
        assertEquals("Capítulo 2", gameState.getCurrentChapter().getTitle());
        gameState.advanceChapter();
        assertFalse(gameState.isRunning());
        assertEquals(null, gameState.getCurrentChapter());
    }

    @Test
    public void testRunningState(){
        assertTrue(gameState.isRunning());

        // Altera o estado de execução manualmente e valida
        gameState.setRunning(false);
        assertFalse(gameState.isRunning());
    }

    @Test
    public void testIsCurrentScenesFinished(){
        // Se o jogo não estiver rodando, deve retornar true
        gameState.setRunning(false);
        assertTrue(gameState.isCurrentScenesFinished());

        // Reativa e testa com um capítulo válido cujas cenas terminaram
        gameState.setRunning(true);
        // Como o capítulo recém-criado não tem cenas ou elas estão vazias, deve retornar true
        assertTrue(gameState.isCurrentScenesFinished());
    }
}