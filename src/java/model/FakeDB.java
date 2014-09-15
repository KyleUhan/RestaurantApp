package model;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

/**
 *
 * @author Kyle Uhan
 */
public class FakeDB implements DataAccessStrategy {

    private static final String NOT_FOUND = "Menu Item not recognized";
    private List<MenuItem> db;

    public FakeDB() {
        setDb(new ArrayList<>());
    }

    @Override
    public void addMenuItem(MenuItem mi) throws IllegalArgumentException {
        if (mi == null) {
            throw new IllegalArgumentException(NOT_FOUND);
        }
        getDb().add(mi);
    }

    @Override
    public void removeMenuItem(MenuItem mi) {
        if (mi == null) {
            throw new IllegalArgumentException(NOT_FOUND);
        }
        for (int i = 0; i < getDb().size(); i++) {
            if (mi.getItemName().equals(getDb().get(i).getItemName())) {
                getDb().remove(i);
            }
        }
    }

    @Override
    public void updateMenuItem(MenuItem oldItem, MenuItem newItem) {
        if (oldItem == null || newItem == null) {
            throw new IllegalArgumentException(NOT_FOUND);
        }
        for (int i = 0; i < getDb().size(); i++) {
            if (oldItem.getItemName().equals(getDb().get(i).getItemName())) {
                getDb().set(i, newItem);
            }
        }
    }

    @Override
    public List<MenuItem> getAllMenuItems() {
        return getDb();
    }
    
    //
    @Override
    public LinkedHashMap<Integer,LinkedHashMap<String,MenuItem>> getAllMenuItemsMap() {
        LinkedHashMap<String,MenuItem> itemsInDB = new LinkedHashMap<>();
        LinkedHashMap<Integer,LinkedHashMap<String,MenuItem>> itemsInDBbyRecord = new LinkedHashMap<>();
        for(MenuItem mi: getDb()){
            itemsInDB.put(mi.getItemName(), mi);
            itemsInDBbyRecord.put(mi.getId(), itemsInDB);
            itemsInDB = new LinkedHashMap<>();
        }
        return itemsInDBbyRecord;
    }

    public List<MenuItem> getDb() {
        return db;
    }

    public final void setDb(List<MenuItem> db) {
        this.db = db;
    }
}
