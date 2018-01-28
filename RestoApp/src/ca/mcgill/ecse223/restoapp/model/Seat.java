/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.27.0.3748.a67f04a modeling language!*/

package ca.mcgill.ecse223.restoapp.model;
import java.util.*;

// line 19 "../../../../../../ump/tmp693360/model.ump"
// line 65 "../../../../../../ump/tmp693360/model.ump"
public class Seat
{

  //------------------------
  // STATIC VARIABLES
  //------------------------

  private static int nextSeatNumber = 1;

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Seat Attributes
  private boolean occupiedStatus;

  //Autounique Attributes
  private int seatNumber;

  //Seat Associations
  private List<Order> orders;
  private Table table;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public Seat()
  {
    occupiedStatus = false;
    seatNumber = nextSeatNumber++;
    orders = new ArrayList<Order>();
  }

  //------------------------
  // INTERFACE
  //------------------------

  public boolean setOccupiedStatus(boolean aOccupiedStatus)
  {
    boolean wasSet = false;
    occupiedStatus = aOccupiedStatus;
    wasSet = true;
    return wasSet;
  }

  public boolean getOccupiedStatus()
  {
    return occupiedStatus;
  }

  public int getSeatNumber()
  {
    return seatNumber;
  }

  public boolean isOccupiedStatus()
  {
    return occupiedStatus;
  }

  public Order getOrder(int index)
  {
    Order aOrder = orders.get(index);
    return aOrder;
  }

  public List<Order> getOrders()
  {
    List<Order> newOrders = Collections.unmodifiableList(orders);
    return newOrders;
  }

  public int numberOfOrders()
  {
    int number = orders.size();
    return number;
  }

  public boolean hasOrders()
  {
    boolean has = orders.size() > 0;
    return has;
  }

  public int indexOfOrder(Order aOrder)
  {
    int index = orders.indexOf(aOrder);
    return index;
  }

  public Table getTable()
  {
    return table;
  }

  public boolean hasTable()
  {
    boolean has = table != null;
    return has;
  }

  public static int minimumNumberOfOrders()
  {
    return 0;
  }

  public boolean addOrder(Order aOrder)
  {
    boolean wasAdded = false;
    if (orders.contains(aOrder)) { return false; }
    orders.add(aOrder);
    if (aOrder.indexOfSeat(this) != -1)
    {
      wasAdded = true;
    }
    else
    {
      wasAdded = aOrder.addSeat(this);
      if (!wasAdded)
      {
        orders.remove(aOrder);
      }
    }
    return wasAdded;
  }

  public boolean removeOrder(Order aOrder)
  {
    boolean wasRemoved = false;
    if (!orders.contains(aOrder))
    {
      return wasRemoved;
    }

    int oldIndex = orders.indexOf(aOrder);
    orders.remove(oldIndex);
    if (aOrder.indexOfSeat(this) == -1)
    {
      wasRemoved = true;
    }
    else
    {
      wasRemoved = aOrder.removeSeat(this);
      if (!wasRemoved)
      {
        orders.add(oldIndex,aOrder);
      }
    }
    return wasRemoved;
  }

  public boolean addOrderAt(Order aOrder, int index)
  {  
    boolean wasAdded = false;
    if(addOrder(aOrder))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfOrders()) { index = numberOfOrders() - 1; }
      orders.remove(aOrder);
      orders.add(index, aOrder);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMoveOrderAt(Order aOrder, int index)
  {
    boolean wasAdded = false;
    if(orders.contains(aOrder))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfOrders()) { index = numberOfOrders() - 1; }
      orders.remove(aOrder);
      orders.add(index, aOrder);
      wasAdded = true;
    } 
    else 
    {
      wasAdded = addOrderAt(aOrder, index);
    }
    return wasAdded;
  }

  public boolean setTable(Table aTable)
  {
    boolean wasSet = false;
    Table existingTable = table;
    table = aTable;
    if (existingTable != null && !existingTable.equals(aTable))
    {
      existingTable.removeSeat(this);
    }
    if (aTable != null)
    {
      aTable.addSeat(this);
    }
    wasSet = true;
    return wasSet;
  }

  public void delete()
  {
    ArrayList<Order> copyOfOrders = new ArrayList<Order>(orders);
    orders.clear();
    for(Order aOrder : copyOfOrders)
    {
      aOrder.removeSeat(this);
    }
    if (table != null)
    {
      Table placeholderTable = table;
      this.table = null;
      placeholderTable.removeSeat(this);
    }
  }


  public String toString()
  {
    return super.toString() + "["+
            "seatNumber" + ":" + getSeatNumber()+ "," +
            "occupiedStatus" + ":" + getOccupiedStatus()+ "]" + System.getProperties().getProperty("line.separator") +
            "  " + "table = "+(getTable()!=null?Integer.toHexString(System.identityHashCode(getTable())):"null");
  }
}


