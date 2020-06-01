package com.hums;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.Socket;

public abstract class Client {
	Socket connection;
	
	public Client() {
		
	}
	
	public abstract void connect();
	
	public Socket getConnection() {
		return this.connection;
	}
	
	public void setConnection(Socket socket) {
		this.connection = socket;
	}
	
	public void sendToServer(Object obj) {
		OutputStream output;
		ObjectOutputStream oos;
		try {
			output = getConnection().getOutputStream();
			oos = new ObjectOutputStream(output);
			oos.writeObject(obj);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
