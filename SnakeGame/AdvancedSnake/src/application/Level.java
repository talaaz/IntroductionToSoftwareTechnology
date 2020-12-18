package application;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Level {
	Scene  LevelScene;
	Main main;
	Stage LevelStage;
	Size size;
	Stage primaryStage;
	BorderPane BPLayout;
	VBox VBButton, VBImage;
	HBox HBButton;
	Label levelsLabel;
	ImageView IVsnake, grass;
	Button easy, normal, hard, returnButton;
	int speed, score;

	public Level(Stage LevelStage, Main main) {
		this.main = main;
		this.primaryStage = LevelStage;
		instruction(LevelStage);
	}

	public void startMenu() {
		main.startMenu(main.primaryStage);
	}
	
	public void instruction(Stage LevelStage) {
		createLayout();
		addImages();
		createButtons();
		addLayout();     
		makeScene(LevelStage, BPLayout);	
	}
	
	public void createLayout() {
		BPLayout = new BorderPane();
		VBButton  = new VBox();
		HBButton = new HBox();
		VBImage = new VBox();
		
		// Adds label
	    levelsLabel = new Label(("Choose level: "));
	    levelsLabel.setStyle("-fx-font: 16 Cambria; -fx-base: #000000;");
	}
	
	public void addLayout() {
		// Adds all images into layout
		BPLayout.getChildren().add(grass);
	    VBImage.getChildren().add(IVsnake);
	    VBImage.setPadding(new Insets(170, 0, 0, 30));
	    
	    // Adds all buttons into layout
	    VBButton.getChildren().addAll(levelsLabel, easy, normal, hard);
	    VBButton.setPadding(new Insets(120, 50, 50, 50));
	    VBButton.setSpacing(10); 
	    
	    HBButton.getChildren().add(returnButton);
	    HBButton.setPadding(new Insets(0, 0, 20, 225));
	    
	    // Set them somewhere into the layout
	    BPLayout.setRight(VBImage);
	    BPLayout.setCenter(VBButton);
	    BPLayout.setBottom(HBButton);
	}
	
	public void addImages() {
	    grass = new ImageView(new Image(getClass().getResourceAsStream("/images/grass.png")));
	    grass.setFitHeight(500);
	    grass.setFitWidth(500);
	    
	    IVsnake = new ImageView(new Image(getClass().getResourceAsStream("/images/snake1.png")));
	    IVsnake.setFitHeight(200);
	    IVsnake.setFitWidth(200);
	}
	
	public void createButtons() {
		// Creates buttons
		returnButton = new Button("Return");
		easy = new Button("Easy");	
		normal = new Button("Normal");
		hard = new Button("Hard");
		
		// Styling buttons
		returnButton.setStyle("-fx-font: 14 arial; -fx-base: #804000;");
		easy.setStyle("-fx-font: 16 arial; -fx-base: #804000; -fx-pref-height:20;-fx-pref-width: 130");
	 	normal.setStyle("-fx-font: 16 arial; -fx-base: #804000; -fx-pref-height:20;-fx-pref-width: 130");
	    hard.setStyle("-fx-font: 16 arial; -fx-base: #804000; -fx-pref-height:20;-fx-pref-width: 130");
	   
	    // Gives the buttons a function
	    returnButton.setOnAction(e -> startMenu());
	    easy.setOnAction(e -> chooseSize(Difficulty.EASY));
        normal.setOnAction(e -> chooseSize(Difficulty.NORMAL));
	    hard.setOnAction(e -> chooseSize(Difficulty.HARD)); 
	}
	
	public void chooseSize(Difficulty level) {
		size = new Size(primaryStage, main, level);
	}
	
	public void makeScene(Stage LevelStage, BorderPane startLayout) {
		LevelStage.setResizable(false);
	    LevelScene = new Scene (startLayout, 500, 500);
	    LevelStage.setScene(LevelScene);
	    LevelStage.show();
	}
}


