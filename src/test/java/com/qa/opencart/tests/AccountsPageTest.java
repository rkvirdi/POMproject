package com.qa.opencart.tests;

import java.util.List;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.opencart.utils.Constants;
import com.qa.opencart.utils.Errors;

public class AccountsPageTest extends BaseTest{
	
	@BeforeClass
	public void accPageSetup() {
		accountsPage= loginPage.doLogin(prop.getProperty("username"), prop.getProperty("password"));
	}
	
	@Test(priority=1)
	public void accPageTitleTest() {
		String realTitle = accountsPage.getAccountPageTitle();
		System.out.println("account page title : "+realTitle);
		Assert.assertEquals(realTitle, Constants.ACCOUNTS_PAGE_TITLE);
	}
	
	@Test(priority=2)
	public void accPageHeaderTest() {
		String header=accountsPage.getAccountsPageHeader();
		System.out.println("account page header : "+header);
		Assert.assertEquals(header, Constants.ACCOUNTS_PAGE_HEADER,Errors.ACC_PAGE_HEADER_NOT_FOUND_ERROR_MESSG);
	}
	
	@Test(priority=3)
	public void accSecListTest() {
		List<String> actaccSecList=accountsPage.getAccountSecList();
		Assert.assertEquals(actaccSecList,Constants.getExpectedAccSecList());
		}
	
	@Test(priority=4)
	public void isLogoutExistTest() {
		Assert.assertTrue(accountsPage.isLogoutLinkExist());
	}
	
	@Test(priority=5)
	public void isSearchExistTest() {
		Assert.assertTrue(accountsPage.isSearchExist());
	}
	
	@DataProvider     //should return 2 D array
	public Object[][] productData() {
		return new Object[][] {  // 1 column 3 rows
			{"MacBook"},
			{"Apple"},
			{"Samsung"},
		};
	}
	
	@Test(priority=6, dataProvider = "productData")
	public void searchTest(String productName) {
		searchResultPage=accountsPage.doSearch(productName);
		Assert.assertTrue( searchResultPage.getProductsListCount()>0);
	}
	
	@DataProvider     //should return 2 D array
	public Object[][] productSelectData() {
		return new Object[][] {  // 2 column 4 rows
			{"MacBook" , "MacBook Pro"},
			{"iMac" , "iMac"},
			{"Samsung" , "Samsung SyncMaster 941BW"},
			{"Apple" , "Apple Cinema 30\""}
		};
	}
	
	@Test(priority=7 , dataProvider = "productSelectData")
	public void selectProductTest(String productName , String mainProductName) {
		searchResultPage = accountsPage.doSearch(productName);
		productInfoPage = searchResultPage.selectProduct(mainProductName);
		Assert.assertEquals(productInfoPage.getProductHeader(),mainProductName);
	}
}
