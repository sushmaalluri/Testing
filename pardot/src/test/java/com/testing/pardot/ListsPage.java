package com.testing.pardot;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class ListsPage {
	
	WebDriver driver;
	
	By  addListButton = By.xpath("//a[@id='listxistx_link_create' and contains(text(),'Add list')]"); //"+Add Lists" button
	By  nameElement = By.xpath("//input[@name='name']"); //name input text box
	By createList = By.xpath("//button[@id='save_information' and text()='Create List']"); //"Create List" button element
	By duplicateListerror = By.id("error_for_name"); // duplicate list error
	By listPage = By.xpath("//div/h1[contains(text(),'Lists')]");// lists element for finding whether we got that page
	
	public ListsPage(WebDriver driver){
		this.driver = driver;
	}
	
	public WebElement getListsHeadingElement(){
		return driver.findElement(listPage); 
	}
	
	public WebElement getListCreatedElement(String listname){
		return driver.findElement(By.xpath("//h1[text()='"+listname+"']"));
	}
	
	public void addList(String name){
		
		// get "+Add Lists" element
       driver.findElement(addListButton).click();
       //wait for form to load
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        // get name element
        PardotTest.waitForElementClickable(driver, driver.findElement(nameElement));
        driver.findElement(nameElement).sendKeys(name);
        // get "Create List" button element
        driver.findElement(createList).submit();
    }
	
	public WebElement getDuplicateErrorElement(){
		return driver.findElement(duplicateListerror);
	}
	
	public void renameList(String listUrl, String listName){
		// navigate to listurl and rename the list
		   driver.navigate().to(listUrl);
		//get the edit element for updating list
		WebElement edit = driver.findElement(By.linkText("Edit"));
	    edit.click();	
	    driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	    
	    // get the list name element and change it
	    WebElement name=driver.findElement(By.id("name"));
	    name.clear();
	    name.sendKeys(listName);
	    
	    // update list by submitting form
	    WebElement createList = driver.findElement(By.id("save_information"));
	    createList.submit();
        
	    // wait for page to load
	    driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	    
	    
	}
	

}
