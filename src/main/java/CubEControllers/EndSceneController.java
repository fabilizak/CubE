package CubEControllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import CubeDB.WriteXML;
import javafx.scene.text.Text;
import org.slf4j.LoggerFactory;

//CHECKSTYLE:OFF
public class EndSceneController implements Initializable {

    private static final org.slf4j.Logger logger = LoggerFactory.getLogger(EndSceneController.class);
    
    String errorMessage = "";
    
    @FXML
    private Pane endPane;
    
    @FXML
    private Text userText;
            
    @FXML
    private Text scoreText;
            
    @FXML
    private Text submittedText;
    
    @FXML
    private Label scoreLabel;
    
    @FXML
    private Label errorLabel;
    
    @FXML
    private TextField nameField;
    
    @FXML
    private Button replayButton;
    
    @FXML
    private Button menuButton;
    
    @FXML
    private Button submitButton;
    
    public void submitAction(ActionEvent event) throws Exception{
        
        WriteXML w = new WriteXML();
        
        errorMessage = errorCheck(nameField.getText());
        if(errorMessage.isEmpty()){
            w.createXml(nameField.getText(), scoreLabel.getText());
            errorLabel.setText("");
            submittedText.setVisible(true);
            submitButton.setVisible(false);
            scoreText.setVisible(false);
            userText.setVisible(false);
            scoreLabel.setVisible(false);
            nameField.setVisible(false);
            logger.info("Score submitted");
        } else {
            errorLabel.setText(errorMessage);
        }
        nameField.setText("");
    }
    
    @FXML
    public void replayAction(ActionEvent event) {

        try {

            Stage stage = (Stage) endPane.getScene().getWindow();

            FXMLLoader fl = new FXMLLoader(getClass().getResource("/fxml/GameScene.fxml"));
            
            Parent root = fl.load();

            Scene scene = new Scene(root);
            scene.getStylesheets().add("/styles/GameStyle.css");

            scene.setOnKeyPressed(new EventHandler<KeyEvent>() {

            @Override
            public void handle(KeyEvent event) {
                switch (event.getCode()) {
                    case LEFT: fl.<GameSceneController>getController().moveBat("left");break;
                    case RIGHT: fl.<GameSceneController>getController().moveBat("right");break;
                    case SPACE: fl.<GameSceneController>getController().ballAnim.play();break;
                    case ESCAPE: fl.<GameSceneController>getController().gamePaused();break;
                    default: break;
                }
            }
                });
            
            stage.setTitle("Cub/E");
            stage.setScene(scene);
            stage.show();
            logger.info("Restarting game");

        } catch (IOException ex) {
            logger.error("Couldn't load GameScene.fxml");
            ex.printStackTrace();
            System.exit(1);
        }
    }
    
    @FXML
    public void menuAction(ActionEvent event) {
    
        try {

            Stage stage = (Stage) endPane.getScene().getWindow();

            Parent root = FXMLLoader.load(getClass().getResource("/fxml/StartScene.fxml"));

            Scene scene = new Scene(root);
            scene.getStylesheets().add("/styles/StartStyle.css");

            stage.setTitle("StartScene");
            stage.setScene(scene);
            stage.show();
            logger.info("Exiting to main menu");
            
        } catch (IOException ex) {
            logger.error("Couldn't load StartScene.fxml");
            ex.printStackTrace();
            System.exit(1);
        }

    }
    
    public String errorCheck(String text){
        String toReturn = "";
        
        if(text.length() < 3){
            toReturn = "Username must be atleast 3 characters!";
            logger.warn("Username must be atleast 3 characters!");
        }
        if(text.contains(" ")){
            toReturn = "Username must not contain spaces!";
            logger.warn("Username must not contain spaces!");
        }
        
        return toReturn;
    }
    
    public void setScore(int scoreVal) {
        scoreLabel.setText(String.valueOf(scoreVal));
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        submittedText.setVisible(false);
    }
//CHECKSTYLE:ON
}
