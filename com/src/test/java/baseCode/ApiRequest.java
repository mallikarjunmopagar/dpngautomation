package baseCode;


import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

import java.util.List;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import javax.xml.transform.Source;
import javax.net.ssl.HttpsURLConnection;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import org.xml.sax.SAXException;

import com.aventstack.extentreports.Status;

import extentlisteners.ExtentListeners;

public class ApiRequest extends ExtentListeners {

	public static void main(String[] args) throws SAXException, IOException {

	/*	XMLUnit.setIgnoreWhitespace(true);
		XMLUnit.setIgnoreAttributeOrder(true);
		XMLUnit.setIgnoreComments(true);
		XMLUnit.setIgnoreDiffBetweenTextAndCDATA(true); */

		//FileReader source = new FileReader(srcpath);
	//	FileReader target = new FileReader(targtpath);
		
	//	String request = Files.readString(Paths.get(srcpath));
		String request = "Hello";
			//	System.out.println(request);
		//String request = "<soapenv:Envelope xmlns:soapenv=\\\"http://schemas.xmlsoap.org/soap/envelope/\\\" xmlns:hs=\\\"http://www.holidaywebservice.com/HolidayService_v2/\\\"><soapenv:Body><hs:GetHolidaysForMonth><hs:year>2018</hs:year><hs:countryCode>UnitedStates</hs:countryCode><hs:month>11</hs:month></hs:GetHolidaysForMonth></soapenv:Body></soapenv:Envelope>";

			URL url = new URL("http://mockbin.com/bin/8f0cf4b4-cfbe-4d19-9c17-48d055eb5f7e/view");
			//http://mockbin.com/request?foo=bar&foo=baz
			//http://mockbin.com/bin/471987de-5663-4280-a354-ac0784a93958/view
			HttpURLConnection connection = (HttpURLConnection) url.openConnection();

			// Set timeout as per needs
			connection.setConnectTimeout(20000);
			connection.setReadTimeout(20000); 

			// Set DoOutput to true if you want to use URLConnection for output.
			// Default is false
			connection.setDoOutput(true);

			connection.setUseCaches(true);
			connection.setRequestMethod("POST");

			// Set Headers
			connection.setRequestProperty("Accept", "application/xml");
			connection.setRequestProperty("Content-Type", "application/xml");

			// Write XML
		OutputStream outputStream = connection.getOutputStream();
			byte[] b = request.getBytes("UTF-8");
			outputStream.write(b);
			outputStream.flush();
			outputStream.close();

			
			 
			String responseStatus = connection.getResponseMessage();
			System.out.println(responseStatus);
			// Read XML
			InputStream inputStream = connection.getInputStream();
			byte[] res = new byte[2048];
			int i = 0;
			StringBuilder response = new StringBuilder();
			while ((i = inputStream.read(res)) != -1) {
				response.append(new String(res, 0, i));
			}
			
			inputStream.close();

			System.out.println("Response= " + response.toString());
                       test.log(Status.INFO, " Api Response status : "+responseStatus);
			//public void whenWriteStringUsingBufferedWritter_thenCorrect() 
				//	  throws IOException {
			try {
				
					    String str = response.toString();
					    String destination="xyz";
						BufferedWriter writer = new BufferedWriter(new FileWriter(System.getProperty("user.dir")+"\\src\\test\\resources\\TO_BE_XMLs\\" +destination +"."+"xml"));
					    writer.write(str);
					    
					    writer.close();
					   
			}
			catch (Exception e)
			{
                 System.out.println(e.getMessage());
			}
		}

	
}
