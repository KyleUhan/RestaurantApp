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
public interface DataAccessStrategy {

    public void addMenuItem(MenuItem mi);
    public void removeMenuItem(MenuItem mi);
    public void updateMenuItem(MenuItem oldItem, MenuItem newItem);
    public List<MenuItem> getAllMenuItems();
    
}
