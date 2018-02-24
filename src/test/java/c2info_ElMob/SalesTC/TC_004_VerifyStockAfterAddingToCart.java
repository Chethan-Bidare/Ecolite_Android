package c2info_ElMob.SalesTC;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import c2info_ElMob.TestBase.TestBase;
import c2info_ElMob.UI_Actions.HomePage;
import c2info_ElMob.UI_Actions.LoginPage;
import c2info_ElMob.UI_Actions.Sales;

public class TC_004_VerifyStockAfterAddingToCart extends TestBase{

	@BeforeClass
	public void openAPP() throws InterruptedException, IOException{
		init();
		LoginPage lp = new LoginPage();
		lp.doLogin(OR.getProperty("userId"), OR.getProperty("pwd"));
	}
	
	@Test
	public void verifyStock() throws InterruptedException{
		HomePage homepage = new HomePage(driver);
		Sales sales = new Sales(driver);
		
		homepage.tapOnStartButton();
		sales.searchByItemName(APP.getProperty("ItemName0"));
		Thread.sleep(2000);
		hideKeyboard();
		int beforeCart = sales.getStockOfSearchedItem();
		sales.clickOnSearchedItem();
		sales.addQtyManually("5");
		hideKeyboard();
		sales.clickOnAddButton();
		Thread.sleep(2000);
		sales.searchByItemName(APP.getProperty("ItemName0"));
		hideKeyboard();
		int afterCart = sales.getStockOfSearchedItem();
		Assert.assertEquals(afterCart, beforeCart-5);
	}
}
