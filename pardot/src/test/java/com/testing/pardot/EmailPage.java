package com.testing.pardot;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;



public class EmailPage {
	
	WebDriver driver;
	
	By templateCheckBox = By.xpath("//input[@type='checkbox' and @id='from_template']");
    By saveButton = By.xpath("//a[@id='save_information']");
    By sendinglink = By.xpath("//a[@id='flow_sending' and contains(text(),'Sending')]");
    By generalUser = By.xpath("//select[@name='a_sender[]']/option[text()='General User']");
    By generalName = By.xpath("//input[@name='a_general_name']");
    By generalEmail = By.xpath("//input[@name='a_general_email']");
    By subjectElement = By.xpath("//input[@name='subject_a']");
    By saveSendEmail = By.xpath("//button[@id='save_footer']");
    By nameElement = By.xpath("//div[@class='controls']/input[@name='name' and @id='name']");
    By campaighId = By.xpath("//span[@class='input-xlarge uneditable-input object-name']");
    By selectFolder = By.xpath("//div[@class='media-body']/h4[text()=' Kat Eagles']");	
    By chooseSelect = By.xpath("//button[@id='select-asset' and contains(text(),'Choose Selected')]");
    By textradioElement = By.xpath("//input[@type='radio' and @id='email_type_text_only' and @name='type']");
    By selectListToAdd = By.xpath("//div[starts-with(@id,'sel')]/a/span[contains(text(),'Select a list to add')]");
    
    
   public EmailPage(WebDriver driver){
	   this.driver = driver;
	   
   }
   
   public void sendEmail(String toList){
	   
	   String emailName="hello";
	   
	     //  filling basic email information 
	     // get the name element and filling name
		 driver.findElement(nameElement).sendKeys(emailName);
		 
		 // get the campaign element and select the particular folder and choosing it
	     driver.findElement(campaighId).click();
	     driver.findElement(selectFolder).click();
         driver.findElement(chooseSelect).click();
         
         // get the email type element and select the text button 
		 driver.findElement(textradioElement).click();
		 driver.findElement(templateCheckBox).click();
		 
		 //save the information
	     driver.findElement(saveButton).click();
		
		PardotTest.waitForElementVisibility(driver, By.xpath("//div[@id='control_name' and contains(text(),'"+emailName+"')]"));
		 //get the sending element and click it
		 driver.findElement(sendinglink).click();
		
		 PardotTest.waitForTitle(driver,"sending");
		//select the lists to which email to be send
		driver.findElement(selectListToAdd).click();
		
		// get the "drop down" element and send newly created list name to it
		WebElement dropDown = driver.findElement(By.xpath("//div[@class='chzn-drop']/div[@class='chzn-search']/input"));
		dropDown.sendKeys(toList);
		
		// get the list name in the search box and click it
		WebElement selectMenuList = driver.findElement(By.xpath("//div[@class='chzn-drop']/ul[@class='chzn-results']/li[contains(text(),'"+toList+"')]"));
		selectMenuList.click();
		
		//selecting general user
		driver.findElement(generalUser).click();
	
		WebElement gnameElement = driver.findElement(generalName);
		PardotTest.waitForElementClickable(driver, gnameElement);
		//enter name
		gnameElement.sendKeys("john");
		//enter email
		driver.findElement(generalEmail).sendKeys("john@gmail.com");
		//entering subject element
		driver.findElement(subjectElement).sendKeys("sending a text email");
		//get save button element and click it
		driver.findElement(saveSendEmail).click();
   }
    

}
