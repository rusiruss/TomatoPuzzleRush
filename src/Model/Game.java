package Model;

import java.awt.image.BufferedImage;

public class Game {
	
	BufferedImage image = null; 	
	int solution ;
	
	/**
	 * Image of the game and what is the solution to the game.
	 * @param image
	 * @param solution
	 */
	public Game(BufferedImage image, int solution) {
		super();
		this.image = image;
		this.solution = solution;
	}
	
	/**
	 * The image of the game. 
	 * @return the location of the game.
	 */
	public BufferedImage getImage() {
		return image;
	}

	/**
	 * @return The solution of the game.
	 */
	public int getSolution() {
		return solution;
	}
	
	
	
	

}
