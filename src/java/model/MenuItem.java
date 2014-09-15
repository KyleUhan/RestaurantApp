/*
 * Menu Items
 * Need Validation - 
 */
package model;

import java.util.Objects;

/**
 *
 * @author Kyle Uhan
 */
public class MenuItem {

    private static int id_manager = 0;
    private Integer id;
    private String itemName = "";
    private double itemPrice = 0.0;
    private double itemCalories = 0.0;
    private String itemDescription = "";
    private String itemPicture = "";

    public MenuItem() {
        id_manager++;
        id = id_manager;
    }

   /* public MenuItem(String itemName, String itemPrice, String itemCalories) {
        setItemName(itemName);
        setItemPrice(itemPrice);
        setItemCalories(itemCalories);
    }*/

    public final String getItemName() {
        return itemName;
    }

    public final void setItemName(String itemName) {
        if (itemName == null) {
            itemName = "Empty Item Name";
        }
        this.itemName = itemName;
    }

    public final double getItemPrice() {
        return itemPrice;
    }

    public final void setItemPrice(String itemPrice) {
        if (itemPrice == null || itemPrice.isEmpty()) {
            itemPrice = "0.0";
        }
        this.itemPrice = Double.parseDouble(itemPrice);
    }

    public final double getItemCalories() {
        return itemCalories;
    }

    public final void setItemCalories(String itemCalories) {
        if (itemCalories == null || itemCalories.isEmpty()) {
            itemCalories = "0.0";
        }
        this.itemCalories = Double.parseDouble(itemCalories);
    }

    public final String getItemDescription() {
        return itemDescription;
    }

    public final void setItemDescription(String itemDescription) {
        if (itemDescription == null) {
            itemDescription = "";
        }
        this.itemDescription = itemDescription;
    }

    public String getItemPicture() {
        return itemPicture;
    }

    public final void setItemPicture(String itemPicture) throws IllegalArgumentException {
        if (itemPicture == null) {
            itemPicture = "";
        }
        this.itemPicture = itemPicture;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
    
    

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 59 * hash + Objects.hashCode(this.itemName);
        hash = 59 * hash + (int) (Double.doubleToLongBits(this.itemPrice) ^ (Double.doubleToLongBits(this.itemPrice) >>> 32));
        hash = 59 * hash + (int) (Double.doubleToLongBits(this.itemCalories) ^ (Double.doubleToLongBits(this.itemCalories) >>> 32));
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final MenuItem other = (MenuItem) obj;
        if (!Objects.equals(this.itemName, other.itemName)) {
            return false;
        }
        if (Double.doubleToLongBits(this.itemPrice) != Double.doubleToLongBits(other.itemPrice)) {
            return false;
        }
        if (Double.doubleToLongBits(this.itemCalories) != Double.doubleToLongBits(other.itemCalories)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "MenuItem{" + "id=" + id + ", itemName=" + itemName + ", itemPrice=" + itemPrice + ", itemCalories=" + itemCalories + ", itemDescription=" + itemDescription + ", itemPicture=" + itemPicture + '}';
    }

}
