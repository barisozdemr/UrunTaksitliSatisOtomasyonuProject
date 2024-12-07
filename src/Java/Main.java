
package Java;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
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
        stage.setTitle("Taksitli Al!"); //set stage title
        stage.getIcons().add(DataStore.image); //set program logo
        stage.setResizable(false);
        stage.show();
    }
}
