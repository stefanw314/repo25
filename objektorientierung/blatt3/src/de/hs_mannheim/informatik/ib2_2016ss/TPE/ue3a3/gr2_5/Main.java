package de.hs_mannheim.informatik.ib2_2016ss.TPE.ue3a3.gr2_5;

import java.io.File;

public class Main {
	public static void main(String[] args) {
		
		CaesarFileEncryptor fileEncryptor = new CaesarFileEncryptor();
		File a = fileEncryptor.encrypt(new File("/Users/danielkoch/Desktop/Minhisstleiderkrank/"));
		
		fileEncryptor.decrypt(a);
		
	}
}
