package com.qa.opencart.tests;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class LoginPageNegativeTest extends BaseTest{

	@DataProvider
	public Object[][] loginWrongTestData() {
		return new Object[][] {
			{"rkv11@gmail.com","rkv@123"},
			{"ravneetvirdi92@gmail.com","test@123"},
			{"gkfdgdfo@gmail.com","test@123"},
			{"@#$$$$##@gmail.com","test@123"},
			{"rtgbf@yahoo.com","cvbgvnfgh"},
		};
	}
	
	@Test(dataProvider="loginWrongTestData")
	public void loginNegativeTest(String username,String password) {
		
		Assert.assertFalse(loginPage.doLoginWithWrongCredentials(username, password));
		
	}
	
}
