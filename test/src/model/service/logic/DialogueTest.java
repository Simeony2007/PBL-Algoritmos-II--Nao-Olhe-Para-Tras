package src.model.service.logic;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

public class DialogueTest {
    private Dialogue dialogue1;
    private Dialogue dialogue2;
    private Dialogue dialogue3;
    private Dialogue dialogue4;

    @Before 
    public void setUp() {
        dialogue1 = new Dialogue("TEXTO TESTE");
        dialogue2 = new Dialogue("TEXTO TESTE2", "NPC1");
        dialogue3 = new Dialogue("TEXTO TESTE3", "NPC2");
        dialogue4 = new Dialogue("TEXTO TESTE4", "NPC1");
    }

    @Test
    public void dialogueReturnsSpeakerAndTextSeparately() {
        assertEquals("", dialogue1.getTexto().getKey());
        assertEquals("TEXTO TESTE", dialogue1.getTexto().getValue());
        assertEquals(null, dialogue1.getNpcThatSaidIt());

        assertEquals("NPC1", dialogue2.getTexto().getKey());
        assertEquals("TEXTO TESTE2", dialogue2.getTexto().getValue());
        assertEquals("NPC1", dialogue2.getNpcThatSaidIt());
        assertEquals("NPC1", dialogue4.getNpcThatSaidIt());
        assertEquals("NPC1", dialogue4.getTexto().getKey());
        assertEquals("TEXTO TESTE4", dialogue4.getTexto().getValue());
        assertEquals("NPC2", dialogue3.getTexto().getKey());
        assertEquals("TEXTO TESTE3", dialogue3.getTexto().getValue());
        assertEquals("NPC2", dialogue3.getNpcThatSaidIt());
    }
}
