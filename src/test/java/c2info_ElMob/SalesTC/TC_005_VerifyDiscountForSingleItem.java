package c2info_ElMob.SalesTC;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import c2info_ElMob.TestBase.TestBase;
import c2info_ElMob.UI_Actions.CheckOutPage;
import c2info_ElMob.UI_Actions.HomePage;
import c2info_ElMob.UI_Actions.LoginPage;
import c2info_ElMob.UI_Actions.Sales;
import c2info_ElMob.UI_Actions.SalesCartPage;

public class TC_005_VerifyDiscountForSingleItem extends TestBase{

	@BeforeClass
	public void openAPP() throws InterruptedException, IOException{
		init();
		LoginPage lp = new LoginPage();
		lp.doLogin(OR.getProperty("userId"), OR.getProperty("pwd"));
	}
	
	@Test
	public void verifySingleItemDisc() throws InterruptedException{
		HomePage homepage = new HomePage(driver);
		Sales sales = new Sales(driver);
		SalesCartPage salesCart = new SalesCartPage(driver);
		CheckOutPage checkOut = new CheckOutPage(driver);
		
		
		//homepage.enterCustomerName(OR.getProperty("custName"));
		homepage.tapOnStartButton();
		sales.searchByItemName(APP.getProperty("ItemName5"));
		sales.clickOnSearchedItem();
		hideKeyboard();
		float mrp = sales.getPriceOfSingleBatch();
		sales.enterDiscount(APP.getProperty("StandardDisc"));
		hideKeyboard();
		String disc = APP.getProperty("StandardDisc");
		int discount = Integer.parseInt(disc);
		sales.clickOnAddButton();
		float calculatedDisc =sales.getDiscountValue(discount, mrp,5);
		calculatedDisc = Math.round(calculatedDisc);
		System.out.println(calculatedDisc);
		salesCart.clickOnCartPage();
		float actualDiscInCart = mrp - salesCart.getPriceForSingleItemInCartPage();
		actualDiscInCart = Math.round(actualDiscInCart);
		salesCart.clickOnGetPayment();
		float actualDiscInCheckOutPage = checkOut.getDiscValueInCheckOutPage();
		actualDiscInCheckOutPage = Math.round(actualDiscInCheckOutPage);
		checkOut.clickOnConfirm();
		checkOut.clickOnDenyButton();
		Thread.sleep(5000);
		swipeUpInBatchList();
		float actualDiscInSuccessPage = checkOut.getDiscValueInSuccessPage();
		actualDiscInSuccessPage = Math.round(actualDiscInSuccessPage);
		Assert.assertEquals(actualDiscInCart, calculatedDisc);
		Assert.assertEquals(actualDiscInCheckOutPage, calculatedDisc);
		Assert.assertEquals(actualDiscInSuccessPage, calculatedDisc);
	}
	
	
}
