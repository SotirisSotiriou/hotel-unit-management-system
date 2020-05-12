package com.app.server;

import java.io.*;
import java.net.*;
import java.util.*;

public class Server {
	private ServerSocket server;
	private ArrayList<Socket> clients;
	
	public static final int RMS_PORT = 5000;
	public static final int HRMS_PORT = 5001;
	public static final int EMS_PORT = 5002;
	public static final int REMS_PORT = 5003;
	
	public Server(int port) {
        try {
            this.server = new ServerSocket(port);
            this.clients = new ArrayList<>();
            
            if(port == 5000)
            	
            	AppFrame.addToServerlog("RMS Server started on port " + port);
            else if(port == 5001) 
            	AppFrame.addToServerlog("HRMS Server started on port " + port);
            else if(port == 5002)
            	AppFrame.addToServerlog("EMS Server started on port " + port);
            else if(port == 5003)
            	AppFrame.addToServerlog("REMS Server started on port " + port);
            else
            	AppFrame.addToServerlog("Server started on port " + port);
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        Thread connectionRequest = new Thread() {
        	public void run() {
                Socket client = null;
                while(true) {
                	try {
                		client = server.accept();
                		if(!client.equals(null)) {
                			clients.add(client);
                			AppFrame.addToServerlog("Cient: " + " " + client.getInetAddress().getHostName() + " connected");
                			OutputStream output = client.getOutputStream();
                			PrintWriter writer = new PrintWriter(output, true);
                			writer.println("You are connected");        			
                		}
                	} catch (Exception e) {
                		e.printStackTrace();
                	}
                }
            }
        };
        connectionRequest.start();
        
        Thread reader = new Thread() {
        	public void run() {
        		InputStream input;
        		ObjectInputStream ois;
        		
        		while(true) {
        			for(Socket client : clients) {
        				try {
							input = client.getInputStream();
							ois = new ObjectInputStream(input);
							Object obj = ois.readObject();
							sendToAll(obj);
						} catch (IOException | ClassNotFoundException e) {
							e.printStackTrace();
						}
        			}
        		}
        	}
        };
        
        reader.start();
    }

    
    
    
    public void sendToClient(Socket client, Object obj) {
    	ObjectOutputStream oos;
    	try {
			oos = new ObjectOutputStream(client.getOutputStream());
			oos.writeObject(obj);
			AppFrame.addToServerlog("Registry sent to client");
		} catch (IOException e) {
			e.printStackTrace();
		}
    }
    
    
    public void sendToAll(Object obj) {
    	if(!this.clients.isEmpty()) {
    		for(Socket client : this.clients) {
        		this.sendToClient(client, obj);
        	}
    		AppFrame.addToServerlog("Registry sent to all clients");
    	}
    	else {
    		AppFrame.addToServerlog("0 Clients connected");
    	}
    }
}
