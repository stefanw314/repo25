package de.hs_mannheim.informatik.ib2_2016ss.TPE.ue3a2.gr2_5;

import java.io.FilterWriter;
import java.io.IOException;
import java.io.Writer;

public class CaesarWriter extends FilterWriter {

	private int delta;
	
	/**
	 * @description: Constructor for Caesar writer
	 * @param out: Writer object
	 * @param delta: Number to be shifted
	 */
	public CaesarWriter(Writer out, int delta) {
		super(out);
		this.delta = delta;
	}
	
	/**
	 * @description: Overwrite method from FilterWriter
	 * @param from: From - where to start in file
	 * @param len: Length - how many chars to write
	 */
	public void write(char[] buf, int from, int len) throws IOException {
		buf = new CrypterCaesar(delta).encrypt(new String(buf)).toCharArray();
		out.write(buf, from, len);
	}
	
	/**
	 * @description: Writes a single int (represents a char) into the file
	 */
	public void write(int c) throws IOException {
		super.write((char) new CrypterCaesar(delta).encrypt("" + c).charAt(0));
	}

	/**
	 * @description: Writes a string into the file
	 * @param off: Offset - where to start in file
	 * @param len: Length - how many chars to write
	 */
	public void write(String str, int off, int len) throws IOException {
		super.write(new CrypterCaesar(delta).encrypt(str),off,len);
	}

	/**
	 * @description: Write the string into the file
	 */
	public void write(String str) throws IOException {
		super.write(new CrypterCaesar(delta).encrypt(str), 0, str.length());
	}
	
}