package com.lt.ex1.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSeparator;

import com.lt.ex1.main.MainController;

public class FrameView extends JFrame{
	private static final long serialVersionUID = 1L;
	
	private JButton resetButton;
	private JButton markBoundaryBtn;
	private JButton applyWBtn;
	private JButton markBoundaryMyBtn;
	private JButton fillHoleMyBtn;
	private JButton newImageBtn;
	private JButton makeHoleBtn;
	private JButton testMyAlgorithmBtn;
	private JButton testOtherAlgorithmBtn;
	private JPanel btnPanel;
	private JLabel durationOfTest;
	private PictureView image;
	private MainController controller;
	public FrameView(PictureView image,MainController controller)  {
		super();
		this.setSize(1200,900);  
		this.setLayout(new BorderLayout());
		this.setVisible(true);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.controller = controller;
		this.image = image;
		if(this.image!= null){
			this.add(this.image,BorderLayout.CENTER);
		}
		durationOfTest = new JLabel();
		durationOfTest.setText("Time in mSec");
		durationOfTest.setVisible(true);
   	 	btnPanel = new JPanel();
   	 	btnPanel.setLayout(new BoxLayout(btnPanel, BoxLayout.Y_AXIS));
   	 	applyWBtn = new JButton("fill hole");
   	 	applyWBtn.addActionListener (new ActionFillHole(controller));
   	 	makeHoleBtn = new JButton("make hole");
   	 	makeHoleBtn.addActionListener (new ActionMakeHole(controller));
   	 	newImageBtn = new JButton("new image");
	 	newImageBtn.addActionListener (new ActionNewImage(controller));
   	 	resetButton = new JButton("delete hole boundary");
   	 	resetButton.addActionListener (new ActionResetImage(controller));
   	 	markBoundaryBtn = new JButton("mark hole boundary");
   	 	markBoundaryBtn.addActionListener (new ActionMarkCounter(this.controller));
   	 	
   	 	markBoundaryMyBtn = new JButton("mark hole boundary my algorithm");
   	 	markBoundaryMyBtn.addActionListener (new ActionMarkBoundaryMine(this.controller));
   	 	fillHoleMyBtn = new JButton("fill hole boundary my algorithm");
   	 	fillHoleMyBtn.addActionListener (new ActionFillHoleMine(this.controller));
	 	
   	 	
   	 
   	 	testMyAlgorithmBtn = new JButton("test my algorithm");
   	 	testMyAlgorithmBtn.addActionListener (new ActionTestMyAlgorithm(this.controller));
   	 	testOtherAlgorithmBtn = new JButton("test question4 algorithm");
   	 	testOtherAlgorithmBtn.addActionListener (new ActionTestMyAlgorithm(this.controller));
	 	
   	 	btnPanel.setBackground(Color.WHITE);
   	 	btnPanel.add(new JSeparator());
   	 	btnPanel.add(newImageBtn);
   	 	btnPanel.add(makeHoleBtn);
   	 	btnPanel.add(resetButton);
   	 	btnPanel.add(new JSeparator());
   	 	btnPanel.add(markBoundaryBtn);
   	 	btnPanel.add(applyWBtn);
   	 	btnPanel.add(new JSeparator());
   	 	btnPanel.add(markBoundaryMyBtn);
   	 	btnPanel.add(fillHoleMyBtn);
   	 	btnPanel.add(new JSeparator());
   	 	btnPanel.add(testMyAlgorithmBtn);
   	 	btnPanel.add(testOtherAlgorithmBtn);
   	 	btnPanel.add(durationOfTest);
   	 	this.add(btnPanel,BorderLayout.EAST);
	}
 
	public JLabel getDurationOfTest() {
		return durationOfTest;
	}

	public void setDurationOfTest(long durationOfTest) {
		String tmp = this.durationOfTest.getText();
		this.durationOfTest.setText(tmp + ":"  + String.valueOf(durationOfTest));
		this.setVisible(true);
	}

	public void refreshImage(PictureView image){
		this.remove(this.image);
		this.image = image;
		this.add(this.image,BorderLayout.CENTER);
	}
    
    static class ActionMakeHole implements ActionListener {
		private MainController controller;
		public ActionMakeHole(MainController controller){
			this.controller= controller;
		}
		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			this.controller.makeHole();
		}
	}
    static class ActionFillHole implements ActionListener {
		private MainController controller;
		public ActionFillHole(MainController controller){
			this.controller= controller;
		}
		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			this.controller.fillHole();
		}
	}
	  static class ActionFillHoleMine implements ActionListener {
			private MainController controller;
			public ActionFillHoleMine(MainController controller){
				this.controller= controller;
			}
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				this.controller.fillHole1();
			}
		}
	static class ActionNewImage implements ActionListener {
		private MainController controller;
		public ActionNewImage(MainController controller){
			this.controller= controller;
		}
		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			this.controller.start();
		}

	}
	static class ActionMarkCounter implements ActionListener {
		private MainController controller;
		public ActionMarkCounter(MainController controller){
			this.controller= controller;
		}
		@Override
		public void actionPerformed(ActionEvent e) {
			this.controller.markBoundary();
		}

	}
	static class ActionMarkBoundaryMine implements ActionListener {
		private MainController controller;
		public ActionMarkBoundaryMine(MainController controller){
			this.controller= controller;
		}
		@Override
		public void actionPerformed(ActionEvent e) {
			this.controller.markBoundary1();
		}

	}
	static class ActionTestMyAlgorithm implements ActionListener {
		private MainController controller;
		public ActionTestMyAlgorithm(MainController controller){
			this.controller= controller;
		}
		@Override
		public void actionPerformed(ActionEvent e) {
			this.controller.testMyAlgorithm();
		}

	}
	static class ActionTestOtherAlgorithm implements ActionListener {
		private MainController controller;
		public ActionTestOtherAlgorithm(MainController controller){
			this.controller= controller;
		}
		@Override
		public void actionPerformed(ActionEvent e) {
			this.controller.testOtherAlgorithm();
		}

	}
	static class ActionResetImage implements ActionListener {
		private MainController controller;
		public ActionResetImage(MainController controller){
			this.controller= controller;
		}
		@Override
		public void actionPerformed(ActionEvent e) {
			this.controller.resetHoleBoundary();
		}

	}
}
