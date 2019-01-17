package data.provider;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;


public class ConfigReader {
	
	private Properties properties;
	
	Logger logger = Logger.getLogger(ConfigReader.class.getName());
	
	public static final String PROPFILEPATH = "config/automationConfigs.properties";
	 
	 public ConfigReader(){
		   			 
			 BufferedReader reader = getBufferedReader(PROPFILEPATH);
			 
			 properties = new Properties();
	 
			 loadProperties(reader);
		 		
	 } 
	 
	 public BufferedReader getBufferedReader(String path) {
		 
		 FileReader filereader = null;
		
		 
		 try {
			 filereader = new FileReader(path);
		} catch (FileNotFoundException e) {
			
			String message = ("automationConfigs.properties could not find" 
					 + PROPFILEPATH);
						
			logger.log(Level.SEVERE, message, e);
		}
		 				 
		 return new BufferedReader(filereader);
		 
	 }
	 
	 public void loadProperties(BufferedReader reader) {
		 
		 try {
				
			 properties.load(reader);
			 reader.close();
		 
		 } catch (IOException e) {
			 
			 String message = ("automationConfigs.properties could not load properties" 
					 + PROPFILEPATH);
						
			logger.log(Level.SEVERE, message, e);
		 }
	 }
	 
	 public String getExPageTitle(){
		 
		 String strExpectedTitle = properties.getProperty("expectedPageTitle");
		 
		 if(strExpectedTitle == null) {
			 configReaderException("ExpectedTitle not specified in the Configuration.properties file."); 	 
		 }
		 
		 return strExpectedTitle;
		 
	}
	 
	 public String getURL(){
		 
		 String strURL = properties.getProperty("URL");
		 
		 if(strURL == null) {
			 configReaderException("URL not specified in the Configuration.properties file."); 
		 }
		 
		 return strURL;
		 
	}
	 
	 public String getBrowserType(){
		 
		 String strBrowserType = properties.getProperty("browserType");
		 
		 if(strBrowserType == null) {
			 configReaderException("BrowserType not specified in the Configuration.properties file.");  
		 }
		  
		 return strBrowserType;
		 
	}
	 
	 public String getIsHeadLess(){
		 
		 String strIsHeadLess = properties.getProperty("isHeadLess");
		 
		 if(strIsHeadLess == null) {
			 configReaderException("BrowserType not specified in the Configuration.properties file."); 	 
		 }
		 		 
		 return strIsHeadLess;
		 
	}
	 
	 public String getDriverPath(){
		 
		 String strDriverPath = properties.getProperty("driverPath");
		 
		 if(strDriverPath == null) {
			 configReaderException("DriverPath not specified in the Configuration.properties file.");  
		 }
		 		 
		 return strDriverPath;
	}

	 public String getUserName(){
		 
		 String strUserName = properties.getProperty("userName");
		 
		 if(strUserName == null) { 
			 configReaderException("userName not specified in the Configuration.properties file."); 
		 }
		 
		 return strUserName;
		 
	}

	 public String getPassword(){
		 
		 String strPassword = properties.getProperty("passWord");
		 
		 if(strPassword== null) {
			configReaderException("passWord not specified in the Configuration.properties file.");
		 }
		 
		 return strPassword;
	 }
	 
	 public String getLoginAlert(){
		 
		 String strLoginAlert = properties.getProperty("LoginAlert");
		 
		 if(strLoginAlert== null)  {
			 configReaderException("LoginAlert not specified in the Configuration.properties file."); 
		 }
		 
		 return strLoginAlert;
	}
	 
	 public String getExpectedFieldValidation(){
		 
		 String strExpectedFieldValidation = properties.getProperty("ExpectedFieldValidation");
		 
		 if(strExpectedFieldValidation== null)  {
			 configReaderException("ExpectedFieldValidation not specified in the Configuration.properties file."); 
		 }
		 
		 return strExpectedFieldValidation;
	}
	 
	 private Exception configReaderException(String string) {
		logger.log(Level.SEVERE, string);
		return null;
	}
	 
}
