package de.hs_mannheim.informatik.ib2_2016ss.TPE.ue3a3.gr2_5;

import java.io.File;

public interface IFileEncryptor {
	public File encrypt(File sourceDirectory);
	public File decrypt(File sourceDirectory);
}
