package application;

import java.util.ArrayList;
import java.util.Random;

public class GameModel {
	
	private int w,h;  
	public ArrayList<Point> snake = new ArrayList<Point>();
	private Point snakeTail, dot;
	
	// Spawn snake and dot inside grid
	public GameModel(int width, int height) {
		w = width; 
		h= height;
		
		snake = makeSnake(new Point(w / 2, h / 2));
		makeDot();
		if (snake.contains(dot)) {
			new GameModel(w, h);
		}
	}
	
	// Create the snake ArrayList
	public ArrayList<Point> makeSnake(Point snakeHead) {
		snake.add(snakeHead);
		snakeTail = new Point(snakeHead.getX() + 1, snakeHead.getY());
		snake.add(snakeTail);
		// the tail will now be behind the head, since the head is facing left (Check GameController)
		
		return snake;
	}
	
	// Creates a dot in random position
	public void makeDot() {
		Random r = new Random();
		int randomX = r.nextInt(w);
		int randomY = r.nextInt(h);
		
		dot = new Point(randomX, randomY);
		
		if (snake.contains(dot)) {
			dot = new Point(randomX, randomY);
		}
	}
	
	public Point getDot() {
		return dot;
	}
}
