package com.lt.ex1.algorithms;

import java.util.ArrayList;
import com.lt.ex1.model.Grid;
import com.lt.ex1.model.Pixel;
import com.lt.ex1.model.PixelConstants;
import com.lt.ex1.utils.HolePixelsInfo;
import com.lt.ex1.utils.Utils;

public class GridAlgorithmMine {
	
	public GridAlgorithmMine(){
		
	}
	/*------------------private function start----------------*/
	/*
	 * !!! RECURSIVE function!!!
	 * */
	private HolePixelsInfo mark(Pixel pixelInHole,HolePixelsInfo holePixelsInfo){
		Grid grid = Grid.getInstance();
		if(!holePixelsInfo.getHolePixelsMap().containsKey(pixelInHole.keyForPixel())){
			holePixelsInfo.getHolePixelsMap().put(pixelInHole.keyForPixel(), pixelInHole);
		}
		ArrayList<Pixel> listOfAllNeighbors = pixelInHole.search8ConnectedInGridByXY(grid);
		ArrayList<Pixel> newAddedList = new ArrayList<Pixel>();
		for (Pixel p :listOfAllNeighbors){
			if(holePixelsInfo.getHolePixelsMap().containsKey(p.keyForPixel())){
				continue;
			}
			if(p.getValue() == PixelConstants.PIXEL_VALUE_WHITE){
				holePixelsInfo.getHolePixelsMap().put(p.keyForPixel(), p);
				newAddedList.add(p);
			}
			else{
				holePixelsInfo.getBoundaryPixels().add(p);
			}
		}
		if(!newAddedList.isEmpty()){
			for (Pixel p :newAddedList){
				/*RECURSIVE call*/
				mark( p,holePixelsInfo);
			}
			
		}
		return holePixelsInfo;
	}
	
	private Pixel findPixelInHole(){
		Grid grid = Grid.getInstance();
		Pixel p = Utils.getInstance().getRandomPixelInGrid(grid.getRows(), grid.getCols());
		while (grid.getPixel(p.getX(), p.getY()).getValue() != PixelConstants.PIXEL_VALUE_WHITE){
			p = Utils.getInstance().getRandomPixelInGrid(grid.getRows(), grid.getCols());
		}
		return grid.getPixel(p.getX(), p.getY());
	}
	 
	/*
	 * !!! RECURSIVE function!!!
	 * */
	private void setHoleInternal(int maxNumberOfPixelsInHole,Pixel p,int markedPixelsCounter){
		
		Grid grid = Grid.getInstance();
		int randomIndex = 0;
		ArrayList<Pixel> listOfAllNeighbors = grid.getPixel(p).search8ConnectedInGridByXY(grid);
		for(Pixel pixel : listOfAllNeighbors){
			if(pixel.getValue() != PixelConstants.PIXEL_VALUE_WHITE){
				markedPixelsCounter++;
				pixel.setValue(PixelConstants.PIXEL_VALUE_WHITE);
			}
		}
		if (markedPixelsCounter<maxNumberOfPixelsInHole){
			randomIndex= Utils.getInstance().getRandomIntInRange(0, listOfAllNeighbors.size()-1);
			/*
			 * !!! RECURSIVE call!!!
			 * */
			setHoleInternal( maxNumberOfPixelsInHole, listOfAllNeighbors.get(randomIndex), markedPixelsCounter);
		}
	}
	
	
	/*------------------private function End----------------*/
	
	public void setHole(int maxNumberOfPixelsInHole){
		int markedPixelsCounter = 1;
		Grid grid = Grid.getInstance();
		Pixel randomPixel = Utils.getInstance().getRandomPixelInCenterOfGrid(grid.getRows()-3,grid.getCols()-3);
		randomPixel.setValue(PixelConstants.PIXEL_VALUE_WHITE);
		setHoleInternal(maxNumberOfPixelsInHole,randomPixel,markedPixelsCounter);
	}
	public HolePixelsInfo findBoundaryAndHoles(){
		HolePixelsInfo holePixelsInfo = new HolePixelsInfo();
		Pixel pixelInHole = findPixelInHole();
		holePixelsInfo = mark(pixelInHole, holePixelsInfo);
		return holePixelsInfo;
	}
	
}
