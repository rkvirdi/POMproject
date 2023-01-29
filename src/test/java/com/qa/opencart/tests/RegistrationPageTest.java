package com.qa.opencart.tests;

import java.util.Random;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.opencart.utils.Constants;
import com.qa.opencart.utils.ExcelUtil;

public class RegistrationPageTest extends BaseTest {

	@BeforeClass
	public void setUpRegistration() {
		
		registrationPage=loginPage.goToRegisterPage();
	}
	
	public String getRandomEmail() {
		Random randomGenerator=new Random();
		String email = "septautomation"+randomGenerator.nextInt(1000)+"@gmail.com";
		return email;
	}
	
	@DataProvider
	public Object[][] getRegisterData() {
		return ExcelUtil.getTestData(Constants.REGISTER_SHEET_NAME);
	}
	
	@Test(dataProvider = "getRegisterData") //no hard coded               for phone no,use single quote before number otherwise number becomes power
	public void userRegistrationTest(String firstName , String lastName,
			String telephone, String password, String subscribe){
		
		Assert.assertTrue(registrationPage.accountRegistration(firstName, lastName, getRandomEmail(), telephone, password, subscribe));
	}
	
	
//	@Test
//	public void userRegistrationTest() {                                                 //hard coded
//		Assert.assertTrue(registrationPage.accountRegistration("Ravnit", "Kar", 
//				"ravi675544@gmail.com", "939394943", "ravik", "yes"));
//		
//	}
	
	
	
}
