/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Johnny
 */
import java.sql.*;
public class UserDAO {

    public boolean UserLogin(String user, String password) {
        try
        {
            String driver = "com.mysql.jdbc.Driver";
            String connection = "jdbc:mysql://eu-cdbr-azure-north-b.cloudapp.net/cdb_9317ad04d7";
            user = "b6a81817dfe22a";
            password = "89a8ee8c";
    
    java.lang.Class.forName(driver);
    
    Connection con = DriverManager.getConnection(connection, user, password);
            Class.forName("com.mysql.jdbc.Driver"); 
            System.out.println("Opened UserLogin");
            //jdbc:sqlserver://donvfg7xum.database.windows.net:1433;database=unison;user=javacourse@donvfg7xum;password={your_password_here};encrypt=true;hostNameInCertificate=*.database.windows.net;loginTimeout=30;
            //Connection con = DriverManager.getConnection("jdbc:mysql://eu-cdbr-azure-north-b.cloudapp.net/cdb_9317ad04d7");
            System.out.println("Connected");
        
            String SQL = String.format("SELECT * FROM login;");
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(SQL);
        
            System.out.print(rs.getString("name"));
            return true;
        }
       catch(Exception e)  
       { 
            System.out.println(e.getMessage()); 
            System.exit(0);
            return false;
       }
    }
}
