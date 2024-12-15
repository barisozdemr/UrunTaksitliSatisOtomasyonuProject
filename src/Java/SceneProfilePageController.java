
package Java;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Cursor;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class SceneProfilePageController {
    
    @FXML
    private ImageView programLogo;
    @FXML
    private Menu usernameMenu;
    @FXML
    private Label usernameLabel;
    @FXML
    private AnchorPane paymentsAnchorPane;
    
    private String loggedUsersName = DataStore.loggedUsersName;
    
    private Map<String, ArrayList<String>> userData = DataStore.getUserData();
    
    public void initialize()
    {
        programLogo.setOnMouseEntered(event -> setCursorToHand(event));
        
        usernameMenu.setText(loggedUsersName);
        
        usernameMenu.setStyle("-fx-min-height: 70; -fx-min-width: 150;");
        
        usernameLabel.setText(loggedUsersName);
    }
    
    public void programLogoClicked(MouseEvent e) throws IOException
    {
        Parent root = FXMLLoader.load(getClass().getResource("/Views/SceneMain.fxml"));
        
        String CssSceneMain = this.getClass().getResource("/Css/CssSceneMain.css").toExternalForm();
        
        Scene scene = new Scene(root);
        scene.getStylesheets().add(CssSceneMain);
        
        Stage stage = (Stage)paymentsAnchorPane.getScene().getWindow();
        
        stage.setScene(scene);
    }
    
    public void setCursorToHand(MouseEvent e)
    {
        ((Node)e.getSource()).setCursor(Cursor.HAND);
    }
    
    //-------------------------------------------------------------------------------------------------- user methods
    
    public void changeUsernameItemSelected(ActionEvent e) throws IOException //action
    {
        openChangeUsernameStage();
    }
    
    public void changePasswordItemSelected(ActionEvent e) throws IOException //action
    {
        openChangePasswordStage();
    }
    
    public void logoutItemSelected(ActionEvent e) throws IOException //action
    {
        openLogoutAlertStage();
    }
    
    public void quitItemSelected(ActionEvent e) throws IOException //action
    {
        openQuitAlertStage();
    }
    
    public void openChangeUsernameStage() throws IOException
    {
        FXMLLoader usernameChangeSceneLoader = new FXMLLoader(getClass().getResource("/Views/SceneChangeUsername.fxml"));
        
        SceneChangeUsernameController scuc = new SceneChangeUsernameController((Stage)paymentsAnchorPane.getScene().getWindow());
        
        usernameChangeSceneLoader.setController(scuc);
        
        Parent root = usernameChangeSceneLoader.load();
        
        Stage stage = new Stage();
        
        stage.setScene(new Scene(root));
        stage.setResizable(false);
        stage.setTitle("Taksitle! - Change Username");
        stage.getIcons().add(DataStore.programLogo);
        stage.show();
    }
    
    public void openChangePasswordStage() throws IOException
    {
        FXMLLoader changePasswordSceneLoader = new FXMLLoader(getClass().getResource("/Views/SceneChangePassword.fxml"));
        
        SceneChangePasswordController scpc = new SceneChangePasswordController((Stage)paymentsAnchorPane.getScene().getWindow());
        
        changePasswordSceneLoader.setController(scpc);
        
        Parent root = changePasswordSceneLoader.load();
        
        Stage stage = new Stage();
        
        stage.setScene(new Scene(root));
        stage.setResizable(false);
        stage.setTitle("Taksitle! - Change Password");
        stage.getIcons().add(DataStore.programLogo);
        stage.show();
    }
    
    public void openLogoutAlertStage() throws IOException
    {
        FXMLLoader logoutAlertSceneLoader = new FXMLLoader(getClass().getResource("/Views/SceneLogoutAlert.fxml"));
        
        SceneLogoutAlertController slac = new SceneLogoutAlertController((Stage)paymentsAnchorPane.getScene().getWindow());
        
        logoutAlertSceneLoader.setController(slac);
        
        Parent root = logoutAlertSceneLoader.load();
        
        Stage stage = new Stage();
        
        stage.setScene(new Scene(root));
        stage.setResizable(false);
        stage.setTitle("Warning");
        stage.getIcons().add(DataStore.programLogo);
        stage.show();
    }
    
    public void openQuitAlertStage() throws IOException
    {
        FXMLLoader quitAlertSceneLoader = new FXMLLoader(getClass().getResource("/Views/SceneQuitAlert.fxml"));
        
        SceneQuitAlertController sqac = new SceneQuitAlertController((Stage)paymentsAnchorPane.getScene().getWindow());
        
        quitAlertSceneLoader.setController(sqac);
        
        Parent root = quitAlertSceneLoader.load();
        
        Stage stage = new Stage();
        
        stage.setScene(new Scene(root));
        stage.setResizable(false);
        stage.setTitle("Warning");
        stage.getIcons().add(DataStore.programLogo);
        stage.show();
    }
}
