package com.lt.ex1.view;

import java.awt.*;  
import java.awt.image.BufferedImage;  

import javax.swing.*;  

import org.opencv.core.Mat;  

public class PictureView extends JPanel{   
	
		private static final long serialVersionUID = 1L;
		Mat picture;  
		  BufferedImage buf_image; 
		
		  public PictureView(Mat mat, String fileExt) throws Exception{
		      super(); // Calls the parent constructor
		      
		    //The input image file is not "right" if it has no columns!
		      if(!mat.empty()){
		    	  MatToBufImg converter = new MatToBufImg(mat,fileExt);
		    	  buf_image = converter.getImage();
		      }
		      else throw new Exception();
		  	}
		  
		   //Override default paint
		   public void paintComponent(Graphics g){  
		     g.drawImage(buf_image,50,10,600,600, this);
		  }  

} 