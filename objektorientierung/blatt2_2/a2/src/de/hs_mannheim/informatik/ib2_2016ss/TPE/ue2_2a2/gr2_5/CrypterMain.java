package de.hs_mannheim.informatik.ib2_2016ss.TPE.ue2_2a2.gr2_5;

public class CrypterMain {
	public static void main(String args[]) {
		//Create objects for Crypter classes
		Crypter caesar = CrypterCaesar.getInstance(5);
		Crypter reverse = CrypterReverse.getInstance();
		
		//Define the string to be decrypted
		String doubleEncryptedString = "XHMSNYYXYJQQJS";
		System.out.println("Entschlüssele den String: " + doubleEncryptedString);
		
		//Reverse decrypt
		String reverse1Decrypted = reverse.decrypt(doubleEncryptedString);
		System.out.println("String nach 1.Reverse-Entschlüsselung: " + reverse1Decrypted);
		
		//Caeser decrypt
		String caeserDecrypted = caesar.decrypt(reverse1Decrypted);
		System.out.println("String nach Caeser-Entschlüsselung: " + caeserDecrypted);
		
		//Reverse decrypt
		String reverse2Decrypted = reverse.decrypt(caeserDecrypted);
		System.out.println("String nach 2.Reverse-Entschlüsselung: " + reverse2Decrypted);
		
	}
}
