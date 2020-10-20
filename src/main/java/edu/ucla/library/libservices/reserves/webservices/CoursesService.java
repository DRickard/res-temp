package edu.ucla.library.libservices.reserves.webservices;

import edu.ucla.library.libservices.reserves.generators.CourseGenerator;

import javax.servlet.ServletConfig;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;

@Path( "/courses/" )
public class CoursesService
{
  @Context
  ServletConfig config;

  public CoursesService()
  {
    super();
  }

  @GET
  @Produces( "text/xml, application/json" )
  @Path( "/dept/{deptID}" )
  public Response coursesByDept( @PathParam( "deptID" ) int deptID )
  {
    CourseGenerator generator;

    generator = new CourseGenerator();
    generator.setDepartmentID( deptID );
    generator.setDbName( config.getServletContext().getInitParameter( "datasource.oracle" ) );

    try
    {
      generator.prepCoursesByDept();
      return Response.ok().entity( generator ).build();
    }
    catch ( Exception e )
    {
      return Response.serverError().entity( "search failed: " +
                                            e.getMessage() ).build();
    }
  }

  @GET
  @Produces( "text/xml, application/json" )
  @Path( "/dept/{deptID}/term/{term}" )
  public Response coursesByTerm( @PathParam( "deptID" ) int deptID, @PathParam( "term" ) String term )
  {
    CourseGenerator generator;

    generator = new CourseGenerator();
    generator.setDepartmentID( deptID );
    generator.setDbName( config.getServletContext().getInitParameter( "datasource.oracle" ) );
    generator.setQuarter( term );

    try
    {
      generator.prepCoursesByQuarter();
      return Response.ok().entity( generator ).build();
    }
    catch ( Exception e )
    {
      return Response.serverError().entity( "search failed: " +
                                            e.getMessage() ).build();
    }
  }

}
