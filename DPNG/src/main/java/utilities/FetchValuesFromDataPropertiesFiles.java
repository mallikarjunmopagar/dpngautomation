package utilities;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class FetchValuesFromDataPropertiesFiles {
public static String ReadValueOf(String variableName) throws IOException {
	FileInputStream DataPropertiesfile;
	final Properties prop;
	prop = new Properties();
	DataPropertiesfile = new FileInputStream(
			System.getProperty("user.dir") + "\\src\\test\\resources\\resources\\data.properties");
	prop.load(DataPropertiesfile);
	String Valueis=prop.getProperty(variableName);
	 DataPropertiesfile.close();
	return Valueis;
	
	
	
}
}
