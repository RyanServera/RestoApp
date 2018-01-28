/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.27.0.3748.a67f04a modeling language!*/

package ca.mcgill.ecse223.restoapp.model;
import java.util.*;

// line 10 "../../../../../../ump/tmp693360/model.ump"
// line 60 "../../../../../../ump/tmp693360/model.ump"
public class Table
{

  //------------------------
  // STATIC VARIABLES
  //------------------------

  private static int nextTableNumber = 1;

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Table Attributes
  private int numberOfSeats;
  private int currentCustomers;
  private boolean occupiedStatus;
  private int x;
  private int y;

  //Autounique Attributes
  private int tableNumber;

  //Table Associations
  private List<Seat> seats;
  private List<Reservation> reservations;
  private RestoAppManager restoAppManager;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public Table(int aX, int aY)
  {
    numberOfSeats = 0;
    currentCustomers = 0;
    occupiedStatus = false;
    x = aX;
    y = aY;
    tableNumber = nextTableNumber++;
    seats = new ArrayList<Seat>();
    reservations = new ArrayList<Reservation>();
  }

  //------------------------
  // INTERFACE
  //------------------------

  public boolean setNumberOfSeats(int aNumberOfSeats)
  {
    boolean wasSet = false;
    numberOfSeats = aNumberOfSeats;
    wasSet = true;
    return wasSet;
  }

  public boolean setCurrentCustomers(int aCurrentCustomers)
  {
    boolean wasSet = false;
    currentCustomers = aCurrentCustomers;
    wasSet = true;
    return wasSet;
  }

  public boolean setOccupiedStatus(boolean aOccupiedStatus)
  {
    boolean wasSet = false;
    occupiedStatus = aOccupiedStatus;
    wasSet = true;
    return wasSet;
  }

  public boolean setX(int aX)
  {
    boolean wasSet = false;
    x = aX;
    wasSet = true;
    return wasSet;
  }

  public boolean setY(int aY)
  {
    boolean wasSet = false;
    y = aY;
    wasSet = true;
    return wasSet;
  }

  public int getNumberOfSeats()
  {
    return numberOfSeats;
  }

  public int getCurrentCustomers()
  {
    return currentCustomers;
  }

  public boolean getOccupiedStatus()
  {
    return occupiedStatus;
  }

  public int getX()
  {
    return x;
  }

  public int getY()
  {
    return y;
  }

  public int getTableNumber()
  {
    return tableNumber;
  }

  public boolean isOccupiedStatus()
  {
    return occupiedStatus;
  }

  public Seat getSeat(int index)
  {
    Seat aSeat = seats.get(index);
    return aSeat;
  }

  public List<Seat> getSeats()
  {
    List<Seat> newSeats = Collections.unmodifiableList(seats);
    return newSeats;
  }

  public int numberOfSeats()
  {
    int number = seats.size();
    return number;
  }

  public boolean hasSeats()
  {
    boolean has = seats.size() > 0;
    return has;
  }

  public int indexOfSeat(Seat aSeat)
  {
    int index = seats.indexOf(aSeat);
    return index;
  }

  public Reservation getReservation(int index)
  {
    Reservation aReservation = reservations.get(index);
    return aReservation;
  }

  public List<Reservation> getReservations()
  {
    List<Reservation> newReservations = Collections.unmodifiableList(reservations);
    return newReservations;
  }

  public int numberOfReservations()
  {
    int number = reservations.size();
    return number;
  }

  public boolean hasReservations()
  {
    boolean has = reservations.size() > 0;
    return has;
  }

  public int indexOfReservation(Reservation aReservation)
  {
    int index = reservations.indexOf(aReservation);
    return index;
  }

  public RestoAppManager getRestoAppManager()
  {
    return restoAppManager;
  }

  public boolean hasRestoAppManager()
  {
    boolean has = restoAppManager != null;
    return has;
  }

  public static int minimumNumberOfSeats()
  {
    return 0;
  }

  public boolean addSeat(Seat aSeat)
  {
    boolean wasAdded = false;
    if (seats.contains(aSeat)) { return false; }
    Table existingTable = aSeat.getTable();
    if (existingTable == null)
    {
      aSeat.setTable(this);
    }
    else if (!this.equals(existingTable))
    {
      existingTable.removeSeat(aSeat);
      addSeat(aSeat);
    }
    else
    {
      seats.add(aSeat);
    }
    wasAdded = true;
    return wasAdded;
  }

  public boolean removeSeat(Seat aSeat)
  {
    boolean wasRemoved = false;
    if (seats.contains(aSeat))
    {
      seats.remove(aSeat);
      aSeat.setTable(null);
      wasRemoved = true;
    }
    return wasRemoved;
  }

  public boolean addSeatAt(Seat aSeat, int index)
  {  
    boolean wasAdded = false;
    if(addSeat(aSeat))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfSeats()) { index = numberOfSeats() - 1; }
      seats.remove(aSeat);
      seats.add(index, aSeat);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMoveSeatAt(Seat aSeat, int index)
  {
    boolean wasAdded = false;
    if(seats.contains(aSeat))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfSeats()) { index = numberOfSeats() - 1; }
      seats.remove(aSeat);
      seats.add(index, aSeat);
      wasAdded = true;
    } 
    else 
    {
      wasAdded = addSeatAt(aSeat, index);
    }
    return wasAdded;
  }

  public static int minimumNumberOfReservations()
  {
    return 0;
  }

  public boolean addReservation(Reservation aReservation)
  {
    boolean wasAdded = false;
    if (reservations.contains(aReservation)) { return false; }
    reservations.add(aReservation);
    if (aReservation.indexOfTable(this) != -1)
    {
      wasAdded = true;
    }
    else
    {
      wasAdded = aReservation.addTable(this);
      if (!wasAdded)
      {
        reservations.remove(aReservation);
      }
    }
    return wasAdded;
  }

  public boolean removeReservation(Reservation aReservation)
  {
    boolean wasRemoved = false;
    if (!reservations.contains(aReservation))
    {
      return wasRemoved;
    }

    int oldIndex = reservations.indexOf(aReservation);
    reservations.remove(oldIndex);
    if (aReservation.indexOfTable(this) == -1)
    {
      wasRemoved = true;
    }
    else
    {
      wasRemoved = aReservation.removeTable(this);
      if (!wasRemoved)
      {
        reservations.add(oldIndex,aReservation);
      }
    }
    return wasRemoved;
  }

  public boolean addReservationAt(Reservation aReservation, int index)
  {  
    boolean wasAdded = false;
    if(addReservation(aReservation))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfReservations()) { index = numberOfReservations() - 1; }
      reservations.remove(aReservation);
      reservations.add(index, aReservation);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMoveReservationAt(Reservation aReservation, int index)
  {
    boolean wasAdded = false;
    if(reservations.contains(aReservation))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfReservations()) { index = numberOfReservations() - 1; }
      reservations.remove(aReservation);
      reservations.add(index, aReservation);
      wasAdded = true;
    } 
    else 
    {
      wasAdded = addReservationAt(aReservation, index);
    }
    return wasAdded;
  }

  public boolean setRestoAppManager(RestoAppManager aRestoAppManager)
  {
    boolean wasSet = false;
    RestoAppManager existingRestoAppManager = restoAppManager;
    restoAppManager = aRestoAppManager;
    if (existingRestoAppManager != null && !existingRestoAppManager.equals(aRestoAppManager))
    {
      existingRestoAppManager.removeTable(this);
    }
    if (aRestoAppManager != null)
    {
      aRestoAppManager.addTable(this);
    }
    wasSet = true;
    return wasSet;
  }

  public void delete()
  {
    while( !seats.isEmpty() )
    {
      seats.get(0).setTable(null);
    }
    ArrayList<Reservation> copyOfReservations = new ArrayList<Reservation>(reservations);
    reservations.clear();
    for(Reservation aReservation : copyOfReservations)
    {
      aReservation.removeTable(this);
    }
    if (restoAppManager != null)
    {
      RestoAppManager placeholderRestoAppManager = restoAppManager;
      this.restoAppManager = null;
      placeholderRestoAppManager.removeTable(this);
    }
  }


  public String toString()
  {
    return super.toString() + "["+
            "tableNumber" + ":" + getTableNumber()+ "," +
            "numberOfSeats" + ":" + getNumberOfSeats()+ "," +
            "currentCustomers" + ":" + getCurrentCustomers()+ "," +
            "occupiedStatus" + ":" + getOccupiedStatus()+ "," +
            "x" + ":" + getX()+ "," +
            "y" + ":" + getY()+ "]" + System.getProperties().getProperty("line.separator") +
            "  " + "restoAppManager = "+(getRestoAppManager()!=null?Integer.toHexString(System.identityHashCode(getRestoAppManager())):"null");
  }
}


