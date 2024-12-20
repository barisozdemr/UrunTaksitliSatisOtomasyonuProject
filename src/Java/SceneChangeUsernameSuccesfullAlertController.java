
package Java;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class SceneChangeUsernameSuccesfullAlertController implements IOkAlert {
    
    @FXML
    private Button okButton;
    
    @Override
    public void okButtonPressed(ActionEvent e)
    {
        Stage stage = (Stage)okButton.getScene().getWindow();
        stage.close();
    }
}
