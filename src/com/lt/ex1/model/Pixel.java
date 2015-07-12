package com.lt.ex1.model;

import java.util.ArrayList;

public class Pixel {
	public enum PixelType{INVALID,HOLE,BOUNDARY,REGULAR};
	
	
	
	private int x;
	private int y;
	private PixelType pixelType;
	
	
	private float value;
	
	
	public Pixel(int x,int y,float value) {
		this.x = x;
		this.y = y;
		this.value = value;
		this.pixelType = PixelType.INVALID;
	}
	
	public PixelType getPixelType() {
		return pixelType;
	}

	public void setPixelType(PixelType pixelType) {
		this.pixelType = pixelType;
	}
	
	public float getValue() {
		return value;
	}
	
	public void setValue(float value) {
		this.value = value;
	}
	
	public int getX() {
		return this.x;
	}
	public void setX(int x) {
		this.x = x;
	}
	public int getY() {
		return this.y;
	}
	public void setY(int y) {
		this.y = y;
	}
	public void print(){
		System.out.println(this.x +"," +  this.y +","+ this.value);
	}
	public void print(String str){
		System.out.println(str +":" + this.x +"," +  this.y +","+ this.value);
	}
	private  ArrayList<Pixel> addToList(ArrayList<Pixel> listOfAllNeighbors , Pixel p ){
		if(p!=null){
			listOfAllNeighbors.add(p);
		}
		return listOfAllNeighbors;
	}
	public ArrayList<Pixel> search4mainNaighbors(Grid grid){
		int x = this.x;
		int y = this.y;
		ArrayList<Pixel> listOfMainNeighbors = new ArrayList<Pixel>();
		addToList(listOfMainNeighbors,grid.getPixel(x+1,y));
		addToList(listOfMainNeighbors,grid.getPixel(x-1,y));
		addToList(listOfMainNeighbors,grid.getPixel(x,y+1));
		addToList(listOfMainNeighbors,grid.getPixel(x,y-1));
		
		return listOfMainNeighbors;
	}
	
	public ArrayList<Pixel> search8ConnectedInGridByXY(Grid grid){
		int x = this.x;
		int y = this.y;
		ArrayList<Pixel> listOfAllNeighbors = new ArrayList<Pixel>();
		listOfAllNeighbors.addAll(search4mainNaighbors( grid));
		addToList(listOfAllNeighbors,grid.getPixel(x+1,y+1));
		addToList(listOfAllNeighbors,grid.getPixel(x-1,y+1));
		addToList(listOfAllNeighbors,grid.getPixel(x-1,y-1));
		addToList(listOfAllNeighbors,grid.getPixel(x+1,y-1));

		return listOfAllNeighbors;
	}
	public ArrayList<Pixel> search4ConnectedInGrid(Grid grid){
		
		return null;
		
	}
	public String keyForPixel(){
		return Integer.toString(this.getX()) +" " + Integer.toString(this.getY()) ;
	}
	public double distance(Pixel pixel){
		double dx = Math.pow(Math.abs(this.x - pixel.x),2);
		double dy = Math.pow(Math.abs(this.y - pixel.y),2);
		return Math.sqrt(dx+dy);
	}
	public void markBoundaryPixel(){
		this.setValue(PixelConstants.PIXEL_VALUE_BLACK);
	}
	public boolean isSame(Pixel p){
		boolean result = false;
		if (this.x == p.x && this.y == p.y){
			result = true;
		}
		return result;
	}
}
