package org.gu.dynamiwebproj;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.UriInfo;

public class PrintUri {

	private String allStrings = "";

	@Path("/ur")
	@GET
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.TEXT_PLAIN)
	public String getUriParameters(@Context UriInfo ui) {

		MultivaluedMap<String, String> queryParams = ui.getQueryParameters();
		MultivaluedMap<String, String> pathParams = ui.getPathParameters();

		for (String x : queryParams.keySet()) {
			allStrings += x + "  :  " + queryParams.get(x);
		}

		for (String y : pathParams.keySet()) {
			allStrings += y + "  :  " + pathParams.get(y);
		}
		
		return allStrings;
	}

}
