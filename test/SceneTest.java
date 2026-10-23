package test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import src.model.service.Choice;
import src.model.service.Condition;
import src.model.service.Scene;

public class SceneTest {

    private Scene scene1;
    private Scene scene2;

    @Before
    public void setUp() {
        // Construtor com apenas o ID da cena
        scene1 = new Scene("s1_intro");
        
        // Construtor com ID da cena e próxima cena padrão
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
        scene1.setEnding(true);
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
        // Testa se começa concluído quando não há diálogos
        assertTrue(scene1.isFinishedAllDialogues());

        scene1.addNewDialogue("Olá, mortal.");
        scene1.addNewDialogue("Cuidado com as sombras.", "Charon");

        assertFalse(scene1.isFinishedAllDialogues());

        // Consome o primeiro diálogo
        assertEquals("Olá, mortal.", scene1.nextDialogue());
        assertFalse(scene1.isFinishedAllDialogues());

        // Consome o segundo diálogo
        assertEquals("Charon: Cuidado com as sombras.", scene1.nextDialogue());
        assertTrue(scene1.isFinishedAllDialogues());
    }

    @Test
    public void testChoicesManagement() {
        assertEquals(0, scene1.choicesSize());

        // Adiciona escolhas usando as duas sobrecarga de métodos
        scene1.addNewChoice("Atacar", "s_attack", 0, 0, 0, 1, 0);
        scene1.addNewChoice("Negociar", "s_negotiate", "Charon", -1, 1, 0, 0, 0);

        assertEquals(2, scene1.choicesSize());

        // Valida se resgata a escolha correta pelo índice (base 0)
        Choice firstChoice = scene1.getChoice(0);
        assertEquals("Atacar", firstChoice.getText());

        Choice secondChoice = scene1.getChoice(1);
        assertEquals("Negociar", secondChoice.getText());

        // Valida o texto formatado das escolhas
        String expectedText = "1 - Atacar\n2 - Negociar\n";
        assertEquals(expectedText, scene1.getChoicesText());
    }
}