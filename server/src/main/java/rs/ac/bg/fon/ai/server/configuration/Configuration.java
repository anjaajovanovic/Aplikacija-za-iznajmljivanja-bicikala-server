package rs.ac.bg.fon.ai.server.configuration;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/**
 *
 * @author PC
 */
public class Configuration {
    private static Configuration instance;
    private ConfigData configData;
    
    private Configuration(){
        try {
        	Gson gson = new Gson();
            configData = gson.fromJson(new FileReader(getClass().getClassLoader().getResource("config.json").getFile()), ConfigData.class);
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
    	switch (key) {
        case "url":
            return configData.getUrl();
        case "username":
            return configData.getUsername();
        case "password":
            return configData.getPassword();
        case "port":
            return String.valueOf(configData.getPort());
        default:
            return "n/a";
    	}
    }
    
    public void setProperty(String key, String value){
    	switch (key) {
        case "url":
            configData.setUrl(value);
            break;
        case "username":
            configData.setUsername(value);
            break;
        case "password":
            configData.setPassword(value);
        case "port":
            configData.setPort(Integer.parseInt(value));
            break;
    	}
    }
    
    
    public void saveChanges(){
        try {
        	Gson gson = new GsonBuilder().setPrettyPrinting().create();
            FileWriter writer = new FileWriter(getClass().getClassLoader().getResource("config.json").getFile());
            gson.toJson(configData, writer);
            writer.flush();
            writer.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
    
    
    
}
