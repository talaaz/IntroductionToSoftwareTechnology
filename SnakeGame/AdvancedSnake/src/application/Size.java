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

public class Size {

	Main main;
	Difficulty level;
	Stage primaryStage;
	BorderPane BPLayout;
	VBox VBButton, VBImage;
	HBox HBButton;
	Button returnButton, smallSize, normalSize, largeSize;
	ImageView IVsnake, grass;
	Scene sizeScene;
	Label sizeLabel;
	public int small = 20;
	public int normal = 30;
	public int large = 40;

	public Size(Stage primaryStage, Main main, Difficulty level) {
		this.main = main;
		this.primaryStage = primaryStage;
		this.level = level;
		instruction(primaryStage, level);
	}
		
	public void instruction(Stage LevelStage, Difficulty level) {
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
		
	    sizeLabel = new Label(("Choose Size: "));
	    sizeLabel.setStyle("-fx-font: 16 Cambria; -fx-base: #000000;");
	}
	
	public void addLayout() {
		// Adds all images into layout
		BPLayout.getChildren().add(grass);	
		VBImage.getChildren().add(IVsnake);
		VBImage.setPadding(new Insets(170, 0, 0, 30));
		
		// Adds all buttons into layout
	    VBButton.getChildren().addAll(sizeLabel, smallSize, normalSize, largeSize);
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
		// Creates Buttons
		returnButton = new Button("Return");
		smallSize = new Button("Small (20 x 20)");	
		normalSize = new Button("Normal (30 x 30)");
		largeSize = new Button("Large (40 x 40)");
	    
		// Styles buttons
		returnButton.setStyle("-fx-font: 14 arial; -fx-base: #804000;");
	 	smallSize.setStyle("-fx-font: 16 arial; -fx-base: #804000; -fx-pref-height:20;-fx-pref-width: 200");
	 	normalSize.setStyle("-fx-font: 16 arial; -fx-base: #804000; -fx-pref-height:20;-fx-pref-width: 200");
	    largeSize.setStyle("-fx-font: 16 arial; -fx-base: #804000; -fx-pref-height:20;-fx-pref-width: 200");
       
	    // Set functions into buttons
	    returnButton.setOnAction(e -> startMenu());
	    smallSize.setOnAction(e -> startGame(level, small));
        normalSize.setOnAction(e -> startGame(level, normal));
	    largeSize.setOnAction(e -> startGame(level, large));  
	}
	
	public void startMenu() {
		main.startMenu(main.primaryStage);
	}
	
	public void makeScene(Stage sizeStage, BorderPane startLayout) {
		sizeStage.setResizable(false);
		sizeScene = new Scene (startLayout, 500, 500);
		sizeStage.setScene(sizeScene);
		sizeStage.show();
	}
	
	public void startGame(Difficulty level, int size) {
		new GameController(primaryStage, main, level, size);
	}
}
