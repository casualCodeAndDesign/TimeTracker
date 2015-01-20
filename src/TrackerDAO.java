/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Nathan
 */
import java.sql.*;
import java.util.Date;

public class TrackerDAO {
    private String user; //this variable values are accessed in the last page after successful login
    private int userID, hours, minutes; //userID is used to store new work-hour information
    
    public boolean UpdateDatabase(java.sql.Date startDate, java.sql.Date endDate, String userName) {

        Statement stmt = null; //creating an SQL-query string
        String SQL = "INSERT INTO tracker (ID, startDate, endDate) VALUES ('" + userName + "','" + startDate + "','" + endDate + "');";
        
        try
        {
            boolean rowFound = false; //initializing a boolean to differentiate succesfull and un-succesful login
            String myDriver = "org.gjt.mm.mysql.Driver"; //using hosted MYSQL-JBDC Driver
            String myUrl = "jdbc:mysql://eu-cdbr-azure-north-b.cloudapp.net/cdb_9317ad04d7"; //url-string for CleraDB-database

            Connection conn = DriverManager.getConnection(myUrl, "b6a81817dfe22a", "89a8ee8c"); //setting the connection string
            stmt = conn.createStatement(); //creating a statement

            System.out.println("Connected"); //testing for connection in development

            ResultSet rs = stmt.executeQuery(SQL); //executing the sql-query

            System.out.println(SQL); //testing for the sql-query
    
            
            if(rs.next()) 
            {
                System.out.print("Username: " + rs.getString("name") + ", ID: " + rs.getString("ID") + ", Total Hours: " + rs.getString("totalHours"));
                //if username exists in the table and the password is inputted correctly the values are stored in the variables 
                // these values are then accessed in the new view after login
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
            //closing the connection and the resultset
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
    //getters for the variables
    public String getUsername() { return user; }
    public int getID() { return userID; }
    public int getHours() { return hours; }
    public int getMinutes() { return minutes; }
}
