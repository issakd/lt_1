package com.lt.ex1.algorithms;

import java.util.ArrayList;

import com.lt.ex1.model.Grid;
import com.lt.ex1.model.Pixel;
import com.lt.ex1.model.PixelConstants;
import com.lt.ex1.utils.Utils;

public class GridAlgorithmQuestion4 {
	
	private enum DIRECTION {DIRECTION_X,DIRECTION_MINUX_X,DIRECTION_Y,DIRECTION_MINUX_Y};
	private DIRECTION currentDirection;
	public GridAlgorithmQuestion4(){
		currentDirection = DIRECTION.DIRECTION_MINUX_X;
	}
	
	/*------------------private function Start----------------*/
	private Pixel forward(Pixel currentPixel){
		Pixel newPixel = null;
		Grid gridInstance = Grid.getInstance();
		int x= currentPixel.getX();
		int y= currentPixel.getY();
		switch (currentDirection) {
		case DIRECTION_MINUX_X:
				y = y+1;
				newPixel =gridInstance.getPixel(x, y);
			break;
		case DIRECTION_X:
				y = y-1;
				newPixel =gridInstance.getPixel(x, y);
			break;
		case DIRECTION_MINUX_Y:
				x = x-1;
				newPixel =gridInstance.getPixel(x, y);
			break;
		case DIRECTION_Y:
				x = x+1;
				newPixel =gridInstance.getPixel(x, y);
			break;
		default:
			break;
		}
		return newPixel;
	}
//	private Pixel back(Pixel currentPixel){
//		Pixel newPixel = null;
//		Grid gridInstance = Grid.getInstance();
//		int x= currentPixel.getX();
//		int y= currentPixel.getY();
//		switch (currentDirection) {
//		case DIRECTION_MINUX_X:
//				y = y-1;
//				newPixel =gridInstance.getPixel(x, y);
//			break;
//		case DIRECTION_X:
//				y = y+1;
//				newPixel =gridInstance.getPixel(x, y);
//			break;
//		case DIRECTION_MINUX_Y:
//				x = x-1;
//				newPixel =gridInstance.getPixel(x, y);
//			break;
//		case DIRECTION_Y:
//				x = x+1;
//				newPixel =gridInstance.getPixel(x, y);
//			break;
//		default:
//			break;
//		}
//		return newPixel;
//	}
	private Pixel next(Pixel currentPixel){
		Pixel newPixel = null;
		Grid gridInstance = Grid.getInstance();
		int x= currentPixel.getX();
		int y= currentPixel.getY();
		switch (currentDirection) {
		case DIRECTION_MINUX_X:
				x = x-1;
				newPixel =gridInstance.getPixel(x, y);
			break;
		case DIRECTION_X:
				x = x+1;
				newPixel =gridInstance.getPixel(x, y);
			break;
		case DIRECTION_MINUX_Y:
				y = y-1;
				newPixel =gridInstance.getPixel(x, y);
			break;
		case DIRECTION_Y:
				y = y+1;
				newPixel =gridInstance.getPixel(x, y);
			break;
		default:
			break;
		}
		return newPixel;
	}
	private Pixel circle(Pixel currentPixel){
		Pixel newPixel = null;
		Grid gridInstance = Grid.getInstance();
		int x= currentPixel.getX();
		int y= currentPixel.getY();
		switch (currentDirection) {
		case DIRECTION_MINUX_X:
				y = y-1;
				newPixel =gridInstance.getPixel(x, y);
			break;
		case DIRECTION_X:
				y = y+1;
				newPixel =gridInstance.getPixel(x, y);
			break;
		case DIRECTION_MINUX_Y:
				x = x+1;
				newPixel =gridInstance.getPixel(x, y);
			break;
		case DIRECTION_Y:
				x=x-1;
				newPixel =gridInstance.getPixel(x, y);
			break;
		default:
			break;
		}
		while (newPixel != null && newPixel.getValue() == PixelConstants.PIXEL_VALUE_WHITE){
			/*we circiled and set on a hole pixel - not good need to recalculate */
			switch (currentDirection) {
			case DIRECTION_MINUX_X:
					newPixel =gridInstance.getPixel(newPixel.getX()+1, newPixel.getY());
					currentDirection = DIRECTION.DIRECTION_MINUX_Y;
				break;
			case DIRECTION_X:
				newPixel =gridInstance.getPixel(newPixel.getX()-1, newPixel.getY());
				currentDirection = DIRECTION.DIRECTION_Y;
				break;
			case DIRECTION_MINUX_Y:
				newPixel =gridInstance.getPixel(newPixel.getX(), newPixel.getY()+1);
				currentDirection = DIRECTION.DIRECTION_X;
				break;
			case DIRECTION_Y:
				newPixel =gridInstance.getPixel(newPixel.getX(), newPixel.getY()-1);
				currentDirection = DIRECTION.DIRECTION_MINUX_X;
				break;
			default:
				break;
			}
		}
		return newPixel;
	}
	private Pixel findPixelInHole(){
		Grid grid = Grid.getInstance();
		Pixel p = Utils.getInstance().getRandomPixelInGrid(grid.getRows(), grid.getCols());
		while (grid.getPixel(p.getX(), p.getY()).getValue() != PixelConstants.PIXEL_VALUE_WHITE){
			p = Utils.getInstance().getRandomPixelInGrid(grid.getRows(), grid.getCols());
		}
		return grid.getPixel(p.getX(), p.getY());
	}
	
	private Pixel findFirstBoundary(){
		Pixel pixel = findPixelInHole();
		Grid grid = Grid.getInstance();
		while (pixel.getValue()==PixelConstants.PIXEL_VALUE_WHITE){
			pixel = grid.getPixel(pixel.getX(), pixel.getY()-1);
			if (pixel == null){
				pixel = findPixelInHole();
			}
		}
		return pixel;
	}
	private void changeDirection(){
		//DIRECTION prevDirection = currentDirection;
		switch (currentDirection) {
		case DIRECTION_MINUX_X:
			currentDirection = DIRECTION.DIRECTION_Y;
			break;
		case DIRECTION_X:
			currentDirection = DIRECTION.DIRECTION_MINUX_Y;
			break;
		case DIRECTION_MINUX_Y:
			currentDirection = DIRECTION.DIRECTION_MINUX_X;
			break;
		case DIRECTION_Y:
			currentDirection = DIRECTION.DIRECTION_X;
			break;
		default:
			break;
		}
		//System.out.println("changeDirectio: from: " + prevDirection.ordinal() + " to: " + currentDirection.ordinal());
	}
	private Pixel step(Pixel currentPixel){
		Pixel tmp = new Pixel(-1, -1, 0);
		Pixel nextPixel = null;
		nextPixel = next(currentPixel);
		if(nextPixel == null){
			/*it means we reach the end of grid and got pixel outside of the gird*/
			return null;
		}
		if (nextPixel.getValue() == PixelConstants.PIXEL_VALUE_WHITE){
			/*next is not a boundary - need to circle it*/
			tmp = circle(currentPixel);
			if(tmp == null){
				/*it means we reach the end of grid and got pixel outside of the gird*/
				return null;
			}
			if (tmp.getValue() == PixelConstants.PIXEL_VALUE_WHITE){
				System.out.println("!!! Error Circle returned Hole pixel");
			}
			nextPixel = tmp;
		}
		else{
			tmp = forward(nextPixel);
			if (tmp.getValue() != PixelConstants.PIXEL_VALUE_WHITE){
				nextPixel = tmp;
				/*change in direction*/
				changeDirection();
			}
			else{
				/*next is a boundary*/
			}
		}
		return nextPixel;
	}
	private ArrayList<Pixel> getBoundary(){
		ArrayList<Pixel>  boundaryPixels = new ArrayList<Pixel>();
		Pixel firstPixel = findFirstBoundary();
		Pixel tmp = new Pixel(-1, -1, 0);
		while (!firstPixel.isSame(tmp)){
			if (tmp.getX() == -1){
				tmp = step(firstPixel);
			}
			else{
				tmp = step(tmp);
			}
			if (tmp != null){
				boundaryPixels.add(tmp);
			}
			else{
				System.out.println("Reached out of Grid - not good ");
				break;
			}
			
		}
		return boundaryPixels;
	}
	/*------------------private function End----------------*/
	
	public ArrayList<Pixel> findBoundary(){
		ArrayList<Pixel>  boundaryPixels = getBoundary();
		return boundaryPixels;
	}
	
	public ArrayList<Pixel> findHolePixels(ArrayList<Pixel>  boundaryPixels){
		ArrayList<Pixel>  holePixels = new ArrayList<Pixel>();
		Grid gridInstance = Grid.getInstance();
		Pixel currentPixel;
		Pixel tmpPixel = new Pixel(-1, -1, 0);
		for (int i=0;i<boundaryPixels.size();i++)
		{
			/*swipe right:
			 * 1. check if the 1st RHS pixle is hole
			 * 2. if yes , continue till we get a boundary
			 * 3. add all the hole pixels into the array
			 * */
			currentPixel = boundaryPixels.get(i);
			tmpPixel = gridInstance.getPixel(currentPixel.getX()-1, currentPixel.getY());
			while (tmpPixel != null && tmpPixel.getValue() == PixelConstants.PIXEL_VALUE_WHITE){
				holePixels.add(tmpPixel);
				tmpPixel = gridInstance.getPixel(tmpPixel.getX()-1, tmpPixel.getY());
			}
			
		}
		return holePixels;
	}
}
