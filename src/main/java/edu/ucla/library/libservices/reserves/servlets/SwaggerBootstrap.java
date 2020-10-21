package edu.ucla.library.libservices.reserves.servlets;

import io.swagger.jaxrs.config.BeanConfig;

import javax.servlet.*;
import javax.servlet.http.*;

public class SwaggerBootstrap
  extends HttpServlet
{
  @SuppressWarnings("compatibility:8965192216080884866")
  private static final long serialVersionUID = 1L;

  public void init(ServletConfig config)
    throws ServletException
  {
    super.init(config);

    BeanConfig beanConfig;

    beanConfig = new BeanConfig();
    beanConfig.setVersion( "2.0.0" );
    beanConfig.setSchemes( new String[] { "https" } );
    beanConfig.setHost( "webservices.library.ucla.edu" );
    beanConfig.setBasePath( "/reserves" );
    beanConfig.setResourcePackage( "edu.ucla.library.libservices.reserves.webservices" ); 
    beanConfig.setScan( true );
  }
}
