package com.app.server;

public class Main {
	
	@SuppressWarnings("unused")
	public static void main(String[] args) {
		new AppFrame();
		Server server1 = new Server(Server.RMS_PORT);
		Server server2 = new Server(Server.HRMS_PORT);
		Server server3 = new Server(Server.EMS_PORT);
		Server server4 = new Server(Server.REMS_PORT);
	}

}
