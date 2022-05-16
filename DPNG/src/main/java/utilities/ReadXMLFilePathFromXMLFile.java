package utilities;

import java.io.File;
import java.io.IOException;

import javax.xml.XMLConstants;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class ReadXMLFilePathFromXMLFile {
	private static final String FILENAME = System.getProperty("user.dir")
			+ "\\src\\test\\resources\\excel\\testdataxml.xml";

	public static void main(String[] args) {

		// Instantiate the Factory
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();

		try {

			// optional, but recommended
			// process XML securely, avoid attacks like XML External Entities (XXE)
			dbf.setFeature(XMLConstants.FEATURE_SECURE_PROCESSING, true);

			// parse XML file
			DocumentBuilder db = dbf.newDocumentBuilder();

			Document doc = db.parse(new File(FILENAME));

			// optional, but recommended
			// http://stackoverflow.com/questions/13786607/normalization-in-dom-parsing-with-java-how-does-it-work
			doc.getDocumentElement().normalize();

			System.out.println("Root Element :" + doc.getDocumentElement().getNodeName());
			System.out.println("------");

			// get <staff>
			NodeList list = doc.getElementsByTagName("design");
			System.out.println(list.getLength());
			for (int temp = 0; temp < list.getLength(); temp++) {

				Node node = list.item(temp);

				if (node.getNodeType() == Node.ELEMENT_NODE) {

					Element element = (Element) node;

					
					String id = element.getAttribute("type");
					System.out.println(id);
					
					String Src = element.getElementsByTagName("SourceFile").item(temp).getTextContent();
					String Trgt = element.getElementsByTagName("TargetFile").item(temp).getTextContent();
					String type = element.getElementsByTagName("Comments").item(temp).getTextContent();

					
					System.out.println("Current Element :" + node.getNodeName());
					
					System.out.println("src : " + Src);
					System.out.println("trgt : " + Trgt);
					System.out.println("type : " + type);
				

				}
			}

		} catch (ParserConfigurationException | SAXException | IOException e) {
			e.printStackTrace();
		}

	}
}
