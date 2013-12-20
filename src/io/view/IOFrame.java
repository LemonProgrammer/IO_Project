package io.view;

import io.controller.IOController;

import javax.swing.JFrame;

public class IOFrame extends JFrame
{
	
	private IOPanel basePanel;
	private IOController baseController;
	
	public IOFrame(IOController baseController)
	{
		basePanel = new IOPanel(baseController);
		
		setupFrame();
	}

	private void setupFrame()
	{
		this.setContentPane(basePanel);
		this.setTitle("IO Project");
		this.setSize(400, 400);
		this.setVisible(true);
		
	}
}
