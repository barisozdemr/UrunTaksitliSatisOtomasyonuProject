
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


public class SceneSignInController {
    
    @FXML
    private Label notificationSignIn;
    @FXML
    private TextField usernameSignInField;
    @FXML
    private PasswordField passwordSignInField;
    
    public void signInButtonPressed(ActionEvent e) //action
    {
        trySignIn(e);
    }
    
    public void enterKeyPressed(KeyEvent e) //action
    {
        if (e.getCode() == KeyCode.ENTER)
        {
            trySignIn(e);
        }
    }
    
    public void trySignIn(Event e)
    {
        if(Log.trySignIn(usernameSignInField.getText(), passwordSignInField.getText())) //checks if the username and password is correct
            {
                DataStore.loggedUsersName = usernameSignInField.getText();
                openSceneMain(e);
            }
            else{
                notificationSignIn.setText("Username or password is incorrect");
            }
    }
    
    public void backToSignUpButtonPressed(ActionEvent e) //action
    {
        switchToSceneSignUp(e);
    }
    
    public void notificationSetNull(MouseEvent e) //clears notificationLabel
    {
        if(notificationSignIn.getText() != null)
        {
            notificationSignIn.setText("");
        }
    }
    
    public void openSceneMain(Event e)
    {
        try {
            Stage oldStage = (Stage)((Node)e.getSource()).getScene().getWindow(); //get sign in stage
            oldStage.close(); //close sign in stage
            
            Parent root = FXMLLoader.load(getClass().getResource("/Views/SceneMain.fxml")); //get fxml contents (Main Scene)
            
            String CssSceneMain = this.getClass().getResource("/Css/CssSceneMain.css").toExternalForm(); // get css contents
            
            Scene scene = new Scene(root); //create new stage with main scene roots
            scene.getStylesheets().add(CssSceneMain); //add css contents to the new scene
            
            Stage stage = new Stage(); //create new stage
            stage.setScene(scene); //set stage's scene
            stage.setTitle("Taksitle!"); //set stage title
            stage.getIcons().add(DataStore.programLogo); //set program logo
            stage.setResizable(false); //set resizeble false
            stage.show(); //show scene
        } catch (IOException ex) {
            System.out.println("/Views/SceneMain.fxml could not be loaded");
        }
    }
    
    public void switchToSceneSignUp(Event e) //switches to signUp scene
    {
        try //switches to signUp scene
        {
            Parent root = FXMLLoader.load(getClass().getResource("/Views/SceneSignUp.fxml")); //get fxml contens
            
            String CssSceneLogOperations = this.getClass().getResource("/Css/CssSceneLogOperations.css").toExternalForm(); //get css contents
            
            Stage stage = (Stage)((Node)e.getSource()).getScene().getWindow(); //get the stage of the event
            
            Scene scene = new Scene(root); //create new scene
            scene.getStylesheets().add(CssSceneLogOperations); //add css contents to our scene
            
            stage.setScene(scene); //set event's stage's scene
            stage.setTitle("Taksitle! - Sign Up");
            stage.show();
        } catch (IOException ex) {
            System.out.println("/Views/SceneSignUp.fxml could not be loaded");
        }
    }
}