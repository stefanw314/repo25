package de.hs_mannheim.informatik.ib2_2016ss.TPE.ue2_2a2.gr2_5;

import static org.junit.Assert.*;

import org.junit.Test;

public class CrypterReverseTest {

	@Test
	public void reverseAndUndoTest() {
		CrypterReverse reverse = CrypterReverse.getInstance();
		String toBeTested = "abcdefgh";
		assertEquals(toBeTested, reverse.decrypt(reverse.encrypt(toBeTested)));
	}

	@Test
	public void reverseEncyrptDecryptTest() {
		CrypterReverse reverse = CrypterReverse.getInstance();
		String toBeTested = "abcdefgh";
		assertEquals("hgfedcba", reverse.encrypt(toBeTested));
		assertEquals("hgfedcba", reverse.decrypt(toBeTested));
	}
	
	@Test
	public void reverseEmpty() {
		CrypterReverse reverse = CrypterReverse.getInstance();
		String toBeTested = "";
		assertEquals("", reverse.decrypt(toBeTested));
		assertEquals("", reverse.encrypt(toBeTested));
	}
	
}