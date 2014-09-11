/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.ArrayList;
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

    public List<MenuItem> getDb() {
        return db;
    }

    public final void setDb(List<MenuItem> db) {
        this.db = db;
    }

    public static void main(String[] args) {
        DataAccessStrategy db = new FakeDB();

        db.addMenuItem(new MenuItem("Hamburger", 4.99, 300.0));
        db.addMenuItem(new MenuItem("Taco", 2.99, 285.0));
        db.addMenuItem(new MenuItem("Steak", 14.99, 596.0));
        System.out.println("ITEMS ADDED USING addMenuItem");
        System.out.println(db.getAllMenuItems());
        MenuItem item = new MenuItem("Hamburger", 4.99, 300.0);
        db.removeMenuItem(item);
        System.out.println("ITEM REMOVED USING removeMenuItem");
        System.out.println(db.getAllMenuItems());
        MenuItem old = new MenuItem("Steak", 14.99, 596.0);
        db.updateMenuItem(old, new MenuItem("Salad", 4.99, 122.0));
        System.out.println("ITEM SWITCHED USING updateMenuItem");
        System.out.println(db.getAllMenuItems());

    }
}
