package test.echec;

import echec.Partie;
import echec.pieces.Cavalier;
import echec.pieces.Fou;
import echec.pieces.Roi;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class PartieTest {

	@Test
	public void testJouer() {
		Partie p = new Partie("Humain", "IA");
		String echiquier = "    a   b   c   d   e   f   g   h    \n"
				+ "   --- --- --- --- --- --- --- ---   \n"
				+ "8 | t | c | f | d | r | f | c | t | 8\n"
				+ "   --- --- --- --- --- --- --- ---   \n"
				+ "7 | p | p | p | p | p | p | p | p | 7\n"
				+ "   --- --- --- --- --- --- --- ---   \n"
				+ "6 |   |   |   |   |   |   |   |   | 6\n"
				+ "   --- --- --- --- --- --- --- ---   \n"
				+ "5 |   |   |   |   |   |   |   |   | 5\n"
				+ "   --- --- --- --- --- --- --- ---   \n"
				+ "4 |   |   |   | P |   |   |   |   | 4\n"
				+ "   --- --- --- --- --- --- --- ---   \n"
				+ "3 |   |   |   |   |   |   |   |   | 3\n"
				+ "   --- --- --- --- --- --- --- ---   \n"
				+ "2 | P | P | P |   | P | P | P | P | 2\n"
				+ "   --- --- --- --- --- --- --- ---   \n"
				+ "1 | T | C | F | D | R | F | C | T | 1\n"
				+ "   --- --- --- --- --- --- --- ---   \n"
				+ "    a   b   c   d   e   f   g   h    \n";
		p.jouer("d2d4");
		assertEquals(p.getEchiquier().toString(), echiquier);
		p.setTourDeBlanc(false);
		p.jouer(null);
		assertNotEquals(p.getEchiquier().toString(), echiquier);
	}

	@Test
	public void testEstPossible() {
		Partie p = new Partie("Humain", "Humain");
		assertTrue(p.estPossible("d2d4"));
		assertFalse(p.estPossible("d1d4"));
		assertTrue(p.estPossible("b1c3"));
	}

	@Test
	public void testDonneEchec() {
		Partie p = new Partie("Humain", "Humain");
		assertTrue(p.donneEchec("e1e6"));
		assertFalse(p.donneEchec("e1e5"));
	}

	@Test
	public void testPat() {
		Partie p = new Partie("Humain", "Humain");
		assertFalse(p.pat());
		for(int i = 0; i < 2; ++i) {
			for(int j = 0; j < 8; ++j) {
				if(!(i == 0 && j == 4))
					p.getEchiquier().setPièce(i, j, null);
			}
		}
		p.jouer("d1d6");
		p.jouer("h1f6");
		p.setTourDeBlanc(false);
		assertTrue(p.pat());
	}

	@Test
	public void testCinquanteCoups() {
		Partie p = new Partie("Humain", "Humain");
		assertFalse(p.cinquanteCoups());
		for(int i = 0; i < 50; ++i) {
			if(i % 2 == 0)
				p.jouer("d2d4");
			else
				p.jouer("d4d2");
		}
		assertTrue(p.cinquanteCoups());
	}

	@Test
	public void testMatérielInsuffisant() {
		Partie p = new Partie("Humain", "Humain");
		assertFalse(p.matérielInsuffisant());
		for(int i = 0; i < 8; ++i) {
			for(int j = 0; j < 8; ++j) {
				p.getEchiquier().setPièce(i, j, null);
			}
		}
		p.getEchiquier().setPièce(0, 4, new Roi("NOIR", 0, 4));
		p.getEchiquier().setPièce(7, 4, new Roi("BLANC", 7, 4));
		p.getEchiquier().setPièce(7, 3, new Cavalier("BLANC", 7, 3));
		assertTrue(p.matérielInsuffisant());

		p.getEchiquier().setPièce(7, 3, new Fou("BLANC", 7, 3));
		assertTrue(p.matérielInsuffisant());

		p.getEchiquier().setPièce(7, 3, null);
		assertTrue(p.matérielInsuffisant());

		p.getEchiquier().setPièce(5, 3, new Fou("NOIR", 5, 3));
		assertTrue(p.matérielInsuffisant());

		p.getEchiquier().setPièce(5, 3, null);
		assertTrue(p.matérielInsuffisant());
	}

	@Test
	public void testEchecEtMat() {
		Partie p = new Partie("Humain", "Humain");
		p.jouer("e7e5");
		p.jouer("d1e5");
		assertFalse(p.échecEtMat(p.getEchiquier().getRoiNoir()));

		p.jouer("e8e7");
		assertTrue(p.échecEtMat(p.getEchiquier().getRoiNoir()));

		p.jouer("e7e6");
		assertFalse(p.échecEtMat(p.getEchiquier().getRoiNoir()));

		p.jouer("h1h5");
		assertTrue(p.échecEtMat(p.getEchiquier().getRoiNoir()));
	}

	@Test
	public void abandon() {
		Partie p = new Partie("Humain", "Humain");
		assertTrue(p.abandon(""));
		assertFalse(p.abandon("test"));
	}

	@Test
	public void TestToStringFin() {
		// pat
		Partie p = new Partie("Humain", "Humain");
		for(int i = 0; i < 2; ++i) {
			for(int j = 0; j < 8; ++j) {
				if(!(i == 0 && j == 4))
					p.getEchiquier().setPièce(i, j, null);
			}
		}
		p.jouer("d1d6");
		p.jouer("h1f6");
		p.setTourDeBlanc(false);
		assertTrue(p.pat());

		String attendu = "partie nulle, PAT";
		assertEquals(p.toStringFin("test"), attendu);

		// cinquantes coups
		p = new Partie("Humain", "Humain");
		for(int i = 0; i < 50; ++i) {
			if(i % 2 == 0)
				p.jouer("d2d4");
			else
				p.jouer("d4d2");
		}
		assertTrue(p.cinquanteCoups());
		attendu = "partie nulle, règle des Cinquantes Coups";
		assertEquals(p.toStringFin("test"), attendu);

		// abandon blanc
		p = new Partie("Humain", "Humain");
		attendu = "partie finie, abandon des Blancs";
		assertEquals(p.toStringFin(""), attendu);

		// abandon noir
		p.setTourDeBlanc(false);
		attendu = "partie finie, abandon des Noirs";
		assertEquals(p.toStringFin(""), attendu);

		// matériel insuffisant
		for(int i = 0; i < 8; ++i) {
			for(int j = 0; j < 8; ++j) {
				p.getEchiquier().setPièce(i, j, null);
			}
		}
		p.getEchiquier().setPièce(0, 4, new Roi("NOIR", 0, 4));
		p.getEchiquier().setPièce(7, 4, new Roi("BLANC", 7, 4));
		p.getEchiquier().setPièce(7, 3, new Cavalier("BLANC", 7, 3));
		assertTrue(p.matérielInsuffisant());

		attendu = "partie nulle, matériel insuffisant";
		assertEquals(p.toStringFin("test"), attendu);

		// echet et mat blanc
		p = new Partie("Humain", "Humain");
		p.jouer("e7e5");
		p.jouer("d1e5");
		p.jouer("e8e7");
		assertTrue(p.échecEtMat(p.getEchiquier().getRoiNoir()));

		attendu = "les Blancs ont gagné par Echec Et Mat";
		assertEquals(p.toStringFin("test"), attendu);

		// echec et mat noir
		p = new Partie("Humain", "Humain");
		p.jouer("e2e4");
		p.jouer("d8e4");
		p.jouer("e1e2");
		assertTrue(p.échecEtMat(p.getEchiquier().getRoiBlanc()));

		attendu = "les Noirs ont gagné par Echec Et Mat";
		assertEquals(p.toStringFin("test"), attendu);
	}
}