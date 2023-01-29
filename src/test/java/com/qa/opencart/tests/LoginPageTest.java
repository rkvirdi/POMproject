package com.qa.opencart.tests;

import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.qa.opencart.listeners.TestAllureListener;
import com.qa.opencart.utils.Constants;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;

@Epic("Epic 100: Design Open Cart App - Login Page")
@Story("US 101 : Open Cart Login Design with multiple features")
@Listeners(TestAllureListener.class)
public class LoginPageTest extends BaseTest{
	
	@Description("login Page Title Test")
	@Severity(SeverityLevel.MINOR)
	@Test(priority=1)
	public void loginPageTitleTest() {
		String realTitle=loginPage.getLoginPageTitle();
		System.out.println("page title : "+realTitle);
		Assert.assertEquals(realTitle, Constants.LOGIN_PAGE_TITLE);
	}
	
	@Description("login Page Url Test")
	@Severity(SeverityLevel.NORMAL)
	@Test(priority=2)
	public void loginPageUrlTest() {
		Assert.assertTrue(loginPage.getLoginPageUrl());
	}
	
	@Description("register link Test")
	@Severity(SeverityLevel.CRITICAL)
	@Test(priority=3)
	public void registerLinkTest(){
		Assert.assertTrue(loginPage.isRegisterLinkExist());
	}
	
	@Description("forgot pwd link Test")
	@Severity(SeverityLevel.CRITICAL)
	@Test(priority=4)
	public void forgotPwdLinkTest() {
		Assert.assertTrue(loginPage.isForgotPwdLinkExist());
	}
	
	@Description("login Test with correct credentials")
	@Severity(SeverityLevel.BLOCKER)
	@Test(priority=5)
	public void loginTest() {
		accountsPage= loginPage.doLogin(prop.getProperty("username").trim(),prop.getProperty("password").trim());
		//to verify if account page open
		Assert.assertEquals(accountsPage.getAccountPageTitle(), Constants.ACCOUNTS_PAGE_TITLE);
	}
//these are positive test cases
}
