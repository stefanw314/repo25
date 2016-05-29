package de.hs_mannheim.informatik.ib2_2016ss.TPE.ue3a3.gr2_5;

import java.io.File;

public interface IFileEncryptor {
	/**
	 * @description: Encrypts a file
	 * @param sourceDirectory
	 * @return: the encrypted file
	 */
	public File encrypt(File sourceDirectory);
	
	/**
	 * @description: decrypts a file
	 * @param sourceDirectory
	 * @return: the decrypted file
	 */
	public File decrypt(File sourceDirectory);
}
