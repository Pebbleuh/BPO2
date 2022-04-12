package test.echec;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import org.junit.Test;

import echec.Joueur;
import echec.Partie;

public class JoueurTest {
	
	@Test
	public void testJouerIA() {
		Partie p = new Partie("IA", "IA");
		Joueur j = new Joueur("IA", "BLANC");
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
				+ "4 |   |   |   |   |   |   |   |   | 4\n"
				+ "   --- --- --- --- --- --- --- ---   \n"
				+ "3 |   |   |   |   |   |   |   |   | 3\n"
				+ "   --- --- --- --- --- --- --- ---   \n"
				+ "2 | P | P | P | P | P | P | P | P | 2\n"
				+ "   --- --- --- --- --- --- --- ---   \n"
				+ "1 | T | C | F | D | R | F | C | T | 1\n"
				+ "   --- --- --- --- --- --- --- ---   \n"
				+ "    a   b   c   d   e   f   g   h    \n";
		assertEquals(echiquier, p.getEchiquier().toString());
		
		j.jouerIA(p);
		assertNotEquals(echiquier, p.getEchiquier().toString());
	}
	
	@Test
	public void testGetCoupIA() {
		Partie p = new Partie("IA", "IA");
		Joueur j = new Joueur("IA", "BLANC");
		String coup = j.getCoupIA(p);
		assert(coup.charAt(0) <= 'h' && coup.charAt(0) >= 'a');
		assert(coup.charAt(1) <= '8' && coup.charAt(1) >= '1');
		assert(coup.charAt(2) <= 'h' && coup.charAt(2) >= 'a');
		assert(coup.charAt(3) <= '8' && coup.charAt(3) >= '1');
	}
}
