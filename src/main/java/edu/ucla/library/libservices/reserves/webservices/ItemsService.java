package edu.ucla.library.libservices.reserves.webservices;

import edu.ucla.library.libservices.reserves.generators.ItemGenerator;

import javax.servlet.ServletConfig;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;

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
  public Response itemsBySRS( @PathParam( "srsNo" ) String srsNo )
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
  public Response itemsBySRSTerm( @PathParam( "srsNo" ) String srsNo, @PathParam( "term" ) String term )
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
