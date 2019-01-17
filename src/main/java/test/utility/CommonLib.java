package test.utility;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


public class CommonLib  {
	
	public boolean isPalindrome(String string) {
		
		StringBuilder reverse = new StringBuilder(string).reverse();
	    return (reverse.toString()).equals(string);
		     
	}
	
	public void printPalindromeCount(int intPalindromesCount) {
		
		 List<String> lines = Arrays.asList("The count of the visible Palindromes", 
				 String.valueOf(intPalindromesCount));
		 
		 Path file = Paths.get("countPalindromeNum.txt");
		 
		 try {
			Files.write(file, lines, Charset.forName("UTF-8"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		     
	}
	
	public void waitForClickableElement(WebDriver driver, By element) {
		  WebDriverWait wait = new WebDriverWait(driver, 10);
		  wait.until(ExpectedConditions.elementToBeClickable(element));
	}
	
	public void waitForVisibility(WebDriver driver, By element) {
		  WebDriverWait wait = new WebDriverWait(driver, 10);
		  wait.until(ExpectedConditions.visibilityOfElementLocated(element));
	}

} 