package test.pieces;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import echec.Coordonnée;
import echec.Echiquier;
import echec.pieces.Pièce;

public class PièceTest {

    @Test
    public void testDéplacer() {
        Echiquier e = new Echiquier();
        Pièce p = new Pièce("BLANC", 4, 4);
        e.setPièce(4, 4, p);
        Coordonnée départ = new Coordonnée(4, 4);
        Coordonnée arrivée = new Coordonnée(0, 0);

        p.déplacer(e, départ, arrivée);
        assertTrue(e.getPièce(4, 4) == null);
        assertTrue(e.getPièce(0, 0) == p);
    }

}