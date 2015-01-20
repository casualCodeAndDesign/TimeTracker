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

        Statement stmt = null;
        String SQL = "SELECT login.ID, name, tracker.totalHours FROM login JOIN tracker ON tracker.ID = login.ID WHERE name='" + username + "' && password='" + password+ "'";
        
        try
        {
            String myDriver = "org.gjt.mm.mysql.Driver";
            String myUrl = "jdbc:mysql://eu-cdbr-azure-north-b.cloudapp.net/cdb_9317ad04d7";

            Connection conn = DriverManager.getConnection(myUrl, "b6a81817dfe22a", "89a8ee8c");
            stmt = conn.createStatement();

            System.out.println("Connected");
            //String SQL = "select * from login;";
            //Statement stmt = conn.createStatement();

            ResultSet rs = stmt.executeQuery(SQL);

            System.out.println(SQL);
            boolean rowFound = false;    
            rs.next();
            System.out.println(rs.getString("ID"));
            while(rs.isBeforeFirst())
            {
                rowFound = true;
            }
            
            if(rowFound == true)
            {
                System.out.print("Username: " + rs.getString("name") + ", ID: " + rs.getString("ID") + ", Total Hours: " + rs.getString("totalHours"));
                TrackerPage.username = rs.getString("name");
                System.out.print(TrackerPage.username);
                TrackerPage.userID = Integer.parseInt(rs.getString("ID"));
                System.out.print(TrackerPage.userID);
                TrackerPage.hours = 0;
                System.out.print(TrackerPage.hours);
                TrackerPage.minutes = 0;
                System.out.print(TrackerPage.minutes);
                System.out.println("Data retrieved");
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
}
