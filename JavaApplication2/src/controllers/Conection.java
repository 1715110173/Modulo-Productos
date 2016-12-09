package controllers;

import java.sql.Connection;
import java.sql.DriverManager;

public class Conection {
    
    Connection conectar = null;

   // Conection(int i, String localhost, String acme, String root, String string) {
   //     throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    //}
    
    public Connection conexion() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            conectar=DriverManager.getConnection("jdbc:mysql://localhost/acme","root","1234");
        } catch (Exception e) {
            System.out.print(e.getMessage());
        }
        return conectar;
    }   

    void executeUpdate(String string) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
