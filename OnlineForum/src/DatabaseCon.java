
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author 
 * ConnectionPool Utility class to get the RDS database connection.
 */
public class DatabaseCon {
    public static Connection getConnection() throws ClassNotFoundException, SQLException {
       
    	  Class.forName("com.mysql.jdbc.Driver");    
          String dbName = "ONLINEFORUM";        
          String userName = "latasundaram";        
          String password = "lata1234";      
          String hostname = "onlineforumdb.c3sasqf65rpn.us-east-2.rds.amazonaws.com";       
          String port = "3306";
          String jdbcUrl = "jdbc:mysql://" + hostname + ":" + port + "/" + dbName + "?user=" + userName + "&password=" + password;        
          return DriverManager.getConnection(jdbcUrl);
    }
}

