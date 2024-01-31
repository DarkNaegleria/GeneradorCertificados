package application;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class Validator {

    public static boolean validateFields(Controller controller) {
        if (isEmpty(controller.getCertificateNumber())
                || isEmpty(controller.getTicketCode())
                || isEmpty(controller.getBusinessName())
                || isEmpty(controller.getOwner())
                || isEmpty(controller.getBusinessType())
                || isEmpty(controller.getAddress())
                || controller.getSanitaryLevel() == null
                || isEmpty(controller.getIssueDateFormated())
                || isEmpty(controller.getDueDateFormated())
                || controller.getTicketType() == null) {
            showAlert("Todos los campos deben estar llenos");
            return false;
        }

        return true;
    }

    private static boolean isEmpty(String value) {
        return value == null || value.isEmpty();
    }

    private static void showAlert(String message) {
        Alert alert = new Alert(AlertType.WARNING);
        alert.setTitle("Campo vacío");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}

