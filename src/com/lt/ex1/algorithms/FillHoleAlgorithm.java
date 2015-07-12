package com.lt.ex1.algorithms;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import com.lt.ex1.model.Grid;
import com.lt.ex1.model.Pixel;
import com.lt.ex1.utils.Config;
import com.lt.ex1.utils.HolePixelsInfo;

public abstract class FillHoleAlgorithm {
	public enum A_TYPE {
		FILL_TYPE_INVALID, FILL_TYPE_DEFAULT
	}
	protected abstract float w(Pixel boundaryPixel, Pixel pixelInHole);
    private Config config;
   
    public FillHoleAlgorithm(Config config) {
        this.config=config;
    }
    
    public Config getConfig() {
		return config;
	}

	public void setConfig(Config config) {
		this.config = config;
		
	}

    public void fillHole(HolePixelsInfo holePixelsInfo){
		Iterator<Entry<String, Pixel>> it = holePixelsInfo.getHolePixelsMap().entrySet().iterator();
	    float pixelValue = 0;
		while (it.hasNext()) {
	    	Map.Entry<String, Pixel> pair = (Map.Entry<String, Pixel>)it.next();
	    	pixelValue = calculateHoleValue(Grid.getInstance(), holePixelsInfo.getBoundaryPixels(), (Pixel)pair.getValue());
	    	((Pixel)pair.getValue()).setValue(pixelValue);
	    }
	} 
    public void fillHole2(ArrayList<Pixel> boundaryPixels,ArrayList<Pixel> holes){
	    float pixelValue = 0;
		for (int i = 0; i<holes.size();i++){
	    	pixelValue = calculateHoleValue(Grid.getInstance(), boundaryPixels, holes.get(i));
	    	holes.get(i).setValue(pixelValue);
	    }
	} 
    public float calculateHoleValue(Grid grid, ArrayList<Pixel> boundaryPixels,  Pixel pixelInHole){
    	float nom = 0;
    	float denom = 0;
    	for(Pixel boundaryPixel:boundaryPixels ){
    		nom += boundaryPixel.getValue() *w(boundaryPixel,pixelInHole);
    		denom += w(boundaryPixel,pixelInHole);
    	}
    	return(nom/denom);
    }
    
}
