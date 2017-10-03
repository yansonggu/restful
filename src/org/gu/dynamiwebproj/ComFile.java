package org.gu.dynamiwebproj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class ComFile {

	public ComFile() {
		super();
		// TODO Auto-generated constructor stub
	}

	public String comExec(String stringCommand) throws InterruptedException, IOException {
		Runtime r = Runtime.getRuntime();
		Process p = r.exec(stringCommand);
		p.waitFor();
		BufferedReader b = new BufferedReader(new InputStreamReader(p.getInputStream()));
		String line = "";
		String results = "";
		while ((line = b.readLine()) != null) {
			System.out.println(line);
			results+=line+"\n";
		}

		b.close();
		return results;

	}
}
