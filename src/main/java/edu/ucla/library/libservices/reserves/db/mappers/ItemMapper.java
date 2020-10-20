package edu.ucla.library.libservices.reserves.db.mappers;

import edu.ucla.library.libservices.reserves.beans.Item;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

public class ItemMapper
  implements RowMapper
{
  public ItemMapper()
  {
    super();
  }

  public Object mapRow( ResultSet rs, int rowNum )
    throws SQLException
  {
    Item bean;
    
    bean = new Item();
    bean.setCallNo( rs.getString( "call_no" ) );
    bean.setCopyNumber( rs.getInt( "copy_number" ) );
    bean.setCourseName( rs.getString( "course_name" ) );
    bean.setItemEnum( rs.getString( "item_enum" ) );
    bean.setItemID( rs.getInt( "item_id" ) );
    bean.setItemStatus( rs.getString( "item_status" ) );
    bean.setLibraryName( rs.getString( "library_name" ) );
    bean.setLocation( rs.getString( "location" ) );
    bean.setMapLink( rs.getString( "map_link" ) );
    bean.setOclc( rs.getString( "oclc" ) );
    bean.setQuarter( rs.getString( "quarter" ) );
    bean.setSrsNumber( rs.getString( "srs_number" ) );
    bean.setTitle( rs.getString( "title" ) );

    return bean;
  }
}

