package com.testing.pardot;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

public class ProspectPage {
	
	WebDriver driver;
	
	public ProspectPage(WebDriver driver){
		this.driver=driver;
	}
	
	public void addProspect(String emailname){
		
		// get "+Add Prospect" element
		WebElement prospectAdd = driver.findElement(By.xpath("//span[@class='link_to_create pull-right']/a[text()=' Add prospect']")) ;
		prospectAdd.click();
		
		//wait for form to load
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		
        // get the email element and send keys
        WebElement emailvalue = driver.findElement(By.xpath("//*[@name='email' and @id='email']"));
       PardotTest.waitForElementVisibility(driver, emailvalue);
		emailvalue.sendKeys(emailname);
		
		// get the campaign element and select index at 1 
		Select campaign = new Select(driver.findElement(By.id("campaign_id")));
		campaign.selectByIndex(1);
		
		// get the profile element and select index 1
		Select profile = new Select(driver.findElement(By.id("profile_id")));
		profile.selectByIndex(1);
		
		// get "create prospect" element and submit it
		WebElement createProspect = driver.findElement(By.xpath("//*[@name='commit' and @value='Create prospect']"));
        createProspect.submit();
        
        //wait for form to load
        PardotTest.waitForTitle(driver, emailname);
		
		
	}
	
	public void addProspectToList(String listName,String emailName){
		
		// get the lists element and click it
		WebElement addProspectToList = driver.findElement(By.xpath("//*[@class='navbar-inner']/ul/li/a[contains(@href, '/list/prospect/prospect_id/') and contains(text(),'Lists')]"));
		addProspectToList.click();
		
		// get the "select list to add" element and click it
		WebElement selectListToAdd=driver.findElement(By.xpath("//div[starts-with(@id,'sel')]/a/span[contains(text(),'Select a list to add')]"));
		selectListToAdd.click();
		
		// get the "drop down" element and send newly created list name to it
		WebElement dropDown = driver.findElement(By.xpath("//div[@class='chzn-drop']/div[@class='chzn-search']/input"));
		dropDown.sendKeys(listName);
		
		// get the list name in the search box and click it
		WebElement selectMenuList = driver.findElement(By.xpath("//div[@class='chzn-drop']/ul[@class='chzn-results']/li[contains(text(),'"+listName+"')]"));
		selectMenuList.click();
		
		// get the "save lists" element and submit it
		WebElement saveList = driver.findElement(By.xpath("//input[@name='commit' and @value='Save lists']"));
		saveList.submit();
		
		//wait for form to load
		PardotTest.waitForTitle(driver, emailName);
	}
	

}
