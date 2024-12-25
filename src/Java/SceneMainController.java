
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
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.RowConstraints;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class SceneMainController {
    
    @FXML
    private ScrollPane scrollPane;
    @FXML
    private Menu usernameMenu;
    @FXML
    private Menu sortMenu;
    
    private Map<String, ArrayList<String>> productData = DataStore.getProductData();
    
    private ArrayList<String> sortedID_LowToHigh = new ArrayList<>();
    
    GridPane gridPane = new GridPane(); // GridPane of the products
    
    public void initialize()
    {
        usernameMenu.setText(DataStore.loggedUsersName);
        
        usernameMenu.setStyle("-fx-min-height: 70; -fx-min-width: 150;");
        
        sortMenu.setId("sortMenu");
        
        scrollPane.setStyle("-fx-background-color: #292929;");
        
        gridPane.setPrefWidth(1164);
        
        ColumnConstraints colC = new ColumnConstraints(); //set gridpane's column constraints
        colC.setPercentWidth(50);
        gridPane.getColumnConstraints().add(colC);
        gridPane.getColumnConstraints().add(colC);
        
        RowConstraints row1 = new RowConstraints(); //set gridpane's row constraints
        row1.setMinHeight(250);
        gridPane.getRowConstraints().add(row1);
        
        displayProductsRandom(); //-------------------------------- primary display of products
        
        sortProductData();
    }
    
    // Opens SceneMain as new scene
    public void programLogoClicked(MouseEvent e)
    {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/Views/SceneMain.fxml"));
            
            String CssSceneMain = this.getClass().getResource("/Css/CssSceneMain.css").toExternalForm();
            
            Scene scene = new Scene(root);
            scene.getStylesheets().add(CssSceneMain);
            
            Stage stage = (Stage)scrollPane.getScene().getWindow();
            
            stage.setScene(scene);
        } catch (IOException ex) {
            System.out.println("/Views/SceneMain.fxml could not be loaded");
        }
    }
    
    public void setCursorToHand(MouseEvent e)
    {
        ((Node)e.getSource()).setCursor(Cursor.HAND);
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
            
            Stage stage = (Stage)scrollPane.getScene().getWindow();
            
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
            SceneChangeUsernameController scuc = new SceneChangeUsernameController((Stage)scrollPane.getScene().getWindow());
            
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
            SceneChangePasswordController scpc = new SceneChangePasswordController((Stage)scrollPane.getScene().getWindow());
            
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
            SceneLogoutAlertController slac = new SceneLogoutAlertController((Stage)scrollPane.getScene().getWindow());
            
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
            SceneQuitAlertController sqac = new SceneQuitAlertController((Stage)scrollPane.getScene().getWindow());
            
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
    
    //-------------------------------------------------------------------------------------------------- sorting methods
    
    public void sortRandomSelected(ActionEvent e) //action
    {
        displayProductsRandom();
    }
    
    public void sortLowToHighSelected(ActionEvent e) //action
    {
        displayProductsLowToHigh();
    }
    
    public void sortHighToLowSelected(ActionEvent e) //action
    {
        displayProductsHighToLow();
    }
    
    public void displayProductsRandom()
    {
        sortMenu.setText("Random");
        buildGridPane(productData);
    }
    
    public void displayProductsLowToHigh()
    {
        sortMenu.setText("Lowest Price First");
        buildGridPane(productData, sortedID_LowToHigh);
    }
    
    public void displayProductsHighToLow()
    {
        sortMenu.setText("Highest Price First");
        buildGridPane(productData, sortedID_LowToHigh, -1);
    }
    
    // Build gridpane in random order
    public void buildGridPane(Map<String, ArrayList<String>> productData)
    {
        RowConstraints row1 = new RowConstraints();
        row1.setMinHeight(250);
        
        int row = 0;
        int col = 0;
        
        for(Map.Entry<String, ArrayList<String>> entry : productData.entrySet())
        {
            HBox hbox = new HBox();
            hbox.getStyleClass().add("hbox");
            
            String productID = entry.getKey(); //----------------- ID
            String productName = entry.getValue().get(0); //------ name
            String productImagePath = entry.getValue().get(1); //- path
            String productPrice = entry.getValue().get(2); //----- price
            
            Label label1 = new Label(productName);
            label1.setStyle("-fx-text-fill: white;-fx-font-size: 25");
            label1.getStyleClass().add("linkLabel");
            label1.setOnMouseEntered(event -> label1.setCursor(Cursor.HAND));
            label1.setOnMouseClicked(event -> switchToSceneProduct(event, productID));
            
            Label label2 = new Label(productPrice+" TL");
            label2.setStyle("-fx-text-fill: white;-fx-font-size: 15");
            
            Image image = new Image(productImagePath);
            
            ImageView imageView = new ImageView(image);
            imageView.setFitHeight(180);
            imageView.setFitWidth(180);
            imageView.getStyleClass().add("image-view");
            imageView.setOnMouseEntered(event -> imageView.setCursor(Cursor.HAND));
            imageView.setOnMouseClicked(event -> switchToSceneProduct(event, productID));
            
            hbox.getChildren().add(imageView);
            
            VBox vbox = new VBox();
            vbox.getStyleClass().add("vbox");
            
            vbox.getChildren().add(label1);
            vbox.getChildren().add(label2);
            
            hbox.getChildren().add(vbox);
            
            gridPane.add(hbox, col, row);
            gridPane.getStyleClass().add("gridpane");
            
            col++;
            if(col == 2) { //add a row in every 2 products
                row++;
                col = 0;
                gridPane.getRowConstraints().add(row1);
            }
        }
        
        scrollPane.setContent(gridPane);
    }
    
    // Build gridpane as low to high price
    public void buildGridPane(Map<String, ArrayList<String>> productData, ArrayList<String> sortedID_LowToHigh)
    {
        int row = 0;
        int col = 0;
        
        for(String ID : sortedID_LowToHigh)
        {
            HBox hbox = new HBox();
            hbox.getStyleClass().add("hbox");
            
            String productID = ID; //-------------------------------- ID
            String productName = productData.get(ID).get(0); //------ name
            String productImagePath = productData.get(ID).get(1); //- path
            String productPrice = productData.get(ID).get(2); //----- price
            
            Label label1 = new Label(productName);
            label1.setStyle("-fx-text-fill: white;-fx-font-size: 25");
            label1.getStyleClass().add("linkLabel");
            label1.setOnMouseEntered(event -> label1.setCursor(Cursor.HAND));
            label1.setOnMouseClicked(event -> switchToSceneProduct(event, productID));
            
            Label label2 = new Label(productPrice+" TL");
            label2.setStyle("-fx-text-fill: white;-fx-font-size: 15");
            
            Image image = new Image(productImagePath);
            
            ImageView imageView = new ImageView(image);
            imageView.setFitHeight(180);
            imageView.setFitWidth(180);
            imageView.setOnMouseEntered(event -> imageView.setCursor(Cursor.HAND));
            imageView.setOnMouseClicked(event -> switchToSceneProduct(event, productID));
            
            hbox.getChildren().add(imageView);
            
            VBox vbox = new VBox();
            vbox.getStyleClass().add("vbox");
            
            vbox.getChildren().add(label1);
            vbox.getChildren().add(label2);
            
            hbox.getChildren().add(vbox);
            
            gridPane.add(hbox, col, row);
            gridPane.getStyleClass().add("gridpane");
            
            col++;
            if(col == 2) { //add a row in every 2 products
                row++;
                col = 0;
            }
        }
        
        scrollPane.setContent(gridPane);
    }
    
    // Build gridpane as high to low price
    public void buildGridPane(Map<String, ArrayList<String>> productData, ArrayList<String> sortedID_LowToHigh, int a)
    {
        int row = 0;
        int col = 0;
        
        for(int i=sortedID_LowToHigh.size()-1 ; 0<=i ; i--) // i goes in opposite order because the list is sorted low to high
        {
            HBox hbox = new HBox();
            hbox.getStyleClass().add("hbox");
            
            String productID = sortedID_LowToHigh.get(i); //-------------------------------- ID
            String productName = productData.get(sortedID_LowToHigh.get(i)).get(0); //------ name
            String productImagePath = productData.get(sortedID_LowToHigh.get(i)).get(1); //- path
            String productPrice = productData.get(sortedID_LowToHigh.get(i)).get(2); //----- price
            
            Label label1 = new Label(productName);
            label1.setStyle("-fx-text-fill: white;-fx-font-size: 25");
            label1.getStyleClass().add("linkLabel");
            label1.setOnMouseEntered(event -> label1.setCursor(Cursor.HAND));
            label1.setOnMouseClicked(event -> switchToSceneProduct(event, productID));
            
            Label label2 = new Label(productPrice+" TL");
            label2.setStyle("-fx-text-fill: white;-fx-font-size: 15");
            
            Image image = new Image(productImagePath);
            
            ImageView imageView = new ImageView(image);
            imageView.setFitHeight(180);
            imageView.setFitWidth(180);
            imageView.setOnMouseEntered(event -> imageView.setCursor(Cursor.HAND));
            imageView.setOnMouseClicked(event -> switchToSceneProduct(event, productID));
            
            hbox.getChildren().add(imageView);
            
            VBox vbox = new VBox();
            vbox.getStyleClass().add("vbox");
            
            vbox.getChildren().add(label1);
            vbox.getChildren().add(label2);
            
            hbox.getChildren().add(vbox);
            
            gridPane.add(hbox, col, row);
            gridPane.getStyleClass().add("gridpane");
            
            col++;
            if(col == 2) { //add a row in every 2 products
                row++;
                col = 0;
            }
        }
        
        scrollPane.setContent(gridPane);
    }
    
    // Sorts products ID's based on their prices as low to high
    public void sortProductData()
    {
        if(sortedID_LowToHigh == null)
        {
            return;
        }
        
        int lowest;
        
        for(int i=0 ; i<productData.size() ; i++)
        {
            lowest = Integer.MAX_VALUE;
            for(Map.Entry<String, ArrayList<String>> entry : productData.entrySet())
            {
                if(! sortedID_LowToHigh.contains(entry.getKey()))
                {
                    if(Integer.parseInt(entry.getValue().get(2)) < lowest)
                    {
                        lowest = Integer.parseInt(entry.getValue().get(2));
                    }
                }
            }
  
            for(String key : productData.keySet())
            {
                if(Integer.parseInt(productData.get(key).get(2)) == lowest)
                {
                    sortedID_LowToHigh.add(key);
                }
            }
        }
    }
    
    //-------------------------------------------------------------------------------------------------- switch scene product
    
    public void switchToSceneProduct(MouseEvent e, String productID)
    {
        try {
            DataStore.chosenProductsID = productID; // Set chosen products ID before opening its scene
            
            FXMLLoader sceneProductLoader = new FXMLLoader(getClass().getResource("/Views/SceneProduct.fxml"));
            
            // Create a controller with this scene's referance
            SceneProductController spc = new SceneProductController(scrollPane.getScene());
            
            sceneProductLoader.setController(spc);
            
            Parent root = sceneProductLoader.load();
            
            Scene scene = new Scene(root);
            scene.getStylesheets().add("/Css/CssSceneProduct.css");
            
            Stage stage = (Stage)scrollPane.getScene().getWindow();
            
            stage.setScene(scene);
        } catch (IOException ex) {
            System.out.println("/Views/SceneProduct.fxml could not be loaded");
        }
    }
}
