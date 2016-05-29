package de.hs_mannheim.informatik.ib2_2016ss.TPE.ue3a2.gr2_5;

import java.io.File;

public interface Crypter {
	
	/**
	 * @description: Encrypts a String
	 * @param sourceDirectory
	 * @return: the encrypted String
	 */
	public String encrypt(String message);
	
	/**
	 * @description: decrypts a String
	 * @param sourceDirectory
	 * @return: the decrypted String
	 */
	public String decrypt(String cypherText);
}
