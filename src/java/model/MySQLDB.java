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
import java.util.Arrays;
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
            stmt = conn.createStatement();
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
        List<Map> data = new ArrayList<>();
        Map dataMap;
        sql = "SELECT * FROM menu_Item";
        try {
            openConnection();
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
        try {
            openConnection();
            sql = "TRUNCATE TABLE menu_Item";
            stmt.executeUpdate(sql);
        } catch (Exception e) {
        } finally {
            closeConnection();
        }
    }

    @Override
    public void createRecord(Map record) {
        sql = "SELECT * FROM menu_Item";
        try {
            openConnection();
            Object[] key = record.keySet().toArray();
            List<Object> values = new ArrayList<>();
            for (int p = 0; p < key.length; p++) {
                values.add(record.get(key[p]));
            }
            rs = stmt.executeQuery(sql);
            ResultSetMetaData rsmd = rs.getMetaData();
            List<String> cols = new ArrayList<>();
            for (int t = 1; t < rsmd.getColumnCount() + 1; t++) {
                cols.add(rsmd.getColumnName(t));
            }

            StringBuilder sb = new StringBuilder("INSERT INTO menu_Item (");
            for (int y = 1; y < values.size(); y++) {
                if (y == (values.size() - 1)) {
                    sb.append(cols.get(y)).append(") VALUES (");
                } else {
                    sb.append(cols.get(y)).append(", ");
                }
            }
            for (int i = 1; i < values.size(); i++) {
                if (i == (values.size() - 1)) {
                    if ("VARCHAR".equals(rsmd.getColumnTypeName(i + 1))) {
                        sb.append("'").append(values.get(i)).append("'");
                    } else {
                        sb.append(values.get(i));
                    }
                } else {
                    if ("VARCHAR".equals(rsmd.getColumnTypeName(i + 1))) {
                        sb.append("'").append(values.get(i)).append("'").append(", ");
                    } else {
                        sb.append(values.get(i)).append(", ");
                    }
                }
            }
            sb.append(")");
            sql = sb.toString();
            stmt.executeUpdate(sql);
        } catch (Exception e) {
        } finally {
            closeConnection();
        }
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
        sql = "SELECT * FROM menu_Item";
        try {
            openConnection();
            rs = stmt.executeQuery(sql);
            ResultSetMetaData rsmd = rs.getMetaData();
            List<String> cols = new ArrayList<>();
            for (int t = 1; t < rsmd.getColumnCount() + 1; t++) {
                cols.add(rsmd.getColumnName(t));
            }
            Object[] key = record.keySet().toArray();
            List<Object> values = new ArrayList<>();
            for (int p = 0; p < key.length; p++) {
                values.add(record.get(key[p]));
            }
            StringBuilder sb = new StringBuilder("UPDATE menu_item SET ");
            for (int y = 1; y < values.size(); y++) {
                if (y == (values.size() - 1)) {
                    sb.append(cols.get(y)).append(" = ").append("'").append(values.get(y)).append("'").append(" ");
                } else {
                    if ("VARCHAR".equals(rsmd.getColumnTypeName(y + 1))) {
                        sb.append(cols.get(y)).append(" = ").append("'").append(values.get(y)).append("'").append(", ");
                    } else {
                        sb.append(cols.get(y)).append(" = ").append(values.get(y)).append(", ");
                    }
                }
            }
            sb.append("WHERE ").append(cols.get(0)).append(" = ").append(id);
            System.out.println(sb.toString());
            sql = sb.toString();
            stmt.executeUpdate(sql);
        } catch (Exception e) {

        } finally {
            closeConnection();
        }
    }

    @Override
    public void removeRecord(int id) {
        sql = "SELECT * FROM menu_Item";
        try {
            openConnection();
            rs = stmt.executeQuery(sql);
            ResultSetMetaData rsmd = rs.getMetaData();
            String keyName = rsmd.getColumnName(1);
            StringBuilder sb = new StringBuilder("DELETE FROM menu_item WHERE ");
            sb.append(keyName).append(" = ").append(id);
            stmt.executeUpdate(sb.toString());
        } catch (Exception e) {
        } finally {
            closeConnection();
        }
    }

    public void setSql(String sql) {
        this.sql = sql;
    }

    public static void main(String[] args) {
        MenuItem mi = new MenuItem("CROSSANT", "2.44", "500", "Buttery goodness", "images/croissantSized.jpg");
        Map convertedMenuItem = new LinkedHashMap<>();
        //change id to static db pk auto incr.
        convertedMenuItem.put("ID", mi.getId());
        convertedMenuItem.put("itemName", mi.getItemName());
        convertedMenuItem.put("itemPrice", String.valueOf(mi.getItemPrice()));
        convertedMenuItem.put("itemCalories", String.valueOf(mi.getItemCalories()));
        convertedMenuItem.put("itemDescription", mi.getItemDescription());
        convertedMenuItem.put("itemPicture", mi.getItemPicture());
        MySQLDB db = new MySQLDB();

        // db.updateRecord(2, convertedMenuItem);
        db.createRecord(convertedMenuItem);
    }

}
