package application;

import java.util.ArrayList;
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

public class HighScore {
	
	ArrayList<Integer> entries = new ArrayList<Integer>();
	Main main;
	BorderPane BPLayout;
	Button returnButton;
	HBox HBButton;
	Scene scoreScene;
	Label rulesLabel;
	VBox VBLabel;

	public HighScore(Stage scoreStage, Main main) {
		this.main = main;
		highScoreScene(scoreStage);
	}
	
	public void highScoreScene(Stage InfoStage) {
		createLayout();
		addImagesAndLayout();
		createButton();
		makeScene(InfoStage, BPLayout);	
	}
	
	public void createLayout() {
		BPLayout = new BorderPane();
		HBButton = new HBox();
		VBLabel  = new VBox();
		returnButton = new Button();
		
		// Adds label
		rulesLabel = new Label(("UNDER CONSTRUCTION"));
		rulesLabel.setStyle("-fx-font: 42 Cambria; -fx-base: #000000;");
	}
	
	public void addImagesAndLayout() {
		// Creates background
		ImageView grass = new ImageView(new Image(getClass().getResourceAsStream("/images/grass.png")));
	    grass.setFitHeight(500);
	    grass.setFitWidth(500);
	    
	    // Add into background and label into layout
	    BPLayout.getChildren().addAll(grass);
	    VBLabel.getChildren().add(rulesLabel);
	    
	    BPLayout.setCenter(VBLabel); 
	    BPLayout.setBottom(HBButton);
	}
	
	// Create buttons and its functions
	public void createButton() {
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
	
	// Adds layout into scene
	public void makeScene(Stage scoreStage, BorderPane startLayout) {
		scoreStage.setResizable(false);
	    scoreScene = new Scene (startLayout, 500, 500);
	    scoreStage.setScene(scoreScene);
	    scoreStage.show();
	}
	
	/*
	import java.io.File;
	import java.util.Collections;
	import java.util.Scanner;
	import java.io.FileWriter;
	import java.io.IOException;

	private int score;
	
	public HighScore(int score) throws IOException {
		this.score = score;

		getHighScore();
		addEntries( score);
		scoreToFile();
	}
	
	public ArrayList<Integer> getHighScoreEntries() {
		return entries;
	}
	
	public int getScore() {
		return score;
	}
	
	 public void addEntries (int score) {
		if (entries.size() < 10) {
			entries.add(score);
			Collections.sort(entries);
			Collections.reverse(entries);
		} else if (entries.size() >= 10) {
			entries.remove(entries.size()-1);
			addEntries(score);
		}
	}
	
	public void getHighScore() throws IOException {
		File file = new File("Images/highScore");
		Scanner s = new Scanner(file);
		while (s.hasNext()) {
			if(s.hasNextInt()) {
				entries.add(s.nextInt());
			} else {
				s.next();
			}
		}
		s.close();
	} 
	
	public void scoreToFile() throws IOException {
		FileWriter writer = new FileWriter("Images/highScore");
			for (int i: entries) {
				writer.write(" " + i);
			}
			writer.close();
		} 
		
	public void tableView() {
		TableView<HighScore> table = new TableView<HighScore>();
	    
	    TableColumn titleCol = new TableColumn("score");
	    titleCol.setCellValueFactory(new PropertyValueFactory<HighScore, String>("score"));
	    table.getColumns().setAll(titleCol);
	    table.setPrefWidth(200);
	    table.setPrefHeight(200);
	    table.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

	    StackPane sp = new StackPane(table); 
	    sp.prefWidthProperty().bind(BPLayout.widthProperty());
        sp.prefHeightProperty().bind(BPLayout.heightProperty());

        BPLayout.setCenter(sp);
	} 
	*/	
}
