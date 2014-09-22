/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Kyle Uhan
 */
public class DataAccessService {

    private static final String NULL_ERROR = "Menu information not received.";
    private static final String NOT_FOUND = "Menu information not found.";

    private DataAccessStrategy accessStrategy;

    public DataAccessService(DataAccessStrategy accessStrategy) {
        setAccessStrategy(accessStrategy);
    }
    
     public DataAccessService(String accessStrategy) {
        try {
            Class clazz;
            clazz = Class.forName(accessStrategy);
            setAccessStrategy((DataAccessStrategy) clazz.newInstance());
        } catch (InstantiationException | IllegalAccessException | ClassNotFoundException ex) {
            Logger.getLogger(DataAccessService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void addMenuItem(MenuItem mi) throws IllegalArgumentException {
        if (mi == null) {
            throw new IllegalArgumentException(NULL_ERROR);
        }
        getAccessStrategy().addMenuItem(mi);
    }

    public void removeMenuItem(MenuItem mi) throws IllegalArgumentException {
        if (mi == null) {
            throw new IllegalArgumentException(NULL_ERROR);
        }
        getAccessStrategy().removeMenuItem(mi);
    }

    public void updateMenuItem(MenuItem oldMi, MenuItem newMi) throws IllegalArgumentException {
        if (oldMi == null || newMi == null) {
            throw new IllegalArgumentException(NULL_ERROR);
        }
        getAccessStrategy().updateMenuItem(oldMi, newMi);
    }

    public List<MenuItem> getAllMenuItems() {
        return getAccessStrategy().getAllMenuItems();
    }

    /**
     * Returns menu item by record number(id) search
     * 
     * @param recordNum
     * @return
     */
    public MenuItem getMenuItem(int recordNum) {
        if (recordNum < 0) {
            throw new IllegalArgumentException(NOT_FOUND);
        }
        List<MenuItem> menuItems = getAccessStrategy().getAllMenuItems();
        return menuItems.get(recordNum);
    }

    /**
     * Return menu item by name search
     * @param itemName
     * @return
     */
    public MenuItem getMenuItem(String itemName) {
        if (itemName == null) {
            throw new IllegalArgumentException(NOT_FOUND);
        }
        int count = 0;
        List<MenuItem> menuItems = getAccessStrategy().getAllMenuItems();
        MenuItem selectedItem = null;
        for (MenuItem mi : menuItems) {
            if (itemName.equals(menuItems.get(count).getItemName())) {
                selectedItem = menuItems.get(count);
            }
            count++;
        }
        return selectedItem;
    }
    
    public void clearAllItems(){
        getAccessStrategy().clearAllData();
    }

    public final DataAccessStrategy getAccessStrategy() {
        return accessStrategy;
    }

    public final void setAccessStrategy(DataAccessStrategy accessStrategy) {
        this.accessStrategy = accessStrategy;
    }

}
