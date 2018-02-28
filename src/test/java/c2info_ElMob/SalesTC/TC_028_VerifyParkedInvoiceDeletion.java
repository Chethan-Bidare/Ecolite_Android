package c2info_ElMob.SalesTC;

import static org.testng.Assert.assertEquals;

import java.io.IOException;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import c2info_ElMob.TestBase.TestBase;
import c2info_ElMob.UI_Actions.HomePage;
import c2info_ElMob.UI_Actions.LoginPage;
import c2info_ElMob.UI_Actions.Sales;
import c2info_ElMob.UI_Actions.SwitchCartPage;

public class TC_028_VerifyParkedInvoiceDeletion extends TestBase{

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
		homepage.tapOnStartButton();
		int cartCountAftrParking = switchCart.getCartCount(); 
		switchCart.clickOnCartIcon();
		switchCart.clickOnDelete();
		switchCart.clickOnNewSales();
		homepage.tapOnStartButton();
		int cartCountAfterDeleting = switchCart.getCartCount();
		
		assertEquals(cartCountAftrParking, cartCountAfterDeleting+1);
		
	}
}
