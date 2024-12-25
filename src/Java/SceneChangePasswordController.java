
package Java;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class SceneChangePasswordController {
    
    @FXML
    private PasswordField newPasswordField;
    @FXML
    private PasswordField newPasswordAgainField;
    @FXML
    private PasswordField oldPasswordField;
    @FXML
    private Label notificationChangePassword;
    
    private final Stage mainStage; //stores the main stage to close it
    
    public SceneChangePasswordController(Stage mainStage)
    {
        this.mainStage = mainStage;
    }
    
    public void changeButtonPressed(ActionEvent e) throws IOException
    {
         tryChangePassword();
    }
    
    public void enterKeyPressed(KeyEvent e) throws IOException
    {
        if (e.getCode() == KeyCode.ENTER)
        {
            tryChangePassword();
        }
    }
    
    // Tries to change password
    public void tryChangePassword()
    {
        if(Log.tryChangePassword(oldPasswordField.getText(), newPasswordField.getText(), newPasswordAgainField.getText()))
        {
            try {
                Parent root = FXMLLoader.load(getClass().getResource("/Views/SceneChangePasswordSuccesfullAlert.fxml"));
                
                Stage newStage = new Stage();
                
                newStage.setScene(new Scene(root));
                newStage.setTitle("Info");
                newStage.getIcons().add(DataStore.programLogo);
                newStage.setResizable(false);
                newStage.show();
                
                mainStage.close();
                
                Parent root2 = FXMLLoader.load(getClass().getResource("/Views/SceneSignIn-Primary.fxml"));
                
                Stage stage = (Stage)newPasswordField.getScene().getWindow();
                
                stage.setScene(new Scene(root2));
            } catch (IOException ex) {
                System.out.print("/Views/SceneChangePasswordSuccesfullAlert.fxml or ");
                System.out.println("/Views/SceneSignIn-Primary.fxml could not be loaded");
            }
        }
        else{
            notificationChangePassword.setText(Log.changePasswordNotification);
        }
    }
    
    public void notificationSetNull(MouseEvent e) //clears notificationLabel
    {
        if(notificationChangePassword.getText() != null)
        {
            notificationChangePassword.setText("");
        }
    }
}