package edu.ucla.library.libservices.reserves.webservices;

import edu.ucla.library.libservices.reserves.generators.EverythingGenerator;

import javax.servlet.ServletConfig;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;

@Path("/allreserves/")
public class EverythingService
{
  @Context
  ServletConfig config;

  public EverythingService()
  {
    super();
  }

  @GET
  @Produces("text/xml, application/json")
  @Path("/during/{term}")
  public Response reservesByTerm(@PathParam("term") String term)
  {
    EverythingGenerator generator;

    generator = new EverythingGenerator();
    generator.setDbName(config.getServletContext().getInitParameter("datasource.oracle"));
    generator.setQuarter(term);

    try
    {
      generator.prepDeptsByQuarter();
      return Response.ok()
                     .entity(generator)
                     .build();
    }
    catch (Exception e)
    {
      return Response.serverError()
                     .entity("search failed: " + e.getMessage())
                     .build();
    }
  }
}
