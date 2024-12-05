
package Java;

import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;


public class SceneSignUpController {
    
    @FXML
    private Label notificationLabel;
    @FXML
    private TextField usernameField;
    @FXML
    private PasswordField passwordField;
    
    public void signUpButtonPressed(ActionEvent e)
    {
        
    }
    
    public void backToSignInButtonPressed(ActionEvent e) throws IOException
    {
        switchToSceneSignIn(e);
    }
    
    public void switchToSceneSignIn(Event e) throws IOException //switches to signIn scene
    {
        Parent root = FXMLLoader.load(getClass().getResource("/Views/SceneSignIn-Primary.fxml")); //get fxml contens
        
        String CssSceneLogOperations = this.getClass().getResource("/css/CssSceneLogOperations.css").toExternalForm(); //get css contents
        
        Stage stage = (Stage)((Node)e.getSource()).getScene().getWindow(); //get the stage of the event
        
        Scene scene = new Scene(root); //create new scene
        scene.getStylesheets().add(CssSceneLogOperations); //add css contents to our scene
        
        stage.setScene(scene); //set event's stage's scene
        stage.show();
    }
}
