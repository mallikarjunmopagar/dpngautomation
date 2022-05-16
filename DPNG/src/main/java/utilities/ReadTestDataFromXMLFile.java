package utilities;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import javax.xml.XMLConstants;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.xml.sax.SAXException;
import org.w3c.dom.NodeList;

public class ReadTestDataFromXMLFile {
	//private static final String FILENAME = System.getProperty("user.dir"+ "\\src\\test\\resources\\excel\\testdataxml.xml";
	static int counter = 0;
	static Object[][] data = null;
	
	public static FileInputStream DataPropertiesfile;

	public static Object[][] ReadPaths(String xmlfileoftestdata) throws ParserConfigurationException, SAXException, IOException {
		
		final Properties prop;
		prop = new Properties();
		
		try {
			DataPropertiesfile = new FileInputStream(
					System.getProperty("user.dir") + "\\src\\test\\resources\\resources\\data.properties");
			prop.load(DataPropertiesfile);
			String path=System.getProperty("user.dir")+ "\\src\\test\\resources\\testData\\";
			File file = new File(path+prop.getProperty(xmlfileoftestdata));

			DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();

			
			dbf.setFeature(XMLConstants.FEATURE_SECURE_PROCESSING, true);
			DocumentBuilder db = dbf.newDocumentBuilder();
			Document doc = db.parse(file);
			doc.getDocumentElement().normalize();

			//System.out.println("Root element: " + doc.getDocumentElement().getNodeName());

			NodeList nodeList = doc.getElementsByTagName("design");

			for (int itr = 0; itr < nodeList.getLength(); itr++) {
				Node node = nodeList.item(itr);

				if (node.getNodeType() == Node.ELEMENT_NODE) {
					Element eElement = (Element) node;

					if (eElement.getElementsByTagName("execute").item(0).getTextContent().equalsIgnoreCase("Y")) {
						counter++;
					//	System.out.println(itr);
					}
				}
			}
			//System.out.println(counter + "size");
			data = new Object[counter][3];
			int j = 0;
			for (int itr = 0; itr < nodeList.getLength(); itr++) {

				Node node = nodeList.item(itr);
				Element element = (Element) node;
				

				//System.out.println("attribute: " + element.getAttribute("type"));
				//System.out.println("size:" + nodeList.getLength());

				if (node.getNodeType() == Node.ELEMENT_NODE) {
					Element eElement = (Element) node;

					if (eElement.getElementsByTagName("execute").item(0).getTextContent().equalsIgnoreCase("Y")) {
						data[j][0] = eElement.getElementsByTagName("SourceFile").item(0).getTextContent();
						data[j][1] = eElement.getElementsByTagName("TargetFile").item(0).getTextContent();
						data[j][2] = eElement.getElementsByTagName("Comments").item(0).getTextContent();
						j++;

					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return data;

	}
}
