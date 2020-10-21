package edu.ucla.library.libservices.reserves.webservices;

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

@Api(value = "/courses")
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
  @ApiOperation(value = "Finds courses in a department with reserves",
                notes = "Valid deptID values are pulled from /departments/current or /departments/during/{term} service", 
		responseContainer = "Response",
                response = CourseGenerator.class, httpMethod = "GET", produces = "text/xml, application/json")
  public Response coursesByDept( @ApiParam(value = "department to be retrieved", required = true) 
		                 @PathParam( "deptID" ) int deptID )
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
  @ApiOperation(value = "Finds courses in a department with reserves during an academic term",
                notes = "Valid deptID values are pulled from /departments/current or /departments/during/{term} service", 
		responseContainer = "Response",
                response = CourseGenerator.class, httpMethod = "GET", produces = "text/xml, application/json")

  public Response coursesByTerm( @ApiParam(value = "department to be retrieved", required = true) 
		                 @PathParam( "deptID" ) int deptID, 
				 @ApiParam(value = "academic term for filter", required = true)  
				 @PathParam( "term" ) String term )
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
