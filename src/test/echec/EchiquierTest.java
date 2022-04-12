package test.echec;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import echec.Coordonnée;
import echec.Echiquier;

public class EchiquierTest {

	@Test
	public void testDéplacer() {
		Echiquier e = new Echiquier();
		assert(e.getPièce(4,7) == null);
		assert(e.getPièce(6,7).getSymbole() == 'P');
		e.déplacer(new Coordonnée(6, 7), new Coordonnée(4, 7));
		assert(e.getPièce(4,7) != null && e.getPièce(4,7).getSymbole() == 'P');
		assert(e.getPièce(6,7) == null);

		assert(e.getPièce(1,7) != null && e.getPièce(1,7).getSymbole() == 'p');
		e.déplacer(new Coordonnée(4, 7), new Coordonnée(1, 7));
		assert(e.getPièce(1,7) != null && e.getPièce(1, 7).getSymbole() == 'P');
		assert(e.getPièce(4,7) == null);
	}

	@Test
	public void testAnnulerDernierCoup() {
		Echiquier e = new Echiquier();

		assert(e.getPièce(4,7) == null);
		assert(e.getPièce(6,7).getSymbole() == 'P');
		e.déplacer(new Coordonnée(6, 7), new Coordonnée(4, 7));
		e.annulerDernierCoup(new Coordonnée(6, 7), new Coordonnée(4, 7));
		assert(e.getPièce(4,7) == null);
		assert(e.getPièce(6,7).getSymbole() == 'P');

		assert(e.getPièce(1,7) != null && e.getPièce(1,7).getSymbole() == 'p');
		e.déplacer(new Coordonnée(6, 7), new Coordonnée(1, 7));
		e.annulerDernierCoup(new Coordonnée(6, 7), new Coordonnée(1, 7));
		assert(e.getPièce(1,7) != null && e.getPièce(1,7).getSymbole() == 'p');
		assert(e.getPièce(6,7).getSymbole() == 'P');
	}

	@Test
	public void testGetPiècesBlanches() {
		Echiquier e = new Echiquier();
		String attendu = "[BLANC[6, 0], BLANC[6, 1], BLANC[6, 2], "
				+ "BLANC[6, 3], BLANC[6, 4], BLANC[6, 5], "
				+ "BLANC[6, 6], BLANC[6, 7], BLANC[7, 0], "
				+ "BLANC[7, 1], BLANC[7, 2], BLANC[7, 3], "
				+ "BLANC[7, 4], BLANC[7, 5], BLANC[7, 6], BLANC[7, 7]]";
		assertEquals(attendu, e.getPiècesBlanches().toString());
	}

	@Test
	public void testGetPiècesNoires() {
		Echiquier e = new Echiquier();
		String attendu = "[NOIR[0, 0], NOIR[0, 1], NOIR[0, 2], "
				+ "NOIR[0, 3], NOIR[0, 4], NOIR[0, 5], "
				+ "NOIR[0, 6], NOIR[0, 7], NOIR[1, 0], "
				+ "NOIR[1, 1], NOIR[1, 2], NOIR[1, 3], "
				+ "NOIR[1, 4], NOIR[1, 5], NOIR[1, 6], NOIR[1, 7]]";
		assertEquals(attendu, e.getPiècesNoires().toString());
	}

	@Test
	public void testToString() {
		Echiquier e = new Echiquier();
		String attendu = "    a   b   c   d   e   f   g   h    \n"
				+ "   --- --- --- --- --- --- --- ---   \n"
				+ "8 | t | c | f | d | r | f | c | t | 8\n"
				+ "   --- --- --- --- --- --- --- ---   \n"
				+ "7 | p | p | p | p | p | p | p | p | 7\n"
				+ "   --- --- --- --- --- --- --- ---   \n"
				+ "6 |   |   |   |   |   |   |   |   | 6\n"
				+ "   --- --- --- --- --- --- --- ---   \n"
				+ "5 |   |   |   |   |   |   |   |   | 5\n"
				+ "   --- --- --- --- --- --- --- ---   \n"
				+ "4 |   |   |   |   |   |   |   |   | 4\n"
				+ "   --- --- --- --- --- --- --- ---   \n"
				+ "3 |   |   |   |   |   |   |   |   | 3\n"
				+ "   --- --- --- --- --- --- --- ---   \n"
				+ "2 | P | P | P | P | P | P | P | P | 2\n"
				+ "   --- --- --- --- --- --- --- ---   \n"
				+ "1 | T | C | F | D | R | F | C | T | 1\n"
				+ "   --- --- --- --- --- --- --- ---   \n"
				+ "    a   b   c   d   e   f   g   h    \n";
		assertEquals(e.toString(), attendu);
	}

}