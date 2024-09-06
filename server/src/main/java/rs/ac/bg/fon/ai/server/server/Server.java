/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package rs.ac.bg.fon.ai.server.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import rs.ac.bg.fon.ai.server.threads.ProcessingClientRequests;

/**
 *
 * @author PC
 */
public class Server extends Thread{
    
    boolean end = false;
    ServerSocket serverSocket;
    List<ProcessingClientRequests> clients;

    public Server() {
        clients = new ArrayList<>();
    }

    @Override
    public void run() {
        try {
            serverSocket = new ServerSocket(9000);
            while(!end){
                Socket s = serverSocket.accept();
                System.out.println("Klijent je povezan...");
                
                ProcessingClientRequests pcr = new ProcessingClientRequests(s);
                pcr.start();
                clients.add(pcr);
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
   
    
    public void stopServer(){
        end = true;
        try {
            for(ProcessingClientRequests c : clients){
                c.stopThread();
            }
            serverSocket.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
    
    
}
