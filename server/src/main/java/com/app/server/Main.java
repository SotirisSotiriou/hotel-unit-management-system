package com.app.server;

public class Main {

	public static void main(String[] args) {
		new AppFrame();
		Server server1 = new Server(5000);
		Server server2 = new Server(5001);
	}

}
