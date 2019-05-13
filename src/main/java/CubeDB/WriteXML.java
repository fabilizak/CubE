package CubeDB;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.slf4j.LoggerFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

/**
 * Class that writes the player scores in an XML file.
 */
public class WriteXML {

    private static final org.slf4j.Logger logger = LoggerFactory.getLogger(WriteXML.class);
    
    /**
     * Merges an XML file with the XML file that contains the name and score of the previous players.
     * 
     * @param temp XML file that contains the name and score of the current player
     */
    public static void mergeXml(File temp) {

        try {
            DocumentBuilderFactory domFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = domFactory.newDocumentBuilder();
            Document doc = builder.parse(new File("src/main/resources/xml/scores.xml"));
            Document doc1 = builder.parse(new File("src/main/resources/xml/temp.xml"));

            NodeList nodes = doc.getElementsByTagName("Player");

            NodeList nodes1 = doc1.getElementsByTagName("Player");

            for (int i = 0; i < nodes1.getLength(); i = i + 1) {
                Node n = (Node) doc.importNode(nodes1.item(i), true);
                nodes.item(i).getParentNode().appendChild(n);
            }

            Transformer transformer = TransformerFactory.newInstance().newTransformer();

            transformer.setOutputProperty(OutputKeys.INDENT, "yes");

            StreamResult result = new StreamResult(new StringWriter());
            DOMSource source = new DOMSource(doc);

            transformer.transform(source, result);
            Writer output = null;
            output = new BufferedWriter(new FileWriter("src/main/resources/xml/scores.xml"));

            String xmlOutput = result.getWriter().toString();

            output.write(xmlOutput);
            output.close();
            logger.info("XML files merged");
        } catch (ParserConfigurationException | SAXException | IOException | TransformerException ex) {
            Logger.getLogger(WriteXML.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Creates the XML file that will contain the names and scores of all players.
     * 
     * @param name the name of the current player
     * @param points the score of the current player
     * @throws Exception throws exception if the path doesn't exist
     */
    public static void createXml(String name, String points) throws Exception {

        File init = new File("src/main/resources/xml/scores.xml");
        if (!init.exists()) {
            logger.info("scores.xml missing! Creating new file");
            createInitXml(init);
        }

        File tempFile = new File("src/main/resources/xml/temp.xml");

        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();

        Document doc = dBuilder.newDocument();

        Element players = doc.createElement("Players");
        doc.appendChild(players);

        Element element = doc.createElement("Player");
        players.appendChild(element);
        Element player = doc.createElement("Name");
        player.appendChild(doc.createTextNode(name));
        element.appendChild(player);

        Element score = doc.createElement("Score");
        score.appendChild(doc.createTextNode(points));
        element.appendChild(score);

        TransformerFactory trFactory = TransformerFactory.newInstance();
        Transformer tr = trFactory.newTransformer();
        DOMSource source = new DOMSource(doc);

        StreamResult strRes = new StreamResult(tempFile);

        tr.transform(source, strRes);

        mergeXml(tempFile);
        logger.info("Updated scores.xml");

    }

    /**
     * Creates a new XML file if the one containing the player scores got deleted.
     * 
     * @param init the directory where the XML file will be created
     */
    public static void createInitXml(File init) {

        try {
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();

            Document doc = dBuilder.newDocument();

            Element players = doc.createElement("Players");
            doc.appendChild(players);

            Element element = doc.createElement("Player");
            players.appendChild(element);

            Element player = doc.createElement("Name");
            player.appendChild(doc.createTextNode("Joker"));
            element.appendChild(player);

            Element score = doc.createElement("Score");
            score.appendChild(doc.createTextNode("NULL"));
            element.appendChild(score);

            TransformerFactory trFactory = TransformerFactory.newInstance();
            Transformer tr = trFactory.newTransformer();
            DOMSource source = new DOMSource(doc);

            StreamResult strRes = new StreamResult(init);

            tr.transform(source, strRes);

            logger.info("Created new scores.xml file");
            
        } catch (ParserConfigurationException | TransformerException ex) {
            Logger.getLogger(WriteXML.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

}
