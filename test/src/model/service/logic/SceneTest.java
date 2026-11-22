package src.model.service.logic;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.util.Arrays;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;

public class SceneTest {

    private Scene scene1;
    private Scene scene2;

    @Before
    public void setUp() {
        scene1 = new Scene("s1_intro");
        scene2 = new Scene("s2_action", "s3_next");
    }

    @Test
    public void testConstructorsAndGetters() {
        assertEquals("s1_intro", scene1.getSceneId());
        assertNull(scene1.getNextSceneId());

        assertEquals("s2_action", scene2.getSceneId());
        assertEquals("s3_next", scene2.getNextSceneId());
    }

    @Test
    public void testEndingState() {
        assertFalse(scene1.isEnding());
        scene1.setEnding();
        assertTrue(scene1.isEnding());
    }

    @Test
    public void testConditionHandling() {
        assertNull(scene1.getCondition());
        
        Condition cond = new Condition("s_target", "Charon", 1, 2, 0, 0, 0);
        scene1.setCondition(cond);
        
        assertNotNull(scene1.getCondition());
        assertEquals("s_target", scene1.getCondition().getNextSceneId());
    }

    @Test
    public void testDialoguesFlow() {
        assertTrue(scene1.isFinishedAllDialogues());

        scene1.addNewDialogue("Olá, mortal.");
        scene1.addNewDialogue("Cuidado com as sombras.", "Charon");

        assertFalse(scene1.isFinishedAllDialogues());

        Map.Entry<String, String> firstDialogue = scene1.nextDialogue();
        assertEquals("", firstDialogue.getKey());
        assertEquals("Olá, mortal.", firstDialogue.getValue());
        assertFalse(scene1.isFinishedAllDialogues());

        Map.Entry<String, String> secondDialogue = scene1.nextDialogue();
        assertEquals("Charon", secondDialogue.getKey());
        assertEquals("Cuidado com as sombras.", secondDialogue.getValue());
        assertTrue(scene1.isFinishedAllDialogues());
    }

    @Test
    public void testChoicesManagement() {
        assertEquals(0, scene1.choicesSize());

        scene1.addNewChoice("Atacar", "s_attack", 0, 0, 0, 1, 0);
        scene1.addNewChoice("Negociar", "s_negotiate", "Charon", -1, 1, 0, 0, 0);

        assertEquals(2, scene1.choicesSize());

        Choice firstChoice = scene1.getChoice(0);
        assertEquals("Atacar", firstChoice.getText());

        Choice secondChoice = scene1.getChoice(1);
        assertEquals("Negociar", secondChoice.getText());

        assertEquals(Arrays.asList("Atacar", "Negociar"), scene1.getChoicesText());
    }
}