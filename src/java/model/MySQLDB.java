/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Kyle Uhan
 */
public class MySQLDB implements DataBaseAccessStrategy {

    private Connection conn;
    private String driverClassName;
    private String url;
    private String userName;
    private String password;
    private Statement stmt;
    private ResultSet rs;
    private String sql;

    public void openConnection() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/restaurant", "root", "root");
        } catch (ClassNotFoundException | SQLException ex) {

        }
    }

    public void closeConnection() {
        try {
            stmt.close();
            conn.close();
            rs.close();
        } catch (Exception e) {

        }
    }

    @Override
    public List<Map> getAllData() {
        openConnection();
        List<Map> data = new ArrayList<>();
        Map dataMap;
        sql = "SELECT * FROM menu_Item";
        try {
            stmt = conn.createStatement();
            rs = stmt.executeQuery(sql);
            ResultSetMetaData rsmd = rs.getMetaData();
            while (rs.next()) {
                dataMap = new LinkedHashMap();
                for (int i = 1; i < rsmd.getColumnCount() + 1; i++) {
                    dataMap.put(rsmd.getColumnName(i), rs.getString(i));
                }
                data.add(dataMap);
            }
        } catch (Exception e) {
        } finally {
            closeConnection();
        }
        return data;
    }

    @Override
    public void clearAllData() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void createRecord(Map record) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Object getRecord(int id) {
        Integer itemId = id;
        openConnection();
        Map dataMap = new LinkedHashMap();
        try {
            List<Map> data = getAllData();
            for (Map record : data) {
                Object key = record.keySet().toArray()[0];
                if (record.get(key).toString().equals(itemId.toString())) {
                    dataMap = record;
                }
            }
        } catch (Exception e) {
        } finally {
            closeConnection();
        }
        return dataMap;
    }

    @Override
    public void updateRecord(int id, Map record) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void removeRecord(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    public void setSql(String sql){
        this.sql = sql;
    }

}
