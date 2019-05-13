package CubE;

import javafx.beans.property.SimpleStringProperty;

/**
 * Class managing player statistics.
 */
public class Player {
    
        //CHECKSTYLE:OFF
        private final SimpleStringProperty playerName;
        private final SimpleStringProperty playerScore;
        //CHECKSTYLE:ON

        /**
         * Sets the name and score of the player.
         * 
         * @param name the name of the {@link Player}
         * @param score the score of the {@link Player}
         */
        public Player(String name, String score) {
            this.playerName = new SimpleStringProperty(name);
            this.playerScore = new SimpleStringProperty(score);
        }

        //CHECKSTYLE:OFF
        public String getPlayerName() {
            return playerName.get();
        }

        public void setPlayerName(String name) {
            playerName.set(name);
        }

        public SimpleStringProperty playerNameProperty() {
            return playerName;
        }

        public String getPlayerScore() {
            return playerScore.get();
        }

        public void setPlayerScore(String score) {
            playerScore.set(score);
        }

        public SimpleStringProperty playerScoreProperty() {
            return playerScore;
        }
        //CHECKSTYLE:ON
    }