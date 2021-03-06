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

public class TC_009_VerifyIGSTTaxForMultipleItems extends TestBase{

	@BeforeClass
	public void openAPP() throws InterruptedException, IOException{
		init();
		LoginPage lp = new LoginPage();
		lp.doLogin(OR.getProperty("userId"), OR.getProperty("pwd"));
	}
	
	@Test(priority=0)
	public void verifyIGSTTax() throws InterruptedException{
		HomePage homepage = new HomePage(driver);
		Sales sales = new Sales(driver);
		SalesCartPage salesCart = new SalesCartPage(driver);
		CheckOutPage checkOut = new CheckOutPage(driver);
		ArrayList<Float> mrps = new ArrayList<Float>();
		ArrayList<Double> taxForAllItems = new ArrayList<Double>();
		homepage.enterCustomerName("i");
		homepage.tapOnStartButton();
		//First Item adding to cart
		sales.searchByItemName(APP.getProperty("ItemName12"));
		sales.clickOnSearchedItem();
		hideKeyboard();
		float mrp = sales.getPriceOfSingleBatch();
		mrps.add(mrp);
		taxForAllItems.add(sales.getTaxAmtCalculated(mrp, 12));
		sales.clickOnAddButton();
		//2nd item adding to cart
		sales.searchByItemName(APP.getProperty("ItemName5"));
		sales.clickOnSearchedItem();
		hideKeyboard();
		mrp = sales.getPriceOfSingleBatch();
		mrps.add(mrp);
		taxForAllItems.add(sales.getTaxAmtCalculated(mrp,5));
		sales.clickOnAddButton();
		//3rd item adding to cart
		sales.searchByItemName(APP.getProperty("ItemName0"));
		sales.clickOnSearchedItem();
		hideKeyboard();
		mrp = sales.getPriceOfSingleBatch();
		mrps.add(mrp);
		taxForAllItems.add(sales.getTaxAmtCalculated(mrp,0));
		sales.clickOnAddButton();
		double totalTaxForAllItems = getSumOfArraysDouble(taxForAllItems);
		totalTaxForAllItems = (double) Math.round(totalTaxForAllItems);
		salesCart.clickOnGetPayment();
		checkOut.clickOnConfirm();
		checkOut.clickOnDenyButton();
		Thread.sleep(5000);
		double IGSTTax = checkOut.getIGSTValueInSuccessPage();
		IGSTTax = Math.round(IGSTTax);
		Assert.assertEquals(IGSTTax, totalTaxForAllItems);
	}
}
