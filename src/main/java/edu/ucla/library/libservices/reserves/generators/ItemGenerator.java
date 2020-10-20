package edu.ucla.library.libservices.reserves.generators;

import edu.ucla.library.libservices.reserves.beans.Item;
import edu.ucla.library.libservices.reserves.db.mappers.ItemMapper;
import edu.ucla.library.libservices.reserves.db.utiltiy.DataSourceFactory;

import java.util.List;

import javax.sql.DataSource;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import org.springframework.jdbc.core.JdbcTemplate;
//import org.springframework.jdbc.datasource.DriverManagerDataSource;

@XmlRootElement( name = "itemList" )
public class ItemGenerator
{
  //private DriverManagerDataSource ds;
  private DataSource ds;
  @XmlElement( name = "item" )
  private List<Item> items;
  private String quarter;
  private String srsNumber;
  private String dbName;

  private static final String ITEMS_QUERY =
    "SELECT * FROM vger_support.reserve_items_course_term WHERE srs_number = ?" +
    " AND quarter = vger_support.get_current_quarter() ORDER BY title";
  private static final String QUARTER_QUERY =
    "SELECT * FROM vger_support.reserve_items_course_term WHERE srs_number = ?" +
    " AND quarter = ? ORDER BY title";

  public ItemGenerator()
  {
    super();
  }

  public List<Item> getItems()
  {
    makeConnection();

    items = new JdbcTemplate( ds ).query( QUARTER_QUERY, new Object[]
          { getSrsNumber(), getQuarter() }, new ItemMapper() );

    return items;
  }

  public void setQuarter( String quarter )
  {
    this.quarter = quarter;
  }

  private String getQuarter()
  {
    return quarter;
  }

  public void setDs( DataSource ds )
  {
    this.ds = ds;
  }

  private DataSource getDs()
  {
    return ds;
  }

  public void setSrsNumber( String srsNumber )
  {
    this.srsNumber = srsNumber;
  }

  private String getSrsNumber()
  {
    return srsNumber;
  }

  public void setDbName( String dbName )
  {
    this.dbName = dbName;
  }

  private String getDbName()
  {
    return dbName;
  }

  private void makeConnection()
  {
    ds = DataSourceFactory.createDataSource( getDbName() );
    //ds = DataSourceFactory.createVgerSource();
  }

  public void prepItems()
  {
    makeConnection();

    items = new JdbcTemplate( ds ).query( ITEMS_QUERY, new Object[]
          { getSrsNumber() }, new ItemMapper() );
  }

  public void testPrepItems()
  {
    items = new JdbcTemplate( getDs() ).query( ITEMS_QUERY, new Object[]
          { getSrsNumber() }, new ItemMapper() );
  }

  public void prepItemsByQuarter()
  {
    makeConnection();

    items = new JdbcTemplate( ds ).query( QUARTER_QUERY, new Object[]
          { getSrsNumber(), getQuarter() }, new ItemMapper() );
  }

  public void testPrepItemsByQuarter()
  {
    items = new JdbcTemplate( getDs() ).query( QUARTER_QUERY, new Object[]
          { getSrsNumber(), getQuarter() }, new ItemMapper() );
  }
}
