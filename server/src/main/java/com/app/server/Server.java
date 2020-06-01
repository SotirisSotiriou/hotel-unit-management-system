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
            
            if(port == RMS_PORT)
            	AppFrame.addToServerlog("RMS Server started on port " + port);
            else if(port == HRMS_PORT) 
            	AppFrame.addToServerlog("HRMS Server started on port " + port);
            else if(port == EMS_PORT)
            	AppFrame.addToServerlog("EMS Server started on port " + port);
            else if(port == REMS_PORT)
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
                			
                			//read from file
                			try {
                				FileInputStream fis = null;
                    			ObjectInputStream ois = null;
                    			Object obj = null;
                    			if (client.getPort() == RMS_PORT) { 
                    				fis = new FileInputStream(new File("rms-registry.ser"));
                    			}
                    			else if(client.getPort() == EMS_PORT) {
                    				fis = new FileInputStream(new File("ems-registry.ser"));
                    			}
                    			else if(client.getPort() == REMS_PORT) {
                    				fis = new FileInputStream(new File("rems-registry.ser"));
                    			}
                    			else if(client.getPort() == HRMS_PORT) {
                    				fis = new FileInputStream(new File("hrms-registry.ser"));
                    			}
                    			
                    			if(!fis.equals(null)) { 
                    				ois = new ObjectInputStream(fis);
                    				obj = ois.readObject();
                    				sendToClient(client,obj);
                    				AppFrame.addToServerlog("Registry sent to client");
                    			}
                			} catch(Exception e) {
                				e.printStackTrace();
                			}
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
        		InputStream input = null;
        		ObjectInputStream ois = null;
        		File rms = new File("rms-registry.ser");
        		File ems = new File("ems-regsitry.ser");
        		File rems = new File("rems-registry.ser");
        		File hrms = new File("hrms-regsitry.ser");
        		
        		while(true) {
        			Object obj = null;
        			for(Socket client : clients) {
        				try {
							input = client.getInputStream();
							ois = new ObjectInputStream(input);
							obj = ois.readObject();
							if (!obj.equals(null)) {
								if(client.getPort() == RMS_PORT) {
									exportToFile(rms,obj);
								}
								else if(client.getPort() == EMS_PORT) {
									exportToFile(ems, obj);
								}
								else if(client.getPort() == REMS_PORT) {
									exportToFile(rems, obj);
								}
								else if(client.getPort() == HRMS_PORT) {
									exportToFile(hrms, obj);
								}
								sendToAll(obj);
							}
							
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
    
    public void exportToFile(File file, Object obj) {
    	try {
			FileOutputStream fos = new FileOutputStream(file);
			ObjectOutputStream oos = new ObjectOutputStream(fos);
			oos.writeObject(obj);
			oos.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
    	
    }
}
