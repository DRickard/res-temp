package edu.ucla.library.libservices.reserves.generators;

import edu.ucla.library.libservices.reserves.beans.Department;
import edu.ucla.library.libservices.reserves.db.mappers.DepartmentMapper;
import edu.ucla.library.libservices.reserves.db.mappers.DistinctDepartmentMapper;
import edu.ucla.library.libservices.reserves.db.utiltiy.DataSourceFactory;

import java.util.List;

import javax.sql.DataSource;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import org.springframework.jdbc.core.JdbcTemplate;
//import org.springframework.jdbc.datasource.DriverManagerDataSource;

@XmlRootElement( name = "departmentList" )
public class DepartmentGenerator
{
  //private DriverManagerDataSource ds;
  private DataSource ds;
  @XmlElement( name = "department" )
  private List<Department> departments;
  private String quarter;
  private String dbName;

  private static final String DEPT_QUERY =
    "SELECT DISTINCT department_id,department_code,department_name "
    + "FROM vger_support.reserve_departments WHERE quarter = "
    + "vger_support.get_current_quarter() ORDER BY department_name";
  private static final String QUARTER_QUERY =
    "SELECT DISTINCT department_id,department_code,department_name, quarter"
    + " FROM vger_support.reserve_departments WHERE quarter = ? "
    + "ORDER BY department_name";

  public DepartmentGenerator()
  {
    super();
  }

  public List<Department> getDepartments()
  {
    return departments;
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

  public void prepCurrentDepts()
  {
    makeConnection();

    departments =
        new JdbcTemplate( ds ).query( DEPT_QUERY, new DistinctDepartmentMapper() );
  }

  public void testPrepCurrentDepts()
  {
    departments =
        new JdbcTemplate( getDs() ).query( DEPT_QUERY, new DistinctDepartmentMapper() );
  }

  public void prepDeptsByQuarter()
  {
    makeConnection();

    departments = new JdbcTemplate( ds ).query( QUARTER_QUERY, new Object[]
          { getQuarter() }, new DepartmentMapper() );
  }

  public void testPrepDeptsByQuarter()
  {
    departments = new JdbcTemplate( getDs() ).query( QUARTER_QUERY, new Object[]
          { getQuarter() }, new DepartmentMapper() );
  }
}
