package rs.ac.bg.fon.ai.server.configuration;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author PC
 */
public class Configuration {
    private static Configuration instance;
    private Properties configuration;
    
    private Configuration(){
        configuration = new Properties();
        try {
            configuration.load(new FileInputStream("C:\\Users\\PC\\Documents\\NetBeansProjects\\0_sem_server\\config\\config.properties"));
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
    
    
    public static Configuration getInstance(){
        if (instance == null){
            instance = new Configuration();
        }
        return instance;
    }

    public String getProperty(String key){
        return configuration.getProperty(key, "n/a");
    }
    
    public void setProperty(String key, String value){
        configuration.setProperty(key, value);
    }
    
    
    public void saveChanges(){
        try {
            configuration.store(new FileOutputStream("C:\\Users\\PC\\Documents\\NetBeansProjects\\0_sem_server\\config\\config.properties"), null);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
    
    
    
}
