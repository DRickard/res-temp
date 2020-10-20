package edu.ucla.library.libservices.reserves.webservices;

import edu.ucla.library.libservices.reserves.generators.DepartmentGenerator;

import javax.servlet.ServletConfig;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;

@Path("/departments/")
public class DepartmentsService
{
  @Context
  ServletConfig config;

  public DepartmentsService()
  {
    super();
  }

  @GET
  @Produces("text/xml, application/json")
  @Path("/current")
  public Response currentDepartments()
  {
    DepartmentGenerator generator;

    generator = new DepartmentGenerator();
    generator.setDbName(config.getServletContext().getInitParameter("datasource.oracle"));

    try
    {
      generator.prepCurrentDepts();
      return Response.ok().entity(generator).build();
    }
    catch (Exception e)
    {
      return Response.serverError()
                     .entity("search failed: " + e.getMessage())
                     .build();
    }
  }

  @GET
  @Produces("text/xml, application/json")
  @Path("/during/{term}")
  public Response departmentsByTerm( @PathParam("term") String term )
  {
    DepartmentGenerator generator;

    generator = new DepartmentGenerator();
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
