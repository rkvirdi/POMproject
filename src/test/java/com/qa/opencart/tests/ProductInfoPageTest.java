package com.qa.opencart.tests;

import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.qa.opencart.utils.Constants;

public class ProductInfoPageTest extends BaseTest{
	
	@BeforeClass
	public void productInfoSetup() {
		accountsPage= loginPage.doLogin(prop.getProperty("username"), prop.getProperty("password"));
	}
	
	@Test(priority=1)
	public void productHeaderTest() {
		searchResultPage=accountsPage.doSearch("MacBook");
		productInfoPage = searchResultPage.selectProduct("MacBook Pro");
		Assert.assertEquals(productInfoPage.getProductHeader(),"MacBook Pro");
	}
	
	@Test(priority=2)
	public void productImageCountTest() {
		searchResultPage=accountsPage.doSearch("iMac");
		productInfoPage = searchResultPage.selectProduct("iMac");
		Assert.assertEquals(productInfoPage.getProductImageCount(),Constants.IMAC_IMAGE_COUNT);
	}
	//try to add only 1 hard assertion each test, if more required then use soft assertion as follows
	
	@Test(priority=3)
	public void productInfoTest() {
		searchResultPage=accountsPage.doSearch("MacBook");
		productInfoPage = searchResultPage.selectProduct("MacBook Pro");
		Map<String,String> actProductInfoMap= productInfoPage.getProductInfo();
		actProductInfoMap.forEach((k,v)-> System.out.println(k+":"+v)); 
		softAssert.assertEquals(actProductInfoMap.get("name"), "MacBook Pro1"); //if this fails then rest assertioms wont work so we use soft assertion
		softAssert.assertEquals(actProductInfoMap.get("Brand"), "Apple");
		softAssert.assertEquals(actProductInfoMap.get("price"), "$2,000.00");
		softAssert.assertAll();  //this will tell how many passed how many failed
		
	}
}
