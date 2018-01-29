package com.Qa.TestScript;

import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.Qa.Test_Form.TestBase;
import com.Qa.Test_Form.TestformPage;

import Utility.Utility;

public class TestForm_Testcase extends TestBase
{
	
	TestformPage ele;
	String sheetname= "valid";
	
	@BeforeMethod()
	public void setup()
	{
		initialisation();
		ele= new TestformPage();
	}
	
	@AfterMethod()
	public void teardown()
	{
		driver.quit();
	}
	@DataProvider
	public Object[][] getdata() throws EncryptedDocumentException, InvalidFormatException, IOException
	{
		Object data[][]=Utility.getTestData(sheetname);
				return data;
	}
	
	@Test(priority=0,dataProvider ="getdata")
	public void validForm(String FirstName,String LastName, String Phone) throws InterruptedException, IOException
	{
	ele.inputfields(FirstName, LastName, Phone);
	Thread.sleep(2000l);
	ele.submitbtn();
	}
	
	@Test(priority=1)
	public void invalidcredentails() throws InterruptedException, IOException
	//invlaid First name, Last Name,Phone
	{
		ele.inputfields(Utility.readExcel(1, 0),Utility.readExcel(1, 1),Utility.readExcel(1, 2));
		ele.submitbtn();
		String fnameErrorTxt= driver.findElement(By.xpath("//*[@id='fname-error']")).getText();
		Assert.assertEquals(fnameErrorTxt, "First name can only be characters");
		String lnameErrorTxt= driver.findElement(By.xpath("//*[@id='lname-error']")).getText();
		Assert.assertEquals(lnameErrorTxt, "Last name can only be characters");
		String phoneErrorTxt = driver.findElement(By.xpath("//*[@id='phone-error']")).getText();
		Assert.assertEquals(phoneErrorTxt, "Phone is incorrect");
	}
	@Test(priority=2)
	public void checkingSubmitbuttonWithEmptyset() 
	{
	//Empty set of  First name, Last Name,Phone
	ele.submitbtn();
	String expFirstnameMessage = driver.findElement(By.xpath("//*[@id='fname-error']")).getText();
	Assert.assertEquals(expFirstnameMessage, "First name is a required field");
	String expLastNameMessage = driver.findElement(By.xpath("//*[@id='lname-error']")).getText();
	Assert.assertEquals(expLastNameMessage, "Last name is a required field");
	String expPhoneMessage = driver.findElement(By.xpath("//*[@id='phone-error']")).getText();
	Assert.assertEquals(expPhoneMessage, "Phone number is a required input");
	}
	
	@Test(priority=3)
	public void invalidMaximumcredentials() throws IOException, InterruptedException 
	{
		//Trying with >maximum no: First name, Last Name,Phone
		ele.inputfields(Utility.readExcel(3, 0),Utility.readExcel(3, 1),Utility.readExcel(3, 2));
		ele.submitbtn();
		String fnameErrorTxt= driver.findElement(By.xpath("//*[@id='fname-error']")).getText();
		Assert.assertEquals(fnameErrorTxt, "The max length of first name is 20");
		String lnameErrorTxt= driver.findElement(By.xpath("//*[@id='lname-error']")).getText();
		SoftAssert soft= new SoftAssert();
		soft.assertEquals(lnameErrorTxt, "The max length of last name is 20");
		String phoneErrorTxt = driver.findElement(By.xpath("//*[@id='phone-error']")).getText();
		Assert.assertEquals(phoneErrorTxt, "Phone is incorrect");
		soft.assertAll();
		ele.clearform();
	}
}
