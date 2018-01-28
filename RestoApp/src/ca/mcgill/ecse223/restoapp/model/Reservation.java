/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.27.0.3748.a67f04a modeling language!*/

package ca.mcgill.ecse223.restoapp.model;
import java.sql.Date;
import java.util.*;

// line 33 "../../../../../../ump/tmp693360/model.ump"
// line 80 "../../../../../../ump/tmp693360/model.ump"
public class Reservation
{

  //------------------------
  // STATIC VARIABLES
  //------------------------

  private static int nextId = 1;

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Reservation Attributes
  private int numberofTables;
  private int numberOfSeats;
  private Date reservationDate;
  private int phone;
  private String email;
  private String customerName;

  //Autounique Attributes
  private int id;

  //Reservation Associations
  private List<Table> tables;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public Reservation(int aNumberofTables, int aNumberOfSeats, Date aReservationDate, int aPhone, String aEmail, String aCustomerName)
  {
    numberofTables = aNumberofTables;
    numberOfSeats = aNumberOfSeats;
    reservationDate = aReservationDate;
    phone = aPhone;
    email = aEmail;
    customerName = aCustomerName;
    id = nextId++;
    tables = new ArrayList<Table>();
  }

  //------------------------
  // INTERFACE
  //------------------------

  public boolean setNumberofTables(int aNumberofTables)
  {
    boolean wasSet = false;
    numberofTables = aNumberofTables;
    wasSet = true;
    return wasSet;
  }

  public boolean setNumberOfSeats(int aNumberOfSeats)
  {
    boolean wasSet = false;
    numberOfSeats = aNumberOfSeats;
    wasSet = true;
    return wasSet;
  }

  public boolean setReservationDate(Date aReservationDate)
  {
    boolean wasSet = false;
    reservationDate = aReservationDate;
    wasSet = true;
    return wasSet;
  }

  public boolean setPhone(int aPhone)
  {
    boolean wasSet = false;
    phone = aPhone;
    wasSet = true;
    return wasSet;
  }

  public boolean setEmail(String aEmail)
  {
    boolean wasSet = false;
    email = aEmail;
    wasSet = true;
    return wasSet;
  }

  public boolean setCustomerName(String aCustomerName)
  {
    boolean wasSet = false;
    customerName = aCustomerName;
    wasSet = true;
    return wasSet;
  }

  public int getNumberofTables()
  {
    return numberofTables;
  }

  public int getNumberOfSeats()
  {
    return numberOfSeats;
  }

  public Date getReservationDate()
  {
    return reservationDate;
  }

  public int getPhone()
  {
    return phone;
  }

  public String getEmail()
  {
    return email;
  }

  public String getCustomerName()
  {
    return customerName;
  }

  public int getId()
  {
    return id;
  }

  public Table getTable(int index)
  {
    Table aTable = tables.get(index);
    return aTable;
  }

  public List<Table> getTables()
  {
    List<Table> newTables = Collections.unmodifiableList(tables);
    return newTables;
  }

  public int numberOfTables()
  {
    int number = tables.size();
    return number;
  }

  public boolean hasTables()
  {
    boolean has = tables.size() > 0;
    return has;
  }

  public int indexOfTable(Table aTable)
  {
    int index = tables.indexOf(aTable);
    return index;
  }

  public static int minimumNumberOfTables()
  {
    return 0;
  }

  public boolean addTable(Table aTable)
  {
    boolean wasAdded = false;
    if (tables.contains(aTable)) { return false; }
    tables.add(aTable);
    if (aTable.indexOfReservation(this) != -1)
    {
      wasAdded = true;
    }
    else
    {
      wasAdded = aTable.addReservation(this);
      if (!wasAdded)
      {
        tables.remove(aTable);
      }
    }
    return wasAdded;
  }

  public boolean removeTable(Table aTable)
  {
    boolean wasRemoved = false;
    if (!tables.contains(aTable))
    {
      return wasRemoved;
    }

    int oldIndex = tables.indexOf(aTable);
    tables.remove(oldIndex);
    if (aTable.indexOfReservation(this) == -1)
    {
      wasRemoved = true;
    }
    else
    {
      wasRemoved = aTable.removeReservation(this);
      if (!wasRemoved)
      {
        tables.add(oldIndex,aTable);
      }
    }
    return wasRemoved;
  }

  public boolean addTableAt(Table aTable, int index)
  {  
    boolean wasAdded = false;
    if(addTable(aTable))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfTables()) { index = numberOfTables() - 1; }
      tables.remove(aTable);
      tables.add(index, aTable);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMoveTableAt(Table aTable, int index)
  {
    boolean wasAdded = false;
    if(tables.contains(aTable))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfTables()) { index = numberOfTables() - 1; }
      tables.remove(aTable);
      tables.add(index, aTable);
      wasAdded = true;
    } 
    else 
    {
      wasAdded = addTableAt(aTable, index);
    }
    return wasAdded;
  }

  public void delete()
  {
    ArrayList<Table> copyOfTables = new ArrayList<Table>(tables);
    tables.clear();
    for(Table aTable : copyOfTables)
    {
      aTable.removeReservation(this);
    }
  }


  public String toString()
  {
    return super.toString() + "["+
            "id" + ":" + getId()+ "," +
            "numberofTables" + ":" + getNumberofTables()+ "," +
            "numberOfSeats" + ":" + getNumberOfSeats()+ "," +
            "phone" + ":" + getPhone()+ "," +
            "email" + ":" + getEmail()+ "," +
            "customerName" + ":" + getCustomerName()+ "]" + System.getProperties().getProperty("line.separator") +
            "  " + "reservationDate" + "=" + (getReservationDate() != null ? !getReservationDate().equals(this)  ? getReservationDate().toString().replaceAll("  ","    ") : "this" : "null");
  }
}


