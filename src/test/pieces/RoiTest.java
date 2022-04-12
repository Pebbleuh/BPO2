package test.pieces;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import echec.Coordonnée;
import echec.Echiquier;
import echec.pieces.Roi;

public class RoiTest {
	@Test
	public void testEstPossible() {
		Echiquier e = new Echiquier();
		Roi r = new Roi("BLANC", 4, 4);

		assertTrue(r.estPossible(e, 3, 3));
		assertTrue(r.estPossible(e, 3, 4));
		assertTrue(r.estPossible(e, 3, 5));
		assertTrue(r.estPossible(e, 4, 3));
		assertTrue(r.estPossible(e, 4, 5));
		assertTrue(r.estPossible(e, 5, 3));
		assertTrue(r.estPossible(e, 5, 4));
		assertTrue(r.estPossible(e, 5, 4));

		assertFalse(r.estPossible(e, 5, 6));
		assertFalse(r.estPossible(e, 6, 5));
		assertFalse(r.estPossible(e, 2, 2));
	}

	@Test
	public void testCoupsPossibles() {
		Echiquier e = new Echiquier();
		Roi r = new Roi("BLANC", 4, 4);
		e.setPièce(4, 4, r);

		String attendu = "[[3, 3], [3, 4], [3, 5], [4, 3], "
				+ "[4, 5], [5, 3], [5, 4], [5, 5]]";
		assertEquals(r.coupsPossibles(e).toString(), attendu);
	}

	@Test
	public void testGetSymbole() {
		Roi r = new Roi("BLANC", 0, 0);
		assertEquals(r.getSymbole(), 'R');

		r = new Roi("NOIR", 0, 0);
		assertEquals(r.getSymbole(), 'r');
	}

	@Test
	public void testEchec() {
		Echiquier e = new Echiquier();
		assertFalse(e.getRoiBlanc().échec(e));

		Coordonnée départ = new Coordonnée(0, 4);
		Coordonnée arrivée = new Coordonnée(5, 4);
		e.déplacer(départ, arrivée);
		assertTrue(e.getRoiNoir().échec(e));

		départ = new Coordonnée(5, 4);
		arrivée = new Coordonnée(4, 4);
		e.déplacer(départ, arrivée);
		assertFalse(e.getRoiNoir().échec(e));

		départ = new Coordonnée(7, 7);
		arrivée = new Coordonnée(4, 7);
		e.déplacer(départ, arrivée);
		assertTrue(e.getRoiNoir().échec(e));
	}

	@Test
	public void testPeutEtreProtégé() {
		Echiquier e = new Echiquier();
		assertFalse(e.getRoiBlanc().échec(e));

		Coordonnée départ = new Coordonnée(0, 4);
		Coordonnée arrivée = new Coordonnée(5, 4);
		e.déplacer(départ, arrivée);
		assertFalse(e.getRoiNoir().peutEtreProtégé(e));

		départ = new Coordonnée(5, 4);
		arrivée = new Coordonnée(4, 4);
		e.déplacer(départ, arrivée);

		départ = new Coordonnée(7, 7);
		arrivée = new Coordonnée(4, 7);
		e.déplacer(départ, arrivée);

		départ = new Coordonnée(0, 3);
		arrivée = new Coordonnée(5, 4);
		e.déplacer(départ, arrivée);
		assertTrue(e.getRoiNoir().peutEtreProtégé(e));

		départ = new Coordonnée(5, 4);
		arrivée = new Coordonnée(5, 7);
		e.déplacer(départ, arrivée);
		assertTrue(e.getRoiNoir().peutEtreProtégé(e));
	}
}