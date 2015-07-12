package com.lt.ex1.utils;

import java.util.ArrayList;
import java.util.HashMap;
import com.lt.ex1.model.Pixel;

public class HolePixelsInfo {
	private ArrayList<Pixel> boundaryPixels;
	private ArrayList<Pixel> holePixels;
	private HashMap<String, Pixel> holePixelsMap;
	
	
	public HolePixelsInfo(ArrayList<Pixel> boundaryPixels,ArrayList<Pixel> holePixels) {
		super();
		this.boundaryPixels = new ArrayList<Pixel>();
		this.holePixels = new ArrayList<Pixel>();
	}
	
	
	public HolePixelsInfo() {
		super();
		this.boundaryPixels = new ArrayList<Pixel>();
		this.holePixels = new ArrayList<Pixel>();
		this.holePixelsMap = new HashMap<String, Pixel>();
	}

	public HashMap<String, Pixel> getHolePixelsMap() {
		return holePixelsMap;
	}


	public void setHolePixelsMap(HashMap<String, Pixel> holePixelsMap) {
		this.holePixelsMap = holePixelsMap;
	}


	public ArrayList<Pixel> getBoundaryPixels() {
		return boundaryPixels;
	}
	public void setBoundaryPixels(ArrayList<Pixel> boundaryPixels) {
		this.boundaryPixels = boundaryPixels;
	}
	public ArrayList<Pixel> getHolePixels() {
		return holePixels;
	}
	public void setHolePixels(ArrayList<Pixel> holePixels) {
		this.holePixels = holePixels;
	}
}
