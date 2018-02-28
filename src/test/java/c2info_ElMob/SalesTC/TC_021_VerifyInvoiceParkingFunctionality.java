package c2info_ElMob.SalesTC;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import c2info_ElMob.TestBase.TestBase;
import c2info_ElMob.UI_Actions.HomePage;
import c2info_ElMob.UI_Actions.LoginPage;
import c2info_ElMob.UI_Actions.Sales;
import c2info_ElMob.UI_Actions.SwitchCartPage;

public class TC_021_VerifyInvoiceParkingFunctionality extends TestBase{
	
	@BeforeClass
	public void openAPP() throws InterruptedException, IOException{
		init();
		LoginPage lp = new LoginPage();
		lp.doLogin(OR.getProperty("userId"), OR.getProperty("pwd"));
	}
	
	@Test
	public void verifyInvoiceParking() throws InterruptedException{
		
		HomePage homepage = new HomePage(driver);
		Sales sales = new Sales(driver);
		SwitchCartPage switchCart = new SwitchCartPage(driver);
		
		
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
		switchCart.clickOnCartIcon();
		switchCart.clickOnNewSales();
		
		
		//starting new sale and parking it
		homepage.enterCustomerName("i");
		homepage.selectCustFromDropdown();
		homepage.tapOnStartButton();
		sales.searchByItemName(APP.getProperty("ItemName5"));
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
		
		Assert.assertTrue(switchCart.getListOfParkedInvoiceCustomer().contains("Local")==true);
		Assert.assertTrue(switchCart.getListOfParkedInvoiceCustomer().contains("Igst")==true);
		
		
		
	}

}
