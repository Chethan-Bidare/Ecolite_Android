package c2info_ElMob.SalesReturnTC;

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

public class TC_012_VerifyInvoiceValueWithDisc extends TestBase{

	@BeforeClass
	public void openAPP() throws InterruptedException, IOException{
		init();
		LoginPage lp = new LoginPage();
		lp.doLogin(OR.getProperty("userId"), OR.getProperty("pwd"));
	}
	
	@Test(priority=0)
	public void verifyInvoiceValue() throws InterruptedException{
		HomePage homepage = new HomePage(driver);
		Sales sales = new Sales(driver);
		SalesCartPage salesCart = new SalesCartPage(driver);
		CheckOutPage checkOut = new CheckOutPage(driver);
		ArrayList<Double> productCost = new ArrayList<Double>();
		
		//Adding first item
		homepage.selectSalesreturnCheckbox();
		homepage.tapOnStartButton();
		sales.searchByItemName(APP.getProperty("ItemName0"));
		sales.clickOnSearchedItem();
		hideKeyboard();
		sales.enterDiscount(APP.getProperty("StandardDisc"));
		hideKeyboard();
		sales.addQtyManually("5");
		hideKeyboard();
		productCost.add(sales.calculateItemCost(sales.getPriceOfSingleBatch(), sales.getQty(),sales.getDiscount()));
		sales.clickOnAddButton();
		
		//Adding 2nd item
		sales.searchByItemName(APP.getProperty("ItemName5"));
		sales.clickOnSearchedItem();
		hideKeyboard();
		sales.enterDiscount(APP.getProperty("StandardDisc"));
		hideKeyboard();
		sales.addQtyManually("5");
		hideKeyboard();
		productCost.add(sales.calculateItemCost(sales.getPriceOfSingleBatch(), sales.getQty(),sales.getDiscount()));
		sales.clickOnAddButton();
		
		//Adding 3rd item
		sales.searchByItemName(APP.getProperty("ItemName12"));
		sales.clickOnSearchedItem();
		hideKeyboard();
		sales.enterDiscount(APP.getProperty("StandardDisc"));
		hideKeyboard();
		sales.addQtyManually("5");
		hideKeyboard();
		productCost.add(sales.calculateItemCost(sales.getPriceOfSingleBatch(), sales.getQty(),sales.getDiscount()));
		sales.clickOnAddButton();
		
		double expectedInvoiceValue = getSumOfArraysDouble(productCost);
		expectedInvoiceValue = Math.round(expectedInvoiceValue);
		
		salesCart.clickOnGetPayment();
		checkOut.clickOnConfirm();
		Thread.sleep(5000);
		double actualInvoiceValue = checkOut.getAmtPaidInSuccessPage();
		actualInvoiceValue = Math.round(actualInvoiceValue);
		Assert.assertEquals(actualInvoiceValue, expectedInvoiceValue);
	}
}
