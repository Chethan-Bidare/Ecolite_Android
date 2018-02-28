package c2info_ElMob.SalesTC;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import java.io.IOException;
import java.util.ArrayList;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import c2info_ElMob.TestBase.TestBase;
import c2info_ElMob.UI_Actions.HomePage;
import c2info_ElMob.UI_Actions.LoginPage;
import c2info_ElMob.UI_Actions.Sales;
import c2info_ElMob.UI_Actions.SalesCartPage;
import c2info_ElMob.UI_Actions.SwitchCartPage;

public class TC_029_VerifyParkedInvoiceByDeletingItems extends TestBase{
	
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
		ArrayList<Integer> itemCountAftrParking = switchCart.getItemCountInCart(); 
		switchCart.clickOnParkedInvoice();
		salesCart.clickOnCartPage();
		salesCart.deleteItemBySwiping();
		Thread.sleep(4000);
		salesCart.clickOnCartPage();
		switchCart.clickOnCartIcon();
		switchCart.clickOnNewSales();
		homepage.tapOnStartButton();
		switchCart.clickOnCartIcon();
		ArrayList<Integer> itemCountAftrDeleting = switchCart.getItemCountInCart();
		
		System.out.println(itemCountAftrParking+"---"+itemCountAftrDeleting);
		Assert.assertTrue((itemCountAftrParking.get(0)==itemCountAftrDeleting.get(0)+1)==true);
		
	}

	private void assertEquals(boolean b) {
		// TODO Auto-generated method stub
		
	}

}
