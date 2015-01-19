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
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver"); 
            Connection con = DriverManager.getConnection("jdbc:sqlserver://donvfg7xum.database.windows.net:1433;database=unison;user=javacourse@donvfg7xum;password={your_password_here};encrypt=true;hostNameInCertificate=*.database.windows.net;loginTimeout=30;");
            System.out.println("Connected");
        
            String SQL = String.format("SELECT username, password FROM user WHERE username = {0} && password = {1}");
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(SQL);
        
            while (rs.next())
            {
                System.out.println(rs.getString(username) + " " + rs.getString(password));
            }
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
