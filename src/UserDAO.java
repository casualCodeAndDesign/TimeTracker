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

    public boolean UserLogin(String username, String password) {
        try
        {
            String myDriver = "org.gjt.mm.mysql.Driver";
            String myUrl = "jdbc:mysql://eu-cdbr-azure-north-b.cloudapp.net/cdb_9317ad04d7";

            Connection conn = DriverManager.getConnection(myUrl, "b6a81817dfe22a", "89a8ee8c");

            System.out.println("Connected");
        
            String SQL = "select * from login;";
       
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(SQL);
            System.out.println(rs.next());
            rs.close();
            stmt.close();
            
            
            
            
            return true;
        }
       catch(Exception e)  
       { 
            System.out.println(e.getMessage()); 
            System.exit(0);
            return false;
       }
        
       finally
        {
           
        }
    }
}
