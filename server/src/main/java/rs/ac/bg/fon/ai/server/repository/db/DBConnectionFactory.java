/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package rs.ac.bg.fon.ai.server.repository.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

import rs.ac.bg.fon.ai.server.configuration.Configuration;

/**
 *
 * @author PC
 */
public class DBConnectionFactory {

    private static DBConnectionFactory instance;
    private Connection connection;

    private DBConnectionFactory() {
    }

    public static DBConnectionFactory getInstance() {
        if (instance == null) {
            instance = new DBConnectionFactory();
        }
        return instance;
    }

    public Connection getConnection() {
        try {
            if (connection == null || connection.isClosed()) {
                String url = Configuration.getInstance().getProperty("url");
                String user = Configuration.getInstance().getProperty("username");
                String password = Configuration.getInstance().getProperty("password");
                connection = DriverManager.getConnection(url, user, password);
                connection.setAutoCommit(false);
           
            }
        
        return connection;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return null;
        }
    }
    
    
    
}
