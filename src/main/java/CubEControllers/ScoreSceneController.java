package CubEControllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import CubeDB.ReadXML;
import javafx.collections.ObservableList;
import CubE.Player;
import org.slf4j.LoggerFactory;

//CHECKSTYLE:OFF
public class ScoreSceneController implements Initializable {

    private static final org.slf4j.Logger logger = LoggerFactory.getLogger(ScoreSceneController.class);
    
    ObservableList<Player> obsList;
    
    @FXML
    private Pane scorePane;
    @FXML
    private Button returnButton;
    @FXML
    private TableView<Player> scoreTable;
    @FXML
    private TableColumn playerColumn;
    @FXML
    private TableColumn scoreColumn;

    @FXML
    public void handleReturnButton(ActionEvent event) {

        try {
            Stage stage = (Stage) scorePane.getScene().getWindow();

            Parent root = FXMLLoader.load(getClass().getResource("/fxml/StartScene.fxml"));

            Scene scene = new Scene(root);
            scene.getStylesheets().add("/styles/StartStyle.css");

            stage.setTitle("StartScene");
            stage.setScene(scene);
            stage.show();
            logger.info("Returned to main menu");
            
        } catch (IOException ex) {
            logger.error("Couldn't load StartScene.fxml");
            ex.printStackTrace();
            System.exit(1);
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ReadXML r = new ReadXML();
        obsList = r.getData();
        scoreTable.setEditable(false);
        playerColumn.setCellValueFactory(new PropertyValueFactory<>("playerName"));
        scoreColumn.setCellValueFactory(new PropertyValueFactory<>("playerScore"));
        scoreColumn.setSortType(TableColumn.SortType.DESCENDING);
        scoreTable.setItems(obsList);
    }
//CHECKSTYLE:OFF
}
