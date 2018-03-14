package c2info_ElMob.SalesReturnTC;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import c2info_ElMob.TestBase.TestBase;
import c2info_ElMob.UI_Actions.CheckOutPage;
import c2info_ElMob.UI_Actions.HomePage;
import c2info_ElMob.UI_Actions.LoginPage;
import c2info_ElMob.UI_Actions.Sales;
import c2info_ElMob.UI_Actions.SalesCartPage;

public class TC_013_VerifyItemCount extends TestBase{

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
		SalesCartPage salesCart = new SalesCartPage(driver);
		CheckOutPage checkOut = new CheckOutPage(driver);
		
		homepage.selectSalesreturnCheckbox();
		homepage.tapOnStartButton();
		sales.searchByItemName(APP.getProperty("ItemName0"));
		sales.clickOnSearchedItem();
		hideKeyboard();
		sales.clickOnAddButton();
		
		//Adding 2nd item
		sales.searchByItemName(APP.getProperty("ItemName5"));
		sales.clickOnSearchedItem();
		hideKeyboard();
		sales.clickOnAddButton();
		int itemCountInCartPage = salesCart.getCountOfItemsAddedToCart();
		salesCart.clickOnGetPayment();
		int itemCountInCheckOutPage = salesCart.getCountOfItemsAddedToCart();
		checkOut.clickOnConfirm();
		checkOut.clickOnDenyButton();
		Thread.sleep(5000);
		int itemCountInSuccessPage = checkOut.getTotalItemCountInSuccessPage();
		
		Assert.assertEquals(itemCountInCartPage, itemCountInCheckOutPage);
		Assert.assertEquals(itemCountInCartPage, itemCountInSuccessPage);
		Assert.assertEquals(itemCountInCheckOutPage, itemCountInSuccessPage);
	}
}
