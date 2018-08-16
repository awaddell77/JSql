/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jsql;
import java.sql.Array;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
/**
 *
 * @author awaddell
 */

public class JSqlM {
    private String host;
    private String dbase;
    private String user;
    private String password;
    private Connection conn;
    public JSqlM(){};
    public JSqlM(String host, String dbase, String user, String password){
        this.host = "jdbc:mysql://" + host + ":3306/"+dbase;
        this.dbase = dbase;
        this.user = user;
        this.password = password;
    }
    public void connect() throws SQLException{
        try{
        this.conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/northwind","root", "password");
        }
        catch (SQLException sqe){
            System.out.println("EXCEPTION CAUGHT: " + sqe.getMessage());
            
        }
    }
    public void testColGet(){
        try{
        //ArrayList<String> res = getColumns("customers");
        String[][] res = getColumns("customers");
        System.out.println(Arrays.toString(res));
        }
        catch (SQLException sqe){
            System.out.println("CONNECTION FAIL");
        }
        
    }
    public void getColTest(String table) throws SQLException{
        ArrayList<String> res = new ArrayList<>();
        int colind = 0;
        Statement stm = this.conn.createStatement();
        ResultSet rs = stm.executeQuery("SHOW COLUMNS FROM " + table);
        ResultSetMetaData met = rs.getMetaData();
        while (rs.next()){
            String rowId = rs.getString("Field");
            System.out.println("COLUMN: " + rowId);
            
        }
    }
    public String[][] getColumns(String table) throws SQLException{
        ArrayList<String> res = new ArrayList<>();
        int colind = 0;
        Statement stm = this.conn.createStatement();
        ResultSet rs = stm.executeQuery("SELECT * FROM " + table + " LIMIT 1");
        ResultSetMetaData met = rs.getMetaData();
        
        
        int colc = met.getColumnCount();
        String[][] tarr = new String[colc][colc];
        //System.out.println("COLUMN NAME: "+ met.getColumnName(0));
        //for whatever reason it doesn't seem to recognize longblob columns
        
        for (int i= 1 ; i <= colc; i++){
            String col = met.getColumnName(i);
            String colType = met.getColumnTypeName(i);
            tarr[i-1][0] = col;
            tarr[i-1][1] = colType;
            System.out.println(col + ", " + colType);
            colind++;
        }
        stm.close();
        return tarr;
        
        
        
    }


}

