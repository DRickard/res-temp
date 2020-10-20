package edu.ucla.library.libservices.reserves.db.mappers;

import edu.ucla.library.libservices.reserves.beans.Department;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

public class DistinctDepartmentMapper
  implements RowMapper
{
  public DistinctDepartmentMapper()
  {
    super();
  }

  public Object mapRow( ResultSet rs, int rowNum )
    throws SQLException
  {
    Department bean;

    bean = new Department();
    bean.setDepartmentCode( rs.getString( "department_code" ) );
    bean.setDepartmentID( rs.getInt( "department_id" ) );
    bean.setDepartmentName( rs.getString( "department_name" ) );

    return bean;
  }
}
