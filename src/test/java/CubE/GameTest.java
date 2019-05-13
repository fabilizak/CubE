package CubE;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class GameTest {
    
    private static Game game;
    
    public GameTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
        game = new Game();
    }
    
    @AfterClass
    public static void tearDownClass() {
        game = null;
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of increaseScore method, of class Game.
     */
    @Test
    public void testIncreaseScore() {
        game.setScore(15300);
        int brickPoints = 100;
        game.increaseScore(brickPoints);
        int expectedRes = 15400;
        assertEquals(expectedRes, game.getScore());
        
        game.setScore(2300);
        brickPoints = 500;
        game.increaseScore(brickPoints);
        expectedRes = 2800;
        assertEquals(expectedRes, game.getScore());
        
        game.setScore(20200);
        brickPoints = 1000;
        game.increaseScore(brickPoints);
        expectedRes = 21200;
        assertEquals(expectedRes, game.getScore());
        
        game.setScore(1500);
        brickPoints = 500;
        game.increaseScore(brickPoints);
        brickPoints = 1000;
        game.increaseScore(brickPoints);
        brickPoints = 100;
        game.increaseScore(brickPoints);
        brickPoints = 500;
        game.increaseScore(brickPoints);
        brickPoints = 100;
        game.increaseScore(brickPoints);
        expectedRes = 3700;
        assertEquals(expectedRes, game.getScore());
        
        game.setScore(1700);
        brickPoints = 1000;
        game.increaseScore(brickPoints);
        brickPoints = 100;
        game.increaseScore(brickPoints);
        brickPoints = 100;
        game.increaseScore(brickPoints);
        brickPoints = 500;
        game.increaseScore(brickPoints);
        brickPoints = 1000;
        game.increaseScore(brickPoints);
        expectedRes = 4400;
        assertEquals(expectedRes, game.getScore());
        
        game.setScore(200);
        brickPoints = 1000;
        game.increaseScore(brickPoints);
        brickPoints = 500;
        game.increaseScore(brickPoints);
        brickPoints = 100;
        game.increaseScore(brickPoints);
        brickPoints = 1000;
        game.increaseScore(brickPoints);
        brickPoints = 100;
        game.increaseScore(brickPoints);
        expectedRes = 2900;
        assertEquals(expectedRes, game.getScore());
    }

    /**
     * Test of decreaseLives method, of class Game.
     */
    @Test
    public void testDecreaseLives() {
        game.setLives(3);
        game.decreaseLives();
        int expectedRes = 2;
        assertEquals(expectedRes, game.getLives());
        
        game.setLives(2);
        game.decreaseLives();
        expectedRes = 1;
        assertEquals(expectedRes, game.getLives());
        
        game.setLives(1);
        game.decreaseLives();
        expectedRes = 0;
        assertEquals(expectedRes, game.getLives());
        
        game.setLives(3);
        game.decreaseLives();
        game.decreaseLives();
        expectedRes = 1;
        assertEquals(expectedRes, game.getLives());
    }
    
    /**
     * Test of increaseBallSpeed method, of class Game.
     */
    @Test
    public void testIncreaseBallSpeed(){
        game.setBallSpeed(200);
        game.increaseBallSpeed();
        int expectedRes = 250;
        assertEquals(expectedRes, game.getBallSpeed());
        
        game.setBallSpeed(100);
        game.increaseBallSpeed();
        expectedRes = 150;
        assertEquals(expectedRes, game.getBallSpeed());
        
        game.setBallSpeed(0);
        game.increaseBallSpeed();
        expectedRes = 50;
        assertEquals(expectedRes, game.getBallSpeed());
        
        game.setBallSpeed(1000);
        game.increaseBallSpeed();
        expectedRes = 1050;
        assertEquals(expectedRes, game.getBallSpeed());
    }
    
    /**
     * Test of decreaseBallSpeed method, of class Game.
     */
    @Test
    public void testDecreaseBallSpeed(){
        game.setBallSpeed(200);
        game.decreaseBallSpeed();
        int expectedRes = 150;
        assertEquals(expectedRes, game.getBallSpeed());
        
        game.setBallSpeed(100);
        game.decreaseBallSpeed();
        expectedRes = 50;
        assertEquals(expectedRes, game.getBallSpeed());
        
        game.setBallSpeed(0);
        game.decreaseBallSpeed();
        expectedRes = 0;
        assertEquals(expectedRes, game.getBallSpeed());
        
        game.setBallSpeed(30);
        game.decreaseBallSpeed();
        expectedRes = 0;
        assertEquals(expectedRes, game.getBallSpeed());
    }
    
}
