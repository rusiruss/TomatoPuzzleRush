package Controller;

import java.awt.image.BufferedImage;

import Model.Game;
import Model.SoundPlayer;


public class Engine {
	String thePlayer = null;

	/**
	 * Each player has their own game engine.
	 * 
	 * @param player
	 */
	public Engine(String player) {
		thePlayer = player;
	}

	int counter = 0;
	int score = 0;
	int level =1;
	
	Server theGames = new Server();
	Game current = null;

	/**
	 * Retrieves a game. This basic version only has two games that alternate.
	 */
	public BufferedImage nextGame() {
		current = theGames.getRandomGame();
		return current.getImage();

	}

	/**
	 * Checks if the parameter i is a solution to the game URL. If so, score is
	 * increased by one.
	 * 
	 * @param game
	 * @param i
	 * @return
	 */
	public boolean checkSolution( int i) {
		if (i == current.getSolution()) {
			score=score+10;
			SoundPlayer.getInstance().playSoundMarks();
			if(score % 50 == 0) {
				level++;	
			}
			return true;
		} else {
			return false;
		}
		
		
	}

	/**
	 * Retrieves the score.
	 * 
	 * @param player
	 * @return
	 */
	public int getScore() {
		return score;
	}
	
	public int getlevel() {
		return level;
	}


}
