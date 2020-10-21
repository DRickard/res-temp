package edu.ucla.library.libservices.reserves.webservices;

import edu.ucla.library.libservices.reserves.generators.EverythingGenerator;
import edu.ucla.library.libservices.reserves.generators.CourseGenerator;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

import javax.servlet.ServletConfig;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;

@Api(value = "/all")
@Path("/all/")
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
  @Path("/reserves/during/{term}")
  @ApiOperation(value = "Finds all reserves data during an academic term",
                notes = "Valid term values are in form YYW|S|1|F", responseContainer = "Response",
                response = EverythingGenerator.class, httpMethod = "GET", 
                produces = "text/xml, application/json")
  public Response reservesByTerm( @ApiParam(value = "academic term for filter", required = true) 
		                  @PathParam("term") String term )
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

  @GET
  @Produces("text/xml, application/json")
  @Path("/courses/during/{term}")
  @ApiOperation(value = "Finds all courses with reserves during an academic term",
                notes = "Valid term values are in form YYW|S|1|F", responseContainer = "Response",
                response = CourseGenerator.class, httpMethod = "GET",
                produces = "text/xml, application/json")
  public Response coursesByTerm( @ApiParam(value = "academic term for filter", required = true)
		                 @PathParam("term") String term )
  {
    CourseGenerator generator;

    generator = new CourseGenerator();
    generator.setDbName(config.getServletContext().getInitParameter("datasource.oracle"));
    generator.setQuarter(term);

    try
    {
      generator.prepAllCoursesByQuarter();
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
