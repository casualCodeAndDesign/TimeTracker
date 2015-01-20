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
   // private String user; //this variable values are accessed in the last page after successful login
    //private int userID, hours, minutes; //userID is used to store new work-hour information
    UserDAO userDAO = new UserDAO();
    public boolean UpdateDatabase(Timestamp startDate, Timestamp endDate, int userName) {

        Statement stmt = null; //creating an SQL-query string
        String SQL = "INSERT INTO tracker (ID, startDate, endDate) VALUES ('" + userName + "','" + startDate + "','" + endDate + "');";
        System.out.println("-------------------------------------------------------------------------------------------------------\nTrackerPage - UpdateDatabase\n-----------------------------------------------------------------------");
        try
        {
            boolean rowFound = false; //initializing a boolean to differentiate succesfull and un-succesful login
            String myDriver = "org.gjt.mm.mysql.Driver"; //using hosted MYSQL-JBDC Driver
            String myUrl = "jdbc:mysql://eu-cdbr-azure-north-b.cloudapp.net/cdb_9317ad04d7"; //url-string for CleraDB-database

            Connection conn = DriverManager.getConnection(myUrl, "b6a81817dfe22a", "89a8ee8c"); //setting the connection string
            stmt = conn.createStatement(); //creating a statement

            System.out.println("Connected"); //testing for connection in development
            System.out.println(SQL);
            int update = stmt.executeUpdate(SQL);
            if(update == 1)
                System.out.println("User id: " + userName);
            else
                System.out.println("Update failed!");
            System.out.println("Update succeeded!");
            
            System.out.println(SQL); //testing for the sql-query

            stmt.close();
            return rowFound;
        }
       catch(SQLException e)  
       { 
            System.out.println(e);
            System.exit(0);
            return false;
       }
        finally
        {
            
        }
    }
    
    public boolean UpdateTotalHours(int userID) {
        Statement stmt = null; //creating an SQL-query string
        String SQL = "SELECT SUM(TIME_TO_SEC(TIMEDIFF(endDate, startDate))) as total_hours FROM tracker WHERE ID = " + userID + ";";
        int total = 0;
        //SELECT SUM(TIME_TO_SEC(TIMEDIFF(endDate, startDate))) AS total_hours FROM tracker WHERE ID = 1;
        System.out.println("-------------------------------------------------------------------------------------------------------\nTrackerPage - UpdateTotalHours\n------------------------------------------------------------------------------");
        try
        {
            System.out.println("SQL-Query:\n" +SQL);
            boolean rowFound = false; //initializing a boolean to differentiate succesfull and un-succesful login
            String myDriver = "org.gjt.mm.mysql.Driver"; //using hosted MYSQL-JBDC Driver
            String myUrl = "jdbc:mysql://eu-cdbr-azure-north-b.cloudapp.net/cdb_9317ad04d7"; //url-string for CleraDB-database

            Connection conn = DriverManager.getConnection(myUrl, "b6a81817dfe22a", "89a8ee8c"); //setting the connection string
            stmt = conn.createStatement(); //creating a statement

            System.out.println("Connected"); //testing for connection in development
            ResultSet rs = stmt.executeQuery(SQL);
            while(rs.next())
            {
                System.out.println(rs.getString("total_hours"));
                total = rs.getInt("total_hours");
            }
            System.out.println("Total hours: " + total);
            userDAO.setHours(total * 3600);
            userDAO.setMinutes(total * 60);
            SQL = "UPDATE totalhours SET totalHours = SEC_TO_TIME(" + total + ") WHERE ID = " + userID + ";";
            int update = stmt.executeUpdate(SQL);
            if(update == 1)
                System.out.println("Updated succesfully!");
            else
                System.out.println("Failed to update");
            System.out.println("New SQL-Query:\n"+SQL);
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
   // public String getUsername() { return user; }
    //public int getID() { return userID; }
    //public int getHours() { return hours; }
    //public int getMinutes() { return minutes; }
}
