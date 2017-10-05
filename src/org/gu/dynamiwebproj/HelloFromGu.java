package org.gu.dynamiwebproj;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.core.Response.ResponseBuilder;

import java.io.*;

@Path("/file")
public class HelloFromGu {

	@Path("/upLoadContent")
	@POST
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	public void writeToFile(@FormParam("uploadContent") String uploadContent) {
		System.out.println("content to upload to server: " + uploadContent);
		new FileContentWriter("/home/yxg23/afile.txt").writeContent(uploadContent);
	}

	@Path("/upLoadContenx")
	@POST
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	public void writeToFile2(@FormParam("uploadContent2") String uploadContent,
			@FormParam("appendFile") String appendFile, @FormParam("writeFile") String writeFile) {
		String t_append = appendFile.trim();
		String t_write = writeFile.trim();

		if (!t_append.equals("")) {
			System.out.println("append files: " + t_append);
			new FileContentWriter("/home/yxg23/" + t_append).writeContent(uploadContent);
		}
		;
		if (!t_write.equals("")) {
			System.out.println("write files: " + t_write);
			new FileContentWriter("/home/yxg23/" + t_write).clearContent();
			new FileContentWriter("/home/yxg23/" + t_write).writeContent(uploadContent);
		}
		;
		if (writeFile.equals("") && writeFile.equals("")) {
			System.out.println("-----------------files: " + appendFile + ":" + writeFile);
			new FileContentWriter("/home/yxg23/afile.txt").writeContent(uploadContent);
		}
	}

	@Path("/create")
	@POST
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.TEXT_PLAIN)
	public String createFile(@FormParam("createFile") String filename) {
		   if(filename.isEmpty()  ) {
			   System.out.println("Missing params for getData");
		        throw new WebApplicationException(501);
		    }
		System.out.println("create file on server ");
		try {
			File file = new File("/home/yxg23/" + filename);
			file.createNewFile();

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return  "looks fine" ;

	}

	
	@Path("/createFileFromUrl")
	@GET
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.TEXT_PLAIN)
	public String createFileFromUrl(@Context UriInfo ui) {
		  String fileString = "";
		  System.out.println("Inside uris "+ui.toString());
		MultivaluedMap<String, String> queryParams = ui.getQueryParameters();
		MultivaluedMap<String, String> pathParams = ui.getPathParameters();

		for (String x : queryParams.keySet()) {
		    System.out.println("key is :  "+x);
			if (  x.equals("filename")) {
				fileString = queryParams.getFirst(x);
				
			};
			
			try {
				File file = new File("/home/yxg23/" + fileString);
				file.createNewFile();
				
				
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		 
		return "file created :  "+ fileString;
	}
	
	@Path("/readFileFromUrl")
	@GET
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.TEXT_PLAIN)
	public String readFileFromUrl(@Context UriInfo ui) {
		  String fileString = "";
		  System.out.println("Inside uris "+ui.toString());
		MultivaluedMap<String, String> queryParams = ui.getQueryParameters();
		MultivaluedMap<String, String> pathParams = ui.getPathParameters();

		for (String x : queryParams.keySet()) {
		    System.out.println("key is :  "+x);
			if (  x.equals("filename")) {
				fileString = queryParams.getFirst(x);
				
			};
			
			File f = new File("/home/yxg23/"+fileString);

			if (f.exists()) {
				System.out.println("File existed");
				String fileContent = new FileContentReader("/home/yxg23/"+fileString).getContent();
				if (fileContent.isEmpty())
					return "File empty";
				return fileContent;
			} else {
				return "File not found!";
			}
			
		}
		 
		return "file created :  "+ fileString;
	}
	
	
	@Path("/clear")
	@POST
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	public void clearFile(@FormParam("clearFile") String filename) {
		System.out.println("clear file on server ");
		new FileContentWriter("/home/yxg23/" + filename).clearContent();
	}

	@Path("/show")
	@POST
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.TEXT_PLAIN)
	public String showFile(@FormParam("showFile") String filename) {
		System.out.println("show file content ");
		String fileContent = new FileContentReader("/home/yxg23/" + filename).getContent();
		return fileContent;
	}

	@Path("/delete")
	@POST
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.TEXT_PLAIN)
	public String deleteFile(@FormParam("deleteFile") String filename) {
		System.out.println(" file delete  ");
		new File("/home/yxg23/" + filename).delete();
		return "deleted file";
	}

	@Path("/getFileContent")
	@GET
	@Produces(MediaType.TEXT_PLAIN)
	public String displayFile() {
		File f = new File("/home/yxg23/afile.txt");

		if (f.exists()) {
			System.out.println("File existed");
			String fileContent = new FileContentReader("/home/yxg23/afile.txt").getContent();
			if (fileContent.isEmpty())
				return "File empty";
			return fileContent;
		} else {
			return "File not found!";
		}

	}

	@Path("/clearFileContent")
	@GET
	public void clearDefaultFile() {
		System.out.println("clear default file on server ");
		new FileContentWriter("/home/yxg23/afile.txt").clearContent();
	}

	@Path("/comExecute/pid")
	@GET
	@Produces(MediaType.TEXT_PLAIN)
	public String displayExeccute() throws InterruptedException, IOException {

		String executeResults = new ComFile().comExec("ps aux");
		return executeResults;
	}

	@Path("/comExecute/docker")
	@GET
	@Produces(MediaType.TEXT_PLAIN)
	public String displayDocker() throws InterruptedException, IOException {

		String executeResults = new ComFile().comExec("docker ps");
		return executeResults;
	}

	@Path("/comExecute/network")
	@GET
	@Produces(MediaType.TEXT_PLAIN)
	public String displayNetwork() throws InterruptedException, IOException {

		String executeResults = new ComFile().comExec("netstat");
		return executeResults;
	}

	@Path("/comExecute/ethernet")
	@GET
	@Produces(MediaType.TEXT_PLAIN)
	public String displayip() throws InterruptedException, IOException {

		String executeResults = new ComFile().comExec("ifconfig");
		return executeResults;
	}

	@Path("/comExecute/reboot")
	@GET
	@Produces(MediaType.TEXT_PLAIN)
	public String displayReboot() throws InterruptedException, IOException {

		String executeResults = new ComFile().comExec("sudo reboot");
		return executeResults;
	}

	@Path("/comExecute/l")
	@GET
	@Produces(MediaType.TEXT_PLAIN)
	public String displayFiles() throws InterruptedException, IOException {

		String executeResults = new ComFile().comExec("ls -lt  /home/yxg23");
		return executeResults;
	}

	@Path("/comExecute/scipull")
	@GET
	@Produces(MediaType.TEXT_PLAIN)
	public String displayScipull() throws InterruptedException, IOException {

		String executeResults = new ComFile().comExec("scipull");
		return executeResults;
	}

	@Path("/comExecute/viewaliases")
	@GET
	@Produces(MediaType.TEXT_PLAIN)
	public String displayViewbash() throws InterruptedException, IOException {

		String executeResults = new ComFile().comExec("grep . /home/yxg23/.bash_aliases");
		return executeResults;
	}

	@Path("/comExecute/history")
	@GET
	@Produces(MediaType.TEXT_PLAIN)
	public String displayHistory() throws InterruptedException, IOException {

		String executeResults = new ComFile().comExec("cat /home/yxg23/.bash_history");
		return executeResults;
	}

	@Path("/comExecute/jetty")
	@GET
	@Produces(MediaType.TEXT_PLAIN)
	public String displayJetty() throws InterruptedException, IOException {

		String executeResults = new ComFile().comExec("ps -n");
		return executeResults;
	}

	@Path("/comExecute/tomcat")
	@GET
	@Produces(MediaType.TEXT_PLAIN)
	public String displayTomcat() throws InterruptedException, IOException {

		String executeResults = new ComFile().comExec("ps -N");
		return executeResults;
	}

	@Path("/uris")
	@GET
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.TEXT_PLAIN)
	public String getUriParameters(@Context UriInfo ui) {
		  String allStrings = "";
		  System.out.println("Inside uris "+ui.toString());
		MultivaluedMap<String, String> queryParams = ui.getQueryParameters();
		MultivaluedMap<String, String> pathParams = ui.getPathParameters();

		for (String x : queryParams.keySet()) {
			allStrings += x + "  :  " + queryParams.get(x);
		}
		allStrings+="-------------------querydone--------------------";
		for (String y : pathParams.keySet()) {
			allStrings += y + "  :  " + pathParams.get(y);
		}

		return "Here are all the query and path keys values pairs:  "+allStrings;
	}

}