
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
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;


public class SceneSignUpController {
    
    @FXML
    private Label notificationSignUp;
    @FXML
    private TextField usernameSignUpField;
    @FXML
    private PasswordField passwordSignUpField;
    @FXML
    private PasswordField passwordSignUpField2;
    
    public void signUpButtonPressed(ActionEvent e) //action
    {
        trySignUp(e);
    }
    
    public void enterKeyPressed(KeyEvent e) //action
    {
        if (e.getCode() == KeyCode.ENTER)
        {
            trySignUp(e);
        }
    }
    
    public void trySignUp(Event e)
    {
        if(Log.trySignUp(usernameSignUpField.getText(), passwordSignUpField.getText(), passwordSignUpField2.getText()))
        {
            openSignUpSuccesfullAlert(e);
        }
        else{
            if(Log.signUpNotification == null)
            {
                notificationSignUp.setText("Sign up was not succesfull due to unknown error");
            }
            else{
                notificationSignUp.setText(Log.signUpNotification);
            }
        }
    }
    
    public void backToSignInButtonPressed(ActionEvent e) //action
    {
        switchToSceneSignIn(e);
    }
    
    public void notificationSetNull(MouseEvent e) //clears notificationLabel
    {
        if(notificationSignUp.getText() != null)
        {
            notificationSignUp.setText("");
        }
    }
    
    public void switchToSceneSignIn(Event e) //switches to signIn scene
    {
        try //switches to signIn scene
        {
            Parent root = FXMLLoader.load(getClass().getResource("/Views/SceneSignIn-Primary.fxml")); //get fxml contens
            
            String CssSceneLogOperations = this.getClass().getResource("/Css/CssSceneLogOperations.css").toExternalForm(); //get css contents
            
            Stage stage = (Stage)((Node)e.getSource()).getScene().getWindow(); //get the stage of the event
            
            Scene scene = new Scene(root); //create new scene
            scene.getStylesheets().add(CssSceneLogOperations); //add css contents to our scene
            
            stage.setScene(scene); //set event's stage's scene
            stage.setTitle("Taksitle! - Sign In");
            stage.show();
        } catch (IOException ex) {
            System.out.println("/Views/SceneSignIn-Primary.fxml could not be loaded");
        }
    }
    
    public void openSignUpSuccesfullAlert(Event e)
    {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/Views/SceneSignUpSuccesfullAlert.fxml")); //get fxml contents
            
            Stage stage = new Stage(); //creating new stage
            stage.setScene(new Scene(root)); //set stage scene
            stage.setTitle("Bilgi"); //set stage title
            stage.getIcons().add(DataStore.programLogo); //set program logo
            stage.setResizable(false);
            stage.show();
            
            switchToSceneSignIn(e);
        } catch (IOException ex) {
            System.out.println("/Views/SceneSignUpSuccesfullAlert.fxml could not be loaded");
        }
    }
}
