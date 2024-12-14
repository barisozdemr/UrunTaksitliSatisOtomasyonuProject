
package Java;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Cursor;
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
    
    GridPane gridPane = new GridPane();
    
    public void initialize()
    {
        usernameMenu.setText(DataStore.loggedUsersName);
        
        usernameMenu.setStyle("-fx-min-height: 70; -fx-min-width: 150;");
        
        sortMenu.setId("sortMenu");
        
        scrollPane.setStyle("-fx-background-color: #292929;");
        
        gridPane.setPrefWidth(1164);
        
        ColumnConstraints colC = new ColumnConstraints();
        colC.setPercentWidth(50);
        gridPane.getColumnConstraints().add(colC);
        gridPane.getColumnConstraints().add(colC);
        
        RowConstraints row1 = new RowConstraints();
        row1.setMinHeight(250);
        gridPane.getRowConstraints().add(row1);
        
        displayProductsRandom(); //-------------------------------- primary display of products
        
        sortProductData();
    }
    
    //-------------------------------------------------------------------------------------------------- user methods
    
    public void yourProfileItemSelected(ActionEvent e) throws IOException //action
    {
        openProfilePage();
    }
    
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
    
    public void openProfilePage() throws IOException
    {
        Parent root = FXMLLoader.load(getClass().getResource("/Views/SceneProfilePage.fxml"));
        
        Scene scene = new Scene(root);
        scene.getStylesheets().add("/Css/CssSceneProfilePage.css");
        
        Stage stage = (Stage)scrollPane.getScene().getWindow();
        
        stage.setScene(scene);
    }
    
    public void openChangeUsernameStage() throws IOException
    {
        FXMLLoader usernameChangeSceneLoader = new FXMLLoader(getClass().getResource("/Views/SceneChangeUsername.fxml"));
        
        SceneChangeUsernameController scuc = new SceneChangeUsernameController((Stage)scrollPane.getScene().getWindow());
        
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
        
        SceneChangePasswordController scpc = new SceneChangePasswordController((Stage)scrollPane.getScene().getWindow());
        
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
        
        SceneLogoutAlertController slac = new SceneLogoutAlertController((Stage)scrollPane.getScene().getWindow());
        
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
        
        SceneQuitAlertController sqac = new SceneQuitAlertController((Stage)scrollPane.getScene().getWindow());
        
        quitAlertSceneLoader.setController(sqac);
        
        Parent root = quitAlertSceneLoader.load();
        
        Scene scene = new Scene(root);
        scene.getStylesheets().add("/Css/CssSceneLogOperations.css");
        
        Stage stage = new Stage();
        
        stage.setScene(scene);
        stage.setResizable(false);
        stage.setTitle("Warning");
        stage.getIcons().add(DataStore.programLogo);
        stage.show();
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
    
    public void buildGridPane(Map<String, ArrayList<String>> productData) //build gridpane random
    {
        RowConstraints row1 = new RowConstraints();
        row1.setMinHeight(250);
        
        int row = 0;
        int col = 0;
        
        for(Map.Entry<String, ArrayList<String>> entry : productData.entrySet())
        {
            HBox hbox = new HBox();
            hbox.getStyleClass().add("hbox");
            
            String productID = entry.getKey(); //ID
            String productName = entry.getValue().get(0); //name
            String productImagePath = entry.getValue().get(1); //path
            String productPrice = entry.getValue().get(2); //price
            
            Label label1 = new Label(productName);
            label1.setStyle("-fx-text-fill: white;-fx-font-size: 25");
            label1.getStyleClass().add("linkLabel");
            label1.setOnMouseEntered(event -> label1.setCursor(Cursor.HAND));
            label1.setOnMouseClicked(event -> {
                try {
                    switchToSceneProduct(event, productID);
                }
                catch (IOException ex) {
                    ex.printStackTrace();
                }
            });
            
            Label label2 = new Label(productPrice+" TL");
            label2.setStyle("-fx-text-fill: white;-fx-font-size: 15");
            
            Image image = new Image(productImagePath);
            
            ImageView imageView = new ImageView(image);
            imageView.setFitHeight(180);
            imageView.setFitWidth(180);
            imageView.getStyleClass().add("image-view");
            imageView.setOnMouseEntered(event -> imageView.setCursor(Cursor.HAND));
            imageView.setOnMouseClicked(event -> {
                try {
                    switchToSceneProduct(event, productID);
                }
                catch (IOException ex) {
                    ex.printStackTrace();
                }
            });
            
            hbox.getChildren().add(imageView);
            
            VBox vbox = new VBox();
            vbox.getStyleClass().add("vbox");
            
            vbox.getChildren().add(label1);
            vbox.getChildren().add(label2);
            
            hbox.getChildren().add(vbox);
            
            gridPane.add(hbox, col, row);
            gridPane.getStyleClass().add("gridpane");
            
            col++;
            if(col == 2) {
                row++;
                col = 0;
                gridPane.getRowConstraints().add(row1);
            }
        }
        
        scrollPane.setContent(gridPane);
    }
    
    public void buildGridPane(Map<String, ArrayList<String>> productData, ArrayList<String> sortedID_LowToHigh) //build gridpane low to high
    {
        int row = 0;
        int col = 0;
        
        for(String ID : sortedID_LowToHigh)
        {
            HBox hbox = new HBox();
            hbox.getStyleClass().add("hbox");
            
            String productID = ID; //ID
            String productName = productData.get(ID).get(0); //name
            String productImagePath = productData.get(ID).get(1); //path
            String productPrice = productData.get(ID).get(2); //price
            
            Label label1 = new Label(productName);
            label1.setStyle("-fx-text-fill: white;-fx-font-size: 25");
            label1.getStyleClass().add("linkLabel");
            label1.setOnMouseEntered(event -> label1.setCursor(Cursor.HAND));
            label1.setOnMouseClicked(event -> {
                try {
                    switchToSceneProduct(event, productID);
                }
                catch (IOException ex) {
                    ex.printStackTrace();
                }
            });
            
            Label label2 = new Label(productPrice+" TL");
            label2.setStyle("-fx-text-fill: white;-fx-font-size: 15");
            
            Image image = new Image(productImagePath);
            
            ImageView imageView = new ImageView(image);
            imageView.setFitHeight(180);
            imageView.setFitWidth(180);
            imageView.setOnMouseEntered(event -> imageView.setCursor(Cursor.HAND));
            imageView.setOnMouseClicked(event -> {
                try {
                    switchToSceneProduct(event, productID);
                }
                catch (IOException ex) {
                    ex.printStackTrace();
                }
            });
            
            hbox.getChildren().add(imageView);
            
            VBox vbox = new VBox();
            vbox.getStyleClass().add("vbox");
            
            vbox.getChildren().add(label1);
            vbox.getChildren().add(label2);
            
            hbox.getChildren().add(vbox);
            
            gridPane.add(hbox, col, row);
            gridPane.getStyleClass().add("gridpane");
            
            col++;
            if(col == 2) {
                row++;
                col = 0;
            }
        }
        
        scrollPane.setContent(gridPane);
    }
    
    public void buildGridPane(Map<String, ArrayList<String>> productData, ArrayList<String> sortedID_LowToHigh, int a) //build gridpane high to low
    {
        int row = 0;
        int col = 0;
        
        for(int i=sortedID_LowToHigh.size()-1 ; 0<=i ; i--)
        {
            HBox hbox = new HBox();
            hbox.getStyleClass().add("hbox");
            
            String productID = sortedID_LowToHigh.get(i); //ID
            String productName = productData.get(sortedID_LowToHigh.get(i)).get(0); //name
            String productImagePath = productData.get(sortedID_LowToHigh.get(i)).get(1); //path
            String productPrice = productData.get(sortedID_LowToHigh.get(i)).get(2); //price
            
            Label label1 = new Label(productName);
            label1.setStyle("-fx-text-fill: white;-fx-font-size: 25");
            label1.getStyleClass().add("linkLabel");
            label1.setOnMouseEntered(event -> label1.setCursor(Cursor.HAND));
            label1.setOnMouseClicked(event -> {
                try {
                    switchToSceneProduct(event, productID);
                }
                catch (IOException ex) {
                    ex.printStackTrace();
                }
            });
            
            Label label2 = new Label(productPrice+" TL");
            label2.setStyle("-fx-text-fill: white;-fx-font-size: 15");
            
            Image image = new Image(productImagePath);
            
            ImageView imageView = new ImageView(image);
            imageView.setFitHeight(180);
            imageView.setFitWidth(180);
            imageView.setOnMouseEntered(event -> imageView.setCursor(Cursor.HAND));
            imageView.setOnMouseClicked(event -> {
                try {
                    switchToSceneProduct(event, productID);
                }
                catch (IOException ex) {
                    ex.printStackTrace();
                }
            });
            
            hbox.getChildren().add(imageView);
            
            VBox vbox = new VBox();
            vbox.getStyleClass().add("vbox");
            
            vbox.getChildren().add(label1);
            vbox.getChildren().add(label2);
            
            hbox.getChildren().add(vbox);
            
            gridPane.add(hbox, col, row);
            gridPane.getStyleClass().add("gridpane");
            
            col++;
            if(col == 2) {
                row++;
                col = 0;
            }
        }
        
        scrollPane.setContent(gridPane);
    }
    
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
    
    //-------------------------------------------------------------------------------------------------- product methods
    
    public void switchToSceneProduct(MouseEvent e, String productID) throws IOException
    {
        DataStore.chosenProductsID = productID;
        
        FXMLLoader sceneProductLoader = new FXMLLoader(getClass().getResource("/Views/SceneProduct.fxml"));
        
        SceneProductController spc = new SceneProductController(scrollPane.getScene());
        
        sceneProductLoader.setController(spc);
        
        Parent root = sceneProductLoader.load();
        
        Scene scene = new Scene(root);
        scene.getStylesheets().add("/Css/CssSceneProduct.css");
        
        Stage stage = (Stage)scrollPane.getScene().getWindow();
        
        stage.setScene(scene);
    }
}
