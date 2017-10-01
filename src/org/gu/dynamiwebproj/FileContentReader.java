package org.gu.dynamiwebproj;

import java.io.*;

/**
 * Created by yxg23 on 9/27/17.
 */
public class FileContentReader {

	String fileName;

	public FileContentReader(String afilename) {
		this.fileName = afilename;
	}

	public String getContent() {

		String buildupString = "";
		String tempString = "";
		
		try {
			BufferedReader br = new BufferedReader(new FileReader(fileName));
			

			while ((tempString = br.readLine()) != null) {
				buildupString += tempString+"\n";
			}
			
		} catch (IOException e) {
			System.out.println("error reading file: "+this.fileName);
			e.printStackTrace();
		}
		
		return buildupString;
		
	}
}
