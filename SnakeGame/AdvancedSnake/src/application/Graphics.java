package application;

import java.util.ArrayList;

import javafx.animation.AnimationTimer;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class Graphics {  
	long then = System.nanoTime();
	long starting = 0;
	int minutes, size;
	int seconds = -2;
	int counter = 2;
	Scene  gameScene;
	GridPane startGridPane;
	BorderPane startLayout;
	GameModel GM;
	GameController GC;
	Image grassImage, bombImage, appleImage;
	StackPane SP, item;
	Stage stage;
	Label scoreLabel, timerLabel, speedLabel;
	AnimationTimer timer;
	Boolean alive = false;
	ArrayList<StackPane> spList = new ArrayList<>();
	ArrayList<StackPane> itemList = new ArrayList<>(); 
	
	// Takes information from arguments to make methods
	public Graphics(Stage gameStage, GameController GC) {
		GM = GC.GM;
		this.GC = GC;
    	size = 400/GC.blockAmount;
    	grassImage = new Image(getClass().getResourceAsStream("/images/grass.jpg"), size, size, false, false);
    	bombImage = new Image(getClass().getResourceAsStream("/images/bomb.png"), size, size, false, false);
    	appleImage = new Image(getClass().getResourceAsStream("/images/apple.png"), size, size, false, false);
    }
	
	// Makes size of , initiate all the methods
	public void startGame(Stage gameStage) {
		try {
			gameStage.setTitle("Snake Game");
			alive = true;

			createLayout();
			makeLabels();
			addGridToLayout();
			makeGrid();
			startGraphic();
			startTime();
			makeScene(gameStage, startLayout);			
		} catch(Exception e) {
			e.printStackTrace();
			}
		}
	
	// Start animation, update graphic based on frame.
	public void startTime() {
		if (alive) {	
			timer = new AnimationTimer() {
				public void handle (long now) {
					if(now - then > (1000000000 / GM.speed)) {					
						starting++;	
						if (starting % GM.speed == 0) {
							seconds++;
							counter--;
							if (seconds < 0) {
							timerLabel.setText("Timer : -" + minutes + ":0" + counter);	
							}
							else if (seconds < 10 && seconds >= 0) {
							timerLabel.setText("Timer : " + minutes + ":0" + seconds);	
							} 
							else {
							timerLabel.setText("Timer : " + minutes + ":" + seconds);
							}
							if (seconds == 60) {
							minutes++;
							seconds = 0;
							timerLabel.setText("Timer : " + minutes + ":0" + seconds);	
						}
					}
					if (starting >= 2 * GM.speed) {
						updateGraphic();
						counter++;			
					}
					then=now;
					}
				}
		};}; timer.start();
	}
	
	public void createLayout() {
		startGridPane = new GridPane();
		startLayout = new BorderPane();
	}
	
	public void makeLabels() {
		scoreLabel = new Label();
		scoreLabel.setText("Score : " + GC.score);
		scoreLabel.setPadding(new Insets(9,14,9,14));
		startLayout.setBottom(scoreLabel);
		
		timerLabel = new Label();
		timerLabel.setText("Timer : -0:0" + counter);
		timerLabel.setPadding(new Insets(9, 14, 9, 14));
		timerLabel.setTranslateY(430);
		timerLabel.setTranslateX(-100);
		startLayout.setRight(timerLabel);
	
		speedLabel = new Label();
		speedLabel.setText("Current Speed: " + GM.speed);
		speedLabel.setPadding(new Insets(9,14,9,14));
		speedLabel.setTranslateX(170);
		startLayout.setTop(speedLabel);
	}
	
	public void addGridToLayout() {
		startLayout.setCenter(startGridPane); 
		startGridPane.setBackground(new Background(new BackgroundFill(Color.WHITE, null, null)));
		startGridPane.setPadding(new Insets(14,14,9,50));
	}
	
	// Makes second scene, and its size and added KeyHandlers into second scene.
	public void makeScene(Stage gameStage, BorderPane startLayout) {
	    gameStage.setResizable(false);
		gameScene = new Scene (startLayout, 500, 500);
		gameStage.setScene(gameScene);
		gameStage.show();
		GC.addKeyHandler(gameScene);
	}
	
	// Makes the grid based on blockAmount on height/width. Canvas gives the size of the block.
	public void makeGrid() {
		for(int i = 0; i < GC.blockAmount; i++) {
			for(int j= 0;j < GC.blockAmount; j++) {	
				ImageView grass = new ImageView(grassImage);
				
				SP = new StackPane();
				item = new StackPane();
				
		    	spList.add(SP);
		    	itemList.add(item);
		    	
				Canvas canvas = new Canvas(size,size);  
		    	SP.getChildren().addAll(canvas,grass);
		    	
		    	startGridPane.add(SP, j, i); 
		    	startGridPane.add(item, j, i);
	    	}
		}
	}
	
	// Place the snake, obstacle and apple into the grid.
	public void startGraphic() {
		GC.drawList(GM.obstacle, Item.OBSTACLE);
		GC.drawList(GM.snake, Item.SNAKE);
		drawTile(GM.getApple(), Item.APPLE);
	}
	
	// Update GameControllers snake movement.
	public void updateGraphic() {
		if (alive = true) {
			GC.move();
		}
	}
	
	// Draw tile in colors based on their respective objects
	public void drawTile(Point p, Item obj) {
		int number = GC.convert(p);
		SP = spList.get(number);
		item = itemList.get(number);
		
	    ImageView apple = new ImageView(appleImage);
	    ImageView bomb = new ImageView(bombImage);
	    ImageView grass = new ImageView(grassImage);
	    
		if (obj == Item.GRID) {
		    SP.getChildren().add(grass);
    	    item.getChildren().clear();
		} 
		if (obj == Item.SNAKE) {
			SP.getChildren().clear();
			SP.setBackground(new Background(new BackgroundFill(Color.DARKSEAGREEN, null, null)));
	    	item.getChildren().clear();
        }
		if (obj == Item.APPLE) {
		    item.getChildren().add(apple);
    	}
		if (obj == Item.OBSTACLE) {
			item.getChildren().add(bomb);
		}
	} 
}