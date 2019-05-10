package com.example.hunziker.listviewexample;

/**
 * Model, welches einen Eintrag in der ListView repräsentiert.
 * <p>
 * History:
 * 18.11.2016	1.0	Nathanael Hunziker  Klasse erstellt.
 *
 * @author Nathanael Hunziker
 * @version 1.0
 */
public class ItemObject {
  
  private String itemName;
  
  private int itemNumber;
  
  public String getItemName() {
    
    return itemName;
  }
  
  public void setItemName(String itemName) {
    
    this.itemName = itemName;
  }
  
  public int getItemNumber() {
    
    return itemNumber;
  }
  
  public void setItemNumber(int itemNumber) {
    
    this.itemNumber = itemNumber;
  }
}
