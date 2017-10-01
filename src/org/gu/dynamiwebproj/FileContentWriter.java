package org.gu.dynamiwebproj;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;

public class FileContentWriter {

	String fileName;

	public FileContentWriter(String afilename) {
		this.fileName = afilename;
	}

	public void writeContent(String fileContent) {

		try {

			FileWriter fw = new FileWriter(this.fileName, true);
			BufferedWriter bw = new BufferedWriter(fw);
			bw.write(fileContent + "\n");
			// fw.close();
			bw.flush();
		
			System.out.println("wrote to file, success");

		} catch (IOException e) {
			System.out.println("error while writing file" + this.fileName);
			e.printStackTrace();
		}
		
	}

	public void clearContent() {
	 
		System.out.println("inside clear  file");
		try {

			FileWriter fw = new FileWriter(this.fileName); 
			BufferedWriter bw = new BufferedWriter(fw);
			System.out.println("inside clear  file1");
			bw.write("\n");
			System.out.println("inside clear  file2");
			// fw.close();
			bw.flush(); 

		} catch (IOException e) {
			System.out.println("error while clearing file" + this.fileName);
			e.printStackTrace();
		}
		 
	}

}
