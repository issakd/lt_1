package com.lt.ex1.algorithms;

import com.lt.ex1.model.Pixel;
import com.lt.ex1.utils.Config;

public class DefaultFillAlgorithm extends FillHoleAlgorithm{

	public DefaultFillAlgorithm(Config config) {
		super(config);
	}

	@Override
	protected float w(Pixel boundaryPixel, Pixel pixelInHole) {
		Config config = super.getConfig();
		double distanceBetweenPixels = boundaryPixel.distance(pixelInHole);
		double denominator = (Math.pow(distanceBetweenPixels, config.getZ())) + config.getEpsilon();
		return((float)(1/denominator));
	}

}
