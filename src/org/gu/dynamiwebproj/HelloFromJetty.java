package org.gu.dynamiwebproj;

import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.io.*;

@Path("/file")
public class HelloFromJetty {

	@Path("/getFileContent")
	@GET
	@Produces(MediaType.TEXT_PLAIN)
	public String displayFile() {
		
		String fileContent = new FileContentReader("/home/yxg23/afile.txt").getContent();
		return fileContent;
	}


	@Path("/upLoadContent")
	@POST
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	public void writeCToFile(@FormParam("uploadContent") String uploadContent) {
			System.out.println("content to upload to server: "+uploadContent);
		new FileContentWriter("/home/yxg23/afile.txt").writeContent(uploadContent);
	}

}