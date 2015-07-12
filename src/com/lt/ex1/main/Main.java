package com.lt.ex1.main;

import java.io.FileNotFoundException;
import java.io.FileReader;

import org.opencv.core.Core;

import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.lt.ex1.utils.Config;

public class Main {

	public static void main(String[] args) {
		System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
		System.out.println("start ex1");
		System.out.println("Welcome to OpenCV " + Core.VERSION);
		/*
		 * reading input config file  - Start
		 * */
		String dir = System.getProperty("user.dir");
		FileReader fullPathToAndNameOfFile = null;
		try {
			fullPathToAndNameOfFile = new FileReader(dir + "/resources/input.json");
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
			return;
		}
		/*
		 * reading input config file  - End
		 * 
		 * */
		
		JsonParser parser = new JsonParser();
		
		JsonElement je =  parser.parse(fullPathToAndNameOfFile);
		Config config = new Config(je);
		MainController controller= new MainController(config);
		controller.start();
	}

}
