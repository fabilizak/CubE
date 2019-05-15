/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CubeDB;

import java.io.File;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import java.util.ArrayList;
import java.util.List;
import CubE.Player;
import java.io.IOException;
import javax.xml.parsers.ParserConfigurationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.w3c.dom.DOMException;
import org.xml.sax.SAXException;

/**
 * Class that reads the player scores from an XML file.
 */
public class ReadXML {
    
    //CHECKSTYLE:OFF
    private static final Logger logger = LoggerFactory.getLogger(ReadXML.class);
    //CHECKSTYLE:ON
    
        /**
         * Gets the data from the XML file.
         * 
         * @return {@code data} which is an {@code ObservableList} containing the names and scores of the players
         */
        public static ObservableList<Player> getData() {
        
        try {

            File file = new File("src/main/resources/xml/scores.xml");
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(file);
            
            doc.getDocumentElement().normalize();
            
            NodeList nList = doc.getElementsByTagName("Player");

            List<Player> playerList = new ArrayList<>();

            for (int temp = 1; temp < nList.getLength(); temp++) {

                Node nNode = nList.item(temp);
                
                
                
                if (nNode.getNodeType() == Node.ELEMENT_NODE) {

                    Element eElement = (Element) nNode;

                    playerList.add(new Player(eElement.getElementsByTagName("Name").item(0).getTextContent(), eElement.getElementsByTagName("Score").item(0).getTextContent()));
                    
                }
            }
            
            ObservableList<Player> data = FXCollections.observableArrayList(playerList);
            logger.info("Data retrieved from XML");
            return data;
            
        } catch (IOException | ParserConfigurationException | DOMException | SAXException e) {
        }
            return null;
        }
}