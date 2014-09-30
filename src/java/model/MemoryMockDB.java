/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Kyle Uhan
 */
public class MemoryMockDB implements DataBaseAccessStrategy {

    private final String UNABLE_TO_LOCATE = "Unable to locate database";
    private final String ITEM_NOT_EXIST = "Item id does not exist.";
    private final String REPLACMENT_ITEM_NOT_FOUND = "Replacement item not found.";
    private final String ID = "ID";

    List<Map> db;

    public MemoryMockDB() {
        setDb(new ArrayList<>());
    }

    public MemoryMockDB(List<Map> dbContent) {
        setDb(dbContent);
    }

    public final List<Map> getDb() {
        return this.db;
    }

    public final void setDb(final List<Map> db) {
        if (db == null) {
            throw new IllegalArgumentException(UNABLE_TO_LOCATE);
        }
        this.db = db;
    }

    @Override
    public List<Map> getAllData() {
        return getDb();
    }

    @Override
    public void clearAllData() {
        getDb().clear();
    }

    @Override
    public void createRecord(Map record) {
        getDb().add(record);
    }

    @Override
    public Object getRecord(int id) throws IllegalArgumentException {
       // List<Integer> idList = new ArrayList();
        Integer mapId;
        boolean itemNotExist = true;
        for (int i = 0; i < getDb().size(); i++) {
            mapId = (Integer) getDb().get(i).get(ID);
            //idList.add(mapId);
            if (mapId.equals(id)) {
                id = i;
                itemNotExist = false;
            }
        }
        if (itemNotExist) {
            throw new IllegalArgumentException(ITEM_NOT_EXIST);
        }
        return getDb().get(id);
    }

    @Override
    public void updateRecord(int id, Map record) throws IllegalArgumentException {
        if (id < 1) {
            throw new IllegalArgumentException(ITEM_NOT_EXIST);
        }
        if (record == null) {
            throw new IllegalArgumentException(REPLACMENT_ITEM_NOT_FOUND);
        }
        Integer mapId;
        for (int i = 0; i < getDb().size(); i++) {
            mapId = (Integer) getDb().get(i).get(ID);
            if (mapId.equals(id)) {
                getDb().remove(i);
                getDb().add(i, record);
            }
        }
    }

    @Override
    public void removeRecord(int id) {
        if (id < 1) {
            throw new IllegalArgumentException(ITEM_NOT_EXIST);
        }
        Integer mapId;
        for (int i = 0; i < getDb().size(); i++) {
            mapId = (Integer) getDb().get(i).get(ID);
            if (mapId.equals(id)) {
                getDb().remove(i);
            }
        }
    }

}
