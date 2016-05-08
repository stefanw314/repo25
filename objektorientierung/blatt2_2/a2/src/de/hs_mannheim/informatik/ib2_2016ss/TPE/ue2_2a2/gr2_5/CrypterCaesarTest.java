package de.hs_mannheim.informatik.ib2_2016ss.TPE.ue2_2a2.gr2_5;

import static org.junit.Assert.*;

import org.junit.Test;

public class CrypterCaesarTest {

	@Test
	public void encryptDecryptTest() {
		CrypterCaesar caesar = CrypterCaesar.getInstance(3);
		String toBeTested = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
		assertEquals(toBeTested.toLowerCase(), caesar.decrypt((caesar.encrypt(toBeTested))));
	}
	
	@Test
	public void encryptTest() {
		CrypterCaesar caesar = CrypterCaesar.getInstance(1);
		String toBeTested = "abcxyz";
		assertEquals("BCDYZA", caesar.encrypt(toBeTested));
	}
	
	@Test
	public void decryptTest() {
		CrypterCaesar caesar = CrypterCaesar.getInstance(1);
		String toBeTested = "BCDYZA";
		assertEquals("abcxyz", caesar.decrypt(toBeTested));
	}
	
	@Test
	public void largeEncryptDecryptTest() {
		CrypterCaesar caesarPositive = CrypterCaesar.getInstance(260);
		String toBeTested1 = "az";
		assertEquals("AZ", caesarPositive.encrypt(toBeTested1));
		assertEquals("az", caesarPositive.decrypt(toBeTested1));
		
		CrypterCaesar caesarNegative = CrypterCaesar.getInstance(-260);
		String toBeTested2 = "az";
		assertEquals("AZ", caesarNegative.encrypt(toBeTested2));
		assertEquals("az", caesarNegative.decrypt(toBeTested2));
	}
	
	@Test
	public void zeroShiftTest() {
		CrypterCaesar caesar = CrypterCaesar.getInstance(0);
		String toBeTested = "AZ";
		assertEquals("az", caesar.decrypt(toBeTested));
		assertEquals("AZ", caesar.encrypt(toBeTested));
	}
	
	@Test
	public void negativeShiftTest() {
		CrypterCaesar caesar = CrypterCaesar.getInstance(-3);
		String toBeTested = "az";
		assertEquals("XW", caesar.encrypt(toBeTested));
		assertEquals("dc", caesar.decrypt(toBeTested));
	}
	
	@Test
	public void otherCharsTest() {
		CrypterCaesar caesar = CrypterCaesar.getInstance(3);
		String toBeTested = "azäöüß&/732-";
		assertEquals("DCäöüß&/732-", caesar.encrypt(toBeTested));
		assertEquals("xwäöüß&/732-", caesar.decrypt(toBeTested));
	}

}
