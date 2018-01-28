/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.27.0.3748.a67f04a modeling language!*/

package ca.mcgill.ecse223.restoapp.model;
import java.util.*;

// line 29 "../../../../../../ump/tmp693360/model.ump"
// line 75 "../../../../../../ump/tmp693360/model.ump"
public class Menu
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Menu Attributes
  private String restaurantName;

  //Menu Associations
  private List<Item> dishes;
  private RestoAppManager restoAppManager;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public Menu(String aRestaurantName)
  {
    restaurantName = aRestaurantName;
    dishes = new ArrayList<Item>();
  }

  //------------------------
  // INTERFACE
  //------------------------

  public boolean setRestaurantName(String aRestaurantName)
  {
    boolean wasSet = false;
    restaurantName = aRestaurantName;
    wasSet = true;
    return wasSet;
  }

  public String getRestaurantName()
  {
    return restaurantName;
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

  public RestoAppManager getRestoAppManager()
  {
    return restoAppManager;
  }

  public boolean hasRestoAppManager()
  {
    boolean has = restoAppManager != null;
    return has;
  }

  public static int minimumNumberOfDishes()
  {
    return 0;
  }

  public boolean addDishe(Item aDishe)
  {
    boolean wasAdded = false;
    if (dishes.contains(aDishe)) { return false; }
    Menu existingMenu = aDishe.getMenu();
    if (existingMenu == null)
    {
      aDishe.setMenu(this);
    }
    else if (!this.equals(existingMenu))
    {
      existingMenu.removeDishe(aDishe);
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
      aDishe.setMenu(null);
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

  public boolean setRestoAppManager(RestoAppManager aNewRestoAppManager)
  {
    boolean wasSet = false;
    if (aNewRestoAppManager == null)
    {
      RestoAppManager existingRestoAppManager = restoAppManager;
      restoAppManager = null;
      
      if (existingRestoAppManager != null && existingRestoAppManager.getTheMenu() != null)
      {
        existingRestoAppManager.setTheMenu(null);
      }
      wasSet = true;
      return wasSet;
    }

    RestoAppManager currentRestoAppManager = getRestoAppManager();
    if (currentRestoAppManager != null && !currentRestoAppManager.equals(aNewRestoAppManager))
    {
      currentRestoAppManager.setTheMenu(null);
    }

    restoAppManager = aNewRestoAppManager;
    Menu existingTheMenu = aNewRestoAppManager.getTheMenu();

    if (!equals(existingTheMenu))
    {
      aNewRestoAppManager.setTheMenu(this);
    }
    wasSet = true;
    return wasSet;
  }

  public void delete()
  {
    while( !dishes.isEmpty() )
    {
      dishes.get(0).setMenu(null);
    }
    if (restoAppManager != null)
    {
      restoAppManager.setTheMenu(null);
    }
  }


  public String toString()
  {
    return super.toString() + "["+
            "restaurantName" + ":" + getRestaurantName()+ "]" + System.getProperties().getProperty("line.separator") +
            "  " + "restoAppManager = "+(getRestoAppManager()!=null?Integer.toHexString(System.identityHashCode(getRestoAppManager())):"null");
  }
}


