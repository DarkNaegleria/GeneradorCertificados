package application;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

import javax.swing.JOptionPane;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.AnchorPane;
import javafx.stage.DirectoryChooser;
import javafx.stage.Stage;

public class Controller implements Initializable{
	
	//------------FXML components
	@FXML
	private Button closeButton, saveButton, cleanButton, directoryButton, directoryButton2;
	
	@FXML
	private AnchorPane anchorPane;
	
	@FXML
	private TextField directoryField, certificateNumberField, ticketCodeField, businessNameField, ownerField, businessTypeField, addressField;
	
	@FXML
	private RadioButton excellentRadio, niceRadio, regularRadio, deficientRadio;
	
	@FXML
	private ToggleGroup sanitaryLevelGroup;
	
	@FXML
	private DatePicker datePicker;

    @FXML
    private ComboBox<String> ticketTypeBox;
	
	
	//------------Variables
	private Stage stage;
	private final String[] ticketTypes = {"BOLETA", "FACTURA"};
	private String directorySelected, fileName, certificateNumber, ticketCode, businessName, owner, businessType, address, sanitaryLevel, issueDateFormated, dueDateFormated, ticketType;
	private LocalDate localDate;

	
	//------------Methods
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		ticketTypeBox.getItems().addAll(ticketTypes);
		ticketTypeBox.setOnAction(this::setTicketType);
	}
	
	public void exit (ActionEvent e) {
		Alert alert = new Alert(AlertType.WARNING, null, ButtonType.CANCEL, ButtonType.YES);
		alert.setTitle("ATENCIÓN");
		alert.setHeaderText("Cerrar programa");
		alert.setContentText("¿Estás seguro que deseas salir del programa?");
		
		if (alert.showAndWait().get() == ButtonType.YES) {
			stage = (Stage) anchorPane.getScene().getWindow();
			stage.close();
		}
	}
	
	public void savePDF (ActionEvent e) {
		certificateNumber = certificateNumberField.getText().toUpperCase();
		fileName = "/Certificado - " + certificateNumberField.getText();
		ticketCode = ticketCodeField.getText().toUpperCase();
		businessName = businessNameField.getText();
		owner = ownerField.getText().toUpperCase();
		businessType = businessTypeField.getText().toUpperCase();
		address = addressField.getText().toUpperCase();
		
		if (!Validator.validateFields(this)) {
	        return;
	    }
		
		PDFGenerator pdfGenerated = new PDFGenerator(this);
		try {
			pdfGenerated.makePDF();
			JOptionPane.showMessageDialog(null, "Se creó correctamente el certificado");
		} catch (IOException e1) {
			JOptionPane.showMessageDialog(null, "Ocurrió un error: " + e1.getMessage());
		}
	}
	
	public void setSanitaryLevel (ActionEvent e) {
	    RadioButton selectedRadioButton = (RadioButton) sanitaryLevelGroup.getSelectedToggle();

	        switch (selectedRadioButton.getId()) {
	            case "excellentRadio":
	                sanitaryLevel = excellentRadio.getText();
	                break;
	            case "niceRadio":
	                sanitaryLevel = niceRadio.getText();
	                break;
	            case "regularRadio":
	                sanitaryLevel = regularRadio.getText();
	                break;
	            case "deficientRadio":
	                sanitaryLevel = deficientRadio.getText();
	                break;
	        }
	}

	public void pickDate (ActionEvent e) {
		localDate = datePicker.getValue();
		DateFormatter dateFormated = new DateFormatter(localDate);
		issueDateFormated = dateFormated.getIssueDateFormated();
		dueDateFormated = dateFormated.getDueDateFormated();
	}
	
	public void setTicketType (ActionEvent e) {
		ticketType = ticketTypeBox.getValue() + ":";
	}
	
	public void setDirectory (ActionEvent e) {
		DirectoryChooser directory = new DirectoryChooser();
		File file = directory.showDialog(null);
		
		if (file != null) {
			directoryField.setText(file.getAbsolutePath());
			directorySelected = file.getAbsolutePath();
		} else {
			directoryField.setText("Selecciona un directorio");
		}
	}
	
	public void showReadme (ActionEvent e) throws MalformedURLException {
		ReadmePanel rp = new ReadmePanel();
		rp.showPanel();
	}
	
	public void clean (ActionEvent e) {
		certificateNumberField.setText(null);
		ticketCodeField.setText(null);
		businessNameField.setText(null);
		ownerField.setText(null);
		businessTypeField.setText(null);
		addressField.setText(null);
		sanitaryLevelGroup.selectToggle(regularRadio);
		ticketTypeBox.setValue(null);
		datePicker.setValue(LocalDate.now());
	}
	
	public String getDirectorySelected() {
		return directorySelected;
	}

	public String getCertificateNumber() {
		return certificateNumber;
	}

	public String getTicketCode() {
		return ticketCode;
	}

	public String getBusinessName() {
		return businessName;
	}

	public String getOwner() {
		return owner;
	}

	public String getBusinessType() {
		return businessType;
	}

	public String getAddress() {
		return address;
	}

	public String getSanitaryLevel() {
		return sanitaryLevel;
	}

	public String getIssueDateFormated() {
		return issueDateFormated;
	}


	public String getDueDateFormated() {
		return dueDateFormated;
	}

	public String getTicketType() {
		return ticketType;
	}

	public String getFileName() {
		return fileName;
	}
}
