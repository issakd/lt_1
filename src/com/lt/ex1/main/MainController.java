package com.lt.ex1.main;

import java.util.ArrayList;

import javax.swing.SwingUtilities;

import org.opencv.core.CvType;
import org.opencv.core.Mat;

import com.lt.ex1.algorithms.FillHoleAlgorithm.A_TYPE;
import com.lt.ex1.algorithms.FillHoleAlgorithmFactory;
import com.lt.ex1.algorithms.GridAlgorithmMine;
import com.lt.ex1.algorithms.GridAlgorithmQuestion4;
import com.lt.ex1.model.Grid;
import com.lt.ex1.model.Pixel;
import com.lt.ex1.utils.Config;
import com.lt.ex1.utils.HolePixelsInfo;
import com.lt.ex1.view.FrameView;
import com.lt.ex1.view.PictureView;

public class MainController {
	private enum CONTROLLER_STATE{CONTROLLER_INVALID,CONTROLLER_NO_HOLE,CONTROLLER_HOLE,CONTROLLER_BOUNDARY};
	private  FrameView panelView;
	private  Config config;
	private  HolePixelsInfo holePixelsInfo;
	private  ArrayList<Pixel> counterPixelsHistory;
	private  Mat mat;
	private  CONTROLLER_STATE state;
	private  GridAlgorithmMine gridAlgorithm;
	private  GridAlgorithmQuestion4 gridAlgorithm2;
	
	public MainController(Config config){
		this.config = config;
		this.state = CONTROLLER_STATE.CONTROLLER_INVALID;
		this.gridAlgorithm = new GridAlgorithmMine();
		this.gridAlgorithm2 = new GridAlgorithmQuestion4();
	}
	/*************private start********************/
		private  Grid initGrid(){
			Grid gridInstance = Grid.getInstance(config.getImageRows(),config.getImageCols());
			gridInstance.initGrid();
			return gridInstance;
		}
		
		private void refreshView(){
			PictureView image = refreshMatrix();
			this.panelView.refreshImage(image);
			SwingUtilities.updateComponentTreeUI(panelView);
		}
		private PictureView refreshMatrix(){
			Grid gridInstance =  Grid.getInstance();
			this.mat = new Mat(Grid.getInstance().getCols(), gridInstance.getRows(), CvType.CV_32SC1);
			for (int i=0; i<Grid.getInstance().getRows(); i++)
			{
			    for(int j=0; j<gridInstance.getCols(); j++)
			    {
			    	this.mat.put(i, j, gridInstance.getPixel(i,j).getValue());
			    }
			}
			PictureView image = null;
			try {
				image = new PictureView(this.mat, ".jpg");
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return image;
		}
		/*************private end********************/
		
		
	public void start(){
		initGrid();
		PictureView image = refreshMatrix();
		this.panelView = new FrameView(image,this); 
		SwingUtilities.updateComponentTreeUI(panelView);
		this.state = CONTROLLER_STATE.CONTROLLER_NO_HOLE;
	}
	public void resetHoleBoundary(){
		if(this.state == CONTROLLER_STATE.CONTROLLER_BOUNDARY){
			if ((this.counterPixelsHistory != null) && 
				(this.counterPixelsHistory.size()>0)){
				for (Pixel p : this.counterPixelsHistory){
					Grid.getInstance().setPixel(p, p.getValue());
				}
				refreshView();
			}
			this.state = CONTROLLER_STATE.CONTROLLER_HOLE;
		}
	}	
	
	public void makeHole(){
		if(this.state == CONTROLLER_STATE.CONTROLLER_NO_HOLE ){
			this.gridAlgorithm.setHole(this.config.getNumberOfPixelsInHole());
			refreshView();
			this.state = CONTROLLER_STATE.CONTROLLER_HOLE;
		}
	}
	
	public void markBoundary1(){
		if(this.state == CONTROLLER_STATE.CONTROLLER_HOLE){
			this.holePixelsInfo = this.gridAlgorithm.findBoundaryAndHoles();
			this.counterPixelsHistory = new ArrayList<Pixel>();
			for(Pixel p : holePixelsInfo.getBoundaryPixels()){
				this.counterPixelsHistory.add(new Pixel(p.getX(), p.getY(), p.getValue()));
			}
			Grid.getInstance().markBoundary(this.holePixelsInfo.getBoundaryPixels());
			refreshView();
			this.state = CONTROLLER_STATE.CONTROLLER_BOUNDARY;
		}
	}
	
	public void markBoundary(){
		if(this.state == CONTROLLER_STATE.CONTROLLER_HOLE){
			ArrayList<Pixel> boundaryPixels = this.gridAlgorithm2.findBoundary();
			this.counterPixelsHistory = new ArrayList<Pixel>();
			for(Pixel p : boundaryPixels){
				this.counterPixelsHistory.add(new Pixel(p.getX(), p.getY(), p.getValue()));
			}
			Grid.getInstance().markBoundary(boundaryPixels);
			refreshView();
			this.state = CONTROLLER_STATE.CONTROLLER_BOUNDARY;
		}
	}
	public void fillHole(){
		if(this.state == CONTROLLER_STATE.CONTROLLER_HOLE){
			ArrayList<Pixel> boundaryPixels = this.gridAlgorithm2.findBoundary();
			ArrayList<Pixel> holes = this.gridAlgorithm2.findHolePixels(boundaryPixels);
			FillHoleAlgorithmFactory.getAlgorithmByType(A_TYPE.FILL_TYPE_DEFAULT, config).fillHole2(boundaryPixels,holes);
			refreshView();
			this.state = CONTROLLER_STATE.CONTROLLER_NO_HOLE;
		}
	}
	public void fillHole1(){
		if(this.state == CONTROLLER_STATE.CONTROLLER_HOLE){
			this.holePixelsInfo = this.gridAlgorithm.findBoundaryAndHoles();
			FillHoleAlgorithmFactory.getAlgorithmByType(A_TYPE.FILL_TYPE_DEFAULT, config).fillHole(holePixelsInfo);
			refreshView();
			this.state = CONTROLLER_STATE.CONTROLLER_NO_HOLE;
		}
	}
	public void testOtherAlgorithm(){
		long totalDeltaMsec = 0;
		for (int i=0 ; i<30 ; i++){
			makeHole();
			long epoch0 = System.currentTimeMillis();
			fillHole();
			long epoch1 = System.currentTimeMillis();
			totalDeltaMsec += (epoch1 - epoch0);
		}
		System.out.println(totalDeltaMsec);
	}
	public void testMyAlgorithm(){
		long totalDeltaMsec = 0;
		for (int i=0 ; i<30 ; i++){
			makeHole();
			long epoch0 = System.currentTimeMillis();
			fillHole1();
			long epoch1 = System.currentTimeMillis();
			totalDeltaMsec += (epoch1 - epoch0);
		}
		System.out.println(totalDeltaMsec);
		this.panelView.setDurationOfTest(totalDeltaMsec);
	}
}
