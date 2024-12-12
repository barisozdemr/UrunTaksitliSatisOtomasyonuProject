
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
    
    public void signInButtonPressed(ActionEvent e) throws IOException //action
    {
        if(Log.trySignIn(usernameSignInField.getText(), passwordSignInField.getText()))
        {
            DataStore.loggedUsersname = usernameSignInField.getText();
            openSceneMain(e);
        }
        else{
            notificationSignIn.setText("Username or password is incorrect");
        }
    }
    
    public void backToSignUpButtonPressed(ActionEvent e) throws IOException //action
    {
        switchToSceneSignUp(e);
    }
    
    public void enterKeyPressed(KeyEvent e) throws IOException
    {
        if(Log.trySignIn(usernameSignInField.getText(), passwordSignInField.getText()))
        {
            DataStore.loggedUsersname = usernameSignInField.getText();
            openSceneMain(e);
        }
        else{
            notificationSignIn.setText("Username or password is incorrect");
        }
    }
    
    public void notificationSetNull(MouseEvent e) //clears notificationLabel
    {
        if(notificationSignIn.getText() != null)
        {
            notificationSignIn.setText("");
        }
    }
    
    public void openSceneMain(Event e) throws IOException
    {
        Stage oldStage = (Stage)((Node)e.getSource()).getScene().getWindow(); //get sign in stage
        oldStage.close(); //close sign in stage
        
        String CssSceneMain = this.getClass().getResource("/Css/CssSceneMain.css").toExternalForm(); // get css contents
        
        Parent root = FXMLLoader.load(getClass().getResource("/Views/SceneMain.fxml")); //get fxml contents (Main Scene)
        
        Scene scene = new Scene(root); //create new stage with main scene roots
        scene.getStylesheets().add(CssSceneMain); //add css contents to the new scene
        
        Stage stage = new Stage(); //create new stage
        stage.setScene(scene); //set stage's scene
        stage.setTitle("Taksitli Al!"); //set stage title
        stage.getIcons().add(DataStore.image); //set program logo
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