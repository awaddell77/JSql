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
        
        this.conn = DriverManager.getConnection(this.host,this.user, this.password);
        }
        catch (SQLException sqe){
            System.out.println("EXCEPTION CAUGHT: " + sqe.getMessage());
            
        }
    }
    public void testColGet(){
        try{
        //ArrayList<String> res = getColumns("customers");
        String[][] res = getColumns1("customers");
        System.out.println(Arrays.toString(res));
        }
        catch (SQLException sqe){
            System.out.println("CONNECTION FAIL");
        }
        
    }
    public void getColTest(String table) throws SQLException{
        //ArrayList<String> res = new ArrayList<>();
        
        int colind = 0;
        Statement stm = this.conn.createStatement();
        
        ResultSet rs = stm.executeQuery("SHOW COLUMNS FROM " + table);
        ResultSetMetaData met = rs.getMetaData();
        rs.last();
        int leng = rs.getRow();
        
        String[][] cols = new String[getResLen(rs)][6];
       
        int rcount = 0;
        //TODO: replace two dimensional array with something else
        while (rs.next()){
            cols[rcount][0] = rs.getString("Field");
            cols[rcount][1] = rs.getString("Type");
            cols[rcount][2] = rs.getString("Null");
            cols[rcount][3] = rs.getString("Key");
            cols[rcount][4] = rs.getString("Default");
            cols[rcount][5] = rs.getString("Extra");
            System.out.println("COLUMNS: " + Arrays.toString(cols[rcount]));
            rcount++;
            
        }
    }
    public int getResLen(ResultSet rs) throws SQLException{
        rs.last();
        int leng = rs.getRow();
        rs.beforeFirst();
        return leng;
        
        
    }
    public String[][] getColumns1(String table) throws SQLException{
        ArrayList<String> res = new ArrayList<>();
        int colind = 0;
        Statement stm = this.conn.createStatement();
        ResultSet rs = stm.executeQuery("SELECT * FROM " + table + " LIMIT 1");
        ResultSetMetaData met = rs.getMetaData();
        
        
        int colc = met.getColumnCount();
        String[][] tarr = new String[colc][colc];
        //System.out.println("COLUMN NAME: "+ met.getColumnName(0));

        
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

