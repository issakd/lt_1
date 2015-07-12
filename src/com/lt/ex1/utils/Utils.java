package com.lt.ex1.utils;

import java.util.Random;

import com.lt.ex1.model.Pixel;


public class Utils {

	private static Utils instance = null;
	public static Utils getInstance() {
		if (instance == null) {

			instance = new Utils();
		}
		return instance;
	}
	public Utils(){
	}
	public float getRandomFloatInRange(float min,float max) {
		  float range = max-min;
		  Random rn = new Random();
		  float scaled = rn.nextFloat() *range;
		  float shifted = scaled + min;
		  return shifted;
	}
	public int getRandomIntInRange(int min , int max){
		Random rn = new Random();
		return rn.nextInt(max - min + 1) + min;

	}
	public Pixel getRandomPixelInCenterOfGrid(int rows , int cols){
		/*TBD - add range check since values come from config*/
		Random rn = new Random();
		int x  = getRandomIntInRange((rows/2)-(rows/4),(rows/2)+(rows/4));
		int y =  getRandomIntInRange((cols/2)-(cols/4),(cols/2)+(cols/4));
		Pixel p = new Pixel(x, y, 0);
		return p;
	}
	public Pixel getRandomPixelInGrid(int rows , int cols){
		Random rn = new Random();
		int x = rn.nextInt(rows);
		int y = rn.nextInt(cols);
		Pixel p = new Pixel(x, y, 0);
		return p;
	}
	
}
