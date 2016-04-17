package com.testing.pardot;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

public class SideMenu {
	
	 WebDriver driver;
	 
	 By signOut = By.xpath("//li[@id='menu-account']/ul/li/a[contains(text(),'Sign Out')]");

	 public SideMenu(WebDriver driver){
		 this.driver=driver;
	 }
	
	// get the marketing menu element
	public WebElement menuMarketing(){
		return driver.findElement(By.xpath("//a[@id='mark-tog']/span[contains(text(),'Marketing')]"));
	}
	
	// get the marketing-->segmentation element
	public  WebElement menuSegmentation(){
		return driver.findElement(By.xpath("//ul[@id='dropmenu-marketing']/li/a[contains(text(),'Segmentation')]"));
	}
	
	// get the marketing-->segmentation-->lists element
	public   WebElement menuLists(){
		return driver.findElement(By.xpath("//ul[@id='dropmenu-marketing']/li/a[contains(text(),'Segmentation')]/../ul/li/a[contains(text(),'Lists')]"));
	}
	
	// go to (Marketing > Segmentation > Lists)
	public void gotoMarkSegLists() {
		Actions a = new Actions(driver);
		//move mouse to marketing element
		a.moveToElement(menuMarketing()).perform();
		//wait for marketing sub menu
		WebElement segmentation = menuSegmentation();
		PardotTest.waitForElementVisibility(driver,segmentation);
		//move mouse to segmentation
		a.moveToElement(segmentation).perform();
		//wait for segmentation sub menu
		WebElement list = menuLists();
		PardotTest.waitForElementVisibility(driver,list);
		// move mouse to list menu and click it
		a.moveToElement(list).click().build().perform();
	}
	
	// go to prospect element
	public  WebElement prospectMenu(){
		return driver.findElement(By.xpath("//a[@class='dropdown-toggle']/span[text()='Prospects']"));
	}
	
	// go to prospect list element
	public  WebElement prospectListMenu(){
		return driver.findElement(By.xpath("//ul[@id='dropmenu-prospects']/li/a[text()='Prospect List']"));
	}
		
	// goto prospect-->prospect list page 
	public void gotoProProspectList(){
		Actions a=new Actions(driver);
		a.moveToElement(prospectMenu()).perform();;
		WebElement prosList = prospectListMenu();
		PardotTest.waitForElementClickable(driver, prosList);
		a.moveToElement(prosList).click().build().perform();
	}
	
	//get the "Emails" element
	public  WebElement emailSearch(){
		return driver.findElement(By.xpath("//ul[@id='dropmenu-marketing']/li/a[text()='Emails']"));
	}
	
	// get the "new email" element
	public  WebElement sendNewEmail(){
		return driver.findElement(By.xpath("//ul[@id='dropmenu-marketing']/li/ul/li/a[text()='New Email']"));
	}
	
	//go to (Marketing > Emails > New Email)
	public  void gotoEmailNewEmail(){
		Actions a=new Actions(driver);
		a.moveToElement(menuMarketing()).perform();
		WebElement emailElement = emailSearch();
		PardotTest.waitForElementClickable(driver, emailElement);
		a.moveToElement(emailElement).perform();
		WebElement sendMailElement = sendNewEmail();
		PardotTest.waitForElementClickable(driver, sendMailElement);
		a.moveToElement(sendMailElement).perform();
		a.click().perform();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	}
	
	//goto logout image and select sign out
	public void logout(){
		WebElement signOutProfileElement=driver.findElement(By.xpath("//a[@class='dropdown-toggle']/span/i[@class='icon icon-user']"));
		Actions a= new Actions(driver);
		a.moveToElement(signOutProfileElement).perform();
		PardotTest.waitForElementClickable(driver, driver.findElement(signOut));
		a.moveToElement(driver.findElement(signOut)).click().build().perform();
        
	}

}
