package de.hs_mannheim.informatik.ib2_2016ss.TPE.ue3a3.gr2_5;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.io.Writer;
import java.util.LinkedList;
import java.util.Queue;

import de.hs_mannheim.informatik.ib2_2016ss.TPE.ue3a2.gr2_5.CaesarReader;
import de.hs_mannheim.informatik.ib2_2016ss.TPE.ue3a2.gr2_5.CaesarWriter;

public class CaesarFileEncryptor implements IFileEncryptor {

	private int shift = 5;
	
	@Override
	/**
	 * @description encrypts all files in the sourceDirectory and it's sub directory
	 * @param File sourceDirectory to be encrypted
	 */
	public File encrypt(File sourceDirectory) {
		if(sourceDirectory.exists()) {
			//Create encrypted folder
			String encryptedFolderName = sourceDirectory.toString() + "_encrypted";
			int i = 0;
			while(new File(encryptedFolderName).exists()) {
				i++;
				encryptedFolderName = sourceDirectory.toString() + "_encrypted" + i;
			}
			
			File encryptedFolder = new File(encryptedFolderName);
			encryptedFolder.mkdir();
			
			//Start recursion
			cryptFileRecursive(encryptedFolderName, sourceDirectory, encryptedFolder, true);
			
			return encryptedFolder;
		}
		// if sourceDirectory does not exists
		else {
			return null;
		}
	}

	@Override
	/**
	 * @description decrypts all files in the sourceDirectory and it's sub directory)
	 */
	public File decrypt(File sourceDirectory) {
		if(sourceDirectory.exists()) {
			//Create encrypted folder
			String encryptedFolderName = sourceDirectory.toString() + "_decrypted";
			int i = 0;
			while(new File(encryptedFolderName).exists()) {
				i++;
				encryptedFolderName = sourceDirectory.toString() + "_decrypted" + i;
			}
			
			File encryptedFolder = new File(encryptedFolderName);
			encryptedFolder.mkdir();
			
			//starts recursion
			cryptFileRecursive(encryptedFolderName, sourceDirectory, encryptedFolder, false);
			
			return encryptedFolder;
		}
		// if sourceDirectory does not exists
		else {
			return null;
		}
	}
	/**
	 * @description: Encrypts or decrypts a file and its sub directories recursively
	 * @param basePath: the base path
	 * @param srcFile: the folder or file to be encrypted or decrypted
	 * @param destFile: the destination file
	 * @param encrypt: true -> encrypt mode, false -> decrypt mode
	 */
	public void cryptFileRecursive(String basePath, File srcFile, File destFile, boolean encrypt) {
		for(File currentFile: srcFile.listFiles()) {
			//If is directory, create directory and call recursion for new directory
			if(currentFile.isDirectory()) {
				File newDestFile = new File(basePath + "/" + currentFile.getName());
				basePath = basePath + "/" + currentFile.getName();
				File folder = new File(basePath);
				folder.mkdir();
				cryptFileRecursive(basePath, currentFile, newDestFile, true);
			}
			else {
				//Create new file
				File copiedFile = new File(destFile.getAbsolutePath() + "/" + currentFile.getName());
				try {
					copiedFile.createNewFile();
				} catch (IOException e) {}
				
				Reader reader = null;
				Writer writer = null;
				
				try {
					//Toggle between encrypt and decrypt mode
					if(encrypt) {
						reader = new FileReader(currentFile);
						writer = new CaesarWriter(new FileWriter(copiedFile), shift);
					}
					else {
						reader = new CaesarReader(new FileReader(currentFile), shift);
						writer = new FileWriter(copiedFile);
					}
				}
				catch(IOException e) {}
				
				//Queue for saving all chars
				Queue<Integer> fileChars = new LinkedList<Integer>(); 
				int currentChar;
				
				//Read all chars
				try {
					while((currentChar = reader.read()) != -1) {
						fileChars.add(currentChar);
					}
					reader.close();
				} catch (IOException e) {}
				
				//Write all chars
				try {
					while(!fileChars.isEmpty()) {
						writer.write("" + (char) (int) fileChars.poll());
					}
					writer.close();
				}
				catch(IOException e) {}
			}

		}
	}
}

