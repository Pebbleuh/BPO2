package test.pieces;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import echec.Echiquier;
import echec.pieces.Fou;

public class FouTest {
	@Test
	public void testEstPossible() {
		Echiquier e = new Echiquier();
		Fou f = new Fou("BLANC", 3, 4);

		assertTrue(f.estPossible(e, 2, 3));
		assertFalse(f.estPossible(e, 6, 7));
		assertTrue(f.estPossible(e, 1, 2));
		assertFalse(f.estPossible(e, 4, 2));
	}
	
	@Test
	public void testCoupsPossibles() {
		Echiquier e = new Echiquier();
		Fou f = new Fou("BLANC", 3, 4);

		String attendu = "[[2, 3], [1, 2], [2, 5], [1, 6], "
				+ "[4, 5], [5, 6], [4, 3], [5, 2]]";
		assertEquals(f.coupsPossibles(e).toString(), attendu);
	}
	
	@Test
	public void testGetSymbole() {
		Fou f = new Fou("BLANC", 0, 0);
		assertEquals(f.getSymbole(), 'F');
		
		f = new Fou("NOIR", 0, 0);
		assertEquals(f.getSymbole(), 'f');
	}
}
