package org.gu.dynamiwebproj;

import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.io.*;

@Path("/file")
public class HelloFromGu {

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
	public void writeToFile(@FormParam("uploadContent") String uploadContent) {
			System.out.println("content to upload to server: "+uploadContent);
		new FileContentWriter("/home/yxg23/afile.txt").writeContent(uploadContent);
	}
	
	@Path("/clearFile")
	@GET
	public void clearFile() {		
		System.out.println("clear file on server ");
		new FileContentWriter("/home/yxg23/afile.txt").clearContent();
	}
	
	@Path("/comExecute/pid")
	@GET
	@Produces(MediaType.TEXT_PLAIN)
	public String displayExeccute() throws InterruptedException, IOException {
		
		String executeResults= new ComFile().comExec("ps aux");
		return executeResults;
	}
	@Path("/comExecute/docker")
	@GET
	@Produces(MediaType.TEXT_PLAIN)
	public String displayDocker() throws InterruptedException, IOException {
		
		String executeResults= new ComFile().comExec("docker ps");
		return executeResults;
	}
	@Path("/comExecute/network")
	@GET
	@Produces(MediaType.TEXT_PLAIN)
	public String displayNetwork() throws InterruptedException, IOException {
		
		String executeResults= new ComFile().comExec("netstat");
		return executeResults;
	}
	@Path("/comExecute/ethernet")
	@GET
	@Produces(MediaType.TEXT_PLAIN)
	public String displayip() throws InterruptedException, IOException {
		
		String executeResults= new ComFile().comExec("ifconfig");
		return executeResults;
	}
	@Path("/comExecute/reboot")
	@GET
	@Produces(MediaType.TEXT_PLAIN)
	public String displayReboot() throws InterruptedException, IOException {
		
		String executeResults= new ComFile().comExec("sudo reboot");
		return executeResults;
	}
	@Path("/comExecute/l")
	@GET
	@Produces(MediaType.TEXT_PLAIN)
	public String displayFiles() throws InterruptedException, IOException {
		
		String executeResults= new ComFile().comExec("ls -l  /home/yxg23");
		return executeResults;
	}
	@Path("/comExecute/scipull")
	@GET
	@Produces(MediaType.TEXT_PLAIN)
	public String displayScipull() throws InterruptedException, IOException {
		
		String executeResults= new ComFile().comExec("scipull");
		return executeResults;
	}
	
	@Path("/comExecute/viewaliases")
	@GET
	@Produces(MediaType.TEXT_PLAIN)
	public String displayViewbash() throws InterruptedException, IOException {
		
		String executeResults= new ComFile().comExec("grep . /home/yxg23/.bash_aliases");
		return executeResults;
	}
	
	@Path("/comExecute/history")
	@GET
	@Produces(MediaType.TEXT_PLAIN)
	public String displayHistory() throws InterruptedException, IOException {
		
		String executeResults= new ComFile().comExec("history");
		return executeResults;
	}
	@Path("/comExecute/jetty")
	@GET
	@Produces(MediaType.TEXT_PLAIN)
	public String displayJetty() throws InterruptedException, IOException {
		
		String executeResults= new ComFile().comExec("ps aux\\|grep jetty");
		return executeResults;
	}
	@Path("/comExecute/tomcat")
	@GET
	@Produces(MediaType.TEXT_PLAIN)
	public String displayTomcat() throws InterruptedException, IOException {
		
		String executeResults= new ComFile().comExec("ps aux\\|grep tomcat");
		return executeResults;
	}
}