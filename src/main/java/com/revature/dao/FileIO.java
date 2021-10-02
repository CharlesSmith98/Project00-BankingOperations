package com.revature.dao;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import com.revature.logging.Logging;

public class FileIO<T> {

	private String fileName;
	
	public FileIO(String fileName) {
		this.fileName = fileName;
	}
	
	public void writeObjects(ArrayList<T> objList) {
		try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(fileName))) {
			out.writeObject(objList);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public ArrayList<T> readObjects() throws IOException, ClassNotFoundException {
		ArrayList<T> objList;
		try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(fileName))) {
			objList = (ArrayList<T>)in.readObject();
		} catch (FileNotFoundException fileNotFoundException) {
			System.out.println("Could not find file: " + fileName);
			System.out.println("Creating Empty List...");
			objList = new ArrayList<T>();
		}
		return objList;
	}
	
}
