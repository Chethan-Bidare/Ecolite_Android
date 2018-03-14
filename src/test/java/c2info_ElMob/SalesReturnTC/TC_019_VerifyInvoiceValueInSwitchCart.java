package c2info_ElMob.SalesReturnTC;

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

public class TC_019_VerifyInvoiceValueInSwitchCart extends TestBase{
	
	@BeforeClass
	public void openAPP() throws InterruptedException, IOException{
		init();
		LoginPage lp = new LoginPage();
		lp.doLogin(OR.getProperty("userId"), OR.getProperty("pwd"));
	}
	
	@Test
	public void verifyInvoiceValue() throws InterruptedException{
		
		HomePage homepage = new HomePage(driver);
		Sales sales = new Sales(driver);
		SwitchCartPage switchCart = new SwitchCartPage(driver);
		SalesCartPage salesCart = new SalesCartPage(driver);
		
		//selecting customer "Local"
		//Adding item to cart
		//Selecting new Sale from switch cart page
		homepage.enterCustomerName("l");
		homepage.selectCustFromDropdown();
		homepage.selectSalesreturnCheckbox();
		homepage.tapOnStartButton();
		sales.searchByItemName(APP.getProperty("ItemName0"));
		sales.clickOnSearchedItem();
		hideKeyboard();
		sales.clickOnAddButton();
		sales.searchByItemName(APP.getProperty("ItemName12"));
		sales.clickOnSearchedItem();
		hideKeyboard();
		sales.addQtyManually("5");
		hideKeyboard();
		sales.clickOnAddButton();
		Double cartTotal = (double) salesCart.getCartTotal();
		cartTotal = (double) Math.round(cartTotal);
		System.out.println(cartTotal);
		switchCart.clickOnCartIcon();
		switchCart.clickOnNewSales();
		homepage.selectSalesreturnCheckbox();
		homepage.tapOnStartButton();
		switchCart.clickOnCartIcon();
		
		ArrayList<Double> invValInSwitchCart = switchCart.getListOfParkedInvoiceValues();
		System.out.println(invValInSwitchCart);
		
		assertTrue(invValInSwitchCart.contains(cartTotal)==true);
		
		
	}

}
