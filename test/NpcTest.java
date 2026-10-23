package test;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import src.model.service.logic.Npc;

public class NpcTest {

    private Npc npc;

    @Before
    public void setUp() {
        npc = new Npc("Charon", 50);
    }

    @Test
    public void testConstructorAndGetters() {
        assertEquals("Charon", npc.getName());
        assertEquals(50, npc.getAffinity());
    }

    @Test
    public void testChangeAffinityNormal() {
        // Aumenta a afinidade de 50 para 65
        npc.changeAffinity(15);
        assertEquals(65, npc.getAffinity());

        // Diminui a afinidade de 65 para 55
        npc.changeAffinity(-10);
        assertEquals(55, npc.getAffinity());
    }

    @Test
    public void testChangeAffinityUpperBoundLimit() {
        // Tenta passar do limite máximo de 99
        npc.changeAffinity(60); // 50 + 60 = 110, deve travar em 99
        assertEquals(99, npc.getAffinity());
    }

    @Test
    public void testChangeAffinityLowerBoundLimit() {
        // Tenta baixar além do limite mínimo de 0
        npc.changeAffinity(-70); // 50 - 70 = -20, deve travar em 0
        assertEquals(0, npc.getAffinity());
    }
}