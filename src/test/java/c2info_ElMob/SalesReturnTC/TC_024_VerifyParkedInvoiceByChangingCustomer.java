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

public class TC_024_VerifyParkedInvoiceByChangingCustomer extends TestBase {

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
		homepage.selectSalesreturnCheckbox();
		homepage.tapOnStartButton();
		sales.searchByItemName(APP.getProperty("ItemName0"));
		sales.clickOnSearchedItem();
		hideKeyboard();
		sales.clickOnAddButton();
		
		switchCart.clickOnCartIcon();
		switchCart.clickOnNewSales();
		homepage.selectSalesreturnCheckbox();
		homepage.tapOnStartButton();
		switchCart.clickOnCartIcon();
		
		switchCart.clickOnParkedInvoice();
		salesCart.clickOnCustIconInCartPage();
		sales.searchByCustomerName(OR.getProperty("custName"));
		switchCart.clickOnCartIcon();
		switchCart.clickOnNewSales();
		homepage.selectSalesreturnCheckbox();
		homepage.tapOnStartButton();
		switchCart.clickOnCartIcon();
		
		Thread.sleep(2000);
		ArrayList<String> custNamesAfterChange = switchCart.getListOfParkedInvoiceCustomer();
		
		assertTrue(custNamesAfterChange.contains(OR.getProperty("custName"))==true);
		
	}

}
