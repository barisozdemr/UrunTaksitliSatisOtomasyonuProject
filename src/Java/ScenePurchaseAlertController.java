
package Java;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class ScenePurchaseAlertController {
    @FXML
    private Button okButton;
    @FXML
    private Label purchaseNotification;
    
    public void initialize()
    {
        purchaseNotification.setText(Buy.buyNotification);
    }
    
    public void okButtonPressed(ActionEvent e)
    {
        Stage stage = (Stage)okButton.getScene().getWindow();
        stage.close();
    }
}
