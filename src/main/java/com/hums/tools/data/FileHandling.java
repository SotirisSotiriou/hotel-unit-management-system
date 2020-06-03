package com.hums.tools.data;

import java.io.*;

import com.hums.eventManagementSystem.EMS_Registry;
import com.hums.humanResourceManagementSystem.HRMS_Registry;
import com.hums.restaurantManagementSystem.REMS_Registry;
import com.hums.roomManagementSystem.RMS_Registry;

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
				return null;
			}
			else {
				ois = new ObjectInputStream(new FileInputStream(file));
				Object obj = ois.readObject();
				ois.close();
				return obj;
			}
		} catch(IOException | ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		return null;
	}
}
