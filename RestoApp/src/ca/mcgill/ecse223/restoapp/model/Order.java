
/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.27.0.3748.a67f04a modeling language!*/

package ca.mcgill.ecse223.restoapp.model;
import java.sql.Date;
import java.util.*;

// line 24 "../../../../../../ump/tmp693360/model.ump"
// line 70 "../../../../../../ump/tmp693360/model.ump"
public class Order
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Order Attributes
  private int totalPrice;
  private Date date;

  //Order Associations
  private List<Item> dishes;
  private List<Seat> seats;
  private List<Bill> bills;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public Order(Date aDate)
  {
    totalPrice = 0;
    date = aDate;
    dishes = new ArrayList<Item>();
    seats = new ArrayList<Seat>();
    bills = new ArrayList<Bill>();
  }

  //------------------------
  // INTERFACE
  //------------------------

  public boolean setTotalPrice(int aTotalPrice)
  {
    boolean wasSet = false;
    totalPrice = aTotalPrice;
    wasSet = true;
    return wasSet;
  }

  public boolean setDate(Date aDate)
  {
    boolean wasSet = false;
    date = aDate;
    wasSet = true;
    return wasSet;
  }

  public int getTotalPrice()
  {
    return totalPrice;
  }

  public Date getDate()
  {
    return date;
  }

  public Item getDishe(int index)
  {
    Item aDishe = dishes.get(index);
    return aDishe;
  }

  public List<Item> getDishes()
  {
    List<Item> newDishes = Collections.unmodifiableList(dishes);
    return newDishes;
  }

  public int numberOfDishes()
  {
    int number = dishes.size();
    return number;
  }

  public boolean hasDishes()
  {
    boolean has = dishes.size() > 0;
    return has;
  }

  public int indexOfDishe(Item aDishe)
  {
    int index = dishes.indexOf(aDishe);
    return index;
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

  public Bill getBill(int index)
  {
    Bill aBill = bills.get(index);
    return aBill;
  }

  public List<Bill> getBills()
  {
    List<Bill> newBills = Collections.unmodifiableList(bills);
    return newBills;
  }

  public int numberOfBills()
  {
    int number = bills.size();
    return number;
  }

  public boolean hasBills()
  {
    boolean has = bills.size() > 0;
    return has;
  }

  public int indexOfBill(Bill aBill)
  {
    int index = bills.indexOf(aBill);
    return index;
  }

  public static int minimumNumberOfDishes()
  {
    return 0;
  }

  public boolean addDishe(Item aDishe)
  {
    boolean wasAdded = false;
    if (dishes.contains(aDishe)) { return false; }
    Order existingOrder = aDishe.getOrder();
    if (existingOrder == null)
    {
      aDishe.setOrder(this);
    }
    else if (!this.equals(existingOrder))
    {
      existingOrder.removeDishe(aDishe);
      addDishe(aDishe);
    }
    else
    {
      dishes.add(aDishe);
    }
    wasAdded = true;
    return wasAdded;
  }

  public boolean removeDishe(Item aDishe)
  {
    boolean wasRemoved = false;
    if (dishes.contains(aDishe))
    {
      dishes.remove(aDishe);
      aDishe.setOrder(null);
      wasRemoved = true;
    }
    return wasRemoved;
  }

  public boolean addDisheAt(Item aDishe, int index)
  {  
    boolean wasAdded = false;
    if(addDishe(aDishe))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfDishes()) { index = numberOfDishes() - 1; }
      dishes.remove(aDishe);
      dishes.add(index, aDishe);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMoveDisheAt(Item aDishe, int index)
  {
    boolean wasAdded = false;
    if(dishes.contains(aDishe))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfDishes()) { index = numberOfDishes() - 1; }
      dishes.remove(aDishe);
      dishes.add(index, aDishe);
      wasAdded = true;
    } 
    else 
    {
      wasAdded = addDisheAt(aDishe, index);
    }
    return wasAdded;
  }

  public static int minimumNumberOfSeats()
  {
    return 0;
  }

  public boolean addSeat(Seat aSeat)
  {
    boolean wasAdded = false;
    if (seats.contains(aSeat)) { return false; }
    seats.add(aSeat);
    if (aSeat.indexOfOrder(this) != -1)
    {
      wasAdded = true;
    }
    else
    {
      wasAdded = aSeat.addOrder(this);
      if (!wasAdded)
      {
        seats.remove(aSeat);
      }
    }
    return wasAdded;
  }

  public boolean removeSeat(Seat aSeat)
  {
    boolean wasRemoved = false;
    if (!seats.contains(aSeat))
    {
      return wasRemoved;
    }

    int oldIndex = seats.indexOf(aSeat);
    seats.remove(oldIndex);
    if (aSeat.indexOfOrder(this) == -1)
    {
      wasRemoved = true;
    }
    else
    {
      wasRemoved = aSeat.removeOrder(this);
      if (!wasRemoved)
      {
        seats.add(oldIndex,aSeat);
      }
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

  public static int minimumNumberOfBills()
  {
    return 0;
  }

  public boolean addBill(Bill aBill)
  {
    boolean wasAdded = false;
    if (bills.contains(aBill)) { return false; }
    bills.add(aBill);
    if (aBill.indexOfOrder(this) != -1)
    {
      wasAdded = true;
    }
    else
    {
      wasAdded = aBill.addOrder(this);
      if (!wasAdded)
      {
        bills.remove(aBill);
      }
    }
    return wasAdded;
  }

  public boolean removeBill(Bill aBill)
  {
    boolean wasRemoved = false;
    if (!bills.contains(aBill))
    {
      return wasRemoved;
    }

    int oldIndex = bills.indexOf(aBill);
    bills.remove(oldIndex);
    if (aBill.indexOfOrder(this) == -1)
    {
      wasRemoved = true;
    }
    else
    {
      wasRemoved = aBill.removeOrder(this);
      if (!wasRemoved)
      {
        bills.add(oldIndex,aBill);
      }
    }
    return wasRemoved;
  }

  public boolean addBillAt(Bill aBill, int index)
  {  
    boolean wasAdded = false;
    if(addBill(aBill))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfBills()) { index = numberOfBills() - 1; }
      bills.remove(aBill);
      bills.add(index, aBill);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMoveBillAt(Bill aBill, int index)
  {
    boolean wasAdded = false;
    if(bills.contains(aBill))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfBills()) { index = numberOfBills() - 1; }
      bills.remove(aBill);
      bills.add(index, aBill);
      wasAdded = true;
    } 
    else 
    {
      wasAdded = addBillAt(aBill, index);
    }
    return wasAdded;
  }

  public void delete()
  {
    while( !dishes.isEmpty() )
    {
      dishes.get(0).setOrder(null);
    }
    ArrayList<Seat> copyOfSeats = new ArrayList<Seat>(seats);
    seats.clear();
    for(Seat aSeat : copyOfSeats)
    {
      aSeat.removeOrder(this);
    }
    ArrayList<Bill> copyOfBills = new ArrayList<Bill>(bills);
    bills.clear();
    for(Bill aBill : copyOfBills)
    {
      aBill.removeOrder(this);
    }
  }


  public String toString()
  {
    return super.toString() + "["+
            "totalPrice" + ":" + getTotalPrice()+ "]" + System.getProperties().getProperty("line.separator") +
            "  " + "date" + "=" + (getDate() != null ? !getDate().equals(this)  ? getDate().toString().replaceAll("  ","    ") : "this" : "null");
  }
}


