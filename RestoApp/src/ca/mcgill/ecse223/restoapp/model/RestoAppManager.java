/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.27.0.3748.a67f04a modeling language!*/

package ca.mcgill.ecse223.restoapp.model;
import java.util.*;

// line 48 "../../../../../../ump/tmp693360/model.ump"
// line 90 "../../../../../../ump/tmp693360/model.ump"
public class RestoAppManager
{

  //------------------------
  // STATIC VARIABLES
  //------------------------

  private static RestoAppManager theInstance = null;

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //RestoAppManager Attributes
  private String restaurantName;

  //RestoAppManager Associations
  private List<Table> tables;
  private Menu theMenu;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  private RestoAppManager()
  {
    restaurantName = null;
    tables = new ArrayList<Table>();
  }

  public static RestoAppManager getInstance()
  {
    if(theInstance == null)
    {
      theInstance = new RestoAppManager();
    }
    return theInstance;
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

  public Menu getTheMenu()
  {
    return theMenu;
  }

  public boolean hasTheMenu()
  {
    boolean has = theMenu != null;
    return has;
  }

  public static int minimumNumberOfTables()
  {
    return 0;
  }

  public boolean addTable(Table aTable)
  {
    boolean wasAdded = false;
    if (tables.contains(aTable)) { return false; }
    RestoAppManager existingRestoAppManager = aTable.getRestoAppManager();
    if (existingRestoAppManager == null)
    {
      aTable.setRestoAppManager(this);
    }
    else if (!this.equals(existingRestoAppManager))
    {
      existingRestoAppManager.removeTable(aTable);
      addTable(aTable);
    }
    else
    {
      tables.add(aTable);
    }
    wasAdded = true;
    return wasAdded;
  }

  public boolean removeTable(Table aTable)
  {
    boolean wasRemoved = false;
    if (tables.contains(aTable))
    {
      tables.remove(aTable);
      aTable.setRestoAppManager(null);
      wasRemoved = true;
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

  public boolean setTheMenu(Menu aNewTheMenu)
  {
    boolean wasSet = false;
    if (aNewTheMenu == null)
    {
      Menu existingTheMenu = theMenu;
      theMenu = null;
      
      if (existingTheMenu != null && existingTheMenu.getRestoAppManager() != null)
      {
        existingTheMenu.setRestoAppManager(null);
      }
      wasSet = true;
      return wasSet;
    }

    Menu currentTheMenu = getTheMenu();
    if (currentTheMenu != null && !currentTheMenu.equals(aNewTheMenu))
    {
      currentTheMenu.setRestoAppManager(null);
    }

    theMenu = aNewTheMenu;
    RestoAppManager existingRestoAppManager = aNewTheMenu.getRestoAppManager();

    if (!equals(existingRestoAppManager))
    {
      aNewTheMenu.setRestoAppManager(this);
    }
    wasSet = true;
    return wasSet;
  }

  public void delete()
  {
    while (tables.size() > 0)
    {
      Table aTable = tables.get(tables.size() - 1);
      aTable.delete();
      tables.remove(aTable);
    }
    
    Menu existingTheMenu = theMenu;
    theMenu = null;
    if (existingTheMenu != null)
    {
      existingTheMenu.delete();
      existingTheMenu.setRestoAppManager(null);
    }
  }


  public String toString()
  {
    return super.toString() + "["+
            "restaurantName" + ":" + getRestaurantName()+ "]" + System.getProperties().getProperty("line.separator") +
            "  " + "theMenu = "+(getTheMenu()!=null?Integer.toHexString(System.identityHashCode(getTheMenu())):"null");
  }
}
