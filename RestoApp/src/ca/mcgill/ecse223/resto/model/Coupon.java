/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.26.0-b05b57321 modeling language!*/

package ca.mcgill.ecse223.resto.model;
import java.io.Serializable;
import java.util.*;

// line 64 "../../../../../RestoAppPersistence.ump"
// line 114 "../../../../../RestoApp V4.ump"
public class Coupon implements Serializable
{

  //------------------------
  // STATIC VARIABLES
  //------------------------

  private static Map<Long, Coupon> couponsById = new HashMap<Long, Coupon>();

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Coupon Attributes
  private Long id;
  private boolean isValid;
  private double discountPercentage;

  //Coupon Associations
  private RestoApp restoApp;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public Coupon(Long aId, boolean aIsValid, double aDiscountPercentage, RestoApp aRestoApp)
  {
    isValid = aIsValid;
    discountPercentage = aDiscountPercentage;
    if (!setId(aId))
    {
      throw new RuntimeException("Cannot create due to duplicate id");
    }
    boolean didAddRestoApp = setRestoApp(aRestoApp);
    if (!didAddRestoApp)
    {
      throw new RuntimeException("Unable to create coupon due to restoApp");
    }
  }

  //------------------------
  // INTERFACE
  //------------------------

  public boolean setId(Long aId)
  {
    boolean wasSet = false;
    Long anOldId = getId();
    if (hasWithId(aId)) {
      return wasSet;
    }
    id = aId;
    wasSet = true;
    if (anOldId != null) {
      couponsById.remove(anOldId);
    }
    couponsById.put(aId, this);
    return wasSet;
  }

  public boolean setIsValid(boolean aIsValid)
  {
    boolean wasSet = false;
    isValid = aIsValid;
    wasSet = true;
    return wasSet;
  }

  public boolean setDiscountPercentage(double aDiscountPercentage)
  {
    boolean wasSet = false;
    discountPercentage = aDiscountPercentage;
    wasSet = true;
    return wasSet;
  }

  public Long getId()
  {
    return id;
  }

  public static Coupon getWithId(Long aId)
  {
    return couponsById.get(aId);
  }

  public static boolean hasWithId(Long aId)
  {
    return getWithId(aId) != null;
  }

  public boolean getIsValid()
  {
    return isValid;
  }

  public double getDiscountPercentage()
  {
    return discountPercentage;
  }

  public RestoApp getRestoApp()
  {
    return restoApp;
  }

  public boolean setRestoApp(RestoApp aRestoApp)
  {
    boolean wasSet = false;
    if (aRestoApp == null)
    {
      return wasSet;
    }

    RestoApp existingRestoApp = restoApp;
    restoApp = aRestoApp;
    if (existingRestoApp != null && !existingRestoApp.equals(aRestoApp))
    {
      existingRestoApp.removeCoupon(this);
    }
    restoApp.addCoupon(this);
    wasSet = true;
    return wasSet;
  }

  public void delete()
  {
    couponsById.remove(getId());
    RestoApp placeholderRestoApp = restoApp;
    this.restoApp = null;
    placeholderRestoApp.removeCoupon(this);
  }


  public String toString()
  {
    return super.toString() + "["+
            "id" + ":" + getId()+ "," +
            "isValid" + ":" + getIsValid()+ "," +
            "discountPercentage" + ":" + getDiscountPercentage()+ "]" + System.getProperties().getProperty("line.separator") +
            "  " + "restoApp = "+(getRestoApp()!=null?Integer.toHexString(System.identityHashCode(getRestoApp())):"null");
  }  
  //------------------------
  // DEVELOPER CODE - PROVIDED AS-IS
  //------------------------
  
  // line 67 ../../../../../RestoAppPersistence.ump
  private static final long serialVersionUID = 2212686255893382526L ;

  
}