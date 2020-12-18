package application;

import java.util.ArrayList;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class GameController {
	
	public GameModel GM;
	public Graphics graphics;
	public Point snakePart;
	public Main main;
	private Stage gameStage;
	private Direction direction = Direction.LEFT;

	// Sets Game stage
	public GameController(Stage gameStage, Main main) {
		this.main = main;
		this.gameStage = gameStage;
		GM = new GameModel(Main.WIDTH, Main.HEIGHT);
		
		// Start drawing
		graphics = new Graphics(gameStage, this);
		graphics.startGame(gameStage);	
	}
	
	// Convert ArrayList of Points to Points -> use drawTile method
	public void drawSnake(ArrayList<Point> wholeSnake) {
		for (Point snakePart: wholeSnake) {
			graphics.drawTile(snakePart, Item.SNAKE);
		}
	}
	
	// Convert Point to integer that matches the index of the ArrayList of StackPanes
	public int convert(Point p) {
		return p.getX() + p.getY() * Main.WIDTH;
	}
	
	// Establishes key-bindings for snakes movement
	public void addKeyHandler(Scene scene) {
		scene.setOnKeyPressed(e -> {
			 {
				Point newPoint = GM.snake.get(0);
				// Ensures direction can't go backwards.
				switch(e.getCode()) {
				case W: 
						if (direction == Direction.DOWN) {
						break;
						} else if (direction != Direction.DOWN) {
							newPoint = new Point(newPoint.getX(), newPoint.getY() - 1);
							checkAndAdd(newPoint);
							direction = Direction.UP;
							break;
						}
				case S: {
						if (direction == Direction.UP) {
							break;
						} else if (direction != Direction.UP) {
							newPoint = new Point(newPoint.getX(), newPoint.getY() + 1);
							checkAndAdd(newPoint);
							direction = Direction.DOWN;
							break;
						}
					}
				case A: {
						if (direction == Direction.RIGHT) {
						break;
						} else if (direction != Direction.RIGHT) {
							newPoint = new Point(newPoint.getX() - 1, newPoint.getY());
							checkAndAdd(newPoint);
							direction = Direction.LEFT;
							break;
						}
					}
				case D: {
						if (direction == Direction.LEFT) {
						break;
						} else if (direction != Direction.LEFT) {
							newPoint = new Point(newPoint.getX() + 1, newPoint.getY());
							checkAndAdd(newPoint);
							direction = Direction.RIGHT;
							break;
						} 
					}
				default:
					break;
					}
					if (newPoint.getX() < 0) {
						newPoint.setX(Main.WIDTH - 1);
						}
					if (newPoint.getX() > Main.WIDTH - 1) {
						newPoint.setX(0);
						}
					if (newPoint.getY() < 0) {
						newPoint.setY(Main.HEIGHT - 1);
						}
					if (newPoint.getY() > Main.HEIGHT -1) {
						newPoint.setY(0);
						}				
					if (!newPoint.equals(GM.snake.get(0))) {
					checkAndReset(newPoint);
					}
				}
		});
	}
	
	public void checkAndAdd(Point newPoint) {
		// Collision with dot, add new point to the snake ArrayList, gives player a point. Spawn a new dot
		if (newPoint.equals(GM.getDot())) {
			GM.snake.add(0, newPoint);
			graphics.score++;
			graphics.scoreLabel.setText("Points : " + graphics.score);
			graphics.drawTile(newPoint, Item.SNAKE);
			GM.makeDot();
			}
		// Ensures the dot cannot spawn inside the snake.
		if (GM.snake.contains(GM.getDot())) {
			GM.makeDot();
			graphics.drawTile(GM.getDot(), Item.SNAKE);
			}
			
		// Now draw the dot tile after checking through all the conditions above
		graphics.drawTile(GM.getDot(), Item.DOT);
	}
	
	// Ends game if snake collides with itself
	public void checkAndReset(Point newPoint) {
		Point lastPoint = GM.snake.get(GM.snake.size()-1);
		GM.snake.add(0, newPoint);
		graphics.drawTile(lastPoint, Item.GRID);
		graphics.drawTile(newPoint, Item.SNAKE);
		GM.snake.remove(GM.snake.size()-1);
		for(int i = 2; i <= GM.snake.size()-1; i++) {
			if(GM.snake.get(i).equals(newPoint)) {
				new GameController(gameStage, main);
				break;
			}
		}
	}
}
