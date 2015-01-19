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



public class ConnectionString {
    String driver = "com.mysql.jdbc.Driver";
    String connection = "jdbc:mysql://eu-cdbr-azure-north-b.cloudapp.net/cdb_9317ad04d7";
    String user = "b6a81817dfe22a";
    String password = "89a8ee8c";
    
    java.lang.Class.forName(driver);
    
    Connection con = DriverManager.getConnection(connection, user, password);

}
