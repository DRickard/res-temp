package edu.ucla.library.libservices.reserves.generators;

import edu.ucla.library.libservices.reserves.beans.Department;
import edu.ucla.library.libservices.reserves.db.mappers.DepartmentMapper;
import edu.ucla.library.libservices.reserves.db.utiltiy.DataSourceFactory;

import java.util.List;

import javax.sql.DataSource;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import org.springframework.jdbc.core.JdbcTemplate;
//import org.springframework.jdbc.datasource.DriverManagerDataSource;

@XmlRootElement( name = "departmentList" )
public class EverythingGenerator
{
  private static final String QUARTER_QUERY =
    "SELECT DISTINCT department_id,department_code,department_name, quarter"
    + " FROM vger_support.reserve_departments WHERE quarter = ? "
    + "ORDER BY department_name";

 //private DriverManagerDataSource ds;
  private DataSource ds;
  @XmlElement( name = "department" )
  private List<Department> departments;
  private String quarter;
  private String dbName;

  public EverythingGenerator()
  {
    super();
  }

  public void setQuarter( String quarter )
  {
    this.quarter = quarter;
  }

  private String getQuarter()
  {
    return quarter;
  }

  public void setDbName( String dbName )
  {
    this.dbName = dbName;
  }

  private String getDbName()
  {
    return dbName;
  }

  public void setDs( DataSource ds )
  {
    this.ds = ds;
  }

  private DataSource getDs()
  {
    return ds;
  }

  private void makeConnection()
  {
    ds = DataSourceFactory.createDataSource( getDbName() );
    //ds = DataSourceFactory.createVgerSource();
  }

  public void prepDeptsByQuarter()
  {
    makeConnection();

    departments = new JdbcTemplate( ds ).query( QUARTER_QUERY, new Object[]
          { getQuarter() }, new DepartmentMapper() );
    for ( Department theDepartment : departments )
    {
      CourseGenerator generator;
      generator = new CourseGenerator();
      generator.setDepartmentID( theDepartment.getDepartmentID() );
      generator.setDbName( getDbName() );
      generator.setQuarter(getQuarter());
      theDepartment.setCourses(generator.getCoursesWithItems());
    }
  }
}
