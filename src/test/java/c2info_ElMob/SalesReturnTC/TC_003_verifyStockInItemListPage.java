package c2info_ElMob.SalesReturnTC;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import c2info_ElMob.TestBase.TestBase;
import c2info_ElMob.UI_Actions.HomePage;
import c2info_ElMob.UI_Actions.LoginPage;
import c2info_ElMob.UI_Actions.Sales;

public class TC_003_verifyStockInItemListPage extends TestBase{

	@BeforeClass
	public void openAPP() throws InterruptedException, IOException{
		init();
		LoginPage lp = new LoginPage();
		lp.doLogin(OR.getProperty("userId"), OR.getProperty("pwd"));
	}
	
	
	//Checks stock in item list and verifies the stock in batch list page   (APP.getProperty("ItemName0")
	@Test
	public void verifyStock(){
		HomePage hp = new HomePage(driver);
		Sales sales = new Sales(driver);
		
		hp.selectSalesreturnCheckbox();
		hp.tapOnStartButton();
		sales.searchByItemName("Ajith test");
		hideKeyboard();
		int actualStock = sales.getStockOfSearchedItem();
		sales.clickOnSearchedItem();
		int expectedStock = sales.getSumOfStockofAllBatchesbySwiping();
		System.out.println(actualStock+","+expectedStock);
		Assert.assertEquals(actualStock, expectedStock);
		
				
		
	}
}
