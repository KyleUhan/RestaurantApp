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

    public final Integer COL_ID_POSITION = 1;
    private Connection conn;
    private String driverClassName;
    private String url;
    private String userName;
    private String password;
    private Statement stmt;
    private ResultSet rs;
    private String sql;
    private String dbName;
    private String tableName;

    public MySQLDB() {
        //DEFAULT VALUES FOR TESTING
        setDbName("restaurant");
        setTableName("menu_Item");
        setDriverClassName("com.mysql.jdbc.Driver");
        setUrl("jdbc:mysql://localhost:3306/" + getDbName());
        setUserName("root");
        setPassword("root");
    }

    public void openConnection() {
        try {
            Class.forName(getDriverClassName());
            setConn(DriverManager.getConnection(getUrl(), getUserName(), getPassword()));
            setStmt(getConn().createStatement());
        } catch (ClassNotFoundException | SQLException ex) {
        }
    }

    public void closeConnection() {
        try {
            getStmt().close();
            getConn().close();
            getRs().close();
        } catch (Exception e) {
        }
    }

    @Override
    public List<Map> getAllData() {
        List<Map> data = new ArrayList<>();
        Map dataMap;
        setSql("SELECT * FROM " + getTableName());
        try {
            openConnection();
            setRs(getStmt().executeQuery(getSql()));
            ResultSetMetaData rsmd = getRs().getMetaData();
            while (getRs().next()) {
                dataMap = new LinkedHashMap();
                for (int i = 1; i < rsmd.getColumnCount() + 1; i++) {
                    dataMap.put(rsmd.getColumnName(i), getRs().getString(i));
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
            setSql("TRUNCATE TABLE " + getTableName());
            getStmt().executeUpdate(getSql());
        } catch (Exception e) {
        } finally {
            closeConnection();
        }
    }

    @Override
    public void createRecord(Map record) {
        setSql("SELECT * FROM " + getTableName());
        try {
            openConnection();
            Object[] key = record.keySet().toArray();
            List<Object> values = new ArrayList<>();
            for (Object key1 : key) {
                values.add(record.get(key1));
            }
            setRs(getStmt().executeQuery(getSql()));
            ResultSetMetaData rsmd = getRs().getMetaData();
            List<String> cols = new ArrayList<>();
            for (int t = 1; t < rsmd.getColumnCount() + 1; t++) {
                cols.add(rsmd.getColumnName(t));
            }

            StringBuilder sb = new StringBuilder("INSERT INTO " + getTableName() + " (");
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
            setSql(sb.toString());
            getStmt().executeUpdate(getSql());
        } catch (Exception e) {
        } finally {
            closeConnection();
        }
    }

    @Override
    public Object getRecord(int id) {
        Integer itemId = id;
        Map dataMap = new LinkedHashMap();
        List<Map> data = getAllData();
        for (Map record : data) {
            Object key = record.keySet().toArray()[0];
            if (record.get(key).toString().equals(itemId.toString())) {
                dataMap = record;
            }
        }
        return dataMap;
    }

    @Override
    public void updateRecord(int id, Map record) {
        setSql("SELECT * FROM " + getTableName());
        try {
            openConnection();
            setRs(getStmt().executeQuery(getSql()));
            ResultSetMetaData rsmd = getRs().getMetaData();
            List<String> cols = new ArrayList<>();
            for (int t = 1; t < rsmd.getColumnCount() + 1; t++) {
                cols.add(rsmd.getColumnName(t));
            }
            Object[] key = record.keySet().toArray();
            List<Object> values = new ArrayList<>();
            for (Object key1 : key) {
                values.add(record.get(key1));
            }
            StringBuilder sb = new StringBuilder("UPDATE " + getTableName() + " SET ");
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
            setSql(sb.toString());
            getStmt().executeUpdate(getSql());
        } catch (Exception e) {
        } finally {
            closeConnection();
        }
    }

    @Override
    public void removeRecord(int id) {
        setSql("SELECT * FROM " + getTableName());
        try {
            openConnection();
            setRs(getStmt().executeQuery(getSql()));
            ResultSetMetaData rsmd = getRs().getMetaData();
            String keyName = rsmd.getColumnName(COL_ID_POSITION);
            StringBuilder sb = new StringBuilder("DELETE FROM " + getTableName() + " WHERE ");
            sb.append(keyName).append(" = ").append(id);
            getStmt().executeUpdate(sb.toString());
        } catch (Exception e) {
        } finally {
            closeConnection();
        }
    }

    // GETTERS/SETTERS
    public final void setSql(String sql) {
        this.sql = sql;
    }

    public final String getSql() {
        return this.sql;
    }

    public final Connection getConn() {
        return conn;
    }

    public final void setConn(Connection conn) {
        this.conn = conn;
    }

    public final String getDriverClassName() {
        return driverClassName;
    }

    public final void setDriverClassName(String driverClassName) {
        this.driverClassName = driverClassName;
    }

    public final String getUrl() {
        return url;
    }

    public final void setUrl(String url) {
        this.url = url;
    }

    public final String getUserName() {
        return userName;
    }

    public final void setUserName(String userName) {
        this.userName = userName;
    }

    public final String getPassword() {
        return password;
    }

    public final void setPassword(String password) {
        this.password = password;
    }

    public final Statement getStmt() {
        return stmt;
    }

    public final void setStmt(Statement stmt) {
        this.stmt = stmt;
    }

    public final ResultSet getRs() {
        return rs;
    }

    public final void setRs(ResultSet rs) {
        this.rs = rs;
    }

    public final String getDbName() {
        return dbName;
    }

    public final void setDbName(String dbName) {
        this.dbName = dbName;
    }

    public final String getTableName() {
        return tableName;
    }

    public final void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public static void main(String[] args) {
        /*   MenuItem mi = new MenuItem("CROSSANT", "2.44", "500", "Buttery goodness", "images/croissantSized.jpg");
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
         db.createRecord(convertedMenuItem);*/
    }

}
