package c2info_ElMob.SalesTC;

import java.io.IOException;
import java.util.HashMap;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import c2info_ElMob.TestBase.TestBase;
import c2info_ElMob.UI_Actions.HomePage;
import c2info_ElMob.UI_Actions.LoginPage;
import c2info_ElMob.UI_Actions.Sales;

public class TC_032_VerifyAddBatchFunctionality extends TestBase {

	String itemName = "A Gen 5 D Data" ;
	
	@BeforeClass
	public void openAPP() throws InterruptedException, IOException{
		init();
		LoginPage lp = new LoginPage();
		lp.doLogin(OR.getProperty("userId"), OR.getProperty("pwd"));
	}
	
	@Test
	public void verifyNewBatch() throws InterruptedException{
		HomePage homepage = new HomePage(driver);
		Sales sales = new Sales(driver);
		
		homepage.tapOnStartButton();
		sales.searchByItemName(itemName);
		sales.clickOnSearchedItem();
		hideKeyboard();
		sales.clickOnAddNewBatch();
		String batchNO = sales.enterNewbatchDetails("250", "100", "10");
		System.out.println(batchNO);
		sales.clickOnAddButton();
		sales.searchByItemName(itemName);
		sales.clickOnSearchedItem();
		hideKeyboard();
		HashMap<String, Integer> batch = sales.getBatchesWithStockBySwiping();
		Assert.assertTrue(batch.containsKey(batchNO)==true);
		
	}
}
