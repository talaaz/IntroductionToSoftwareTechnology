package application;

import java.util.ArrayList;
import java.util.Random;

public class GameModel {
	
	public ArrayList<Point> snake = new ArrayList<Point>(), obstacle = new ArrayList<Point>();
	private Point snakeTail, apple, bomb;
	private int numberOfObstacles, randomX, randomY, w, h;
	public int speed; // public so graphics can change speed
	private Difficulty level;
	
	// Spawn snake and dot inside grid
	public GameModel(int width, int height, Difficulty level) {
		w = width; 
		h= height;
		this.level = level;
		
		startSpeed();
		setNumberOfObstacles();
		
		snake = makeSnake(new Point(w / 2, h / 2));
		obstacle = makeObstacle(numberOfObstacles);
		makeApple();
		checkApple();
	}
	
	// Create the snake ArrayList
	public ArrayList<Point> makeSnake(Point snakeHead) {
		snake.add(snakeHead);
		snakeTail = new Point(snakeHead.getX() + 1, snakeHead.getY());
		snake.add(snakeTail);
		// the tail will now be behind the head, since the head is facing left (Check GameController)
		
		return snake;
	}
	
	public void startSpeed() {
		if (level == Difficulty.EASY) {
			speed = 5;
		} else if (level == Difficulty.NORMAL) {
			speed = 5;
		} else {
			speed = 6;
		}
	}
	
	public void setNumberOfObstacles() {
		if (level == Difficulty.EASY) {
			numberOfObstacles = (w*h) / 100;
		} else if (level == Difficulty.NORMAL) {
			numberOfObstacles = (w*h) / 50;
		} else {
			numberOfObstacles = (w*h) / 33;
		}
	}
	
	public double getScoreMultiplier() {
		if (level == Difficulty.EASY) {
			return 1;
		} else if (level == Difficulty.NORMAL) {
			return 1.5;
		} else {
			return 2;
		}
	}
	
	public int speedAdder() {
		if (level == Difficulty.HARD) {
			return 4;
		} else if (level == Difficulty.NORMAL) {
			return 6;
		} else {
			return 10;
		}
	}
	
	// Creates a point in random position
	public Point makePoint() {
		Random r = new Random();
		randomX = r.nextInt(w);
		randomY = r.nextInt(h);
		
		return new Point(randomX, randomY);
	}
	
	public void makeApple() {
		apple = makePoint();
	}
	
	public void checkApple() {
		if (obstacle.contains(apple)) {
			apple = makePoint();
			checkApple();
		}
	}
	
	public Point getApple() {
		return apple;
	}
	
	
	public ArrayList<Point> makeObstacle(int amount) {
		for (int i = 0; i < amount; i++) {
			bomb = makePoint();
			if (snake.contains(bomb)) {
				makeObstacle(amount - i);
			}
			obstacle.add(bomb);
		}
		return obstacle;
	}
}