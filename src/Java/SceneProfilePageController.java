
package Java;

import javafx.scene.paint.Color;
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
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Line;
import javafx.stage.Stage;

public class SceneProfilePageController {
    
    @FXML
    private Menu usernameMenu;
    @FXML
    private Label usernameLabel;
    @FXML
    private AnchorPane paymentsAnchorPane;
    
    private final String loggedUsersName = DataStore.loggedUsersName;
    
    public void initialize()
    {
        usernameMenu.setText(loggedUsersName);
        
        usernameMenu.setStyle("-fx-min-height: 70; -fx-min-width: 150;");
        
        usernameLabel.setText(loggedUsersName);
        
        buildPayments();
    }
    
    public void programLogoClicked(MouseEvent e)
    {
        openSceneMain();
    }
    
    public void openSceneMain()
    {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/Views/SceneMain.fxml"));
            
            String CssSceneMain = this.getClass().getResource("/Css/CssSceneMain.css").toExternalForm();
            
            Scene scene = new Scene(root);
            scene.getStylesheets().add(CssSceneMain);
            
            Stage stage = (Stage)paymentsAnchorPane.getScene().getWindow();
            
            stage.setScene(scene);
        } catch (IOException ex) {
            System.out.println("/Views/SceneMain.fxml could not be loaded");
        }
    }
    
    public void setCursorToHand(MouseEvent e)
    {
        ((Node)e.getSource()).setCursor(Cursor.HAND);
    }
    
    // Sets users payments of each month respectively
    public void buildPayments()
    {
        Map<String, ArrayList<String>> userData = DataStore.getUserData();
        
        GridPane gridPane = new GridPane();
        
        AnchorPane.setTopAnchor(gridPane, 120.0);
        AnchorPane.setLeftAnchor(gridPane, 44.0);
        
        gridPane.getStyleClass().add("grid-pane");
        gridPane.setHgap(10);
        gridPane.setVgap(10);
        gridPane.setMinWidth(1100);
        
        ColumnConstraints col1 = new ColumnConstraints();
        col1.setPercentWidth(100 / 12.0);
        gridPane.getColumnConstraints().add(col1);
        gridPane.getColumnConstraints().add(col1);
        gridPane.getColumnConstraints().add(col1);
        gridPane.getColumnConstraints().add(col1);
        gridPane.getColumnConstraints().add(col1);
        gridPane.getColumnConstraints().add(col1);
        gridPane.getColumnConstraints().add(col1);
        gridPane.getColumnConstraints().add(col1);
        gridPane.getColumnConstraints().add(col1);
        gridPane.getColumnConstraints().add(col1);
        gridPane.getColumnConstraints().add(col1);
        gridPane.getColumnConstraints().add(col1);
        
        RowConstraints row1 = new RowConstraints();
        row1.setMinHeight(100);
        gridPane.getRowConstraints().add(row1);
        
        for(int i=0 ; i<12 ; i++) // one cycle for each month
        {
            VBox vbox = new VBox();
            vbox.getStyleClass().add("vbox");
            
            Label label;
            
            if(i==0)
            {
                label = new Label(String.valueOf(i+1)+"st Month's");
                label.setStyle("-fx-padding: 8 0 0 11;");
            }
            else if(i==1)
            {
                label = new Label(String.valueOf(i+1)+"nd Month's");
                label.setStyle("-fx-padding: 8 0 0 11;");
            }
            else if(i==2)
            {
                label = new Label(String.valueOf(i+1)+"rd Month's");
                label.setStyle("-fx-padding: 8 0 0 11;");
            }
            else if(i==9 || i==10 || i==11)
            {
                label = new Label(String.valueOf(i+1)+"th Month's");
                label.setStyle("-fx-padding: 8 0 0 6;");
            }
            else{
                label = new Label(String.valueOf(i+1)+"th Month's");
                label.setStyle("-fx-padding: 8 0 0 11;");
            }
            
            Label label2 = new Label("Payment");
            label2.setStyle("-fx-padding: 0 0 0 20");
            
            Line line = new Line();
            line.setStartX(0);
            line.setStartY(25);
            line.setEndX(87);
            line.setEndY(25);
            line.setStrokeWidth(1);
            line.setStroke(Color.web("#606060"));
            
            double payment = Double.parseDouble(userData.get(DataStore.loggedUsersName).get(i+1));
            
            Label label3 = new Label(String.format("%.2f", payment)+" TL");
            label3.setStyle("-fx-padding: 8 0 0 7;");
            
            vbox.getChildren().add(label);
            vbox.getChildren().add(label2);
            vbox.getChildren().add(line);
            vbox.getChildren().add(label3);
            
            gridPane.add(vbox, i, 0);
        }
        
        paymentsAnchorPane.getChildren().add(gridPane);
    }
    
    //-------------------------------------------------------------------------------------------------- user methods
    
    public void yourProfileItemSelected(ActionEvent e) //action
    {
        openProfilePage();
    }
    
    public void changeUsernameItemSelected(ActionEvent e) //action
    {
        openChangeUsernameStage();
    }
    
    public void changePasswordItemSelected(ActionEvent e) //action
    {
        openChangePasswordStage();
    }
    
    public void logoutItemSelected(ActionEvent e) //action
    {
        openLogoutAlertStage();
    }
    
    public void quitItemSelected(ActionEvent e) //action
    {
        openQuitAlertStage();
    }
    
    // Open profile page
    public void openProfilePage()
    {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/Views/SceneProfilePage.fxml"));
            
            Scene scene = new Scene(root);
            scene.getStylesheets().add("/Css/CssSceneProfilePage.css");
            
            Stage stage = (Stage)paymentsAnchorPane.getScene().getWindow();
            
            stage.setScene(scene);
        } catch (IOException ex) {
            System.out.println("/Views/SceneProfilePage.fxml could not be loaded");
        }
    }
    
    // Open change username stage if user tries to change their username
    public void openChangeUsernameStage()
    {
        try {
            FXMLLoader usernameChangeSceneLoader = new FXMLLoader(getClass().getResource("/Views/SceneChangeUsername.fxml"));
            
            // Create a controller with this stage's referance
            SceneChangeUsernameController scuc = new SceneChangeUsernameController((Stage)paymentsAnchorPane.getScene().getWindow());
            
            usernameChangeSceneLoader.setController(scuc);
            
            Parent root = usernameChangeSceneLoader.load();
            
            Stage stage = new Stage();
            
            stage.setScene(new Scene(root));
            stage.setResizable(false);
            stage.setTitle("Taksitle! - Change Username");
            stage.getIcons().add(DataStore.programLogo);
            stage.show();
        } catch (IOException ex) {
            System.out.println("/Views/SceneChangeUsername.fxml could not be loaded");
        }
    }
    
    // Open change password stage if user tries to change their password
    public void openChangePasswordStage()
    {
        try {
            FXMLLoader changePasswordSceneLoader = new FXMLLoader(getClass().getResource("/Views/SceneChangePassword.fxml"));
            
            // Create a controller with this stage's referance
            SceneChangePasswordController scpc = new SceneChangePasswordController((Stage)paymentsAnchorPane.getScene().getWindow());
            
            changePasswordSceneLoader.setController(scpc);
            
            Parent root = changePasswordSceneLoader.load();
            
            Stage stage = new Stage();
            
            stage.setScene(new Scene(root));
            stage.setResizable(false);
            stage.setTitle("Taksitle! - Change Password");
            stage.getIcons().add(DataStore.programLogo);
            stage.show();
        } catch (IOException ex) {
            System.out.println("/Views/SceneChangePassword.fxml could not be loaded");
        }
    }
    
    // Open logout alert if user tries to logout
    public void openLogoutAlertStage()
    {
        try {
            FXMLLoader logoutAlertSceneLoader = new FXMLLoader(getClass().getResource("/Views/SceneLogoutAlert.fxml"));
            
            // Create a controller with this stage's referance
            SceneLogoutAlertController slac = new SceneLogoutAlertController((Stage)paymentsAnchorPane.getScene().getWindow());
            
            logoutAlertSceneLoader.setController(slac);
            
            Parent root = logoutAlertSceneLoader.load();
            
            Stage stage = new Stage();
            
            stage.setScene(new Scene(root));
            stage.setResizable(false);
            stage.setTitle("Warning");
            stage.getIcons().add(DataStore.programLogo);
            stage.show();
        } catch (IOException ex) {
            System.out.println("/Views/SceneLogoutAlert.fxml could not be loaded");
        }
    }
    
    // Open quit alert if user tries to quit
    public void openQuitAlertStage()
    {
        try {
            FXMLLoader quitAlertSceneLoader = new FXMLLoader(getClass().getResource("/Views/SceneQuitAlert.fxml"));
            
            // Create a controller with this stage's referance
            SceneQuitAlertController sqac = new SceneQuitAlertController((Stage)paymentsAnchorPane.getScene().getWindow());
            
            quitAlertSceneLoader.setController(sqac);
            
            Parent root = quitAlertSceneLoader.load();
            
            Stage stage = new Stage();
            
            stage.setScene(new Scene(root));
            stage.setResizable(false);
            stage.setTitle("Warning");
            stage.getIcons().add(DataStore.programLogo);
            stage.show();
        } catch (IOException ex) {
            System.out.println("/Views/SceneQuitAlert.fxml");
        }
    }
}
