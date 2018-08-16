/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jsql;

import java.sql.SQLException;
import java.util.ArrayList;


/**
 *
 * @author awaddell
 */
public class JSql {
    public static void main(String[] args){
        JSqlM test = new JSqlM("localhost", "northwind","root", "password");
        try {
            test.connect();
            //ArrayList<String> testarr = test.getColumns("customers");
            //test.getColumns("northwind.customers");
            test.getColTest("northwind.customers");
        } catch (SQLException ex) {
            System.out.println("ERROR: " + ex.getMessage());
        }
        
    }
    
}
