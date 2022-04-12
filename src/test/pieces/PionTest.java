package test.pieces;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import echec.Echiquier;
import echec.pieces.Pion;

public class PionTest {
	@Test
	public void testEstPossible() {
		Echiquier e = new Echiquier();
		Pion p = new Pion("BLANC", 4, 4);
		
		assertTrue(p.estPossible(e, 2, 4));
		assertTrue(p.estPossible(e, 3, 4));
		assertFalse(p.estPossible(e, 3, 5));
		assertFalse(p.estPossible(e, 5, 4));
		
		p.setPremierCoup(false);
		assertFalse(p.estPossible(e, 2, 4));
		
		p = new Pion("BLANC", 2, 4);
		assertFalse(p.estPossible(e, 1, 4));
	}
	
	@Test
	public void testCoupsPossibles() {
		Echiquier e = new Echiquier();
		Pion p = new Pion("BLANC", 4, 4);

		String attendu = "[[3, 4], [2, 4]]";
		assertEquals(p.coupsPossibles(e).toString(), attendu);
		
		p.setPremierCoup(false);
		attendu = "[[3, 4]]";
		assertEquals(p.coupsPossibles(e).toString(), attendu);
		
		p = new Pion("BLANC", 2, 4);
		attendu = "[[1, 5], [1, 3]]";
		assertEquals(p.coupsPossibles(e).toString(), attendu);
	}
	
	@Test
	public void testGetSymbole() {
		Pion p = new Pion("BLANC", 0, 0);
		assertEquals(p.getSymbole(), 'P');
		
		p = new Pion("NOIR", 0, 0);
		assertEquals(p.getSymbole(), 'p');
	}
}
