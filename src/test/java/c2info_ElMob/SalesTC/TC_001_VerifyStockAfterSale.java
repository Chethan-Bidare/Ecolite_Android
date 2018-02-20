package c2info_ElMob.SalesTC;

import java.net.MalformedURLException;
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

public class TC_001_VerifyStockAfterSale extends TestBase {

	@BeforeClass
	public void openAPP() throws MalformedURLException{
		DeviceCapabilities();
	}
	
	@Test(priority=0)
	public void login() throws InterruptedException{
		LoginPage lp = new LoginPage();
		lp.doLogin("8147519664", "224488");
		Assert.assertEquals(lp.getHomepageTitle(), "Home");
	}
	
	@Test(priority=1)
	public void verifyStockAfterSale(){
		HomePage homepage = new HomePage(driver);
		Sales sales = new Sales(driver);
		SalesCartPage salescartpage = new SalesCartPage(driver);
		CheckOutPage checkout = new CheckOutPage(driver);
		
		homepage.tapOnStartButton();
		sales.searchByItemName("Astyfer Syp");
		HashMap<String,Integer> stockcheck = sales.getItemNamesWithStock();
		System.out.println(stockcheck.get("Astyfer Syp"));
		int stock = stockcheck.get("Astyfer Syp") ;
		sales.clickOnSearchedItem();
		hideKeyboard();
		sales.clickOnAddButton();
		salescartpage.clickOnGetPayment();
		checkout.clickOnConfirm();
		checkout.clickOnDenyButton();
		swipeUpInBatchList();
		checkout.clickOnNewSaleButton();
		homepage.tapOnStartButton();
		sales.searchByItemName("Astyfer Syp");
		HashMap<String,Integer> stockchecknew =  sales.getItemNamesWithStock();
		int actualStock = stockchecknew.get("Astyfer Syp")+1 ;
		Assert.assertEquals(actualStock, stock);
		
		
	}
}
