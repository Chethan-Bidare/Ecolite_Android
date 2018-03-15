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

public class TC_001_VerifyStockAfterSaleReturn extends TestBase{

	@BeforeClass
	public void openAPP() throws IOException, InterruptedException{
		init();
		LoginPage lp = new LoginPage();
		lp.doLogin(OR.getProperty("userId"), OR.getProperty("pwd"));
	}
	
	
	
	@Test(priority=1)
	public void verifyStockAfterSaleReturn() throws InterruptedException{
		HomePage homepage = new HomePage(driver);
		Sales sales = new Sales(driver);
		SalesCartPage salescartpage = new SalesCartPage(driver);
		CheckOutPage checkout = new CheckOutPage(driver);
		
		homepage.selectSalesreturnCheckbox();
		homepage.tapOnStartButton();
		sales.searchByItemName(APP.getProperty("ItemName0"));
		int stock = sales.getStockForSingleItem();
		sales.clickOnSearchedItem();
		hideKeyboard();
		sales.clickOnAddButton();
		salescartpage.clickOnGetPayment();
		checkout.clickOnConfirm();
		Thread.sleep(2000);
		swipeUpInBatchList();
		checkout.clickOnNewSaleButton();
		homepage.tapOnStartButton();
		sales.searchByItemName(APP.getProperty("ItemName0"));
		
		int actualStock = sales.getStockForSingleItem();
		Assert.assertEquals(actualStock, stock+1);
		
		
	}
}
