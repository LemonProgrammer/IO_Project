package io.model;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.swing.JOptionPane;

public class Game
{
	private ArrayList<String> gameRules;
	private int funRanking;
	private String gameTitle;
	
	public Game()
	{
		gameRules = new ArrayList<String>();
		funRanking = 0;
		gameTitle = " ";
	}
	
	public Game(ArrayList<String> gameRules, int funRanking, String gameTitle)
	{
		this.gameRules = gameRules;
		this.funRanking = funRanking;
		this.gameTitle = gameTitle;
	}

	/**
	 * @return the gameRules
	 */
	public ArrayList<String> getGameRules()
	{
		return gameRules;
	}

	/**
	 * @param gameRules the gameRules to set
	 */
	public void setGameRules(ArrayList<String> gameRules)
	{
		this.gameRules = gameRules;
	}

	/**
	 * @return the funRanking
	 */
	public int getFunRanking()
	{
		return funRanking;
	}

	/**
	 * @param funRanking the funRanking to set
	 */
	public void setFunRanking(int funRanking)
	{
		this.funRanking = funRanking;
	}

	/**
	 * @return the gameTitle
	 */
	public String getGameTitle()
	{
		return gameTitle;
	}

	/**
	 * @param gameTitle the gameTitle to set
	 */
	public void setGameTitle(String gameTitle)
	{
		this.gameTitle = gameTitle;
	}
	
	
}
