
package jsql;

import java.sql.SQLException;
import java.util.ArrayList;


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
