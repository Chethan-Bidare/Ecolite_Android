package c2info_ElMob.SalesReturnTC;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import c2info_ElMob.TestBase.TestBase;
import c2info_ElMob.UI_Actions.HomePage;
import c2info_ElMob.UI_Actions.LoginPage;
import c2info_ElMob.UI_Actions.Sales;
import c2info_ElMob.UI_Actions.SalesCartPage;

public class TC_030_VerifyQtyChangeOnUpdate extends TestBase{

	@BeforeClass
	public void openAPP() throws InterruptedException, IOException{
		init();
		LoginPage lp = new LoginPage();
		lp.doLogin(OR.getProperty("userId"), OR.getProperty("pwd"));
	}
	
	@Test
	public void qtyChangeOnUpdate(){
		
		HomePage homepage = new HomePage(driver);
		Sales sales = new Sales(driver);
		SalesCartPage cart = new SalesCartPage(driver);
		homepage.selectSalesreturnCheckbox();
		homepage.tapOnStartButton();
		sales.searchByItemName(APP.getProperty("ItemName0"));
		sales.clickOnSearchedItem();
		hideKeyboard();
		sales.clickOnAddButton();
		cart.clickOnCartPage();
		cart.clickOnItemInCart();
		sales.addQtyManually("5");
		hideKeyboard();
		sales.clickOnAddButton();
		
		int actualQty = cart.getQtyForSingleItemInCartPage();
		Assert.assertEquals(actualQty, 5);	
	}
	
}
