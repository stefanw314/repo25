package de.hs_mannheim.informatik.ib2_2016ss.TPE.ue3a3.gr2_5;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	public static void main(String[] args) {
		///Users/danielkoch/Desktop/ordner
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		
		System.out.println("Geben Sie den Dateipfad zum ver- oder entschlüsseln ein.");
		
		String filename = "";
		try {
			filename = reader.readLine();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		CaesarFileEncryptor fileEncryptor = new CaesarFileEncryptor();
		
		System.out.println("Möchten Sie verschlüsseln(0) oder entschlüsseln(1)");
		
		int decision = -1;
		try {
			decision = Integer.parseInt("" + (char) reader.read());
			reader.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.out.println("decision" + decision);
		
		if(decision == 0) {
			fileEncryptor.encrypt(new File(filename));
			System.out.println("Encrypted file");
		}
		else if(decision == 1) {
			fileEncryptor.decrypt(new File(filename));
			System.out.println("Decrypted file");
		}
		else {
			System.out.println("Falsche Entscheidung!");
		}	
	}
}
