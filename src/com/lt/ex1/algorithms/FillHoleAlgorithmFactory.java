package com.lt.ex1.algorithms;

import com.lt.ex1.algorithms.FillHoleAlgorithm.A_TYPE;
import com.lt.ex1.utils.Config;

public class FillHoleAlgorithmFactory {
	public static FillHoleAlgorithm getAlgorithmByType(A_TYPE type,Config config) {
		FillHoleAlgorithm fillHoleAlgorithm = null;
        switch (type) {
        case FILL_TYPE_DEFAULT:
        	fillHoleAlgorithm = new DefaultFillAlgorithm(config);
            break;
        default:
            // throw some exception since we dont have other algo'
            break;
        }
        return fillHoleAlgorithm;
    }
}
