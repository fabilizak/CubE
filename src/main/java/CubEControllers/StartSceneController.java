package CubEControllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import org.slf4j.LoggerFactory;

//CHECKSTYLE:OFF
public class StartSceneController implements Initializable {

    private static final org.slf4j.Logger logger = LoggerFactory.getLogger(StartSceneController.class);
    
    @FXML
    private Pane startPane;
    @FXML
    private Button playButton;
    @FXML
    private Button scoreButton;
    @FXML
    private Button exitButton;
    
    @FXML
    public void gameAction(ActionEvent event) {

        try {

            Stage stage = (Stage) startPane.getScene().getWindow();

            FXMLLoader fl = new FXMLLoader(getClass().getResource("/fxml/GameScene.fxml"));
            
            Parent root = fl.load();

            Scene scene = new Scene(root);
            scene.getStylesheets().add("/styles/GameStyle.css");

            scene.setOnKeyPressed((KeyEvent event1) -> {
                switch (event1.getCode()) {
                    case LEFT: fl.<GameSceneController>getController().moveBat("left");break;
                    case RIGHT: fl.<GameSceneController>getController().moveBat("right");break;
                    case SPACE: fl.<GameSceneController>getController().ballAnim.play();break;
                    case ENTER: fl.<GameSceneController>getController().resumeGame();break;
                    case ESCAPE: fl.<GameSceneController>getController().gamePaused();break;
                    case UP: fl.<GameSceneController>getController().speedUp();break;
                    case DOWN: fl.<GameSceneController>getController().slowDown();break;
                    default: break;
                }
            });
            
            stage.setTitle("Cub/E");
            stage.setScene(scene);
            stage.show();
            logger.info("Starting game");

        } catch (IOException ex) {
            logger.error("Couldn't load GameScene.fxml");
            ex.printStackTrace();
            System.exit(1);
        }
    }
    
    @FXML
    public void scoreAction(ActionEvent event) {
    
        try {

            Stage stage = (Stage) startPane.getScene().getWindow();

            Parent root = FXMLLoader.load(getClass().getResource("/fxml/ScoreScene.fxml"));

            Scene scene = new Scene(root);
            scene.getStylesheets().add("/styles/ScoreStyle.css");

            stage.setTitle("Scores");
            stage.setScene(scene);
            stage.show();
            logger.info("Fetching leaderboard");

        } catch (IOException ex) {
            logger.error("Couldn't load ScoreScene.fxml");
            ex.printStackTrace();
            System.exit(1);
        }

    }
    
    @FXML
    public void exitAction(ActionEvent event){
        logger.info("Exiting game");
        Platform.exit();
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        logger.info("Opened main menu");
    }    
//CHECKSTYLE:OFF 
}
