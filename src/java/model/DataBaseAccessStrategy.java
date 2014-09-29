/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.List;
import java.util.Map;

/**
 *
 * @author Kyle Uhan
 */
public interface DataBaseAccessStrategy {

    public abstract List<Map> getAllData();

    public abstract void clearAllData();

    //C.R.U.D
    public void createRecord(Map record);

    public abstract Object getRecord(int id);

    public abstract void updateRecord(int id, Map record);

    public abstract void removeRecord(int id);

}
