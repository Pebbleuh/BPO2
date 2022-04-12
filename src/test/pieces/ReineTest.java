package test.pieces;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import echec.Echiquier;
import echec.pieces.Reine;

public class ReineTest {
	@Test
	public void testEstPossible() {
		Echiquier e = new Echiquier();
		Reine r = new Reine("BLANC", 4, 4);
		
		assertTrue(r.estPossible(e, 5, 4));
		assertFalse(r.estPossible(e, 6, 4));
		assertTrue(r.estPossible(e, 4, 6));
		assertTrue(r.estPossible(e, 4, 3));
		assertTrue(r.estPossible(e, 1, 4));
		
		assertFalse(r.estPossible(e, 2, 5));
		
		r = new Reine("BLANC", 3, 4);
		assertTrue(r.estPossible(e, 2, 3));
		assertFalse(r.estPossible(e, 6, 7));
		assertTrue(r.estPossible(e, 1, 2));
		assertFalse(r.estPossible(e, 4, 2));
	}
	
	@Test
	public void testCoupsPossibles() {
		Echiquier e = new Echiquier();
		Reine r = new Reine("BLANC", 4, 4);
		
		String attendu = "[[5, 4], [3, 4], [2, 4], "
				+ "[1, 4], [4, 5], [4, 6], "
				+ "[4, 7], [4, 3], [4, 2], "
				+ "[4, 1], [4, 0], [3, 3], "
				+ "[2, 2], [1, 1], [3, 5], "
				+ "[2, 6], [1, 7], [5, 5], [5, 3]]";
		
		assertEquals(r.coupsPossibles(e).toString(), attendu);
	}
	
	@Test
	public void testGetSymbole() {
		Reine d = new Reine("BLANC", 0, 0);
		assertEquals(d.getSymbole(), 'D');
		
		d = new Reine("NOIR", 0, 0);
		assertEquals(d.getSymbole(), 'd');
	}
}
