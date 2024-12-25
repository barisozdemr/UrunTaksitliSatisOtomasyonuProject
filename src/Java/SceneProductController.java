
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
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.RowConstraints;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class SceneProductController { // Scene of the chosen Product
    
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
    private ScrollPane paymentsScrollPane;
    
    private final Scene mainScene; // The old scene
    
    private final ToggleGroup paymentToggle = new ToggleGroup();
    
    private final String productID = DataStore.chosenProductsID;
    
    public SceneProductController(Scene mainScene) // Constructor to store the old scene as where you left
    {
        this.mainScene = mainScene;
    }
    
    public void initialize()
    {
        usernameMenu.setText(DataStore.loggedUsersName);
        
        usernameMenu.setStyle("-fx-min-height: 70; -fx-min-width: 150;");
        
        setProductInfo();
        
        buildPayments();
    }
    
    // Set chosen products datas
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
    
    // Switches back to SceneMain as where you left
    public void backToSceneMain(MouseEvent e)
    {
        Stage stage = (Stage)borderPane.getScene().getWindow();
        
        stage.setScene(mainScene);
    }
    
    public void programLogoClicked(MouseEvent e) throws IOException
    {
        openSceneMain();
    }
    
    // Opens SceneMain as new scene
    public void openSceneMain()
    {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/Views/SceneMain.fxml"));
            
            String CssSceneMain = this.getClass().getResource("/Css/CssSceneMain.css").toExternalForm();
            
            Scene scene = new Scene(root);
            scene.getStylesheets().add(CssSceneMain);
            
            Stage stage = (Stage)borderPane.getScene().getWindow();
            
            stage.setScene(scene);
        } catch (IOException ex) {
            System.out.println("/Views/SceneMain.fxml could not be loaded");
        }
    }
    
    public void setCursorToHand(MouseEvent e)
    {
        ((Node)e.getSource()).setCursor(Cursor.HAND);
    }
    
    public void setBackButtonBackgroundBrighter() //back button css
    {
        backButtonBackground.setStyle("-fx-background-color: #505050");
    }
    
    public void setBackButtonBackgroundDarker() //back button css
    {
        backButtonBackground.setStyle("-fx-background-color: #353535");
    }
    
    // Build all payment methods of the chosen product
    public void buildPayments()
    {
        Map<String, ArrayList<String>> bankData = DataStore.getBankData();
        
        Map<String, ArrayList<String>> productData = DataStore.getProductData();
        
        AnchorPane paymentsAnchorPane = new AnchorPane();
        
        paymentsAnchorPane.setId("paymentsAnchorPane");
        
        paymentsAnchorPane.setMinWidth(1185);
        paymentsAnchorPane.setMinHeight(200);
        
        GridPane gridPane = new GridPane();
        
        gridPane.getStyleClass().add("grid-pane");
        gridPane.setHgap(10);
        gridPane.setVgap(10);
        
        gridPane.setMinWidth(950);
        
        AnchorPane.setTopAnchor(gridPane, 20.0);
        AnchorPane.setLeftAnchor(gridPane, 130.0);
        
        ColumnConstraints col1 = new ColumnConstraints();
        col1.setPercentWidth(30);
        gridPane.getColumnConstraints().add(col1);
        
        ColumnConstraints col2 = new ColumnConstraints();
        col2.setPercentWidth(18);
        gridPane.getColumnConstraints().add(col2);
        gridPane.getColumnConstraints().add(col2);
        gridPane.getColumnConstraints().add(col2);
        gridPane.getColumnConstraints().add(col2);
        gridPane.getColumnConstraints().add(col2);
        
        RowConstraints row1 = new RowConstraints();
        row1.setMinHeight(100);
        
        int i=0;
        
        for(Map.Entry<String, ArrayList<String>> entry : bankData.entrySet())
        {
            gridPane.getRowConstraints().add(row1);
            
            for(int j=0 ; j<6 ; j++)
            {
                if(j==0)
                {
                    HBox hbox = new HBox();
                    hbox.setId("paymentsHbox");
                    
                    ImageView imageView = new ImageView(new Image(entry.getValue().get(1)));
                    imageView.setFitHeight(70);
                    imageView.setFitWidth(70);
                    
                    hbox.getChildren().add(imageView);
                    
                    Label label1 = new Label(entry.getValue().get(0));
                    label1.setStyle("-fx-padding: 10;-fx-font-size: 15;");
                    
                    hbox.getChildren().add(label1);
                    
                    gridPane.add(hbox, j, i);
                }
                else if(j==1) //advance
                {
                    VBox vbox = new VBox();
                    vbox.setId("paymentsVbox");
                    
                    Label label1 = new Label("Advance");
                    label1.setStyle("-fx-padding: 20 0 0 10");
                    Label label2 = new Label("Total: "+productData.get(productID).get(2)+" TL");
                    label2.setId("paymentsLabel");
                    
                    RadioButton radioButton = new RadioButton("1;" + productData.get(productID).get(2));
                    radioButton.setId("paymentsRadioButton");
                    radioButton.setToggleGroup(paymentToggle);
                    
                    vbox.getChildren().add(label1);
                    vbox.getChildren().add(label2);
                    vbox.getChildren().add(radioButton);
                    
                    gridPane.add(vbox, j, i);
                }
                else if(j==2) // 3 months installment
                {
                    VBox vbox = new VBox();
                    vbox.setId("paymentsVbox");
                    
                    double price = Integer.parseInt(productData.get(productID).get(2)) * (100+Double.parseDouble(entry.getValue().get(2)))/100;
                    
                    String singleInstallment = String.format("%.2f", price/3);
                    
                    Label label1 = new Label("3 Months Installment");
                    label1.setStyle("-fx-padding: 10 0 0 5");
                    Label label2 = new Label("3 x " + singleInstallment + " TL");
                    label2.setId("paymentsLabel");
                    Label label3 = new Label("Total: "+String.format("%.2f", price)+" TL");
                    label3.setId("paymentsLabel");
                    
                    RadioButton radioButton = new RadioButton("3;" + singleInstallment);
                    radioButton.setId("paymentsRadioButton");
                    radioButton.setToggleGroup(paymentToggle);
                    
                    vbox.getChildren().add(label1);
                    vbox.getChildren().add(label2);
                    vbox.getChildren().add(label3);
                    vbox.getChildren().add(radioButton);
                    
                    gridPane.add(vbox, j, i);
                }
                else if(j==3) // 6 months installment
                {
                    VBox vbox = new VBox();
                    vbox.setId("paymentsVbox");
                    
                    double price = Integer.parseInt(productData.get(productID).get(2)) * (100+Double.parseDouble(entry.getValue().get(3)))/100;
                    
                    String singleInstallment = String.format("%.2f", price/6);
                    
                    Label label1 = new Label("6 Months Installment");
                    label1.setStyle("-fx-padding: 10 0 0 5");
                    Label label2 = new Label("6 x " + singleInstallment + " TL");
                    label2.setId("paymentsLabel");
                    Label label3 = new Label("Total: "+String.format("%.2f", price)+" TL");
                    label3.setId("paymentsLabel");
                    
                    RadioButton radioButton = new RadioButton("6;" + singleInstallment);
                    radioButton.setId("paymentsRadioButton");
                    radioButton.setToggleGroup(paymentToggle);
                    
                    vbox.getChildren().add(label1);
                    vbox.getChildren().add(label2);
                    vbox.getChildren().add(label3);
                    vbox.getChildren().add(radioButton);
                    
                    gridPane.add(vbox, j, i);
                }
                else if(j==4) // 9 months installment
                {
                    VBox vbox = new VBox();
                    vbox.setId("paymentsVbox");
                    
                    double price = Integer.parseInt(productData.get(productID).get(2)) * (100+Double.parseDouble(entry.getValue().get(4)))/100;
                    
                    String singleInstallment = String.format("%.2f", price/9);
                    
                    Label label1 = new Label("9 Months Installment");
                    label1.setStyle("-fx-padding: 10 0 0 5");
                    Label label2 = new Label("9 x " + singleInstallment + " TL");
                    label2.setId("paymentsLabel");
                    Label label3 = new Label("Total: "+String.format("%.2f", price)+" TL");
                    label3.setId("paymentsLabel");
                    
                    RadioButton radioButton = new RadioButton("9;" + singleInstallment);
                    radioButton.setId("paymentsRadioButton");
                    radioButton.setToggleGroup(paymentToggle);
                    
                    vbox.getChildren().add(label1);
                    vbox.getChildren().add(label2);
                    vbox.getChildren().add(label3);
                    vbox.getChildren().add(radioButton);
                    
                    gridPane.add(vbox, j, i);
                }
                else if(j==5) // 12 months installment
                {
                    VBox vbox = new VBox();
                    vbox.setId("paymentsVbox");
                    
                    double price = Integer.parseInt(productData.get(productID).get(2)) * (100+Double.parseDouble(entry.getValue().get(5)))/100;
                    
                    String singleInstallment = String.format("%.2f", price/12);
                    
                    Label label1 = new Label("12 Months Installment");
                    label1.setStyle("-fx-padding: 10 0 0 5");
                    Label label2 = new Label("12 x " + singleInstallment + " TL");
                    label2.setId("paymentsLabel");
                    Label label3 = new Label("Total: "+String.format("%.2f", price)+" TL");
                    label3.setId("paymentsLabel");
                    
                    RadioButton radioButton = new RadioButton("12;" + singleInstallment);
                    radioButton.setId("paymentsRadioButton");
                    radioButton.setToggleGroup(paymentToggle);
                    
                    vbox.getChildren().add(label1);
                    vbox.getChildren().add(label2);
                    vbox.getChildren().add(label3);
                    vbox.getChildren().add(radioButton);
                    
                    gridPane.add(vbox, j, i);
                }
            }
            i++;
            
            paymentsAnchorPane.setMinHeight(paymentsAnchorPane.getMinHeight() + 100);
        }
        
        paymentsAnchorPane.getChildren().add(gridPane);
        
        Button buyButton = new Button("Buy");
        buyButton.setId("paymentsBuyButton");
        buyButton.setOnMouseEntered(event -> setCursorToHand(event));
        buyButton.setOnMouseClicked(event -> buyButtonPressed(event));
        AnchorPane.setTopAnchor(buyButton, Double.parseDouble(String.valueOf(i*100))+85);
        AnchorPane.setLeftAnchor(buyButton, 500.0);
        paymentsAnchorPane.getChildren().add(buyButton);
        
        paymentsScrollPane.setContent(paymentsAnchorPane);
    }
    
    public void buyButtonPressed(MouseEvent e)
    {
        if(paymentToggle.getSelectedToggle() != null)
        {
            String[] data = ((RadioButton)paymentToggle.getSelectedToggle()).getText().split(";");
            
            String months = data[0];
            String singlePayment = data[1].replace(",", ".");
            
            if(Buy.tryBuy(months, singlePayment))
            {
                openPurchaseAlert();
                
                openSceneMain();
            }
            else{
                openPurchaseAlert();
            }
        }
    }
    
    public void openPurchaseAlert()
    {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/Views/ScenePurchaseAlert.fxml"));
            
            Stage stage = new Stage();
            
            stage.setScene(new Scene(root));
            stage.setTitle("Taksitle! - Info");
            stage.getIcons().add(DataStore.programLogo);
            stage.setResizable(false);
            stage.show();
        } catch (IOException ex) {
            System.out.println("/Views/ScenePurchaseAlert.fxml could not be loaded");
        }
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
            
            Stage stage = (Stage)borderPane.getScene().getWindow();
            
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
            SceneChangeUsernameController scuc = new SceneChangeUsernameController((Stage)borderPane.getScene().getWindow());
            
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
            SceneChangePasswordController scpc = new SceneChangePasswordController((Stage)borderPane.getScene().getWindow());
            
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
            SceneLogoutAlertController slac = new SceneLogoutAlertController((Stage)borderPane.getScene().getWindow());
            
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
            SceneQuitAlertController sqac = new SceneQuitAlertController((Stage)borderPane.getScene().getWindow());
            
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
