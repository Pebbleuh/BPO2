package test.pieces;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import echec.Echiquier;
import echec.pieces.Tour;

public class TourTest {
	@Test
	public void testEstPossible() {
		Echiquier e = new Echiquier();
		Tour t = new Tour("BLANC", 4, 4);
		
		assertTrue(t.estPossible(e, 5, 4));
		assertFalse(t.estPossible(e, 6, 4));
		assertTrue(t.estPossible(e, 4, 6));
		assertTrue(t.estPossible(e, 4, 3));
		assertTrue(t.estPossible(e, 1, 4));
		
		assertFalse(t.estPossible(e, 2, 5));
		assertFalse(t.estPossible(e, 3, 3));
	}
	
	@Test
	public void testCoupsPossibles() {
		Echiquier e = new Echiquier();
		Tour t = new Tour("BLANC", 4, 4);
		String attendu = "[[5, 4], [3, 4], [2, 4], [1, 4], "
				+ "[4, 5], [4, 6], [4, 7], [4, 3], "
				+"[4, 2], [4, 1], [4, 0]]";
		assertEquals(t.coupsPossibles(e).toString(), attendu);
	}
	
	@Test
	public void testGetSymbole() {
		Tour t = new Tour("BLANC", 0, 0);
		assertEquals(t.getSymbole(), 'T');
		
		t = new Tour("NOIR", 0, 0);
		assertEquals(t.getSymbole(), 't');
	}
}
