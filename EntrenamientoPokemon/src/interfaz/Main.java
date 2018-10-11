package interfaz;
	
import interfaz.OpcionController;
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.fxml.FXMLLoader;


public class Main extends Application {
	
	private  Scene scene;
	private  Stage primaryStage;

	@Override
	public void start(Stage primaryStage) {
		try {
			FXMLLoader loader=new FXMLLoader(getClass().getResource("Opcion.fxml"));
			AnchorPane root = (AnchorPane)loader.load();
			OpcionController controlador= loader.getController();
			
		

			this.primaryStage= primaryStage;
			scene = new Scene(root);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(scene);
			controlador.pasarMain(this);
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public  Scene getScene() {
		return scene;
	}



	public  Stage getPrimaryStage() {
		return primaryStage;
	}

	
	public void setPrimaryStage(Stage primaryStage) {
		this.primaryStage = primaryStage;
	}

	public static void main(String[] args) {
		launch(args);
	}
}
