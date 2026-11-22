package src.model.service.logic;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

public class ConditionTest {
    private Orfeu orfeu;
    private ArrayList<Npc> npcs;

    @Before 
    public void setUp() {
        orfeu = new Orfeu(10, 5, 10, 7);
        npcs = new ArrayList<>();
        npcs.add(new Npc("NPC1", 10));
    }

    @Test
    public void checksAttributesWhenNoNpcIsSpecified() {
        assertTrue(new Condition("love", null, 1, 0, 5, 0, 0).isMet(orfeu, npcs));
        assertFalse(new Condition("love", null, 1, 0, 6, 0, 0).isMet(orfeu, npcs));
        assertTrue(new Condition("sadness", null, 2, 0, 0, 7, 0).isMet(orfeu, npcs));
        assertTrue(new Condition("anger", null, 3, 0, 0, 0, 10).isMet(orfeu, npcs));
        assertFalse(new Condition("unknown", null, 0, 0, 0, 0, 0).isMet(orfeu, npcs));
    }

    @Test
    public void checksNpcAffinityAndHandlesMissingNpc() {
        assertTrue(new Condition("next", "NPC1", 1, 10, 0, 0, 0).isMet(orfeu, npcs));
        assertFalse(new Condition("next", "NPC1", 1, 11, 0, 0, 0).isMet(orfeu, npcs));
        assertFalse(new Condition("next", "missing", 1, 0, 0, 0, 0).isMet(orfeu, npcs));
    }

    @Test
    public void gettersReturnConditionData() {
        Condition condition = new Condition("next", null, 2, 3, 4, 5, 6);
        assertEquals("next", condition.getNextSceneId());
        assertNull(condition.getNpcName());
        assertEquals(2, condition.getType());
        assertEquals(3, condition.getAffinity());
        assertEquals(4, condition.getLove());
        assertEquals(5, condition.getSadness());
        assertEquals(6, condition.getAngry());
    }
}
