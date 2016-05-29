package de.hs_mannheim.informatik.ib2_2016ss.TPE.ue3a2.gr2_5;

import java.io.FilterReader;
import java.io.IOException;
import java.io.Reader;

public class CaesarReader extends FilterReader {

	private int delta;
	
	/**
	 * @description: Constructor for Caesar Reader
	 * @param in: Object to be read
	 * @param delta: Number to be shifted
	 */
	public CaesarReader(Reader in, int delta) {
		super(in);
		this.delta = delta;
	}

	/**
	 * @description: Overwrite method from FilterReader
	 */
	public int read(char[] buf, int from, int len) throws IOException {
		int countBuffer = in.read(buf, from, len);
		buf = new CrypterCaesar(delta).decrypt(new String(buf)).toCharArray();
		return countBuffer;
	}
	
	/**
	 * @description: Overwrite method from FilterReader
	 */
	public int read() throws IOException {
		int readInt = super.read();
		
		if(readInt == -1) {
			return -1;
		}
		
		return (int) new CrypterCaesar(delta).decrypt("" + (char) readInt).charAt(0);
	 }

}