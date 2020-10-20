package edu.ucla.library.libservices.reserves.beans;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlType(name = "theCourse")
@XmlAccessorType( XmlAccessType.FIELD )
public class Course
{
  @XmlElement( name = "courseNumber" )
  private String courseNumber;
  @XmlElement( name = "courseName" )
  private String courseName;
  @XmlElement( name = "sectionID" )
  private int sectionID;
  @XmlElement( name = "departmentID" )
  private int departmentID;
  @XmlElement( name = "departmentName" )
  private String departmentName;
  @XmlElement( name = "srsNumber" )
  private String srsNumber;
  @XmlElement( name = "quarter" )
  private String quarter;
  @XmlElement( name = "url" )
  private String url;
  @XmlElement( name = "items", required = false )
  private List<Item> items;

  public Course()
  {
    super();
  }

  public void setCourseNumber( String courseNumber )
  {
    this.courseNumber = courseNumber;
  }

  public String getCourseNumber()
  {
    return courseNumber;
  }

  public void setCourseName( String courseName )
  {
    this.courseName = courseName;
  }

  public String getCourseName()
  {
    return courseName;
  }

  public void setSectionID( int sectionID )
  {
    this.sectionID = sectionID;
  }

  public int getSectionID()
  {
    return sectionID;
  }

  public void setSrsNumber( String srsNumber )
  {
    this.srsNumber = srsNumber;
  }

  public String getSrsNumber()
  {
    return srsNumber;
  }

  public void setQuarter( String quarter )
  {
    this.quarter = quarter;
  }

  public String getQuarter()
  {
    return quarter;
  }

  public void setDepartmentID( int departmentID )
  {
    this.departmentID = departmentID;
  }

  public int getDepartmentID()
  {
    return departmentID;
  }

  public void setUrl(String url)
  {
    this.url = url;
  }

  public String getUrl()
  {
    return url;
  }

  public void setItems(List<Item> items)
  {
    this.items = items;
  }

  public List<Item> getItems()
  {
    return items;
  }

  public void setDepartmentName(String departmentName)
  {
    this.departmentName = departmentName;
  }

  public String getDepartmentName()
  {
    return departmentName;
  }
}
