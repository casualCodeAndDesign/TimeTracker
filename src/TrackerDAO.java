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
import java.lang.Object;
import java.io.*;
import com.opencsv.*;

public class TrackerDAO {

    UserDAO userDAO = new UserDAO();
    public boolean UpdateDatabase(Timestamp startDate, Timestamp endDate, int userName) throws SQLException{
//everything works
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
          stmt.close();  
        }
    }
    
    public boolean UpdateTotalHours(int userID) throws SQLException{
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
            userDAO.setHours(total / 3600);
            userDAO.setMinutes(total / 60);
            System.out.println("\n\n-------------------------------------------------------------------------------------------------------\n\nTOTAL HOURS: " + userDAO.getHours() + ":" + userDAO.getMinutes());
            SQL = "UPDATE totalhours SET totalHours = SEC_TO_TIME(" + total + ") WHERE ID = " + userID + ";";
            int update = stmt.executeUpdate(SQL);
            if(update == 1)
                System.out.println("Updated succesfully!");
            else
                System.out.println("Failed to update");
            System.out.println("New SQL-Query:\n"+SQL);
            
            return rowFound;
        }
       catch(SQLException e)  
       { 
            System.out.println(e);
            System.exit(0);
            return false;
       }
        finally{
            stmt.close();
        }
    }
    
    public double totalHoursInSeconds(int userID) throws SQLException{
        Statement stmt = null; //creating an SQL-query string
        String SQL = "SELECT SUM(TIME_TO_SEC(TIMEDIFF(endDate, startDate))) as total_hours FROM tracker WHERE ID = " + userID + ";";
        System.out.println("\n\n------------------------------------------------------------------------------------------------------\n\n");
        System.out.print("TotalHours Getter Method");
        double totalHours = 0;
        try
        {
            System.out.println("SQL-Query:\n" +SQL);
            String myDriver = "org.gjt.mm.mysql.Driver"; //using hosted MYSQL-JBDC Driver
            String myUrl = "jdbc:mysql://eu-cdbr-azure-north-b.cloudapp.net/cdb_9317ad04d7"; //url-string for CleraDB-database

            Connection conn = DriverManager.getConnection(myUrl, "b6a81817dfe22a", "89a8ee8c"); //setting the connection string
            stmt = conn.createStatement(); //creating a statement

            System.out.println("Connected"); //testing for connection in development
            ResultSet rs = stmt.executeQuery(SQL);
            while(rs.next()) {
                    totalHours = rs.getDouble("total_hours");
            }   
            rs.close();
            
            return totalHours;
        }
       catch(SQLException e)  
       { 
            System.out.println(e);
            System.exit(0);
            return 0;
       }
        finally{
            stmt.close();
        }
    }

    public boolean exportToText(int userID) throws SQLException {
        Statement stmt = null; //creating an SQL-query string
        String SQL = "SELECT * FROM tracker WHERE ID = " + userID + ";";
        System.out.println("\n\n------------------------------------------------------------------------------------------------------\n\n");
        System.out.print("Export to Text Method");  
        
        try
        {
            System.out.println("SQL-Query:\n" +SQL);
            String myDriver = "org.gjt.mm.mysql.Driver"; //using hosted MYSQL-JBDC Driver
            String myUrl = "jdbc:mysql://eu-cdbr-azure-north-b.cloudapp.net/cdb_9317ad04d7"; //url-string for CleraDB-database
            
            String csv = "data.csv"; //declare new csv file
            CSVWriter writer = new CSVWriter(new FileWriter(csv)); //Instantiate new writer
            
            Connection conn = DriverManager.getConnection(myUrl, "b6a81817dfe22a", "89a8ee8c"); //setting the connection string
            stmt = conn.createStatement(); //creating a statement

            System.out.println("Connected"); //testing for connection in development
            ResultSet rs = stmt.executeQuery(SQL);
            
            //Write to csv file, flush stream, then finally close stream
            writer.writeAll(rs, true); 
            writer.flush();
            writer.close();
            
            rs.close();
            
            return true;
        }
       catch(SQLException e)  
       { 
            System.out.println(e);
            System.exit(0);
            return false;
       }
        catch(IOException x)
        {
            return false;
        }

        finally
        {
            stmt.close();
        }
    }
}
