package de.hs_mannheim.informatik.ib2_2016ss.TPE.ue2_2a2.gr2_5;

public class CrypterReverse implements Crypter {
	
	/**
	 * @description: Private constructor
	 */
	private CrypterReverse() {}
	
	/**
	 * @description: factory pattern
	 * @return: object of CrypterReverse
	 */
	public static CrypterReverse getInstance() {
		return new CrypterReverse();
	}
	
	/**
	 * @description: Encrypts the string message by reversing it
	 * @return: the encrypted string
	 */
	public String encrypt(String message) {
		return reverse(message);
	}

	/**
	 * @description: Decrypts the string cypherText by reversing it again
	 * @return: the decrypted string
	 */
	public String decrypt(String cypherText) {
		return reverse(cypherText);
	}
	
	/**
	 * @description: reverses a string
	 * @param normalString: the string to be reversed
	 * @return the reversed string
	 * @throws NullPointerException if input string is null
	 */
	private static String reverse(String normalString) {
		//Throw an exception if normalString is null
		if(normalString == null) {
			throw new NullPointerException("normalString is null.");
		}
		
		String reversedString = "";
		
		if(normalString.length() > 0) {
			//Loop through string from other side
			for(int i = normalString.length() - 1; i >= 0; i--) {
				//Add all chars to the new string
				reversedString += normalString.charAt(i);
			}
		}
		
		return reversedString;
	}

}
