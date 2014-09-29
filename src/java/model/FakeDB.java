package model;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Kyle Uhan
 */
public class FakeDB implements DataAccessStrategy {

    private static final String NOT_FOUND = "Menu Item not recognized";
    private List<MenuItem> db;
    private List<Map<String,Object>> db2;

    public FakeDB() {
        setDb(new ArrayList<>());
        Map<String,Object> data = new LinkedHashMap<>();
        
        
        getDb().add(new MenuItem("","","","","images/fillerItemFadeL.png"));
        getDb().add(new MenuItem("IMPORTED COFFEE","2.99","100","Fine imported coffee. Molto Bene!"
        ,"images/coffee2.png"));
        getDb().add(new MenuItem("LATTE","3.49","210","Thanks a Latte..."
        ,"images/coffee3.jpg"));
        getDb().add(new MenuItem("HOUSE COFFEE","1.99","65","Our famous home made coffee!"
        ,"images/coffeeSized.jpg"));
        getDb().add(new MenuItem("DESSERT COFFEE","3.95","480","Calorie counters go away."
        ,"images/coffee5.jpg"));
        getDb().add(new MenuItem("MUFFIN","3.55","225","These muffins are out of control."
        ,"images/muffinSized.jpg"));
        getDb().add(new MenuItem("","","","","images/fillerItemFadeR.png"));
        
       data.put("ItemName", "IMPORTED COFFEE");
       
    }

    @Override
    public void addMenuItem(MenuItem mi) throws IllegalArgumentException {
        if (mi == null) {
            throw new IllegalArgumentException(NOT_FOUND);
        }
        getDb().add(getDb().size()-1, mi);
    }

    @Override
    public void removeMenuItem(MenuItem mi) {
        if (mi == null) {
            throw new IllegalArgumentException(NOT_FOUND);
        }
        for (int i = 0; i < getDb().size(); i++) {
            if (mi.getId().equals(getDb().get(i).getId())) {
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
    public List<Map<String,Object>> getAllMenuItemsMap() {
       Map<String,Object> infoHolder = new LinkedHashMap<>();
       List<Map<String,Object>> allData = new ArrayList<>();
       for(MenuItem mi: getDb()){
           infoHolder.put(mi.getItemName(), mi);
           allData.add(infoHolder);
       }
        
       return allData;
    }
    
    @Override
    public void clearAllData(){
        getDb().clear();
    }

    public final List<MenuItem> getDb() {
        return db;
    }

    public final void setDb(List<MenuItem> db) {
        this.db = db;
    }

    public List<Map<String, Object>> getDb2() {
        return db2;
    }

    public void setDb2(List<Map<String, Object>> db2) {
        this.db2 = db2;
    }
    
    
}
