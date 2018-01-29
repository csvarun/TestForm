package com.Qa.Test_Form;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class TestformPage extends TestBase

{
	@FindBy(xpath="//*[text()='Test Form']") WebElement testFormtxt;
	@FindBy(id="fname") WebElement firstNamelnk;
	@FindBy(id="lname") WebElement lastNamelnk;
	@FindBy(id="phone") WebElement phoneLnk;
	@FindBy(id="submit") WebElement submitBtn;
	@FindBy(xpath="//button[text()='Clear Form']") WebElement clearFormBtn;
	@FindBy(xpath="//p[text()='Form submitted!'") WebElement formSubmittedTxt;

		
	public TestformPage() 
	{
		PageFactory.initElements(driver, this);
	}
	public boolean textform_txt()
	{
		return testFormtxt.isDisplayed();
	}
	public void inputfields(String firstName,String lastName,String phoneNo)
	{
		firstNamelnk.sendKeys(firstName);
		lastNamelnk.sendKeys(lastName);
		phoneLnk.sendKeys(phoneNo);
	}
	public void submitbtn()
	{
		submitBtn.click();
	}
	public void clearform()
	{
		clearFormBtn.click();
	}
	public boolean formSubmittedText()
	{
		return formSubmittedTxt.isDisplayed();
	}
	
	
}