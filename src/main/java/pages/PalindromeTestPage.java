package pages;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import data.provider.ConfigReader;
import test.utility.CommonLib;


public class PalindromeTestPage {

	WebDriver driver;
	
	CommonLib common = new CommonLib();
	
	ConfigReader reader = new ConfigReader();
	
	By pageTitle = By.className("navbar-brand");
	
	RadioLabels radioLabels = new RadioLabels();
	
	InputLabels inputLabels = new InputLabels();
	
	CheckBoxLabels checkBoxLabels = new CheckBoxLabels();
	
	By dropDown = By.id("select");
	
	By table = By.id("table-body");
	
	By moreButton = By.cssSelector(".btn.btn-secondary.btn-lg.btn-block");
	
	List<String> hiddenValues = new ArrayList<>();
	
	List<String> allTextBoxValues = new ArrayList<>();
		
	public PalindromeTestPage(WebDriver driver){
		
		this.driver = driver;
		
	}
	
	public String getPageTitle(){
		
		common.waitForClickableElement(this.driver, pageTitle);
		
		return driver.findElement(pageTitle).getText();

	}
	
	public String getElementValue(By element){
		
		return driver.findElement(element).getText();

	}
	
	public boolean isExpectedPageTitle(){
								
		String strExpectedPageTitle = reader.getExPageTitle();
		
		boolean isTitleEqual = false;
		
		String strPageTitle =  getPageTitle();
		
		if (strPageTitle.equals(strExpectedPageTitle)) {
			isTitleEqual = true;
		}
		
		return isTitleEqual;

	}
	
	public void setDropDown(String strSelection){
		
		Select selectDropDown = new Select(driver.findElement(dropDown));
		
		selectDropDown.selectByValue(strSelection);
	}
	
	public List<WebElement> getDropDownOptions(){
		
		Select selectDropDown = new Select(driver.findElement(dropDown));
		
		return selectDropDown.getOptions();
	}
	
	public void clickMoreButton(){
		  
		driver.findElement(moreButton).click();
		  
	}
	
	public List<String> getAllPalindromes() {
						
		List<String> lstPalindromes = new ArrayList<>();
		
		List<String> allRowValues = new ArrayList<>();
				
		List<String> allAlertValues = new ArrayList<>();
					
		allRowValues.addAll(getAllVisableRowsValues());
				
		allTextBoxValues.addAll(getTextBoxValues());
		 
		allAlertValues.addAll(getAlertValues()); 
		
		lstPalindromes.addAll(allRowValues);
		
		lstPalindromes.addAll(allTextBoxValues);	
		
		lstPalindromes.addAll(allAlertValues);	
		
		return lstPalindromes;
	}
	
	private List<String> getTextBoxValues(){
				
		 List<WebElement> elements = driver.findElements(By.tagName("input"));
				 
		 elements.forEach(element -> {
			 
			 String strTemp = element.getAttribute("value");
			 
			 if (!strTemp.equals("on") && (common.isPalindrome(strTemp))) {
				 allTextBoxValues.add(strTemp);	 
			 }
			 
		 });		 
		 
		 return allTextBoxValues;
		 		 		
	}
	
	private List<String> getAlertValues(){
		
		clickMoreButton();
				
		List<String> alertPalindromes = new ArrayList<>();
		
		Alert alert = driver.switchTo().alert();
		
		String strAlertText =  alert.getText(); 
		
		alert.accept();
				
		List<String> alertValues = Arrays.asList(strAlertText.split("\\s+"));
		
		alertValues.forEach(alertMsg -> {
			 if (common.isPalindrome(alertMsg)) {
				 alertPalindromes.add(alertMsg);	 
			 }
			}
		);
				 	
		 return alertPalindromes;
		
	}
	
	public boolean isMorePalindromesButton() {
		
		List<String> alertValues = new ArrayList<>();
		
		alertValues.addAll(getAlertValues());
		
		boolean isAlertNotEmpty = false;
		
		isAlertNotEmpty = (!alertValues.isEmpty());
		
		return isAlertNotEmpty;
		
	}
	
	private List<String> getHiddenRowValues(){
			 
		List<WebElement> hiddenElements 
				= driver.findElements(By.cssSelector(".sr-only"));
		 		 		 
		 hiddenElements.forEach(webElement -> 
		 hiddenValues.add(webElement.getText()));
		 
		 hiddenValues.remove(0);
		 
		 return hiddenValues;
	}
	
	private List<String> getAllVisableRowsValues() { 
				
		 hiddenValues = getHiddenRowValues();
			 		 
		 List<WebElement> elements = driver.findElements(By.className("row"));
		 
		 List <String> elementValues = new ArrayList<>();
		 		 
		 elements.forEach(webElement -> {
				 
			 List<String> lstTemp = new ArrayList<>();
			 
			 String strCurrText = webElement.getText().trim();
			 
			 if (strCurrText.contains(" ")) {
				 lstTemp = Arrays.asList(webElement.getText().split("\\s+"));
			 }
			 else
			 {
				 lstTemp = Arrays.asList(webElement.getText().split("\\r?\\n"));
			 }
			 			 			  			 
			 if(!lstTemp.equals(hiddenValues))
							
				 lstTemp.forEach(temp -> {
					 
					 if (common.isPalindrome(temp)){
						 elementValues.add(temp.trim());	 
					 }
				 });
								
				});
		 		 			 		 		 
		return elementValues; 
		 		  
	}
		
}