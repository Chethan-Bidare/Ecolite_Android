package c2info_ElMob.SalesTC;

import java.io.IOException;
import java.util.ArrayList;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import c2info_ElMob.TestBase.TestBase;
import c2info_ElMob.UI_Actions.CheckOutPage;
import c2info_ElMob.UI_Actions.HomePage;
import c2info_ElMob.UI_Actions.LoginPage;
import c2info_ElMob.UI_Actions.Sales;
import c2info_ElMob.UI_Actions.SalesCartPage;

public class TC_006_VerifyDiscountForMultipleItems extends TestBase{

	@BeforeClass
	public void openAPP() throws InterruptedException, IOException{
		init();
		LoginPage lp = new LoginPage();
		lp.doLogin(OR.getProperty("userId"), OR.getProperty("pwd"));
	}
	
	float calculatedTotalDisc;
	
	@Test(priority=0)
	public void verifyMultipleItemDiscInCartPage() throws InterruptedException{
		HomePage homepage = new HomePage(driver);
		Sales sales = new Sales(driver);
		SalesCartPage salesCart = new SalesCartPage(driver);
		ArrayList<Float> mrps = new ArrayList<Float>();
		
		homepage.tapOnStartButton();
		sales.searchByItemName(APP.getProperty("ItemName5"));
		sales.clickOnSearchedItem();
		hideKeyboard();
		mrps.add(sales.getPriceOfSingleBatch());
		sales.enterDiscount(APP.getProperty("StandardDisc"));
		hideKeyboard();
		sales.clickOnAddButton();
		sales.searchByItemName(APP.getProperty("ItemName12"));
		sales.clickOnSearchedItem();
		hideKeyboard();
		mrps.add(sales.getPriceOfSingleBatch());
		sales.enterDiscount(APP.getProperty("StandardDisc"));
		hideKeyboard();
		sales.clickOnAddButton();
		String disc = APP.getProperty("StandardDisc");
		int discount = Integer.parseInt(disc);
		float totalMRPInBatches = getSumOfArraysFloat(mrps);
		float calculatedDisc =sales.getDiscountValue(discount,mrps.get(0),5);
		float calculatedDisc1 =sales.getDiscountValue(discount,mrps.get(1),12);
		calculatedTotalDisc = calculatedDisc + calculatedDisc1 ;
		calculatedTotalDisc = Math.round(calculatedTotalDisc);
		
		System.out.println("calculatedTotalDisc"+calculatedTotalDisc);
		salesCart.clickOnCartPage();
		float totalMRPAfterDiscInCart = getSumOfArraysFloat(salesCart.getPriceForMultipleItemsInCartPage());
		float actualDiscInCart = totalMRPInBatches - totalMRPAfterDiscInCart ;
		actualDiscInCart = Math.round(actualDiscInCart);
		Assert.assertEquals(actualDiscInCart, calculatedTotalDisc);
		
		
	}
	
	@Test(priority=1)
	public void verifyMultipleItemDiscInCheckOutPage(){
		SalesCartPage salesCart = new SalesCartPage(driver);
		CheckOutPage checkOut = new CheckOutPage(driver);
		
		salesCart.clickOnGetPayment();
		float actualDiscInCheckOutPage = checkOut.getDiscValueInCheckOutPage();
		actualDiscInCheckOutPage = Math.round(actualDiscInCheckOutPage);
		Assert.assertEquals(actualDiscInCheckOutPage, calculatedTotalDisc);
	}
	
	@Test(priority=2)
	public void verifyMultipleItemDiscInSuccessPage() throws InterruptedException{
		CheckOutPage checkOut = new CheckOutPage(driver);
		
		checkOut.clickOnConfirm();
		checkOut.clickOnDenyButton();
		Thread.sleep(5000);
		swipeUpInBatchList();
		float actualDiscInSuccessPage = checkOut.getDiscValueInSuccessPage();
		actualDiscInSuccessPage = Math.round(actualDiscInSuccessPage);
		Assert.assertEquals(actualDiscInSuccessPage, calculatedTotalDisc);
	}
}
