/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Johnny
 */
public class ConnectionString {
    
    String connection;
    public ConnectionString() {
        connection = "jdbc:sqlserver://donvfg7xum.database.windows.net:1433;database=unison;user=javacourse@donvfg7xum;password={your_password_here};encrypt=true;hostNameInCertificate=*.database.windows.net;loginTimeout=30;";
    }
}
