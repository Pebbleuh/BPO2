package test.echec;

import static org.junit.Assert.assertEquals;
import org.junit.Test;
import echec.Coordonnée;

public class CoordonneeTest {

    @Test
    public void testToString() {
        Coordonnée c = new Coordonnée(0, 4);
        String attendu = "[0, 4]";
        assertEquals(c.toString(), attendu);
    }

    @Test
    public void testStringToInt() {
        Coordonnée[] c = Coordonnée.stringToInt("a8h1");
        Coordonnée[] coord = Coordonnée.stringToInt("b3d4");
        assertEquals(c[0].toString(), "[0, 0]");
        assertEquals(c[1].toString(), "[7, 7]");
        assertEquals(coord[0].toString(), "[5, 1]");
        assertEquals(coord[1].toString(), "[4, 3]");
    }

    @Test
    public void testIntToString() {
        Coordonnée arrivée = new Coordonnée(0,0);
        Coordonnée départ = new Coordonnée(7,7);
        String c = Coordonnée.intToString(arrivée, départ);
        assertEquals(c, "a8h1");

        départ = new Coordonnée(5, 1);
        arrivée = new Coordonnée(4, 3);
        String coord = Coordonnée.intToString(départ, arrivée);
        assertEquals(coord, "b3d4");
    }
}