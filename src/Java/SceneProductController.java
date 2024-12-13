
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
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class SceneProductController {
    
    @FXML
    private BorderPane borderPane;
    @FXML
    private Menu usernameMenu;
    @FXML
    private AnchorPane backButtonBackground;
    @FXML
    private ImageView productImageView;
    @FXML
    private Label productNameLabel;
    @FXML
    private Label productPriceLabel;
    @FXML
    private AnchorPane paymentsAnchorPane;
    
    private Scene mainScene;
    
    private String productID = DataStore.chosenProductsID;
    
    public SceneProductController(Scene mainScene)
    {
        this.mainScene = mainScene;
    }
    
    public void initialize()
    {
        usernameMenu.setText(DataStore.loggedUsersname);
        
        usernameMenu.setStyle("-fx-min-height: 70; -fx-min-width: 150;");
        
        setProductInfo();
    }
    
    public void setProductInfo()
    {
        Map<String, ArrayList<String>> productData = DataStore.getProductData();
        
        String productName = productData.get(productID).get(0);
        String productImagePath = productData.get(productID).get(1);
        String productPrice = productData.get(productID).get(2);
        
        Image image = new Image(productImagePath);
        
        productImageView.setImage(image);
        productNameLabel.setText(productName);
        productPriceLabel.setText(productPrice+" TL");
    }
    
    public void backToSceneMain(MouseEvent e) throws IOException
    {
        Stage stage = (Stage)borderPane.getScene().getWindow();
        
        stage.setScene(mainScene);
    }
    
    public void setCursorToHand(MouseEvent e)
    {
        ((Node)e.getSource()).setCursor(Cursor.HAND);
    }
    
    public void setBackButtonBackgroundBrighter()
    {
        backButtonBackground.setStyle("-fx-background-color: #505050");
    }
    
    public void setBackButtonBackgroundDarker()
    {
        backButtonBackground.setStyle("-fx-background-color: #353535");
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
        
        SceneChangeUsernameController scuc = new SceneChangeUsernameController((Stage)borderPane.getScene().getWindow());
        
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
        
        SceneChangePasswordController scpc = new SceneChangePasswordController((Stage)borderPane.getScene().getWindow());
        
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
        
        SceneLogoutAlertController slac = new SceneLogoutAlertController((Stage)borderPane.getScene().getWindow());
        
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
        
        SceneQuitAlertController sqac = new SceneQuitAlertController((Stage)borderPane.getScene().getWindow());
        
        quitAlertSceneLoader.setController(sqac);
        
        Parent root = quitAlertSceneLoader.load();
        
        Stage stage = new Stage();
        
        stage.setScene(new Scene(root));
        stage.setResizable(false);
        stage.setTitle("Warning");
        stage.getIcons().add(DataStore.programLogo);
        stage.show();
    }

    private void setCursor(Cursor HAND) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
