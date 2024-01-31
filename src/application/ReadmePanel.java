package application;

import java.awt.Desktop;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;

import javafx.scene.control.Alert;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;

public class ReadmePanel {
	
	public void showPanel () throws MalformedURLException {
		URL url = new URL("https://www.linkedin.com/in/rubenv-sd/"); // Store the URL
		Hyperlink link = new Hyperlink(url.toString()); // Create Hyperlink
		TextFlow ta = new TextFlow();
		ta.getChildren().add(new Text("Creado por: Fernando Ruben Villalaz Mori\nContacto: fvillalazm@outlook.com\nLinkedin:"));
		ta.getChildren().add(link);
		
		link.setOnAction(event -> {
		    try {
		        Desktop.getDesktop().browse(url.toURI()); // Use the stored URL
		    } catch (IOException | URISyntaxException error) {
		        System.err.println("Error opening browser: " + error.getMessage());
		    }
		});
		
		Alert information = new Alert(AlertType.INFORMATION);
		information.setTitle("ACERCA DEL PROGRAMA");
		information.setHeaderText("C.S. Pedro Ruiz Gallo - Amazonas: Generador de Certificados Higienico Sanitario");
		information.getDialogPane().setContent(ta);
		information.showAndWait();
	}

}
