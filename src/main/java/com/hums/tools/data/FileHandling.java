package com.hums.tools.data;

import java.io.*;

import com.hums.eventManagementSystem.EMS_Registry;
import com.hums.humanResourceManagementSystem.HRMS_Registry;
import com.hums.restaurantManagementSystem.REMS_Registry;
import com.hums.roomManagementSystem.RMS_Registry;
import com.hums.tools.login.UserList;

public abstract class FileHandling {
	public static void exportToFile(Object obj) {
		ObjectOutputStream oos = null;
		try {
			if(obj.getClass().equals(RMS_Registry.class)) {
				oos = new ObjectOutputStream(new FileOutputStream(new File("src/main/resources/rms-registry.ser")));
			}
			else if(obj.getClass().equals(EMS_Registry.class)) {
				oos = new ObjectOutputStream(new FileOutputStream(new File("src/main/resources/ems-registry.ser")));
			}
			else if(obj.getClass().equals(REMS_Registry.class)) {
				oos = new ObjectOutputStream(new FileOutputStream(new File("src/main/resources/rems-registry.ser")));
			}
			else if(obj.getClass().equals(HRMS_Registry.class)) {
				oos = new ObjectOutputStream(new FileOutputStream(new File("src/main/resources/hrms-registry.ser")));
			}
			else if(obj.getClass().equals(UserList.class)) {
				oos = new ObjectOutputStream(new FileOutputStream(new File("src/main/resources/users.ser")));
			}
			
			if(oos != null) {
				oos.writeObject(obj);
				oos.flush();
				oos.close();
			}
		} catch(IOException e) {
			e.printStackTrace();
		}
	}
	
	
	public static Object importFromFile(String filename) {
		ObjectInputStream ois;
		File file = new File("src/main/resources/" + filename);
		
		try {
			if(file.length() == 0) {
				if(filename.equals("users.ser")) return UserList.getInstance();
				if(filename.equals("rms-registry.ser")) return RMS_Registry.getInstance();
				if(filename.equals("ems-registry.ser")) return EMS_Registry.getInstance();
				if(filename.equals("rems-registry.ser")) return REMS_Registry.getInstance();
				if(filename.equals("hrms-registry.ser")) return HRMS_Registry.getInstance();
			}
			else {
				ois = new ObjectInputStream(new FileInputStream(file));
				Object obj = ois.readObject();
				ois.close();
				return obj;
			}
		} catch(FileNotFoundException | ClassNotFoundException | WriteAbortedException e) {
			if(filename.equals("users.ser")) return UserList.getInstance();
			if(filename.equals("rms-registry.ser")) return RMS_Registry.getInstance();
			if(filename.equals("ems-registry.ser")) return EMS_Registry.getInstance();
			if(filename.equals("rems-registry.ser")) return REMS_Registry.getInstance();
			if(filename.equals("hrms-registry.ser")) return HRMS_Registry.getInstance();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return null;
	}
}
