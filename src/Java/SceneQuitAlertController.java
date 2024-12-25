
package Java;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class SceneQuitAlertController {
    @FXML
    private Button cancelButton;
    @FXML
    private Button yesButton;
    
    private final Stage mainStage;
    
    public SceneQuitAlertController(Stage mainStage)
    {
        this.mainStage = mainStage;
    }
    
    // Nothing happens and alert closes
    public void cancelButtonPressed(ActionEvent e)
    {
        Stage stage = (Stage)cancelButton.getScene().getWindow();
        stage.close();
    }
    
    // All stages closed
    public void yesButtonPressed(ActionEvent e)
    {
        Stage stage = (Stage)yesButton.getScene().getWindow();
        stage.close();
        
        mainStage.close();
    }
}
