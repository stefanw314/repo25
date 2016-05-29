package de.hs_mannheim.informatik.ib2_2016ss.TPE.ue3a2.gr2_5;

public class CrypterCaesar implements Crypter {

	private int delta;
	private char[] complete = new char[58];

	/**
	 * @description: Create complete caesar array for shifting
	 * @param delta: Shift can be positive or negative
	 */
	public CrypterCaesar(int delta) {
		this.delta = delta;

		for(int i = 0; i < 26; i++) {
			complete[i] = (char) (i + 'A');
		}

		int j = 0;
		for(int i = 26; i < 52; i++) {
			complete[i] = (char) (j + 'a');
			j++;
		}

		complete[52] = 'Ä';
		complete[53] = 'Ö';
		complete[54] = 'Ü';
		complete[55] = 'ä';
		complete[56] = 'ö';
		complete[57] = 'ü';
	}

	/**
	 * @description: Make default constructor private, we need a delta
	 */
	private CrypterCaesar() {}

	/**
	 * @description: Encrypts a message by using caesar function
	 * @return: encrypted message
	 */
	public String encrypt(String message) {
		return caesar(message, false);
	}

	/**
	 * @description: Decrypts a message by using caesar function
	 * @return: decrypted message
	 */
	public String decrypt(String cypherText) {
		return caesar(cypherText, true);
	}

	/**
	 * @description: Shifts a message by using the Caesar shift function for each letter
	 * @param message
	 * @param reverse: Switching between encrypt (false) and decrypt (true)
	 * @return: crypted message
	 */
	public String caesar(String message, boolean reverse) {
		String cryptedString = "";
		for(int a = 0; a < message.length(); a++) {
			int positionCompleteArray = findPosition(message.charAt(a));
			if(positionCompleteArray != -1) {
				cryptedString += caesarShift(positionCompleteArray, reverse);
			}
			else {
				cryptedString += message.charAt(a);
			}
		}
		return cryptedString;
	}

	/**
	 * @description: Searches for message char position in complete array 
	 * @param a the search char 
	 * @return -1 if not found, else position
	 */
	public int findPosition(char a) {
		for(int i = 0; i < complete.length; i++) {
			if(a == complete[i]) {
				return i;
			}
		}
		return -1;
	}

	/**
	 * @description: Shifts letter to left or right, if out of bounce go to beginning or end
	 * @param positionCompleteArray: position to be shifted
	 * @param reverse: Switching between encrypt (false) and decrypt (true)
	 * @return: shifted char String
	 */
	public String caesarShift(int positionCompleteArray, boolean reverse) {
		int tempDelta = reverse ? - delta : delta;
		int newPosition = positionCompleteArray + tempDelta;

		while(newPosition < 0) {
			newPosition += 58;
		}
		while(newPosition >= complete.length) {
			newPosition -= 58;
		}

		return "" + complete[newPosition];
	}

}
