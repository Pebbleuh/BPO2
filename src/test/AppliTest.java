package test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import appli.Appli;

public class AppliTest {
	
	@Test
	public void testEstValide() {
		assertTrue(Appli.estValide(""));
		assertTrue(Appli.estValide("b4g6"));
		assertTrue(Appli.estValide("H4D5"));
		assertTrue(Appli.estValide("   h4   d5  "));
		
		assertFalse(Appli.estValide("r4t5"));
		assertFalse(Appli.estValide("d8g9"));
		assertFalse(Appli.estValide("r4"));
		assertFalse(Appli.estValide("4f5d"));
		assertFalse(Appli.estValide("3456"));
		assertFalse(Appli.estValide("erhv"));
		assertFalse(Appli.estValide("h4h5f4"));
	}
}
