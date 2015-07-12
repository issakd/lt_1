package com.lt.ex1.utils;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

public class Config{
	private int imageRows;
	private int imageCols;
	private int numberOfPixelsInHole;
	private float z;
	private float epsilon;
	
	public Config(JsonElement je) {
		super();
		 JsonObject  jobject = je.getAsJsonObject();
		 this.imageRows  = jobject.get("imageRows").getAsInt();
		 this.imageCols  = jobject.get("imageCols").getAsInt();
		 this.numberOfPixelsInHole = jobject.get("numberOfPixelsInHole").getAsInt();
		 this.z = jobject.get("z").getAsFloat();
		 this.epsilon = jobject.get("epsilon").getAsFloat();
	}
	public Config(int imageRows, int imageCols, int numberOfPixelsInHole) {
		super();
		this.imageRows = imageRows;
		this.imageCols = imageCols;
		this.numberOfPixelsInHole = numberOfPixelsInHole;
	}
	
	public int getImageRows() {
		return imageRows;
	}
	public void setImageRows(int imageRows) {
		this.imageRows = imageRows;
	}
	public int getImageCols() {
		return imageCols;
	}
	public void setImageCols(int imageCols) {
		this.imageCols = imageCols;
	}
	public int getNumberOfPixelsInHole() {
		return numberOfPixelsInHole;
	}
	public void setNumberOfPixelsInHole(int numberOfPixelsInHole) {
		this.numberOfPixelsInHole = numberOfPixelsInHole;
	}
	public float getZ() {
		return z;
	}
	public void setZ(float z) {
		this.z = z;
	}
	public float getEpsilon() {
		return epsilon;
	}
	public void setEpsilon(float epsilon) {
		this.epsilon = epsilon;
	}
}
