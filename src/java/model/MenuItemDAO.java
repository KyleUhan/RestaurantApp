/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Kyle Uhan
 */
public class MenuItemDAO implements RestaurantDAOStrategy {

    private final String UNABLE_TO_LOCATE = "Unable to locate database.";

    private DataBaseAccessStrategy db;

    public MenuItemDAO() {
    }

    public MenuItemDAO(DataBaseAccessStrategy db) {
        setDb(db);
    }

    public final DataBaseAccessStrategy getDb() {
        return db;
    }

    public final void setDb(final DataBaseAccessStrategy db) throws IllegalArgumentException {
        if (db == null) {
            throw new IllegalArgumentException(UNABLE_TO_LOCATE);
        }
        this.db = db;
    }

    @Override
    public List<MenuItem> getAllMenuItems() {
        List<MenuItem> menuItems = new ArrayList<>();
        List<Map> dbRecords = getDb().getAllData();
        for (Map item : dbRecords) {
            menuItems.add(convertToMenuItem(item));
        }
        return menuItems;
    }

    @Override
    public void clearAllMenuItem() {
        getDb().clearAllData();
    }

    @Override
    public void addMenuItem(MenuItem mi) {
        Map menuItem = convertToMap(mi);
        getDb().createRecord(menuItem);
    }

    @Override
    public Object getMenuItem(int id) {
        return getDb().getRecord(id);
    }

    @Override
    public void updateMenuItem(int id, MenuItem mi) {
        Map menuItem = convertToMap(mi);
        getDb().updateRecord(id, menuItem);
    }

    @Override
    public void removeMenuItem(int id) {
        getDb().removeRecord(id);
    }

    private Map convertToMap(MenuItem mi) {
        Map convertedMenuItem = new LinkedHashMap<>();
        //change id to static db pk auto incr.
        convertedMenuItem.put("ID", mi.getId());
        convertedMenuItem.put("itemName", mi.getItemName());
        convertedMenuItem.put("itemPrice", String.valueOf(mi.getItemPrice()));
        convertedMenuItem.put("itemCalories", String.valueOf(mi.getItemCalories()));
        convertedMenuItem.put("itemDescription", mi.getItemDescription());
        convertedMenuItem.put("itemPicture", mi.getItemPicture());
        return convertedMenuItem;
    }

    private MenuItem convertToMenuItem(Map record) {
        MenuItem mi = new MenuItem();
        Object[] keySet = record.keySet().toArray();
        mi.setId(Integer.parseInt(record.get(keySet[0]).toString()));
        mi.setItemName((String) record.get(keySet[1]));
        mi.setItemPrice((String) record.get(keySet[2]));
        mi.setItemCalories((String) record.get(keySet[3]));
        mi.setItemDescription((String) record.get(keySet[4]));
        mi.setItemPicture((String) record.get(keySet[5]));
        return mi;
    }

}
