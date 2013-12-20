package io.view;

import io.controller.IOController;
import io.model.Game;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

import javax.swing.JPanel;

public class IOPanel extends JPanel
{
	
	private IOController baseController;
	private JButton loadButton;
	private JButton saveButton;
	private JTextField titleField;
	private JLabel titleLabel;
	private JLabel rankingLabel;
	private JTextArea rulesArea;
	private SpringLayout baseLayout;
	private JLabel rulesLabel;
	private JTextField rankingField;
	private JLabel gameCountLabel;
	

	public IOPanel(IOController baseController)
	{
		this.baseController = baseController;
		
		saveButton = new JButton("save the game stuff");
		titleField = new JTextField(15);
		titleLabel = new JLabel("Game Title: ");
		rankingField = new JTextField(5);
		rankingLabel = new JLabel("Game Ranking: ");
		rulesArea = new JTextArea(5, 20);
		rulesLabel = new JLabel("Game Rules: ");
		baseLayout = new SpringLayout();
		baseLayout.putConstraint(SpringLayout.NORTH, saveButton, 6, SpringLayout.SOUTH, rulesArea);
		baseLayout.putConstraint(SpringLayout.WEST, saveButton, 0, SpringLayout.WEST, rankingField);
		baseLayout.putConstraint(SpringLayout.NORTH, titleLabel, 44, SpringLayout.NORTH, this);
		baseLayout.putConstraint(SpringLayout.NORTH, titleField, -3, SpringLayout.NORTH, titleLabel);
		baseLayout.putConstraint(SpringLayout.WEST, titleField, 0, SpringLayout.WEST, rankingField);
		baseLayout.putConstraint(SpringLayout.EAST, titleLabel, -311, SpringLayout.EAST, this);
		baseLayout.putConstraint(SpringLayout.EAST, rankingLabel, -314, SpringLayout.EAST, this);
		baseLayout.putConstraint(SpringLayout.NORTH, rankingLabel, 25, SpringLayout.SOUTH, titleLabel);
		baseLayout.putConstraint(SpringLayout.NORTH, rankingField, -3, SpringLayout.NORTH, rankingLabel);
		baseLayout.putConstraint(SpringLayout.WEST, rankingField, 6, SpringLayout.EAST, rankingLabel);
		baseLayout.putConstraint(SpringLayout.NORTH, rulesLabel, 30, SpringLayout.SOUTH, rankingLabel);
		baseLayout.putConstraint(SpringLayout.NORTH, rulesArea, -5, SpringLayout.NORTH, rulesLabel);
		baseLayout.putConstraint(SpringLayout.WEST, rulesArea, 21, SpringLayout.EAST, rulesLabel);
		baseLayout.putConstraint(SpringLayout.WEST, rulesLabel, 0, SpringLayout.WEST, rankingLabel);
		gameCountLabel = new JLabel("current game count: ");
		loadButton = new JButton("load Game");
		baseLayout.putConstraint(SpringLayout.NORTH, loadButton, -1, SpringLayout.NORTH, rankingField);
		baseLayout.putConstraint(SpringLayout.WEST, loadButton, 30, SpringLayout.EAST, rankingField);
		
		
		setupPanel();
		setupLayout();
		setupListeneres();
		
	}
	
	
	private void setupPanel()
	{
		this.setLayout(baseLayout);
		this.setBackground(Color.RED);
		this.add(rankingField);
		this.add(rankingLabel);
		this.add(rulesArea);
		this.add(rulesLabel);
		this.add(saveButton);
		this.add(titleField);
		this.add(titleLabel);
		this.add(loadButton);
		
	}


	private void setupListeneres()
	{
		saveButton.addActionListener(new ActionListener()
		{
			public void actionPerformed (ActionEvent click)
			{
				Game tempGame = baseController.makeGameFromInput(titleField.getText(), rankingField.getText(), rulesArea.getText());
				if (tempGame != null)
				{
					baseController.saveGameInformation(tempGame);
					gameCountLabel.setText("current game coutn: " + baseController.getProjectGames().size());
				}
				else
				{
					JOptionPane.showMessageDialog(null, "Try again with a valid number");
				}
			}
		});
		
		loadButton.addActionListener(new ActionListener()
		{
			public void actionPerformed (ActionEvent click)
			{
				Game tempGame = baseController.readGameInformation();
				if( tempGame != null)
				{
					titleField.setText(tempGame.getGameTitle());
					rankingField.setText(Integer.toString(tempGame.getFunRanking()));
					String tempRules = " ";
					for(String currentRule : tempGame.getGameRules())
					{
						tempRules += currentRule + "\n";
					}
					rulesArea.setText(tempRules);
					
				}
				else
				{
					JOptionPane.showMessageDialog(null, "Check the save file make sure it is in order. " );
				}
			}
		});
		
	}


	

	private void setupLayout()
	{
		
	}



	
}
