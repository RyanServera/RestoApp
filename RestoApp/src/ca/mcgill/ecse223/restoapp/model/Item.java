/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.27.0.3748.a67f04a modeling language!*/

package ca.mcgill.ecse223.restoapp.model;

// line 3 "../../../../../../ump/tmp693360/model.ump"
// line 55 "../../../../../../ump/tmp693360/model.ump"
public class Item
{

  //------------------------
  // ENUMERATIONS
  //------------------------

  public enum Category { Appetizer, MainDish, Dessert, Beverage }

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Item Attributes
  private String name;
  private boolean isBeverage;
  private Category category;
  private float unitPrice;

  //Item Associations
  private Order order;
  private Menu menu;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public Item(String aName, Category aCategory, float aUnitPrice)
  {
    name = aName;
    isBeverage = false;
    category = aCategory;
    unitPrice = aUnitPrice;
  }

  //------------------------
  // INTERFACE
  //------------------------

  public boolean setName(String aName)
  {
    boolean wasSet = false;
    name = aName;
    wasSet = true;
    return wasSet;
  }

  public boolean setIsBeverage(boolean aIsBeverage)
  {
    boolean wasSet = false;
    isBeverage = aIsBeverage;
    wasSet = true;
    return wasSet;
  }

  public boolean setCategory(Category aCategory)
  {
    boolean wasSet = false;
    category = aCategory;
    wasSet = true;
    return wasSet;
  }

  public boolean setUnitPrice(float aUnitPrice)
  {
    boolean wasSet = false;
    unitPrice = aUnitPrice;
    wasSet = true;
    return wasSet;
  }

  public String getName()
  {
    return name;
  }

  public boolean getIsBeverage()
  {
    return isBeverage;
  }

  public Category getCategory()
  {
    return category;
  }

  public float getUnitPrice()
  {
    return unitPrice;
  }

  public boolean isIsBeverage()
  {
    return isBeverage;
  }

  public Order getOrder()
  {
    return order;
  }

  public boolean hasOrder()
  {
    boolean has = order != null;
    return has;
  }

  public Menu getMenu()
  {
    return menu;
  }

  public boolean hasMenu()
  {
    boolean has = menu != null;
    return has;
  }

  public boolean setOrder(Order aOrder)
  {
    boolean wasSet = false;
    Order existingOrder = order;
    order = aOrder;
    if (existingOrder != null && !existingOrder.equals(aOrder))
    {
      existingOrder.removeDishe(this);
    }
    if (aOrder != null)
    {
      aOrder.addDishe(this);
    }
    wasSet = true;
    return wasSet;
  }

  public boolean setMenu(Menu aMenu)
  {
    boolean wasSet = false;
    Menu existingMenu = menu;
    menu = aMenu;
    if (existingMenu != null && !existingMenu.equals(aMenu))
    {
      existingMenu.removeDishe(this);
    }
    if (aMenu != null)
    {
      aMenu.addDishe(this);
    }
    wasSet = true;
    return wasSet;
  }

  public void delete()
  {
    if (order != null)
    {
      Order placeholderOrder = order;
      this.order = null;
      placeholderOrder.removeDishe(this);
    }
    if (menu != null)
    {
      Menu placeholderMenu = menu;
      this.menu = null;
      placeholderMenu.removeDishe(this);
    }
  }


  public String toString()
  {
    return super.toString() + "["+
            "name" + ":" + getName()+ "," +
            "isBeverage" + ":" + getIsBeverage()+ "," +
            "unitPrice" + ":" + getUnitPrice()+ "]" + System.getProperties().getProperty("line.separator") +
            "  " + "category" + "=" + (getCategory() != null ? !getCategory().equals(this)  ? getCategory().toString().replaceAll("  ","    ") : "this" : "null") + System.getProperties().getProperty("line.separator") +
            "  " + "order = "+(getOrder()!=null?Integer.toHexString(System.identityHashCode(getOrder())):"null") + System.getProperties().getProperty("line.separator") +
            "  " + "menu = "+(getMenu()!=null?Integer.toHexString(System.identityHashCode(getMenu())):"null");
  }
}


