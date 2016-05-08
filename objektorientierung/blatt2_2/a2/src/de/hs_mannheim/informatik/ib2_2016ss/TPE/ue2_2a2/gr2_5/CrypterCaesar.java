package de.hs_mannheim.informatik.ib2_2016ss.TPE.ue2_2a2.gr2_5;

public class CrypterCaesar implements Crypter {

	private char[] plainChars = new char[26];
	private char[] encryptedChars = new char[26];
	private char[] decryptedChars = new char[26];
	
	private CrypterCaesar() {
		
	}
	
	/**
	 * @description: Private constructor to prevent CrypterCaesar objects without delta
	 * @param delta
	 */
	private CrypterCaesar(int delta) {
		while(delta < -26) {
			delta += 26;
		}
		while(delta > 26) {
			delta -= 26;
		}
		
		char alphabetLetter = 'A';
		int charsPosition = 0;
		//Go through all values from A to Z
		while(alphabetLetter <= 'Z') {
			//Figure out encrypted and decrypted value
			char encryptedNextChar = (char) (alphabetLetter + delta);
			char decryptedNextChar = (char) (alphabetLetter - delta);
			
			//Handle out of bounce for encrypted values
			if(encryptedNextChar < 'A') {
				encryptedNextChar += 26;
			}
			else if(encryptedNextChar > 'Z') {
				encryptedNextChar -= 26;
			}
			
			//Handle out of bounce for decrypted values
			if(decryptedNextChar < 'A') {
				decryptedNextChar += 26;
			}
			else if(decryptedNextChar > 'Z') {
				decryptedNextChar -= 26;
			}
			
			decryptedNextChar = (char) (decryptedNextChar - 'A' + 'a');
			
			//Set the values
			plainChars[charsPosition] = alphabetLetter;
			encryptedChars[charsPosition] = encryptedNextChar;
			decryptedChars[charsPosition] = decryptedNextChar;
			
			charsPosition++;
			alphabetLetter++;
		}
	}
	
	/**
	 * @description: factory pattern
	 * @param delta
	 * @return: object of CrypterCaesar
	 */
	public static CrypterCaesar getInstance(int delta) {
		return new CrypterCaesar(delta);
	}

	
	/**
	 * @description: Encrypts the string message by using caesar algorithm
	 */
	public String encrypt(String message) {
		return caesar(message, false);
	}

	/**
	 * @description: Decrypts the string message by using caesar algorithm
	 */
	public String decrypt(String cypherText) {
		return caesar(cypherText, true);
	}
	
	/**
	 * @description: Uses caesar algorithm for encrypt or decrypt this string
	 * @param normalString: string to be encrypted or decrypted
	 * @param reverse: should caesar algorithm executed in reverse order
	 * @return the encrypted or decrypted string
	 * @throws NullPointerException if input string is null
	 */
	private String caesar(String normalString, boolean reverse) {
		//Throw an exception if normalString is null
		if(normalString == null) {
			throw new NullPointerException("normalString was null.");
		}
		
		//Create space for encrypted or decrypted string
		String cryptString = "";
		
		//Make string values between a-z to upper case
		normalString = toUpperCase(normalString);
		
		//Shift all values by delta using the arrays created in constructor
		int position = 0;
		while(position < normalString.length()) {
			//If it is encrypt mode, use the encryptedChars array
			if(normalString.charAt(position) >= 'A' && normalString.charAt(position) <= 'Z') {
				if(!reverse) {
					cryptString += encryptedChars[normalString.charAt(position) - 'A'];
				}
				//If it is decrypt mode, use the encryptedChars array
				else {
					cryptString += decryptedChars[normalString.charAt(position) - 'A'];
				}
			}
			else {
				cryptString += normalString.charAt(position);
			}
			position++;
		}
		
		return cryptString;
	}
	
	/**
	 * @description: Makes a string to upper case (only a-z)
	 * @param original
	 * @return upper case string
	 */
	private String toUpperCase(String original) {
		String uppercase = "";
		int i = 0;
		while(i < original.length()) {
			char currentChar = original.charAt(i);
			//If the character is between a and z, change it to uppercase
			if(currentChar >= 'a' && currentChar <= 'z') {
				uppercase += (char) (original.charAt(i) - 'a' + 'A');
			}
			//Write other characters to the new string
			else {
				uppercase += currentChar;
			}
			i++;
		}
		return uppercase;
	}

}
