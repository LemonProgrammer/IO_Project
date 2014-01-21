package io.view;

import io.controller.IOController;
import io.model.Game;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

import javax.swing.JPanel;
/**
 * The Panel class for the IO Project
 * @author fbla1201
 *
 */
public class IOPanel extends JPanel
{
	/**
	 * reference to the IOController.
	 */
	private IOController baseController;
	/**
	 * The Button for loading the game
	 */
	private JButton loadButton;
	/**
	 * The button for saving the game.
	 */
	private JButton saveButton;
	/**
	 * The title where the user puts the name of the game.
	 */
	private JTextField titleField;
	/**
	 * The label for the title.
	 */
	private JLabel titleLabel;
	/**
	 * The label for the Ranking 
	 */
	private JLabel rankingLabel;
	private JTextArea rulesArea;
	private SpringLayout baseLayout;
	private JLabel rulesLabel;
	private JTextField rankingField;
	private JLabel gameCountLabel;
	
/**
 * The constructor for the IOPanel.
 * @param baseController
 */
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

		gameCountLabel = new JLabel("current game count: ");
		loadButton = new JButton("load Game");
		
		
		
		setupPanel();
		setupLayout();
		setupListeners();
		
	}
	
	/**
	 * The setup for the components to be added to the panel
	 */
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
/**
 * Action listeners for the save and load buttons when they're clicked.
 */
	private void setupListeners()
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
	/**
	 * The layout for all the components on the the GUI Panel
	 */
	private void setupLayout()
	{
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
		baseLayout.putConstraint(SpringLayout.NORTH, loadButton, -1, SpringLayout.NORTH, rankingField);
		baseLayout.putConstraint(SpringLayout.WEST, loadButton, 30, SpringLayout.EAST, rankingField);
	}



	
}
