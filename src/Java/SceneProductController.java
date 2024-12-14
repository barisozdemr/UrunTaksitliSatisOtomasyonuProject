
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
    private ScrollPane paymentsScrollPane;
    
    private Scene mainScene;
    
    private String productID = DataStore.chosenProductsID;
    
    public SceneProductController(Scene mainScene)
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
        gridPane.getRowConstraints().add(row1);
        gridPane.getRowConstraints().add(row1);
        
        ToggleGroup paymentToggle = new ToggleGroup();
        
        int i=0;
        for(Map.Entry<String, ArrayList<String>> entry : bankData.entrySet())
        {
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
        AnchorPane.setTopAnchor(buyButton, Double.parseDouble(String.valueOf(i*100))+85);
        AnchorPane.setLeftAnchor(buyButton, 500.0);
        paymentsAnchorPane.getChildren().add(buyButton);
        
        paymentsScrollPane.setContent(paymentsAnchorPane);
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
}
