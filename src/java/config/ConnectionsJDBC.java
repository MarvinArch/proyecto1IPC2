/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package config;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.sql.DataSource;
import java.sql.DriverManager;
import org.apache.tomcat.dbcp.dbcp2.BasicDataSource;

/**
 *
 * @author david
 */
public class ConnectionsJDBC {
    private static final String JDBC_URL="jdbc:mysql://us-cdbr-east-06.cleardb.net/heroku_26d72fe9c74218d";
    private static final String JDBC_USER="b7200cd50cd7c9";
    private static final String JDBC_PASSWORD="c5d4be37";
    private static Connection conn;
        
    
    public static DataSource getDataSource(){
        BasicDataSource ds = new BasicDataSource();
        ds.setDriverClassName("com.mysql.jdbc.Driver");
        ds.setUrl(JDBC_URL);
        ds.setUsername(JDBC_USER);
        ds.setPassword(JDBC_PASSWORD);
        //ds.setInitialSize(25);

        return ds;
    }
    
    public static Connection  getConnection() throws SQLException{
        try {
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection(JDBC_URL,JDBC_USER,JDBC_PASSWORD);
            
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ConnectionsJDBC.class.getName()).log(Level.SEVERE, null, ex);
        }
        return conn;
    }
    
}
