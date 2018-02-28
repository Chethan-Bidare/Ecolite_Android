package c2info_ElMob.SalesTC;

import static org.testng.Assert.assertTrue;

import java.io.IOException;
import java.util.ArrayList;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import c2info_ElMob.TestBase.TestBase;
import c2info_ElMob.UI_Actions.HomePage;
import c2info_ElMob.UI_Actions.LoginPage;
import c2info_ElMob.UI_Actions.Sales;
import c2info_ElMob.UI_Actions.SalesCartPage;
import c2info_ElMob.UI_Actions.SwitchCartPage;

public class TC_025_VerifyParkedInvoiceIsLoadedToSalesPage extends TestBase{
	
	@BeforeClass
	public void openAPP() throws InterruptedException, IOException{
		init();
		LoginPage lp = new LoginPage();
		lp.doLogin(OR.getProperty("userId"), OR.getProperty("pwd"));
	}
	
	@Test
	public void verifyInvoiceLoading() throws InterruptedException{
		
		HomePage homepage = new HomePage(driver);
		Sales sales = new Sales(driver);
		SwitchCartPage switchCart = new SwitchCartPage(driver);
		SalesCartPage salesCart = new SalesCartPage(driver);
		
		//selecting customer "Local"
		//Adding item to cart
		//Selecting new Sale from switch cart page
		homepage.enterCustomerName("l");
		homepage.selectCustFromDropdown();
		homepage.tapOnStartButton();
		sales.searchByItemName(APP.getProperty("ItemName0"));
		sales.clickOnSearchedItem();
		hideKeyboard();
		sales.clickOnAddButton();
		sales.searchByItemName(APP.getProperty("ItemName12"));
		sales.clickOnSearchedItem();
		hideKeyboard();
		sales.clickOnAddButton();
		
		
		switchCart.clickOnCartIcon();
		switchCart.clickOnNewSales();
		homepage.tapOnStartButton();
		switchCart.clickOnCartIcon();
		
		ArrayList<String> custNames= switchCart.getListOfParkedInvoiceCustomer();
		ArrayList<Double> invVals = switchCart.getListOfParkedInvoiceValues();
		ArrayList<Integer> items = switchCart.getItemCountInCart();
		
		switchCart.clickOnParkedInvoice();
		String custName = salesCart.getCustomerNameFromCustIcon();
		double invValue = salesCart.getCartTotal();
		int itemCount = salesCart.getCountOfItemsAddedToCart();
		
		assertTrue(custNames.contains(custName)==true);
		assertTrue(invVals.contains(invValue)==true);
		assertTrue(items.contains(itemCount)==true);
		
		
	}


}
