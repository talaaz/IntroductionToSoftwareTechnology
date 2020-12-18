package application;

import java.util.ArrayList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Label;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class Graphics {  
	Scene  gameScene;	
	GameModel GM;
	GameController GC;
	Stage stage;
	Canvas canvas;
	BorderPane startLayout;
	GridPane startGridPane;
	StackPane SP, dot;
	ArrayList<StackPane> spList = new ArrayList<>(), dotList = new ArrayList<>();
	Label scoreLabel; 
	int score = 0;
	
	// Takes information from arguments to make methods
	public Graphics(Stage gameStage, GameController GC) {
		GM = GC.GM;
		this.GC = GC;
    	stage = gameStage;
    }
	
	// Makes size of , initiate all the methods
	public void startGame(Stage gameStage) {
		try {
			makeLayout();	
			makeGrid();
			startGraphic();
			makeScene(gameStage, startLayout);				
		} catch(Exception e) {
			e.printStackTrace();
			}
		}
	
	public void makeLayout() {
		startLayout = new BorderPane();
		startGridPane = new GridPane();
		
		// Label for scores
		scoreLabel = new Label();
		scoreLabel.setText("Points : " + score);
		scoreLabel.setPadding(new Insets(9,14,9,14));
		startLayout.setBottom(scoreLabel);
		
		// Sets the game in the center
		startLayout.setCenter(startGridPane); 
		startGridPane.setBackground(new Background(new BackgroundFill(Color.WHITE, null, null)));
		startGridPane.setPadding(new Insets(30,1,1,30));
	}
	
	// Makes second scene, and its size and added KeyHandlers into second scene.
	public void makeScene(Stage gameStage, BorderPane startLayout) {
		gameScene = new Scene (startLayout, Main.IntWIDTH , Main.IntHEIGHT + 20 );
		gameStage.setScene(gameScene);
		gameStage.show();
		GC.addKeyHandler(gameScene);
	}
	
	// Makes the grid based on blockAmount on height/width. Canvas gives the size of the block.
	public void makeGrid() {
		for(int i = 0; i < Main.HEIGHT; i++) {
			for(int j= 0;j < Main.WIDTH;j++) {
				SP = new StackPane();
				dot = new StackPane();
				int canvasw = (Main.IntWIDTH-100)/Main.WIDTH, canvasH =(Main.IntHEIGHT-50)/Main.HEIGHT,
						blockAmount= Math.min(canvasw, canvasH);
				canvas = new Canvas(blockAmount,blockAmount);	
				SP.getChildren().add(canvas);
	    	    spList.add(SP);
	    	    dotList.add(dot);
	    	    
	    	    SP.setBackground(new Background(new BackgroundFill(Color.GREY, null, null)));
	    	   
	    	    startGridPane.add(SP, j, i);
	    	    startGridPane.add(dot, j, i);
	    	    }
			}
		}
	
	// Place the snake and dot into the grid.
	public void startGraphic() {
		GC.drawSnake(GM.snake);
		drawTile(GM.getDot(), Item.DOT);
	}
	
	
	// Draw tile in colors based on their respective objects
	public void drawTile(Point p, Item obj) {
		int number = GC.convert(p);
		SP = spList.get(number);
		dot = dotList.get(number);
		if (obj == Item.GRID) {
			SP.setBackground(new Background(new BackgroundFill(Color.GREY, null, null)));
			dot.setBackground(new Background(new BackgroundFill(null, null, null)));
		} 
		if (obj == Item.SNAKE) {
    	    SP.setBackground(new Background(new BackgroundFill(Color.LIME, null, null)));
    	    dot.setBackground(new Background(new BackgroundFill(null, null, null)));
        }
		if (obj == Item.DOT) {
			dot.setBackground(new Background(new BackgroundFill(Color.RED, new CornerRadii(90), null)));
    	}
	} 
}
