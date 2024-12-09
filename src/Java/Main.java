
package Java;

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
    public void start(Stage stage) throws Exception {
        DataStore.loadUserData();
        DataStore.loadProductData();
        DataStore.loadBankData();
        
        Parent root = FXMLLoader.load(getClass().getResource("/Views/SceneSignIn-Primary.fxml"));
        
        String SceneSignInCss = this.getClass().getResource("/css/CssSceneLogOperations.css").toExternalForm();
        
        Scene scene = new Scene(root);
        scene.getStylesheets().add(SceneSignInCss);
        
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }
}
