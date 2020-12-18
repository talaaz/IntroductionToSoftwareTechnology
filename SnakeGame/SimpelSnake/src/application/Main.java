package application;
	
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;

public class Main extends Application {
	
	static int WIDTH, HEIGHT, IntWIDTH = 800, IntHEIGHT=600;
	public Stage primaryStage;
	public GameController GC;
	Scene primaryScene;
	
	// Launches the primary window for the game
	public static void main(String[] args) {
		try {
			if (args.length != 2) {
				System.err.println("Needs two arguments to start Simpel-Snake");
				System.exit(0);
			}
			HEIGHT = Integer.parseInt(args[0]);
			WIDTH = Integer.parseInt(args[1]);
			if (WIDTH > 100 || WIDTH < 5 || HEIGHT > 100 || HEIGHT < 5) {
				System.err.println("Illegal argument: Argument needs to be from a number between 5 to 100");
				System.exit(0);
			} else {
			launch(args);
			}	
		}
		catch(NumberFormatException exception) {
			System.err.println("Illegal argument: Argument need to be a number");
			System.exit(0);
		}
	}
	    
	@Override
	// Open up start menu.
	public void start(Stage primaryStage) {
		try {
			primaryStage.setTitle("Simpel-Snake");
			GC = new GameController(primaryStage, this);
	       }
		catch(Exception e) {
			e.printStackTrace();
		}
	}
}
