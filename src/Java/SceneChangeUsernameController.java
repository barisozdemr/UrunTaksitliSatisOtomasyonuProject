
package Java;

import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class SceneChangeUsernameController {
    
    @FXML
    private TextField newUsernameField;
    @FXML
    private PasswordField oldPasswordField;
    @FXML
    private Label notificationChangeUsername;
    
    private final Stage mainStage;
    
    public SceneChangeUsernameController(Stage mainStage)
    {
        this.mainStage = mainStage;
    }
    
    public void changeButtonPressed(ActionEvent e) throws IOException
    {
         if(Log.tryChangeUsername(newUsernameField.getText(), oldPasswordField.getText()))
         {
             Parent root = FXMLLoader.load(getClass().getResource("/Views/SceneChangeUsernameSuccesfullAlert.fxml"));
             
             Stage newStage = new Stage();
             
             newStage.setScene(new Scene(root));
             newStage.setTitle("Info");
             newStage.getIcons().add(DataStore.programLogo);
             newStage.setResizable(false);
             newStage.show();
             
             mainStage.close();
             
             Parent root2 = FXMLLoader.load(getClass().getResource("/Views/SceneSignIn-Primary.fxml"));
             
             Stage stage = (Stage)newUsernameField.getScene().getWindow();
             
             stage.setScene(new Scene(root2));
         }
         else{
             notificationChangeUsername.setText(Log.changeUsernameNotification);
         }
    }
    
    public void enterKeyPressed(KeyEvent e) throws IOException
    {
        if (e.getCode() == KeyCode.ENTER)
        {
            if(Log.tryChangeUsername(newUsernameField.getText(), oldPasswordField.getText()))
            {
                Parent root = FXMLLoader.load(getClass().getResource("/Views/SceneChangeUsernameSuccesfullAlert.fxml"));

                Stage newStage = new Stage();

                newStage.setScene(new Scene(root));
                newStage.setTitle("Info");
                newStage.getIcons().add(DataStore.programLogo);
                newStage.setResizable(false);
                newStage.show();

                mainStage.close();

                Parent root2 = FXMLLoader.load(getClass().getResource("/Views/SceneSignIn-Primary.fxml"));

                Stage stage = (Stage)newUsernameField.getScene().getWindow();

                stage.setScene(new Scene(root2));
            }
            else{
                notificationChangeUsername.setText(Log.changeUsernameNotification);
            }
        }
    }
    
    public void notificationSetNull(MouseEvent e) //clears notificationLabel
    {
        if(notificationChangeUsername.getText() != null)
        {
            notificationChangeUsername.setText("");
        }
    }
}
