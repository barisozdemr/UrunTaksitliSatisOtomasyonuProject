
package Java;

import java.io.IOException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application{
    
    public static void main(String[] args) {
        launch(args);
    }
    
    @Override
    public void start(Stage stage) {
        try {
            DataStore.loadUserData();
            DataStore.loadProductData();
            DataStore.loadBankData();
            
            Parent root = FXMLLoader.load(getClass().getResource("/Views/SceneSignIn-Primary.fxml"));
            
            String SceneSignInCss = this.getClass().getResource("/css/CssSceneLogOperations.css").toExternalForm();
            
            Scene scene = new Scene(root);
            scene.getStylesheets().add(SceneSignInCss);
            
            stage.setScene(scene);
            stage.setTitle("Taksitle! - Sign In"); //set stage title
            stage.getIcons().add(DataStore.programLogo); //set program logo
            stage.setResizable(false);
            stage.show();
        } catch (IOException ex) {
            System.out.println("/Views/SceneSignIn-Primary.fxml could not be loaded");
        }
    }
}
