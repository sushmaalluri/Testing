package com.testing.pardot;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import junit.framework.TestCase;

public class PardotTest extends TestCase{
	
	WebDriver driver;
	By dashBoard = By.xpath("//h1[contains(text(),'Dashboard')]");// dashboard element
	@Override
	public void setUp(){
		driver = new FirefoxDriver();
	}
	
	public void testPardot(){
		
        // get login page and login
		LoginPage loginPage = new LoginPage(driver);
		loginPage.login();
	   //on successful login the url appears to be "https://pi.pardot.com/"
       assertEquals("login was unsuccessful","https://pi.pardot.com/",driver.getCurrentUrl());
      // verifying for availability of dashboard element after logging in
      assertTrue("unable to go to lists page", driver.findElement(dashBoard).isDisplayed());
       
       // go to (Marketing > Segmentation > Lists)
       SideMenu menupage = new SideMenu(driver);
       menupage.gotoMarkSegLists();
	   ListsPage listPage=new ListsPage(driver);
       //verify lists page
	   assertEquals("unable to go to lists page","https://pi.pardot.com/list",driver.getCurrentUrl());
       // verify for avalibility of Lists element after clicking (Marketing > Segmentation > Lists)
	   assertTrue("unable to go to lists page", listPage.getListsHeadingElement().isDisplayed());
	   
	  
	   String listName="sushma"+System.currentTimeMillis();
	   listPage.addList(listName);
	   // verify if list is created
	   assertTrue("unable to create a list",driver.getTitle().contains(listName));
	   // verify for list created by getting the heading element
	   assertEquals("List is not created",listName,listPage.getListCreatedElement(listName).getText());
	   
	   // saving the firstlist link for future use
	   String firstListUrl= driver.getCurrentUrl();	  
	   // navigate to lists page to create new list 
	   driver.navigate().to("https://pi.pardot.com/list");
	   // retrying to create the list with same name
	   listPage.addList(listName);
	   // duplicate list is not allowed so it will return a error message
	   assertTrue("Duplicate List names are allowed",  listPage.getDuplicateErrorElement().isDisplayed());
	   
	   String reNameList="rensushma"+System.currentTimeMillis();
	   // navigate to firstlist and rename the list
	   listPage.renameList(firstListUrl, reNameList);
	   // verify that firstlist has been renamed
	   assertTrue("unsuccessful rename",driver.getTitle().contains(reNameList));
	// verify for list created by getting the heading element
	   assertEquals("List is not renamed",reNameList,listPage.getListCreatedElement(reNameList).getText());
	   
	   // navigate to lists page to create new list 
	   driver.navigate().to("https://pi.pardot.com/list");
	   // retrying to create list with firstlist name after renaming the list
	   listPage.addList(listName);
	   // verify if list is created
	   assertTrue("unable to create a list",driver.getTitle().contains(listName));
	   // verify for list created by getting the heading element
	   assertEquals("List is not created",listName,listPage.getListCreatedElement(listName).getText());
	   
	   // saving the url to ensure the prospect is added to the list in future
	   String listUrl=driver.getCurrentUrl();
	   
	   // go to prospect list page
	   menupage.gotoProProspectList();
	   ProspectPage prosPage = new ProspectPage(driver);
       assertTrue("unable to reach prospect list page",driver.getTitle().contains("Prospects"));
       // verify whether prospect header is present when selected prospect list
       assertTrue("header not present so no page",prosPage.getProsHeader().isDisplayed());
	   
       // add new prospect 
	   String prospectEmail = "mail"+System.currentTimeMillis()+"@gmail.com";	 
	   prosPage.addProspect(prospectEmail);
	   // verifying that new prospect has created
       assertTrue("prospect is not created sucessfully",driver.getTitle().contains(prospectEmail));
       // verify the header of the page after creating prospect
       assertEquals("prospect is not created successfully",prospectEmail,prosPage.getProsCreatedHeader(prospectEmail).getText());
       
       // adding prospect to newly created list 
       prosPage.addProspectToList(listName, prospectEmail);
       assertTrue("prospect is not added to list sucessfully",driver.getTitle().contains(prospectEmail));
       // to ensure whether the prospect is added to the newly created list
       driver.navigate().to(listUrl);
       waitForElementVisibility(driver, By.xpath("//tbody/tr/td/a[./text()='"+prospectEmail+"']"));  
 	   WebElement pros =driver.findElement(By.xpath("//tbody/tr/td/a[./text()='"+prospectEmail+"']"));
 	   assertTrue("Prospect is not displayed",pros.isDisplayed());
       
       // go to marketing-->emails-->new email
       menupage.gotoEmailNewEmail();
       // create,build and send an email to firstlist created
       EmailPage emailpage = new EmailPage(driver);
       emailpage.sendEmail(listName);
       
       // logging out from pardot
       menupage.logout();
       //verify logogut by checking for password element on login page
       assertTrue("logout is unsuccessful", loginPage.getPasswordElement().isDisplayed());
	}


	

	// wait for an title to be visible else timeout
	public static void waitForTitle(WebDriver driver, final String title) {
		WebDriverWait wait= new WebDriverWait(driver, 20);
		wait.until(new ExpectedCondition<Boolean>() {
			public Boolean apply(WebDriver d) {
				return d.getTitle().toLowerCase().contains(title);
			}
		});
	}

	
	// wait for an WebElement to be clickable else timeout
		public static void waitForElementClickable(WebDriver driver,WebElement element) {
			WebDriverWait wait= new WebDriverWait(driver, 20);
			wait.until(ExpectedConditions.elementToBeClickable(element));
		}
		
	// wait for an WebElement to be visible else timeout
	public static void waitForElementVisibility(WebDriver driver,WebElement element) {
		WebDriverWait wait= new WebDriverWait(driver, 20);
		wait.until(ExpectedConditions.visibilityOf(element));
	}
	
	// wait for an xpath to be visible else timeout
	public static void waitForElementVisibility(WebDriver driver,By xpath) {
		WebDriverWait wait= new WebDriverWait(driver, 20);
		wait.until(ExpectedConditions.visibilityOfElementLocated(xpath));
	}

	@Override
	public void tearDown(){
	    driver.close();
	}
}
