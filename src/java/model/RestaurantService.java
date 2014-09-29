/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Kyle Uhan
 */
public class RestaurantService {
    
    private final String DAO_NULL = "Unknown DAO Strategy";

    RestaurantDAOStrategy daoStrategy;

    public RestaurantService() {
    }

    public RestaurantService(RestaurantDAOStrategy daoStrategy) {
        setDaoStrategy(daoStrategy);
    }

    public RestaurantDAOStrategy getDaoStrategy() {
        return daoStrategy;
    }

    public final void setDaoStrategy(RestaurantDAOStrategy daoStrategy) throws IllegalArgumentException {
        if (daoStrategy == null) {
            throw new IllegalArgumentException(DAO_NULL);
        }
        this.daoStrategy = daoStrategy;
    }

    public List<MenuItem> getAllMenuItems() {
        return getDaoStrategy().getAllMenuItems();
    }

    public void clearAllMenuItem() {
        getDaoStrategy().clearAllMenuItem();
    }

    public void addMenuItem(MenuItem mi) {
        getDaoStrategy().addMenuItem(mi);
    }

    public Object getMenuItem(int id) {
        return getDaoStrategy().getMenuItem(id);
    }

    public void updateMenuItem(int id, MenuItem mi) {
        getDaoStrategy().updateMenuItem(id, mi);
    }

    public void removeMenuItem(int id) {
        getDaoStrategy().removeMenuItem(id);
    }


    public static void main(String[] args) {
        RestaurantService menu = new RestaurantService(new MenuItemDAO(new MockDB()));

        menu.addMenuItem(new MenuItem("", "", "", "", "images/fillerItemFadeL.png"));
        menu.addMenuItem(new MenuItem("IMPORTED COFFEE", "2.99", "100", "Fine imported coffee. Molto Bene!", "images/coffee2.png"));
        menu.addMenuItem(new MenuItem("LATTE", "3.49", "210", "Thanks a Latte...", "images/coffee3.jpg"));
        menu.addMenuItem(new MenuItem("HOUSE COFFEE", "1.99", "65", "Our famous home made coffee!", "images/coffeeSized.jpg"));
        menu.addMenuItem(new MenuItem("DESSERT COFFEE", "3.95", "480", "Calorie counters go away.", "images/coffee5.jpg"));
        menu.addMenuItem(new MenuItem("MUFFIN", "3.55", "225", "These muffins are out of control.", "images/muffinSized.jpg"));
        menu.addMenuItem(new MenuItem("", "", "", "", "images/fillerItemFadeR.png"));

        System.out.println("CREATE: \n" + menu.getAllMenuItems() + "\n--");
        System.out.println("RETRIEVE: 5\n" + menu.getMenuItem(5) + "\n--");
        menu.updateMenuItem(2, new MenuItem("CROSSANT", "1.55", "200", "Crossants, just like mom use to make.", "images/croissantSized.jpg"));
        System.out.println("UPDATE: 2(gets new id)\n" + menu.getAllMenuItems() + "\n--");
        menu.removeMenuItem(1);
        System.out.println("REMOVE: 1\n" + menu.getAllMenuItems() + "\n--");
        menu.clearAllMenuItem();
        System.out.println("CLEAR ALL: \n" + menu.getAllMenuItems());

    }

}
