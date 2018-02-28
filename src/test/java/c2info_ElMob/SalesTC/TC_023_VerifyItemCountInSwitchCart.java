package c2info_ElMob.SalesTC;

import static org.testng.Assert.assertTrue;

import java.io.IOException;
import java.util.HashMap;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import c2info_ElMob.TestBase.TestBase;
import c2info_ElMob.UI_Actions.HomePage;
import c2info_ElMob.UI_Actions.LoginPage;
import c2info_ElMob.UI_Actions.Sales;
import c2info_ElMob.UI_Actions.SalesCartPage;
import c2info_ElMob.UI_Actions.SwitchCartPage;

public class TC_023_VerifyItemCountInSwitchCart extends TestBase{
	
	@BeforeClass
	public void openAPP() throws InterruptedException, IOException{
		init();
		LoginPage lp = new LoginPage();
		lp.doLogin(OR.getProperty("userId"), OR.getProperty("pwd"));
	}
	
	@Test
	public void verifyItemCount() throws InterruptedException{
		
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
		sales.addQtyManually("5");
		hideKeyboard();
		sales.clickOnAddButton();
		int itemCount = salesCart.getCountOfItemsAddedToCart();
		
		switchCart.clickOnCartIcon();
		switchCart.clickOnNewSales();
		homepage.tapOnStartButton();
		switchCart.clickOnCartIcon();
		
		HashMap<String, Integer> itemCountInSwitchCart = switchCart.getCustNameAndItemCount();
		System.out.println(itemCountInSwitchCart);
		
		assertTrue(itemCountInSwitchCart.get("Local")==itemCount);
		
		
	}

}
