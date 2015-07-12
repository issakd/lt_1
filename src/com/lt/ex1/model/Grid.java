package com.lt.ex1.model;

import java.util.ArrayList;
import com.lt.ex1.utils.Utils;

public class Grid {
	
	private static Grid grid = null;
	private Pixel[][] _grid;
	private int cols;
	private int rows;
	protected Grid() {

	}
	public static Grid getInstance() {
		if (grid == null) {
			return null;
		}
		return grid;
	}
	public static Grid getInstance(int cols, int rows) {
		if (grid == null) {
			grid = new Grid(cols, rows);
		}
		return grid;
	}
	public int  getCols(){
		return this.cols;
	}
	public int  getRows(){
		return this.rows;
	}
	public Grid(int cols, int rows) {
		this.rows = rows;
		this.cols = cols;
		this._grid = new Pixel[cols][rows];
	}
	public void setGrid(Pixel[][] grid, int cols, int rows) {
		this._grid = grid;
		this.cols = cols;
		this.rows = rows;
	}
	public Pixel getPixel(Pixel p){
		return getPixel(p.getX(),p.getY());
	}
	public Pixel getPixel(int x, int y) {
		return (x < this.rows && y < this.cols && x>=0 && y>=0) ? this._grid[x][y] : null;
	}

	public void initGrid() {
		for (int i = 0; i < this.cols; i++) {
			for (int j = 0; j < this.rows; j++) {
				// Initialize each object
				_grid[i][j] = new Pixel(i, j,
						Utils.getInstance().getRandomFloatInRange(PixelConstants.MIN_PIXEL_VALUE,
								PixelConstants.MAX_PIXEL_VALUE)*(PixelConstants.PIXEL_VALUE_WHITE-1));/*just for better difference*/
			}
		}
	}

	public void printGrid() {
		for (int i = 0; i < cols; i++) {
			for (int j = 0; j < rows; j++) {
				_grid[i][j].print();
				System.out.print(",");
			}
			System.out.print("\n");
		}
	}

	public void setPixel(Pixel pixel,float value){
		this._grid[pixel.getX()][pixel.getY()].setValue(value);
	}
	public void markBoundary(ArrayList<Pixel> pixelList){
		for(Pixel p : pixelList){
			p.markBoundaryPixel();
		}
	}
	
	
}
