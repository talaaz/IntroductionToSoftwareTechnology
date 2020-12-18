package application;

import java.util.ArrayList;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.stage.Stage;

public class GameController {
	
	public GameModel GM;
	public Graphics graphics;
	public Main main;
	public Stage gameStage;
	public Direction direction = Direction.LEFT;
	private Difficulty level;
	public int score, counter, blockAmount;

	// Sets Game stage
	public GameController(Stage gameStage, Main main, Difficulty level, int blockAmount) {
		this.main = main;	
		this.level = level;
		this.blockAmount = blockAmount;
		this.gameStage = gameStage;
		
		GM = new GameModel(blockAmount, blockAmount, level);
		
		
		// Start drawing
		graphics = new Graphics(gameStage, this);
		graphics.startGame(gameStage);	
	}
	
	// Convert ArrayList of Points to Points -> use drawTile method
	public void drawList(ArrayList<Point> list, Item item) {
		for (Point part: list) {
			graphics.drawTile(part, item);
		}
	}
	
	
	// Convert Point to integer that matches the index of the ArrayList of StackPanes
	public int convert(Point p) {
		return p.getX() + p.getY() * blockAmount;
	}
	
	// Establishes key-bindings for snakes movement
	public void addKeyHandler(Scene scene) {
		scene.setOnKeyPressed(e -> {
			if (graphics.alive) {
				// Ensures direction can't go backwards.
					if (e.getCode() == KeyCode.UP || e.getCode() == KeyCode.W) {
						if (direction != Direction.DOWN) {
							direction = Direction.UP;
						}
					}
					else if (e.getCode() == KeyCode.DOWN || e.getCode() == KeyCode.S) {
						if (direction != Direction.UP) {
							direction = Direction.DOWN;
						}
					}
					else if (e.getCode() == KeyCode.LEFT || e.getCode() == KeyCode.A) {
						if (direction != Direction.RIGHT) {
							direction = Direction.LEFT;
						}
					}
					else if (e.getCode() == KeyCode.RIGHT || e.getCode() == KeyCode.D) {
						if (direction != Direction.LEFT) {
							direction = Direction.RIGHT;
						}
					}
					else if (e.getCode() == KeyCode.ESCAPE) {	
						graphics.timer.stop();
						Stage stage1 = new Stage();
						new Exit(stage1, main, this);
						}
					}			
			// Ensures double press will not change direction to the opposite side
			graphics.alive = false;
		});
	}
	
	public void move() {
		// Snake Movement
		if (!graphics.alive) {
			return;
		}
		if (graphics.alive) {	
		// Gets previous head and tail position
		Point newPoint = GM.snake.get(0);
		Point lastPoint = GM.snake.get(GM.snake.size()-1);
		
		switch (direction) {
			/* Case switches depending on KeyPress. Makes new point with increment or decrement
			of either x or y coordinates depending on direction. */
			case UP:
				newPoint = new Point(newPoint.getX(), newPoint.getY() - 1);
				break;
			case DOWN:
				newPoint = new Point(newPoint.getX(), newPoint.getY() + 1);
				break;
			case LEFT:
				newPoint = new Point(newPoint.getX() - 1, newPoint.getY());
				break;
			case RIGHT:
				newPoint = new Point(newPoint.getX() + 1, newPoint.getY());
				break;
			}
		// Adds new point from direction
		GM.snake.add(0, newPoint);
			
		if(level == Difficulty.EASY) {
			noWallCollision(newPoint);
		} 
		else {
			wallCollision(newPoint);
		}
		
		// Now draws the new points and remove last point in snake ArrayList
		graphics.drawTile(lastPoint, Item.GRID);
		graphics.drawTile(newPoint, Item.SNAKE);
		GM.snake.remove(GM.snake.size()-1);
		
		checkAndAdd(newPoint);
		checkAndReset(newPoint);
		}
	}
	
	public void checkAndAdd(Point newPoint) {
		// Collision with apple, add new point to the snake ArrayList. Spawn a new apple
		if (newPoint.equals(GM.getApple())) {
			GM.snake.add(0, newPoint);
			graphics.drawTile(newPoint, Item.SNAKE);
			GM.makeApple();
			counter++;
			if (counter % GM.speedAdder() == 0) {
				GM.speed++;
				graphics.speedLabel.setText("Current Speed: " + GM.speed);
			}
			score += (double) (100 * (1* GM.getScoreMultiplier()));
			graphics.scoreLabel.setText("Score : " + score);
			}
		// Ensures the apple cannot spawn inside the snake.
		if (GM.snake.contains(GM.getApple())) {
			GM.makeApple();
			graphics.drawTile(GM.getApple(), Item.SNAKE);
			} else if (GM.obstacle.contains(GM.getApple())) {
				GM.makeApple();
			}
			
		// Now draw the apple tile after checking through all the conditions above
		graphics.drawTile(GM.getApple(), Item.APPLE);

	}
	
	public void checkAndReset(Point newPoint) {
		// Ends game if snake collides with itself
		for(int i = 2; i <= GM.snake.size()-1; i++) {
			if(GM.snake.get(i).equals(newPoint)) {
				stopGame();
				break;
			}
		}
		// Ends game if collision with obstacles
		for (Point o: GM.obstacle) {
			if (GM.snake.contains(o)) {
				stopGame();	
			}
		}
	}
	
	public void stopGame() {
		/* try {
			new HighScore(score);
		} catch (IOException e) {
			e.printStackTrace();
		} */
		graphics.alive = false;
		graphics.timer.stop();
		main.startMenu(gameStage);
	}
	
	// If out of boundaries = end game.
	public void wallCollision(Point newPoint) {
		if (newPoint.getX() < 0) {
			stopGame();
			}
		if (newPoint.getX() > blockAmount - 1) {
			stopGame();
			}
		if (newPoint.getY() < 0) {
			newPoint.setY(blockAmount - 1);
			if (newPoint.getY() == blockAmount -1) {
				stopGame();
			}
		}
		if (newPoint.getY() > blockAmount - 1) {
			newPoint.setY(0);
			if (newPoint.getY() == 0) {
				stopGame();			
			}
		}
	}
	
	// If out of boundaries, make new point on opposite side.
	public void noWallCollision(Point newPoint) {
		if (newPoint.getX() < 0) {
			newPoint.setX(blockAmount - 1);
			}
		if (newPoint.getX() > blockAmount - 1) {
			newPoint.setX(0);
			}
		if (newPoint.getY() < 0) {
			newPoint.setY(blockAmount - 1);
			}
		if (newPoint.getY() > blockAmount -1) {
			newPoint.setY(0);
		}
	}
}