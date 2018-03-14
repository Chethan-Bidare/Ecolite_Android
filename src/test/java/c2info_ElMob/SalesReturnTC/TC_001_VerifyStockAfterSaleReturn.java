package c2info_ElMob.SalesReturnTC;

import java.io.IOException;
import java.util.HashMap;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import c2info_ElMob.TestBase.TestBase;
import c2info_ElMob.UI_Actions.CheckOutPage;
import c2info_ElMob.UI_Actions.HomePage;
import c2info_ElMob.UI_Actions.LoginPage;
import c2info_ElMob.UI_Actions.Sales;
import c2info_ElMob.UI_Actions.SalesCartPage;

public class TC_001_VerifyStockAfterSaleReturn extends TestBase{

	@BeforeClass
	public void openAPP() throws IOException, InterruptedException{
		init();
		LoginPage lp = new LoginPage();
		lp.doLogin(OR.getProperty("userId"), OR.getProperty("pwd"));
	}
	
	
	
	@Test(priority=1)
	public void verifyStockAfterSaleReturn(){
		HomePage homepage = new HomePage(driver);
		Sales sales = new Sales(driver);
		SalesCartPage salescartpage = new SalesCartPage(driver);
		CheckOutPage checkout = new CheckOutPage(driver);
		
		homepage.selectSalesreturnCheckbox();
		homepage.tapOnStartButton();
		sales.searchByItemName(APP.getProperty("ItemName0"));
		HashMap<String,Integer> stockcheck = sales.getItemNamesWithStock();
		int stock = stockcheck.get(APP.getProperty("ItemName0")) ;
		sales.clickOnSearchedItem();
		hideKeyboard();
		sales.clickOnAddButton();
		salescartpage.clickOnGetPayment();
		checkout.clickOnConfirm();
		checkout.clickOnDenyButton();
		swipeUpInBatchList();
		checkout.clickOnNewSaleButton();
		homepage.tapOnStartButton();
		sales.searchByItemName(APP.getProperty("ItemName0"));
		HashMap<String,Integer> stockchecknew =  sales.getItemNamesWithStock();
		int actualStock = stockchecknew.get(APP.getProperty("ItemName0")) ;
		Assert.assertEquals(actualStock, stock+1);
		
		
	}
}
