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

    private String itemName;
    private double itemPrice;
    private double itemCalories;

    public MenuItem() {
    }

    public MenuItem(String itemName, double itemPrice, double itemCalories) {
        setItemName(itemName);
        setItemPrice(itemPrice);
        setItemCalories(itemCalories);
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public double getItemPrice() {
        return itemPrice;
    }

    public void setItemPrice(double itemPrice) {
        this.itemPrice = itemPrice;
    }

    public double getItemCalories() {
        return itemCalories;
    }

    public void setItemCalories(double itemCalories) {
        this.itemCalories = itemCalories;
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
        return "Name: " + itemName + ", Price: " + itemPrice + ", Calories: " + itemCalories;
    }

}
