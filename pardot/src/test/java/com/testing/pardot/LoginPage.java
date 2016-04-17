package com.testing.pardot;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage {
	
	WebDriver driver;
	By emailAddress = By.id("email_address");
	By password = By.id("password");
	By login = By.name("commit");
	
	public LoginPage(WebDriver driver){
		this.driver=driver;
	}
	
	public void login(){
		// go to pardot.com login page 
		driver.navigate().to("https://pi.pardot.com");
		driver.manage().window().maximize();
		
		//login to pardot.com by giving email address and password
		 driver.findElement(emailAddress).sendKeys("pardot.applicant@pardot.com");
		
		 driver.findElement(password).sendKeys("Applicant2012");
		
		 driver.findElement(login).submit();
	}

}
