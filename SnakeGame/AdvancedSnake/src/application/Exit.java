package application;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class Exit {
	Scene exitScene;
	Stage exitStage;
	Main main;
	GameController GC;
	BorderPane BPLayout;
	HBox HBButton;
	Button returnButton,resumeButton;

	
	public Exit(Stage exitStage, Main main, GameController GC) {
		this.main = main;
		this.GC = GC;
		exit(exitStage);
	}
	
	public void makeScene(Stage exitStage, BorderPane startLayout) {
		exitStage.setTitle("Paused");
		exitStage.setResizable(false);
	    exitScene = new Scene (startLayout,300,100);
	    exitStage.setScene(exitScene);
	    exitStage.show();
	}
	
	public void startMenu() {
		main.startMenu(main.primaryStage);
	}

	public void exit(Stage exitStage) {
		createLayout();
		createButtons(exitStage);	
		addLayout();	
		makeScene(exitStage, BPLayout);	
		addKeyHandler(exitScene);
	}
	
	// Resumes game and closes this window
	public void unPause(Stage exitStage) {
		GC.graphics.startTime();
		exitStage.close();
	}
	
	public void createLayout() {
		BPLayout = new BorderPane();
		HBButton = new HBox();
	}
	
	public void createButtons(Stage exitStage) {
		// Creates buttons
		returnButton = new Button("Return");
		resumeButton = new Button("Resume");
		
		// Styles them
	    returnButton.setStyle("-fx-font: 14 arial; -fx-base: #804000;-fx-pref-height:20;-fx-pref-width: 100");
	    resumeButton.setStyle("-fx-font: 14 arial; -fx-base: #804000;-fx-pref-height:20;-fx-pref-width: 100");
	    
	    // Gives them functions
	    returnButton.setOnAction(e -> startMenu());
	    resumeButton.setOnAction(e -> unPause(exitStage));
	}
	
	// Places all the buttons at the center with spacing between them
	public void addLayout() { 
		HBButton.getChildren().addAll(returnButton,resumeButton);
		HBButton.setSpacing(10);
		HBButton.setPadding(new Insets(40, 0, 0, 45));
		
		BPLayout.setCenter(HBButton);		   
	}
	
	// Adds escape buttons to resume game.
	public void addKeyHandler(Scene exitScene) {
		exitScene.setOnKeyPressed(e -> {
		    if (e.getCode() == KeyCode.ESCAPE) {
			unPause(exitStage);
			}
		});
	}
}