package com.qa.opencart.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.opencart.utils.Constants;
import com.qa.opencart.utils.ElementUtil;

import io.qameta.allure.Step;

public class LoginPage {
	
	// 1. declare private driver
	private WebDriver driver;
	private ElementUtil eleUtil;
	
	//2. create page constructor
	public LoginPage(WebDriver driver) {
		this.driver=driver;
		eleUtil =new ElementUtil(driver);
	}
	
	//3. By locators
	private By emailId = By.id("input-email");
	private By password = By.id("input-password");
	private By loginBtn = By.xpath("//input[@value='Login']");
	private By registerLink=By.linkText("Register");
	private By forgotPwdLink=By.linkText("Forgotten Password");
	private By loginErrMsg=By.cssSelector("div.alert.alert-danger.alert-dismissible");
	
	//4.Page Actions
	@Step("getting login page title..........")
	public String getLoginPageTitle() {
		return eleUtil.doGetTitle(Constants.LOGIN_PAGE_TITLE, Constants.DEFAULT_TIME_OUT);
	}
	
	@Step("getting login page url..........")
	public boolean getLoginPageUrl() {
		return eleUtil.waitForURLToContain(Constants.LOGIN_PAGE_URL_FRACTION, Constants.DEFAULT_TIME_OUT);
	}
	
	@Step("checking register link exist or not..........")
	public boolean isRegisterLinkExist() {
		return eleUtil.doIsDisplayed(registerLink);
	}
	
	@Step("checking forgot password link exist or not..........")
	public boolean isForgotPwdLinkExist() {
		return eleUtil.doIsDisplayed(forgotPwdLink);
	}
	
	@Step("do login with username : {0} and password: {1}")
	public AccountsPage doLogin(String un, String pwd) {
		System.out.println("login with : "+un+" : "+pwd);
		eleUtil.doSendKeys(emailId, un);
		eleUtil.doSendKeys(password, pwd);
		eleUtil.doClick(loginBtn);
		return new AccountsPage(driver);  //page chaining - login page is responsible to launch account page
		//so creating object of account page here
	}
	
	@Step("do login with wrong username : {0} and wrong password: {1}")
	public boolean doLoginWithWrongCredentials(String un,String pwd) {
		System.out.println("try to login with wrong credentials : "+un+" : "+pwd);
		eleUtil.doSendKeys(emailId, un);
		eleUtil.doSendKeys(password, pwd);
		eleUtil.doClick(loginBtn);
		String errorMsg=eleUtil.doGetText(loginErrMsg);
		System.out.println(errorMsg);
		if(errorMsg.contains(Constants.LOGIN_ERROR_MESSAGE)) {
			System.out.println("login is not done....");
			return false;
		}
		return true;
	}
	@Step("navigating to registration page.......")
	public RegistrationPage goToRegisterPage() {
		eleUtil.doClick(registerLink);
		return new RegistrationPage(driver);
	}
}
