package test;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import src.model.service.Orfeu;

public class OrfeuTest {

    private Orfeu orfeu;

    @Before
    public void setUp() {
        // Construtor: (obol, love, angry, sadness)
        orfeu = new Orfeu(5, 10, 15, 20);
    }

    @Test
    public void testConstructorAndGetters() {
        assertEquals(5, orfeu.getObol());
        assertEquals(10, orfeu.getLove());
        assertEquals(15, orfeu.getAngry());
        assertEquals(20, orfeu.getSadness());
    }

    @Test
    public void testToStringFormat() {
        String expected = "	   [Obolos: 5]\n[Amor: 10 | Tristeza: 20 | Raiva: 15]";
        assertEquals(expected, orfeu.toString());
    }

    @Test
    public void testChangeAttributesNormal() {
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
    public void testChangeAttributesUpperBoundLimit() {
        // Testa se trava no teto de 99
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
    public void testChangeAttributesLowerBoundLimit() {
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
}