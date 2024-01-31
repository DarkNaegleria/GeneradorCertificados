package application;
	
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.image.Image;
import javafx.scene.control.Alert.AlertType;


public class Main extends Application {
	@Override
	public void start(Stage primaryStage) {
		try {
			Parent root = FXMLLoader.load(getClass().getResource("/src/application/GUI.fxml"));
			//Parent root = FXMLLoader.load(getClass().getResource("GUI.fxml"));
			Scene scene = new Scene(root);
			Image icon = new Image(getClass().getResourceAsStream("/src/resources/LogoDN.png"));
			//Image icon = new Image(getClass().getResourceAsStream("/resources/LogoDN.png"));
			scene.getStylesheets().add(getClass().getResource("/src/application/application.css").toExternalForm());
			//scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.show();
			primaryStage.getIcons().add(icon);
			primaryStage.setTitle("C.S. Pedro Ruiz Gallo: Generador de Certificados - Salud Ambiental");
			primaryStage.setResizable(false);
			
			primaryStage.setOnCloseRequest(event -> {
				event.consume();
				exit(primaryStage);
				});
			
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public void exit (Stage primaryStage) {
		Alert alert = new Alert(AlertType.WARNING, null, ButtonType.CANCEL, ButtonType.YES);
		alert.setTitle("ATENCIÓN");
		alert.setHeaderText("Cerrar programa");
		alert.setContentText("¿Estás seguro que deseas salir del programa?");
		
		if (alert.showAndWait().get() == ButtonType.YES) {
			primaryStage.close();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
