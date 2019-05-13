package CubEControllers;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Bounds;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import CubE.Game;
import java.io.IOException;
import java.util.Arrays;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.util.Duration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

//CHECKSTYLE:OFF
public class GameSceneController implements Initializable {

    private static final Logger logger = LoggerFactory.getLogger(GameSceneController.class);
    
    //List for storing the bricks
    List<Rectangle> rectList = new ArrayList<>();
    
    //Number of pixels for moving the ball coordinates
    int ballDx = GAME.bDx;
    int ballDy = GAME.bDy;
    
    private static final Game GAME = new Game();
    
    //<editor-fold defaultstate="collapsed" desc="FXML item declarations">
    @FXML
    private AnchorPane gamePane;
    @FXML
    private Rectangle bat;
    @FXML
    private Circle ball;
    @FXML
    private Label livesLabel;
    @FXML
    private Label scoreLabel;
    @FXML
    private Pane pausePane;
    @FXML
    private Button resumeButton;
    @FXML
    private Button endGameButton;
    //<editor-fold defaultstate="collapsed" desc="declarations of bricks">
    @FXML
    public Rectangle brick1;
    @FXML
    public Rectangle brick2;
    @FXML
    public Rectangle brick3;
    @FXML
    public Rectangle brick4;
    @FXML
    public Rectangle brick5;
    @FXML
    public Rectangle brick6;
    @FXML
    public Rectangle brick7;
    @FXML
    public Rectangle brick8;
    @FXML
    public Rectangle brick9;
    @FXML
    public Rectangle brick10;
    @FXML
    public Rectangle brick11;
    @FXML
    public Rectangle brick12;
    @FXML
    public Rectangle brick13;
    @FXML
    public Rectangle brick14;
    @FXML
    public Rectangle brick15;
    @FXML
    public Rectangle brick16;
    @FXML
    public Rectangle brick17;
    @FXML
    public Rectangle brick18;
    @FXML
    public Rectangle brick19;
    @FXML
    public Rectangle brick20;
    @FXML
    public Rectangle brick21;
    @FXML
    public Rectangle brick22;
    @FXML
    public Rectangle brick23;
    @FXML
    public Rectangle brick24;
    @FXML
    public Rectangle brick25;
    @FXML
    public Rectangle brick26;
    @FXML
    public Rectangle brick27;
    @FXML
    public Rectangle brick28;
    @FXML
    public Rectangle brick29;
    @FXML
    public Rectangle brick30;
    @FXML
    public Rectangle brick31;
    @FXML
    public Rectangle brick32;
    @FXML
    public Rectangle brick33;
    @FXML
    public Rectangle brick34;
    @FXML
    public Rectangle brick35;
    @FXML
    public Rectangle brick36;
//</editor-fold>
//</editor-fold>
    
    //Method for moving the bat
    public void moveBat(String direction){
        Bounds bounds = gamePane.getBoundsInLocal();
        
        if (direction.equals("left")){
            if(bat.getLayoutX()!=bounds.getMinX())
            bat.setLayoutX(bat.getLayoutX() - 10);
        }
        if (direction.equals("right")){
            if((bat.getLayoutX() + bat.getWidth())!=bounds.getMaxX())
            bat.setLayoutX(bat.getLayoutX() + 10);
        }
    }
    
    //Method for increasing the score
    public void scoreUp(int bp){
        GAME.increaseScore(bp);
        scoreLabel.setText(String.valueOf(GAME.getScore()));
    }
    
    
    //Method that makes the ball move
    public void ballMovement() {
        
        ball.setLayoutX(ball.getLayoutX() + ballDx);
        ball.setLayoutY(ball.getLayoutY() + ballDy);
        
        collisionCheck();
    }

    //Method for checking ball collisions
    public void collisionCheck(){
        
                checkBorderHit(ball);
                checkBatHit(ball, bat);
                for(int i=0; i<rectList.size(); i++){
                    if (i<12) checkBrickHit(ball, rectList.get(i), 1000);
                    if (i>=12 && i <=23) checkBrickHit(ball, rectList.get(i), 500);
                    if (i>23) checkBrickHit(ball, rectList.get(i), 100);
                }
    }
    
    //Method for checking if the ball hit a brick
    public void checkBrickHit (Circle ball, Rectangle brick, int brickPoints){
        
        //checks if the brick is still on the gamePane
        if(gamePane.getChildren().contains(brick)){
            
            //coordinates of the ball and the brick
            double ballX = ball.getLayoutX();
            double ballY = ball.getLayoutY();
            double brickX = brick.getLayoutX();
            double brickY = brick.getLayoutY();
            
            //checks if the ball hit the brick's corners
            if((ballX == (brickX - ball.getRadius()) &&
                    ballY == (brickY - ball.getRadius())) ||
                (ballX == (brickX - ball.getRadius()) &&
                    ballY == (brickY + brick.getHeight() - ball.getRadius())) ||
                (ballX == (brickX + brick.getWidth() + ball.getRadius()) &&
                    ballY == (brickY - ball.getRadius())) ||
                (ballX == (brickX + brick.getWidth() + ball.getRadius()) &&
                    ballY == (brickY + brick.getHeight() + ball.getRadius()))) {
                    ballDx = -ballDx;
                    ballDy = -ballDy;
                    gamePane.getChildren().remove(brick);
                    rectList.remove(brick);
                    scoreUp(brickPoints);
                    checkBricksLeft();
                    return;
            }
            
            //checks if the ball hit the brick from the bottom
            if((ballY == ((brickY + brick.getHeight()) + ball.getRadius())) &&
                ((ballX >= brickX - ball.getRadius()) && 
                (ballX <= (brickX + brick.getWidth() + ball.getRadius())))) {
                    ballDy = -ballDy;
                    gamePane.getChildren().remove(brick);
                    rectList.remove(brick);
                    scoreUp(brickPoints);
                    checkBricksLeft();
                    return;
            }
            
            //checks if the ball hit the brick from the top
            if((ballY == (brickY - ball.getRadius())) &&
                ((ballX >= brickX - ball.getRadius()) && 
                (ballX <= (brickX + brick.getWidth() + ball.getRadius())))) {
                    ballDy = -ballDy;
                    gamePane.getChildren().remove(brick);
                    rectList.remove(brick);
                    scoreUp(brickPoints);
                    checkBricksLeft();
                    return;
            }
            
            //checks if the ball hit the brick from the left side
            if((ballX == (brickX - ball.getRadius()) && 
                (ballY >= brickY - ball.getRadius()) &&
                (ballY <= (brickY + brick.getHeight() + ball.getRadius())))) {
                    ballDx = -ballDx;
                    gamePane.getChildren().remove(brick);
                    rectList.remove(brick);
                    scoreUp(brickPoints);
                    checkBricksLeft();
                    return;
            }
            
            //checks if the ball hit the brick from the right side
            if((ballX == (brickX + brick.getWidth() + ball.getRadius()) && 
                (ballY >= brickY - ball.getRadius()) &&
                (ballY <= (brickY + brick.getHeight() + ball.getRadius())))) {
                    ballDx = -ballDx;
                    gamePane.getChildren().remove(brick);
                    rectList.remove(brick);
                    scoreUp(brickPoints);
                    checkBricksLeft();
                    return;
            }
        }
    }
    
    //Method for checking if the ball hit the borders of the gamePane
    public void checkBorderHit (Circle ball){
        Bounds bounds = gamePane.getBoundsInLocal();
        
                //If the ball reaches the top border make the step negative
                if(ball.getLayoutY() <= (bounds.getMinY() + ball.getRadius())){

                	ballDy = -ballDy;

                }
        
                //If the ball reaches the bottom border make the step negative and decrese lives
                if(ball.getLayoutY() >= (bounds.getMaxY() - ball.getRadius())){
                    
                    ballAnim.stop();
                    
                    if(GAME.getLives() > 0){
                        GAME.decreaseLives();
                        livesLabel.setText(String.valueOf(GAME.getLives()));
                    } else {
                        switchToEndScene();
                    }
                    ball.setLayoutX(313);
                    ball.setLayoutY(774);
                    ballDy = -ballDy;
                }
                
                //If the ball reaches the left or right border make the step negative
                if(ball.getLayoutX() <= (bounds.getMinX() + ball.getRadius()) || 
                        ball.getLayoutX() >= (bounds.getMaxX() - ball.getRadius()) ){

                	ballDx = -ballDx;

                }
    }
    
    //Method for checking if the ball hit the bat
    public void checkBatHit (Circle ball, Rectangle bat) {
        
        //coordinates of the ball and the bat
        double ballX = ball.getLayoutX();
        double ballY = ball.getLayoutY();
        double batX = bat.getLayoutX();
        double batY = bat.getLayoutY();
        
            //checks if the ball hit the left half of the bat
            if((ballY == (batY - ball.getRadius())) &&
                ((ballX >= batX - ball.getRadius()) && 
                (ballX < (batX + bat.getWidth()/2)))) {
                    if (ballDx > 0) ballDx = -ballDx;
                    ballDy = -ballDy;
            }
                
            //checks if the ball hit the right half of the bat
            if((ballY == (batY - ball.getRadius())) &&
                (ballX >= (batX + bat.getWidth()/2) && 
                (ballX <= (batX + bat.getWidth() + ball.getRadius())))) {
                    if (ballDx < 0) ballDx = -ballDx;
                    ballDy = -ballDy;
            }
            
            //checks if the ball hit the bat from the left side
            if((ballX == (batX - ball.getRadius()) && 
                (ballY >= batY - ball.getRadius()) &&
                (ballY <= (batY + bat.getHeight())))) {
                    ballDx = -ballDx;
            }
            
            //checks if the ball hit the bat from the right side
            if((ballX == (batX + bat.getWidth() + ball.getRadius()) && 
                (ballY >= batY - ball.getRadius()) &&
                (ballY <= (batY + bat.getHeight())))) {
                    ballDx = -ballDx;
            }
            
            //checks if the ball hit the bat's corners
            if ((ballX == (batX - ball.getRadius()) &&
                    ballY == (batY - ball.getRadius())) ||
                (ballX == (batX + bat.getWidth() + ball.getRadius()) &&
                    ballY == (batY - ball.getRadius()))) {
                    ballDx = -ballDx;
                    ballDy = -ballDy;
            }
    }
    
    //Method that adds the bricks to rectList
    public void addRectToList(){
        rectList.addAll(Arrays.asList(brick1,brick2,brick3,brick4,brick5,brick6,brick7,brick8,brick9,brick10,brick11,brick12,
            brick13,brick14,brick15,brick16,brick17,brick18,brick19,brick20,brick21,brick22,brick23,brick24,brick25,brick26,
            brick27,brick28,brick29,brick30,brick31,brick32,brick33,brick34,brick35,brick36));
    }
    
    //Method that checks if there are any bricks left
    public void checkBricksLeft(){
        if(rectList.isEmpty()){
            logger.info("No bricks left");
            switchToEndScene();
        }
    }
    
    //Method that pauses the game
    public void gamePaused(){
        if(!pausePane.isVisible()){
            ballAnim.stop();
            pausePane.setVisible(true);
            logger.info("Game paused");
        }
    }
    
    //Method that resumes the game
    public void resumeGame(){
        if(pausePane.isVisible()){
            ballAnim.play();
            pausePane.setVisible(false);
            logger.info("Game resumed");
        }
    }
    
    //Method that switches to the EndScene
    public void switchToEndScene(){
        try {   
            ballAnim.stop();
            
            Stage stage = (Stage) gamePane.getScene().getWindow();

            FXMLLoader fl = new FXMLLoader(getClass().getResource("/fxml/EndScene.fxml"));

            Parent root = fl.load();
                        
            fl.<EndSceneController>getController().setScore(GAME.getScore());

            if (GAME.getLives() == 0)
                logger.info("Game Over, user ran out of lives");
            else
                logger.info("Game Over, user won");
            
            GAME.setScore(0);
            GAME.setLives(3);
            
            Scene scene = new Scene(root);
            scene.getStylesheets().add("/styles/EndStyle.css");
            
            stage.setTitle("EndOfGame");
            stage.setScene(scene);
            stage.show();
            logger.info("Switched to end scene.");
            
        } catch (IOException ex) {
            logger.error("Couldn't load EndScene.fxml");
            ex.printStackTrace();
            System.exit(1);
        }
    }
    
    /*//AnimationTimer that moves the ball
    AnimationTimer ballAnim = new AnimationTimer() {
           
        @Override
        public void handle(long currentNanoTime) {
            
            ballMovement();
            logger.info(Double.toString(ball.getLayoutX()) + " " + Double.toString(ball.getLayoutY()));
            try{
                Thread.sleep(1);
            } catch (InterruptedException e){}
        }
    };*/
    
    //Timeline that moves the ball
    Timeline ballAnim = new Timeline(new KeyFrame(Duration.seconds(1), event -> {
    ballMovement();
    //logger.info(Double.toString(ball.getLayoutX()) + " " + Double.toString(ball.getLayoutY()));
    }));
    
    public void speedUp(){
        GAME.increaseBallSpeed();
        ballAnim.setRate(GAME.getBallSpeed());
    }
    
    public void slowDown(){
        GAME.decreaseBallSpeed();
        ballAnim.setRate(GAME.getBallSpeed());
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        logger.info("Game started");
        addRectToList();
        pausePane.setVisible(false);
        ballAnim.setCycleCount(Animation.INDEFINITE);
        ballAnim.setRate(GAME.getBallSpeed());
        logger.info("The ball's speed is set to " + Integer.toString(GAME.getBallSpeed()) + " by default");
    }
//CHECKSTYLE:OFF
}
