package src.model.service.logic;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import org.junit.Before;
import org.junit.Test;

public class ChoiceTest {
    private Choice choice1;
    private Choice choice2;
    
    @Before
    public void setUp() {
        choice1 = new Choice("TEXTO", null, 5, 2, 3, 4, 8);
        choice2 = new Choice("TEXTO2", "CENATESTE", "NPC1", 0, 3, 0, 8, 0);
    }

    @Test
    public void gettersReturnChoiceData() {
        assertEquals("TEXTO", choice1.getText());
        assertNull(choice1.getNextSceneIdChoice());
        assertEquals(5, choice1.getObol());
        assertEquals(2, choice1.getAffinity());
        assertEquals(3, choice1.getLove());
        assertEquals(4, choice1.getAngry());
        assertEquals(8, choice1.getSadness());

        assertEquals("TEXTO2", choice2.getText());
        assertEquals("CENATESTE", choice2.getNextSceneIdChoice());
        assertEquals("NPC1", choice2.getNpcInChoice());
        assertEquals(0, choice2.getObol());
        assertEquals(3, choice2.getAffinity());
        assertEquals(0, choice2.getLove());
        assertEquals(8, choice2.getAngry());
        assertEquals(0, choice2.getSadness());
    }
}
