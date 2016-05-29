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
			
			cryptFileRecursive(encryptedFolderName, sourceDirectory, encryptedFolder, true);
			
			return encryptedFolder;
		}
		else {
			return null;
		}
	}

	@Override
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
			
			cryptFileRecursive(encryptedFolderName, sourceDirectory, encryptedFolder, false);
			
			return encryptedFolder;
		}
		else {
			return null;
		}
	}
	
	public void cryptFileRecursive(String basePath, File srcFile, File destFile, boolean encrypt) {
		for(File currentFile: srcFile.listFiles()) {
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
					if(encrypt) {
						reader = new CaesarReader(new FileReader(currentFile), shift);
						writer = new FileWriter(copiedFile);
					}
					else {
						reader = new FileReader(currentFile);
						writer = new CaesarWriter(new FileWriter(copiedFile), shift);
					}
				}
				catch(IOException e) {}
				
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
					writer = new FileWriter(copiedFile);
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

