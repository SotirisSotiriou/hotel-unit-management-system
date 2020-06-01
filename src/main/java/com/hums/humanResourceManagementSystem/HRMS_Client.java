package com.hums.humanResourceManagementSystem;


import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.net.InetAddress;
import java.net.Socket;

import com.hums.Client;

public class HRMS_Client extends Client{

	
	public void connect() {
		try {
			this.setConnection(new Socket(InetAddress.getLocalHost(), 5001));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		Thread reader = new Thread() {
			public void run() {
				InputStream input;
				ObjectInputStream ois;		
				while(true) {
					try {
						input = getConnection().getInputStream();
						ois = new ObjectInputStream(input);
						HRMS_Registry response = (HRMS_Registry) ois.readObject();
						//HERE WILL BE THE HRMS_GUI.setRegistry(response);
					} catch (IOException | ClassNotFoundException e) {
						e.printStackTrace();
					}
				}
			}
		};
		reader.start();
	}
}
