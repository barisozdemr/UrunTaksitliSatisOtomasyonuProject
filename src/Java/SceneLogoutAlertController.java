
package Java;

import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class SceneLogoutAlertController {
    @FXML
    private Button cancelButton;
    @FXML
    private Button yesButton;
    
    private final Stage mainStage;
    
    public SceneLogoutAlertController(Stage mainStage)
    {
        this.mainStage = mainStage;
    }
    
    public void cancelButtonPressed(ActionEvent e)
    {
        Stage stage = (Stage)cancelButton.getScene().getWindow();
        stage.close();
    }
    
    public void yesButtonPressed(ActionEvent e) throws IOException
    {
        Stage stage = (Stage)yesButton.getScene().getWindow();
        stage.close();
        
        mainStage.close();
        
        openSceneSignIn();
    }
    
    public void openSceneSignIn() throws IOException
    {
        Parent root = FXMLLoader.load(getClass().getResource("/Views/SceneSignIn-Primary.fxml"));
        
        Stage stage = new Stage();
        
        stage.setScene(new Scene(root));
        stage.setTitle("Taksitle! - Sign In");
        stage.getIcons().add(DataStore.programLogo);
        stage.setResizable(false);
        stage.show();
    }
}
