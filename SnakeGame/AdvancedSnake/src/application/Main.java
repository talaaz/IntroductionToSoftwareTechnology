package application;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class Main extends Application {

	Stage primaryStage;
	Help help;        
	HighScore highScore;        
	Level level;             
	Scene primaryScene;
	BorderPane BPLayout;
	VBox VBButton, VBImage;
	Button startButton,instructions, quitButton, highScoreButton;
	ImageView IVsnake, grass;
	HBox HBLabel;
	Label makersLabel;
	
	// Launches the primary window for the game
	public static void main(String[] args) {
		launch();
	}
	    
	@Override
	// Open up start menu.
	public void start(Stage primaryStage) {
		try {
			startMenu(primaryStage);
	       }
		catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	// Show start menu with the buttons with their respective action events.
	public void startMenu(Stage primaryStage) {
		this.primaryStage = primaryStage;
		
		createLayout();
		createButtons(); 
		addImages();
		addLayout();
		addButtons();
	    makeCredits();
		makeScene();
	}		
	
	// Adds all the images
	public void addImages() {
		grass = new ImageView(new Image(getClass().getResourceAsStream("/images/grass.png")));
	   
	    IVsnake = new ImageView(new Image(getClass().getResourceAsStream("/images/snake.png")));
	    IVsnake.setFitHeight(200);
	    IVsnake.setFitWidth(200);
	}
	
	// Creates the buttons
	public void createButtons() {
		startButton = new Button("Start Game");	
		startButton.setStyle("-fx-font: 16 arial; -fx-base: #804000; -fx-pref-height:20;-fx-pref-width: 130");

	    instructions = new Button("Help");
	    instructions.setStyle("-fx-font: 16 arial; -fx-base: #804000; -fx-pref-height:20;-fx-pref-width: 130");
	    
	    quitButton = new Button("Quit Game");
	    quitButton.setStyle("-fx-font: 16 arial; -fx-base: #804000; -fx-pref-height:20;-fx-pref-width: 130");
	    
	    highScoreButton = new Button("High Score");
	    highScoreButton.setStyle("-fx-font: 16 arial; -fx-base: #804000; -fx-pref-height:20;-fx-pref-width: 130");
	}
	
	// Creates the boxes and borderpane, where we put stuff inside.
	public void createLayout() {
		BPLayout = new BorderPane();
		VBImage = new VBox();		
		HBLabel = new HBox();
	    VBButton = new VBox(); 
	}
	
	// Add the parts into the layout
	public void addLayout() {
		BPLayout.getChildren().add(grass);   
		
		VBImage.getChildren().add(IVsnake);
		VBImage.setPadding(new Insets(170, 0, 0, 30));
		
	    BPLayout.setRight(VBButton);
	    BPLayout.setLeft(VBImage);
	    BPLayout.setBottom(HBLabel);
	}
	
	public void addButtons() {
		// Adds the buttons into the VBButton
	    VBButton.getChildren().addAll(startButton, instructions, highScoreButton, quitButton);
	    VBButton.setPadding(new Insets(120, 50, 50, 50));
	    VBButton.setSpacing(10);
	    
	    // Adds the actions for the buttons
	    startButton.setOnAction(e -> level());
	    instructions.setOnAction(e -> instruction());
	    highScoreButton.setOnAction(e -> highScore());
	    quitButton.setOnAction(e -> System.exit(0));
	}
	
	// Starts new scene when clicked on button
	public void instruction() {
		help = new Help(primaryStage,this);
	}
	
	public void highScore() {
		highScore = new HighScore(primaryStage, this);
	}
 	public void level() {
		level = new Level(primaryStage,this);
	}
	
	// Makes the credits and puts it into the corner
	public void makeCredits() {
		makersLabel = new Label("Made by:\r\n" + "David & Tala & Anja"); 
		HBLabel.getChildren().add(makersLabel);
	}
	
	// Makes the scene based on the layout and size
	public void makeScene() {
		primaryStage.setTitle("Snake Game");
	    primaryStage.setResizable(false);
	    primaryScene = new Scene (BPLayout, 500, 500);
		primaryStage.setScene(primaryScene);
		primaryStage.show();
	}
}