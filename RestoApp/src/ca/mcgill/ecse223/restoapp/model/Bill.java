/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.27.0.3748.a67f04a modeling language!*/

package ca.mcgill.ecse223.restoapp.model;
import java.util.*;

// line 43 "../../../../../../ump/tmp693360/model.ump"
// line 85 "../../../../../../ump/tmp693360/model.ump"
public class Bill
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Bill Attributes
  private int numberOfPayingCustomers;
  private int pricePerCustomer;

  //Bill Associations
  private List<Order> orders;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public Bill(int aNumberOfPayingCustomers)
  {
    numberOfPayingCustomers = aNumberOfPayingCustomers;
    pricePerCustomer = 0;
    orders = new ArrayList<Order>();
  }

  //------------------------
  // INTERFACE
  //------------------------

  public boolean setNumberOfPayingCustomers(int aNumberOfPayingCustomers)
  {
    boolean wasSet = false;
    numberOfPayingCustomers = aNumberOfPayingCustomers;
    wasSet = true;
    return wasSet;
  }

  public boolean setPricePerCustomer(int aPricePerCustomer)
  {
    boolean wasSet = false;
    pricePerCustomer = aPricePerCustomer;
    wasSet = true;
    return wasSet;
  }

  public int getNumberOfPayingCustomers()
  {
    return numberOfPayingCustomers;
  }

  public int getPricePerCustomer()
  {
    return pricePerCustomer;
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

  public static int minimumNumberOfOrders()
  {
    return 0;
  }

  public boolean addOrder(Order aOrder)
  {
    boolean wasAdded = false;
    if (orders.contains(aOrder)) { return false; }
    orders.add(aOrder);
    if (aOrder.indexOfBill(this) != -1)
    {
      wasAdded = true;
    }
    else
    {
      wasAdded = aOrder.addBill(this);
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
    if (aOrder.indexOfBill(this) == -1)
    {
      wasRemoved = true;
    }
    else
    {
      wasRemoved = aOrder.removeBill(this);
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

  public void delete()
  {
    ArrayList<Order> copyOfOrders = new ArrayList<Order>(orders);
    orders.clear();
    for(Order aOrder : copyOfOrders)
    {
      aOrder.removeBill(this);
    }
  }


  public String toString()
  {
    return super.toString() + "["+
            "numberOfPayingCustomers" + ":" + getNumberOfPayingCustomers()+ "," +
            "pricePerCustomer" + ":" + getPricePerCustomer()+ "]";
  }
}


