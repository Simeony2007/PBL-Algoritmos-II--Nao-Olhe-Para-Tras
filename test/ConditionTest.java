package test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import src.model.service.Condition;
import src.model.service.Npc;
import src.model.service.Orfeu;

public class ConditionTest {
    private Condition condition;
    private Condition condition2;
    private Orfeu orfeu;
    private ArrayList<Npc> npcs1;
    private ArrayList<Npc> npcs2;

    @Before 
    public void setUp(){
        condition = new Condition("NOVACENA", "NPC1", 1, 2, 3, 4, 5);
        condition2 = new Condition("NOVACENA", null, 1, 2, 3, 4, 5);
        orfeu = new Orfeu(10, 5, 2, 1);
        npcs1 = new ArrayList<>();
        npcs2 = new ArrayList<>();
        npcs1.add(new Npc("NPC1", 5));
        npcs1.add(new Npc("NPC2", 10));
        npcs1.add(new Npc("NPC3", 20));
        npcs2.add(new Npc("NPC0", 0));
    }

    @Test
    public void isMetTest(){
        assertTrue(condition.isMet(orfeu, npcs1));
        assertFalse(condition.isMet(orfeu, npcs2));
        assertTrue(condition2.isMet(orfeu, npcs1));

    }

    @Test
    public void testGetters(){
        // Testa os getters do primeiro objeto (condition)
        assertEquals("NOVACENA", condition.getNextSceneId());
        assertEquals("NPC1", condition.getNpcName());
        assertEquals(1, condition.getType());
        assertEquals(2, condition.getAffinity());
        assertEquals(3, condition.getLove());
        assertEquals(4, condition.getSadness());
        assertEquals(5, condition.getAngry());

        // Testa os getters do segundo objeto (condition2 lidando com null)
        assertEquals("NOVACENA", condition2.getNextSceneId());
        assertNull(condition2.getNpcName());
        assertEquals(1, condition2.getType());
        assertEquals(2, condition2.getAffinity());
        assertEquals(3, condition2.getLove());
        assertEquals(4, condition2.getSadness());
        assertEquals(5, condition2.getAngry());
    }
}
