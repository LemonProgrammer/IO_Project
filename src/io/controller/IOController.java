package io.controller;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

import javax.swing.JOptionPane;

import io.model.Game;
import io.view.IOFrame;
/**
 * Controller class for the IO Project.
 * @author fernando blanco
 * @version 1.2 Added a lot of methods and chunks of code to the class files.
 *
 */
public class IOController
{
	private IOFrame	appFrame;
	/**
	 * The ArrayList for the projectGames from the class Game.
	 */
	private ArrayList<Game>	projectGames;
	
	/**
	 * Constructor for the IO Controller.
	 */
	public IOController()
	{
		setProjectGames(new ArrayList<Game>());
	}
	
	/**
	 * The start method for the IO Controller.
	 */
	public void start()
	
	{
		appFrame = new IOFrame(this);
	}
	
	/**
	 * The method that reads the information of the game.
	 * @return
	 */
	public Game readGameInformation()
	{
		String fileName = "save file.txt";
		File currentSaveFile = new File(fileName);
		Scanner fileReader;
		Game currentGame = null;
		int gameRanking = 0;
		String gameTitle = "";
		ArrayList<String> gameRules = new ArrayList<String>();
		
		try
		{
			fileReader = new Scanner(currentSaveFile);
			gameTitle = fileReader.nextLine();
			gameRanking = fileReader.nextInt();
			while(fileReader.hasNext())
			{
				gameRules.add(fileReader.nextLine());
			}
			currentGame = new  Game(gameRules, gameRanking, gameTitle);
			fileReader.close();
		}
		catch(FileNotFoundException currentFileDoesNotExist)
		{
			JOptionPane.showMessageDialog(appFrame, currentFileDoesNotExist.getMessage());
		}
		
		return currentGame;
	}
	
	/**
	 * Method for reading the savefile for the game.
	 * @return
	 */
	private String readAllGameInformation()
	{
		String fileContents = " ";
		String fileName = "save file.txt";
		File currentSaveFile = new File(fileName);
		Scanner fileReader;
		
		try
		{
			fileReader = new Scanner(currentSaveFile);
			while (fileReader.hasNext())
			{
				fileContents += fileReader.nextLine();
				
			}
			fileReader.close();
		} catch (FileNotFoundException fileDeosNotExist)
		{
			JOptionPane.showMessageDialog(appFrame,fileDeosNotExist.getMessage());
		}
		
		return fileContents;
	}
	
	/**
	 * Method for converting the text from the file to strings and integers.
	 * @param currentInfo
	 */
	private void convertTextToGames(String currentInfo)
	{
		String [] gameChunks = currentInfo.split(";");
		
		for(String currentBlock : gameChunks)
		{
			int currentIndex = currentBlock.indexOf("\n");
			String title = currentBlock.substring(0, currentIndex);
			int nextIndex = currentBlock.indexOf("\n", currentIndex);
			String ranking = currentBlock.substring(currentIndex+1, nextIndex);
			String rules = currentBlock.substring(nextIndex+1);
			Game currentGame = makeGameFromInput(title, ranking, rules);
			projectGames.add(currentGame);
		}
	}

	/**
	 * Method that creates the game based on the user's input.
	 * @param gameTitle
	 * @param gameRanking
	 * @param gameRules
	 * @return
	 */
	public Game makeGameFromInput(String gameTitle, String gameRanking, String gameRules)
	{
		Game currentGame = new Game();
		currentGame.setGameTitle(gameTitle);
		if (checkNumberFormat(gameRanking))
		{
			currentGame.setFunRanking(Integer.parseInt(gameRanking));
		}
		else
		{
			return null;
		}
		String [] temp = gameRules.split("\n");
		ArrayList<String> tempRules = new ArrayList<String>();
		
		for(String tempWord : temp)
		{
			tempRules.add(tempWord);
		}
		currentGame.setGameRules(tempRules);
		
		return currentGame;
	}
/**
 * Method that picks a random game from the save info.
 * @return
 */
	public Game pickRandomGameFromSaveFile()
	{
		Game currentGame = null;
		
		String allInfo = readAllGameInformation();
		convertTextToGames(allInfo);
		int randomIndex = (int) (Math.random()* (double) projectGames.size());
		currentGame = projectGames.get(randomIndex);
		
		return currentGame;
	}
	
	private boolean checkNumberFormat(String toBeParsed)
	{
		boolean isNumber = false;
		
		try
		{
			int valid = Integer.parseInt(toBeParsed);
			isNumber = true;
		}
		catch(NumberFormatException error)
		{
			JOptionPane.showMessageDialog(appFrame, "Please try again with an actual number for the ranking.");
		}
		return isNumber;
	}
	
	public void saveGameInformation(Game currentGame)
	{
		PrintWriter gameWriter;
		String saveFile = "save file.txt";
		
		try
		{
			gameWriter = new PrintWriter(saveFile); // Creates the save file
			
			gameWriter.println(currentGame.getGameTitle());
			gameWriter.println(currentGame.getFunRanking());
			for (int count = 0; count < currentGame.getGameRules().size(); count++)
			{
				gameWriter.println(currentGame.getGameRules().get(count));
				
			}
			gameWriter.close();
		}
		
		catch (FileNotFoundException noFilesExists)
		{
			JOptionPane.showMessageDialog(appFrame,
					"Sorry, file could not be found.");
			JOptionPane.showMessageDialog(appFrame, noFilesExists.getMessage());
		}
	}
	
	/**
	 * @return the projectGames
	 */
	public ArrayList<Game> getProjectGames()
	{
		return projectGames;
	}
	
	/**
	 * @param projectGames
	 *            the projectGames to set
	 */
	public void setProjectGames(ArrayList<Game> projectGames)
	{
		this.projectGames = projectGames;
	}
}
