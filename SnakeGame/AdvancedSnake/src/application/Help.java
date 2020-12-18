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

public class Help {
	
	Main main;
	Scene  InfoScene;
	BorderPane BPLayout;
	VBox VBImage, VBLabel;
	HBox HBButton;
	Label rulesLabel;
	ImageView IVsnake, IVkey, grass;
	Button returnButton;
	
	public Help(Stage InfoStage, Main main) {
		this.main = main;
		instructions(InfoStage);
	}

	public void instructions(Stage InfoStage) {
		createLayout();
		addImages();
		addLayout();
		createButtons();
		makeScene(InfoStage, BPLayout);	
	}
	
	// Create Layout
	public void createLayout() {
		BPLayout = new BorderPane();
		VBImage  = new VBox();
		VBLabel  = new VBox();
		HBButton = new HBox();
		createLabel();
	}
	
	// Creates the text
	public void createLabel() {
		rulesLabel = new Label(("" +
		"Objectives:\r\n" + 
		"Get your score up by eating apples. \r\n" + 
		"Don't eat the bombs. \r\n" + 
		"Don't eat yourself. \r\n\r\n" + 
		"Controls: \r\n" +
		"Use your arrow keys: up, down, left and right \r\n" + 
		"to control the snake's movement \r\n" +
		"You can also use W, S, A and D for controlling the snake \r\n" +
		"Press escape to pause or unpause the game \r\n\r\n" +
		"Level: Easy / Normal / Hard \r\n" + 
		"There is up to 5 / 10 / 15 bombs in grid. \r\n" +
		"The snake can go out of boundaries, if the level is easy \r\n" +
		"If the level is hard or normal, you cannot go out of boundaries\r\n" +
		"You get +100 / +150 / +200 score for each apple depending on level \r\n" +
		"Snake accelerate depending on amount of apple eaten and on the level"));
		
		rulesLabel.setStyle("-fx-font: 13 Cambria; -fx-base: #000000;");	
	}
	
	// Adds the parts into the layout
	public void addLayout() {
		BPLayout.getChildren().add(grass);
		
		VBLabel.getChildren().addAll(rulesLabel,IVkey);
		VBImage.getChildren().add(IVsnake);
		VBImage.setPadding(new Insets(300, 0, 0, -200));
		
		BPLayout.setLeft(VBLabel);
		BPLayout.setRight(VBImage);
		BPLayout.setBottom(HBButton);
	}
	
	// Adds all the images
	public void addImages() {
		grass = new ImageView(new Image(getClass().getResourceAsStream("/images/grass.png")));
		grass.setFitHeight(500);
		grass.setFitWidth(500);
		
		IVkey = new ImageView(new Image(getClass().getResourceAsStream("/images/key.png")));
		IVkey.setFitHeight(150);
		IVkey.setFitWidth(150);
		
	    IVsnake = new ImageView(new Image(getClass().getResourceAsStream("/images/snake.png")));
	    IVsnake.setFitHeight(150);
	    IVsnake.setFitWidth(150);
	}
	
	// Creates button to go back to menu
	public void createButtons() {
		returnButton = new Button();
		returnButton.setText("return");
		returnButton.setStyle("-fx-font: 14 arial; -fx-base: #804000;");
		HBButton.getChildren().add(returnButton);
		HBButton.setPadding(new Insets(0, 0, 20, 225));
		returnButton.setOnAction(e -> startMenu());
	}
	
	// Returns to start menu
	public void startMenu() {
		main.startMenu(main.primaryStage);
	}
	
	// Creates scene and show it
	public void makeScene(Stage InfoStage, BorderPane startLayout) {
		InfoStage.setResizable(false);
	    InfoScene = new Scene (startLayout, 500, 500);
	    InfoStage.setScene(InfoScene);
	    InfoStage.show();
	}
}



