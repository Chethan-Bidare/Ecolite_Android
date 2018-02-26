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

public class TC_011_VerifyInvoiceValue extends TestBase{
		
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
			homepage.tapOnStartButton();
			sales.searchByItemName(APP.getProperty("ItemName0"));
			sales.clickOnSearchedItem();
			hideKeyboard();
			sales.addQtyManually("5");
			hideKeyboard();
			productCost.add(sales.calculateItemCost(sales.getPriceOfSingleBatch(), sales.getQty(),0));
			sales.clickOnAddButton();
			
			//Adding 2nd item
			sales.searchByItemName(APP.getProperty("ItemName5"));
			sales.clickOnSearchedItem();
			hideKeyboard();
			sales.addQtyManually("5");
			hideKeyboard();
			productCost.add(sales.calculateItemCost(sales.getPriceOfSingleBatch(), sales.getQty(),0));
			sales.clickOnAddButton();
			
			//Adding 3rd item
			sales.searchByItemName(APP.getProperty("ItemName12"));
			sales.clickOnSearchedItem();
			hideKeyboard();
			sales.addQtyManually("5");
			hideKeyboard();
			productCost.add(sales.calculateItemCost(sales.getPriceOfSingleBatch(), sales.getQty(),0));
			sales.clickOnAddButton();
			
			double expectedInvoiceValue = getSumOfArraysDouble(productCost);
			expectedInvoiceValue = Math.round(expectedInvoiceValue);
			
			salesCart.clickOnGetPayment();
			checkOut.clickOnConfirm();
			checkOut.clickOnDenyButton();
			Thread.sleep(5000);
			double actualInvoiceValue = checkOut.getInvoiceValueInSuccessPage();
			actualInvoiceValue = Math.round(actualInvoiceValue);
			Assert.assertEquals(actualInvoiceValue, expectedInvoiceValue);
	}
}
