package CubE;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Class managing main functionalities of the game.
 */
public class Game {
    
    //CHECKSTYLE:OFF
    private static final Logger logger = LoggerFactory.getLogger(Game.class);
    
    private int lives = 3;
    private int score = 0;
    public int bDx = 1;
    public int bDy = -1;
    private int ballSpeed = 200;
    //CHECKSTYLE:ON
    
    /**
     * Increases the score of the player.
     * 
     * @param brickPoints the point value of the brick that has been hit by the ball.
     */
    public void increaseScore(int brickPoints){
        score = score+brickPoints;
        logger.info("Score increased by " + brickPoints + " points");
    }
    
    /**
     * Decreases the remaining lives of the player by one.
     */
    public void decreaseLives(){
        lives--;
        logger.info("Lives decreased to " + this.lives);
    }

    //CHECKSTYLE:OFF
    public int getLives() {
        return lives;
    }

    public void setLives(int lives) {
        this.lives = lives;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public int getBallSpeed() {
        return ballSpeed;
    }

    public void setBallSpeed(int ballSpeed) {
        this.ballSpeed = ballSpeed;
    }
    //CHECKSTYLE:ON
    
    /**
     * Increases the speed of the ball.
     */
    public void increaseBallSpeed(){
        this.ballSpeed += 50;
        logger.info("Ball speed increased to " + this.ballSpeed);
    }
    
    /**
     * Decreases the speed of the ball.
     */
    public void decreaseBallSpeed(){
        if(this.ballSpeed - 50 >= 0){
            this.ballSpeed -= 50;
            logger.info("Ball speed increased to " + this.ballSpeed);
        }
        else {
            this.ballSpeed = 0;
            logger.warn("Can't decrease ball speed anymore!");
        }
    }
    
    
}