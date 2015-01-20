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
    private String user;
    private int userID, hours, minutes;
    
    public boolean UserLogin(String username, String password) {

        Statement stmt = null;
        String SQL = "SELECT login.ID, login.name, tracker.totalHours FROM login LEFT JOIN tracker ON tracker.ID=login.ID WHERE login.name  = '" + username + "' && password = '" + password + "';";
        
        try
        {
            boolean rowFound = false;
            String myDriver = "org.gjt.mm.mysql.Driver";
            String myUrl = "jdbc:mysql://eu-cdbr-azure-north-b.cloudapp.net/cdb_9317ad04d7";

            Connection conn = DriverManager.getConnection(myUrl, "b6a81817dfe22a", "89a8ee8c");
            stmt = conn.createStatement();

            System.out.println("Connected");
            //String SQL = "select * from login;";
            //Statement stmt = conn.createStatement();

            ResultSet rs = stmt.executeQuery(SQL);

            System.out.println(SQL);
    
            
            if(rs.next())
            {
                System.out.print("Username: " + rs.getString("name") + ", ID: " + rs.getString("ID") + ", Total Hours: " + rs.getString("totalHours"));
                
                
                user = rs.getString("name");
                System.out.print(user);
                userID = Integer.parseInt(rs.getString("ID"));
                System.out.print(userID);
                hours = 0;
                System.out.print(hours);
                minutes = 0;
                System.out.print(minutes);
                System.out.println("Data retrieved");
                rowFound = true;
            }
            else
            {
                System.out.println("Error in connection");
            }
            //String name = rs.getString("name");
            //System.out.println(names[0] + "," + names[1] + "," + names[2] + "," + names[3] + "," + names[4]);
            rs.close(); 
            stmt.close();
            return rowFound;
        }
       catch(SQLException e)  
       { 
            System.out.println(e); 
            System.exit(0);
            return false;
       }
    }
    
    public String getUsername() { return user; }
    public int getID() { return userID; }
    public int getHours() { return hours; }
    public int getMinutes() { return minutes; }
}
