package edu.ucla.library.libservices.reserves.webservices;

import edu.ucla.library.libservices.reserves.generators.ItemGenerator;

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

@Api(value = "/items")
@Path( "/items/" )
public class ItemsService
{
  @Context
  ServletConfig config;

  public ItemsService()
  {
    super();
  }

  @GET
  @Produces( "text/xml, application/json" )
  @Path( "/course/{srsNo}" )
  @ApiOperation(value = "Finds reserved items for a course identified by Registrar srsNo",
                responseContainer = "Response",
                response = ItemGenerator.class, httpMethod = "GET", 
		produces = "text/xml, application/json")
  public Response itemsBySRS( @ApiParam(value = "course SRS number", required = true) 
		              @PathParam( "srsNo" ) String srsNo )
  {
    ItemGenerator generator;

    generator = new ItemGenerator();
    generator.setSrsNumber( srsNo );
    generator.setDbName( config.getServletContext().getInitParameter( "datasource.oracle" ) );

    try
    {
      generator.prepItems();
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
  @Path( "/course/{srsNo}/term/{term}" )
  @ApiOperation(value = "Finds reserved items for a course during {term} academic term, identified by Registrar srsNo",
                responseContainer = "Response",
                response = ItemGenerator.class, httpMethod = "GET", 
		produces = "text/xml, application/json")
  public Response itemsBySRSTerm( @ApiParam(value = "course SRS number", required = true) 
		                  @PathParam( "srsNo" ) String srsNo, 
				  @ApiParam(value = "UCLA academic term", required = true) 
				  @PathParam( "term" ) String term )
  {
    ItemGenerator generator;

    generator = new ItemGenerator();
    generator.setSrsNumber( srsNo );
    generator.setDbName( config.getServletContext().getInitParameter( "datasource.oracle" ) );
    generator.setQuarter( term );

    try
    {
      generator.prepItemsByQuarter();
      return Response.ok().entity( generator ).build();
    }
    catch ( Exception e )
    {
      return Response.serverError().entity( "search failed: " +
                                            e.getMessage() ).build();
    }
  }
}
