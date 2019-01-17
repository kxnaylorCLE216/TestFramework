package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import data.provider.ConfigReader;
import test.utility.CommonLib;

public class LoginPage {
	
	  WebDriver driver;
	  
	  By userName = By.id("inputUsername");
	  
	  By passWord = By.id("inputPassword");
	  
	  By signIn = By.cssSelector(".btn.btn-lg.btn-primary.btn-block");
	  
	  By alert = By.id("alert");
	  
	  ConfigReader reader = new ConfigReader();
	  
	  String strUserName = reader.getUserName();
		
	  String strPassword = reader.getPassword();
	  
	  String strExpectedValidation = reader.getExpectedFieldValidation();
	  
	  CommonLib common = new CommonLib();
	  
	  public LoginPage(WebDriver driver){
		  
		  this.driver = driver;
	  }
	  
	  public void setUserName(String strUserName){
		  
		 common.waitForVisibility(this.driver, userName);
		  
		 this.driver.findElement(userName).sendKeys(strUserName);
	  }
	  
	  public void setPassword(String strPassword){
		  
		  common.waitForVisibility(this.driver, passWord);
		  
		  driver.findElement(passWord).sendKeys(strPassword);
	  }

	  public void clickLogin(){
		  
		  common.waitForClickableElement(this.driver, signIn);
		  
		  driver.findElement(signIn).click();
		  
	  }
	  
		public String getIncorrectAlert(){
			
			common.waitForClickableElement(this.driver, alert);
			
			return driver.findElement(alert).getText();

		}
	  
	  public void loginIntoPage(){
		  		  		  		  
		  this.setUserName(strUserName);
		  
		  this.setPassword(strPassword);
		  
		  this.clickLogin();
		  
	  }
	  
	  public void loginIntoPage(String strUserName, String strPassword){
	  		  
		  this.setUserName(strUserName);
		  
		  this.setPassword(strPassword);
		  
		  this.clickLogin();
		  
	  }
	  
	  public void backToLogin() {
		   			
			String strURL = reader.getURL();
					
			driver.navigate().to(strURL);
	  }
	  
	  public boolean loginNoUserName(){
		  
		  common.waitForVisibility(this.driver, userName);
			 		  
		  String strValidateMessage 
		  = driver.findElement(userName).getAttribute("validationMessage");
		  	  	  			  
		  this.setPassword(strPassword);
		  
		  this.clickLogin();
		  
		  return (strValidateMessage.equals(strExpectedValidation));
		  	  
	  }
	  
	  public boolean loginNoPassword(){
		  	  
		  String strValidateMessage 
		  = driver.findElement(passWord).getAttribute("validationMessage");
		  		  			  		  
		  this.setUserName(strUserName);
		  		  
		  this.clickLogin();
		  
		  return (strValidateMessage.equals(strExpectedValidation));
		  
	  }
  	  
	  public boolean isExpectedAlert(){
			
			String strPageTitle =  getIncorrectAlert();
			
			String strExpectedPageTitle = reader.getLoginAlert();
							
			return (strPageTitle.equals(strExpectedPageTitle));

		}
}