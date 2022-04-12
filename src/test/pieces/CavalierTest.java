package test.pieces;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import echec.Echiquier;
import echec.pieces.Cavalier;

public class CavalierTest {

	@Test
	public void testEstPossible() {
		Echiquier e = new Echiquier();
		Cavalier c = new Cavalier("BLANC", 3, 4);

		assertTrue(c.estPossible(e, 2, 2));
		assertFalse(c.estPossible(e, 3, 1));
		assertTrue(c.estPossible(e, 5, 5));
		assertFalse(c.estPossible(e, 5, 7));
		
		c = new Cavalier("BLANC", 4, 4);
		assertFalse(c.estPossible(e, 6, 5));
	}
	
	@Test
	public void testCoupsPossibles() {
		Echiquier e = new Echiquier();
		Cavalier c = new Cavalier("BLANC", 3, 4);
		String attendu = "[[5, 3], [5, 5], [1, 5], [1, 3], "
				+ "[4, 6], [2, 6], [4, 2], [2, 2]]";
		assertEquals(c.coupsPossibles(e).toString(), attendu);
		
		c = new Cavalier("BLANC", 4, 4);
		attendu = "[[2, 5], [2, 3], [5, 6], [3, 6], [5, 2], [3, 2]]";
		assertEquals(c.coupsPossibles(e).toString(), attendu);
	}
	
	@Test
	public void testGetSymbole() {
		Cavalier c = new Cavalier("BLANC", 0, 0);
		assertEquals(c.getSymbole(), 'C');
		
		c = new Cavalier("NOIR", 0, 0);
		assertEquals(c.getSymbole(), 'c');
	}
}
