package application;
	
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;


public class Main extends Application {
	@Override
	public void start(Stage stage) throws Exception {
		Parent parent =(Parent) FXMLLoader.load(getClass().getResource("/application/view/MainView.fxml"));
		Scene scene = new Scene(parent);
		stage.setScene(scene);
		stage.setTitle("WITAJ!");
		stage.getIcons().add(new Image("http://www.iconsplace.com/download/orange-database-512.png"));
		stage.show();
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
