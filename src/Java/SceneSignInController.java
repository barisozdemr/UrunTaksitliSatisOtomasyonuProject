
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


public class SceneSignInController {
    
    @FXML
    private Label notificationLabel;
    @FXML
    private TextField usernameField;
    @FXML
    private PasswordField passwordField;
    
    public void signInButtonPressed(ActionEvent e) throws IOException //action
    {
        if(Log.trySignIn(usernameField.getText(), passwordField.getText()))
        {
            openSceneMain(e);
        }
        else{
            notificationLabel.setText("Username or password is incorrect");
        }
    }
    
    public void backToSignUpButtonPressed(ActionEvent e) throws IOException //action
    {
        switchToSceneSignUp(e);
    }
    
    public void notificationSetNull(ActionEvent e) //clears notificationLabel
    {
        if(! notificationLabel.getText().equals(null))
        {
            notificationLabel.setText("");
        }
    }
    
    public void openSceneMain(Event e) throws IOException
    {
        Stage oldStage = (Stage)((Node)e.getSource()).getScene().getWindow();
        oldStage.close();
        
        String CssSceneMain = this.getClass().getResource("/Css/CssSceneMain.css").toExternalForm();
        
        Parent root = FXMLLoader.load(getClass().getResource("/Views/SceneMain.fxml"));
        
        Scene scene = new Scene(root);
        scene.getStylesheets().add(CssSceneMain);
        
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }
    
    public void switchToSceneSignUp(Event e) throws IOException //switches to signUp scene
    {
        Parent root = FXMLLoader.load(getClass().getResource("/Views/SceneSignUp.fxml")); //get fxml contens
        
        String CssSceneLogOperations = this.getClass().getResource("/css/CssSceneLogOperations.css").toExternalForm(); //get css contents
        
        Stage stage = (Stage)((Node)e.getSource()).getScene().getWindow(); //get the stage of the event
        
        Scene scene = new Scene(root); //create new scene
        scene.getStylesheets().add(CssSceneLogOperations); //add css contents to our scene
        
        stage.setScene(scene); //set event's stage's scene
        stage.show();
    }
}