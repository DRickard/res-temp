package edu.ucla.library.libservices.reserves.beans;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlType(name = "theItem")
@XmlAccessorType( XmlAccessType.FIELD )
public class Item
{
  @XmlElement( name = "srsNumber" )
  private String srsNumber;
  @XmlElement( name = "courseName" )
  private String courseName;
  @XmlElement( name = "location" )
  private String location;
  @XmlElement( name = "libraryName" )
  private String libraryName;
  @XmlElement( name = "mapLink" )
  private String mapLink;
  @XmlElement( name = "itemID" )
  private int itemID;
  @XmlElement( name = "title" )
  private String title;
  @XmlElement( name = "itemEnum" )
  private String itemEnum;
  @XmlElement( name = "copyNumber" )
  private int copyNumber;
  @XmlElement( name = "callNo" )
  private String callNo;
  @XmlElement( name = "itemStatus" )
  private String itemStatus;
  @XmlElement( name = "quarter" )
  private String quarter;
  @XmlElement( name = "oclc" )
  private String oclc;

  public Item()
  {
    super();
  }

  public void setSrsNumber( String srsNumber )
  {
    this.srsNumber = srsNumber;
  }

  public String getSrsNumber()
  {
    return srsNumber;
  }

  public void setCourseName( String courseName )
  {
    this.courseName = courseName;
  }

  public String getCourseName()
  {
    return courseName;
  }

  public void setLocation( String location )
  {
    this.location = location;
  }

  public String getLocation()
  {
    return location;
  }

  public void setLibraryName( String libraryName )
  {
    this.libraryName = libraryName;
  }

  public String getLibraryName()
  {
    return libraryName;
  }

  public void setMapLink( String mapLink )
  {
    this.mapLink = mapLink;
  }

  public String getMapLink()
  {
    return mapLink;
  }

  public void setItemID( int itemID )
  {
    this.itemID = itemID;
  }

  public int getItemID()
  {
    return itemID;
  }

  public void setTitle( String title )
  {
    this.title = title;
  }

  public String getTitle()
  {
    return title;
  }

  public void setItemEnum( String itemEnum )
  {
    this.itemEnum = itemEnum;
  }

  public String getItemEnum()
  {
    return itemEnum;
  }

  public void setCopyNumber( int copyNumber )
  {
    this.copyNumber = copyNumber;
  }

  public int getCopyNumber()
  {
    return copyNumber;
  }

  public void setCallNo( String callNo )
  {
    this.callNo = callNo;
  }

  public String getCallNo()
  {
    return callNo;
  }

  public void setItemStatus( String itemStatus )
  {
    this.itemStatus = itemStatus;
  }

  public String getItemStatus()
  {
    return itemStatus;
  }

  public void setQuarter( String quarter )
  {
    this.quarter = quarter;
  }

  public String getQuarter()
  {
    return quarter;
  }

  public void setOclc( String oclc )
  {
    this.oclc = oclc;
  }

  public String getOclc()
  {
    return oclc;
  }
}
