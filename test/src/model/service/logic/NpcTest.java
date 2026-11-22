package src.model.service.logic;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotSame;

import org.junit.Before;
import org.junit.Test;

public class NpcTest {

    private Npc npc;

    @Before
    public void setUp() {
        npc = new Npc("Charon", 50);
    }

    @Test
    public void constructorAndGetters() {
        assertEquals("Charon", npc.getName());
        assertEquals(50, npc.getAffinity());
    }

    @Test
    public void changeAffinityWithinBounds() {
        npc.changeAffinity(15);
        assertEquals(65, npc.getAffinity());

        npc.changeAffinity(-10);
        assertEquals(55, npc.getAffinity());
    }

    @Test
    public void affinityIsCappedAt99() {
        npc.changeAffinity(60);
        assertEquals(99, npc.getAffinity());
    }

    @Test
    public void affinityIsFlooredAtZero() {
        npc.changeAffinity(-70);
        assertEquals(0, npc.getAffinity());
    }

    @Test
    public void cloneCopiesNpcValuesWithoutSharingInstance() {
        Npc clone = npc.clone();

        assertEquals(npc.getName(), clone.getName());
        assertEquals(npc.getAffinity(), clone.getAffinity());
        assertNotSame(npc, clone);

        clone.changeAffinity(10);
        assertEquals(50, npc.getAffinity());
        assertEquals(60, clone.getAffinity());
    }
}