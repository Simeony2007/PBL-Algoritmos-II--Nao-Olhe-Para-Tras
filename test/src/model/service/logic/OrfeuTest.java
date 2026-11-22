package src.model.service.logic;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotSame;

import org.junit.Before;
import org.junit.Test;

public class OrfeuTest {

    private Orfeu orfeu;

    @Before
    public void setUp() {
        // Construtor: (obol, love, angry, sadness)
        orfeu = new Orfeu(5, 10, 15, 20);
    }

    @Test
    public void constructorAndGetters() {
        assertEquals(5, orfeu.getObol());
        assertEquals(10, orfeu.getLove());
        assertEquals(15, orfeu.getAngry());
        assertEquals(20, orfeu.getSadness());
    }

    @Test
    public void toStringIncludesAllAttributes() {
        String expected = "	   [Obolos: 5]\n[Amor: 10 | Tristeza: 20 | Raiva: 15]";
        assertEquals(expected, orfeu.toString());
    }

    @Test
    public void changeAttributesWithinBounds() {
        orfeu.changeObol(2);
        orfeu.changeLove(5);
        orfeu.changeAngry(-5);
        orfeu.changeSadness(10);

        assertEquals(7, orfeu.getObol());
        assertEquals(15, orfeu.getLove());
        assertEquals(10, orfeu.getAngry());
        assertEquals(30, orfeu.getSadness());
    }

    @Test
    public void attributesAreCappedAt99() {
        orfeu.changeObol(100);
        orfeu.changeLove(100);
        orfeu.changeAngry(100);
        orfeu.changeSadness(100);

        assertEquals(99, orfeu.getObol());
        assertEquals(99, orfeu.getLove());
        assertEquals(99, orfeu.getAngry());
        assertEquals(99, orfeu.getSadness());
    }

    @Test
    public void attributesAreFlooredAtZero() {
        // Testa se trava no piso de 0
        orfeu.changeObol(-50);
        orfeu.changeLove(-50);
        orfeu.changeAngry(-50);
        orfeu.changeSadness(-50);

        assertEquals(0, orfeu.getObol());
        assertEquals(0, orfeu.getLove());
        assertEquals(0, orfeu.getAngry());
        assertEquals(0, orfeu.getSadness());
    }

    @Test
    public void cloneCopiesAttributesWithoutSharingInstance() {
        Orfeu clone = orfeu.clone();

        assertEquals(orfeu.getObol(), clone.getObol());
        assertEquals(orfeu.getLove(), clone.getLove());
        assertEquals(orfeu.getAngry(), clone.getAngry());
        assertEquals(orfeu.getSadness(), clone.getSadness());
        assertNotSame(orfeu, clone);

        clone.changeLove(5);
        assertEquals(10, orfeu.getLove());
        assertEquals(15, clone.getLove());
    }
}