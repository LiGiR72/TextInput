import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;


public class Settings {
    private boolean loadEnabled;
    private String loadFileName;
    private String loadFileFormat;

    private boolean saveEnabled;
    private String saveFileName;
    private String saveFileFormat;

    private boolean logEnabled;
    private String logFileName;

    public Settings() throws ParserConfigurationException, IOException, SAXException {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document doc = builder.parse(new File("shop.xml"));
        Node root = doc.getDocumentElement();
        this.readXML(root);
    }

    public void readXML(Node node) {
        NodeList nodeList = node.getChildNodes();
        for (int i = 0; i < nodeList.getLength(); i++) {
            Node currentNode = nodeList.item(i);
            if (Node.ELEMENT_NODE == currentNode.getNodeType()) {
                if (currentNode.getNodeName().equals("load")) {
                    load(currentNode);
                }
                if (currentNode.getNodeName().equals("save")) {
                    save(currentNode);
                }
                if (currentNode.getNodeName().equals("log")) {
                    log(currentNode);
                }
            }
        }
    }

    private void load(Node node) {
        if (node.getNodeType() == Node.ELEMENT_NODE) {
            Element element = (Element) node;
            this.loadEnabled = Boolean.parseBoolean(element.getElementsByTagName("enabled").item(0).getTextContent());
            this.loadFileName = element.getElementsByTagName("fileName").item(0).getTextContent();
            this.loadFileFormat = element.getElementsByTagName("format").item(0).getTextContent();
        }
    }

    private void save(Node node) {
        if (node.getNodeType() == Node.ELEMENT_NODE) {
            Element element = (Element) node;
            this.saveEnabled = Boolean.parseBoolean(element.getElementsByTagName("enabled").item(0).getTextContent());
            this.saveFileName = element.getElementsByTagName("fileName").item(0).getTextContent();
            this.saveFileFormat = element.getElementsByTagName("format").item(0).getTextContent();
        }
    }

    private void log(Node node) {
        if (node.getNodeType() == Node.ELEMENT_NODE) {
            Element element = (Element) node;
            this.logEnabled = Boolean.parseBoolean(element.getElementsByTagName("enabled").item(0).getTextContent());
            this.logFileName = element.getElementsByTagName("fileName").item(0).getTextContent();

        }
    }

    public boolean getLoadEnabled() {
        return loadEnabled;
    }

    public String getLoadFileName() {
        return loadFileName;
    }

    public String getLoadFileFormat() {
        return loadFileFormat;
    }

    public boolean getSaveEnabled() {
        return saveEnabled;
    }

    public String getSaveFileName() {
        return saveFileName;
    }

    public String getSaveFileFormat() {
        return saveFileFormat;
    }

    public boolean getLogEnabled() {
        return logEnabled;
    }

    public String getLogFileName() {
        return logFileName;
    }
}
