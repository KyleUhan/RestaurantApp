/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package model;

import java.util.List;

/**
 *
 * @author Kyle Uhan
 */
public interface MenuItemDAOStrategy {
    
    public abstract List<MenuItem> getAllMenuItems();

    public abstract void clearAllMenuItem();

    public void addMenuItem(MenuItem mi);

    public abstract Object getMenuItem(int id);

    public abstract void updateMenuItem(int id, MenuItem mi);

    public abstract void removeMenuItem(int id);
}
