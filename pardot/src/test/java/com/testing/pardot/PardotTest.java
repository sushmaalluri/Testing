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
       
       // go to (Marketing > Segmentation > Lists)
       SideMenu menupage = new SideMenu(driver);
       menupage.gotoMarkSegLists();
       //verify lists page
	   assertEquals("unable to go to lists page","https://pi.pardot.com/list",driver.getCurrentUrl());
      
	   ListsPage listPage=new ListsPage(driver);
	   String listName="sushma"+System.currentTimeMillis();
	   listPage.addList(listName);
	   // verify if list is created
	   assertTrue("unable to create a list",driver.getTitle().contains(listName));
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
	   
	   // navigate to lists page to create new list 
	   driver.navigate().to("https://pi.pardot.com/list");
	   // retrying to create list with firstlist name after renaming the list
	   listPage.addList(listName);
	   // verify if list is created
	   assertTrue("unable to create a list",driver.getTitle().contains(listName));
	   
	   // saving the url to ensure the prospect is added to the list in future
	   String listUrl=driver.getCurrentUrl();
	   
	   // go to prospect list page
	   menupage.gotoProProspectList();
       assertTrue("unable to reach prospect list page",driver.getTitle().contains("Prospects"));

	   // add new prospect 
	   String prospectEmail = "mail"+System.currentTimeMillis()+"@gmail.com";
	   ProspectPage prosPage = new ProspectPage(driver);
	   prosPage.addProspect(prospectEmail);
	   // verifying that new prospect has created
       assertTrue("prospect is not created sucessfully",driver.getTitle().contains(prospectEmail));
      
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
