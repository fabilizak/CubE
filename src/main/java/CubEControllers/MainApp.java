package CubEControllers;

import java.io.IOException;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.slf4j.LoggerFactory;

//CHECKSTYLE:OFF
public class MainApp extends Application {
   
    private static final org.slf4j.Logger logger = LoggerFactory.getLogger(MainApp.class);
    
    @Override
    public void start(Stage stage) throws Exception {
        
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/fxml/StartScene.fxml"));
            
            Scene scene = new Scene(root);
            scene.getStylesheets().add("/styles/StartStyle.css");
            
            stage.setResizable(false);
            stage.setTitle("StartScene");
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex){
            logger.error("Couldn't load StartScene.fxml");
            ex.printStackTrace();
            System.exit(1);
        }
    }
    
    public static void main(String[] args) {
        launch(args);
    }
//CHECKSTYLE:OFF
}
