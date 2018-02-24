package c2info_ElMob.SalesTC;

import java.io.IOException;
import java.util.Collections;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import c2info_ElMob.TestBase.TestBase;
import c2info_ElMob.UI_Actions.HomePage;
import c2info_ElMob.UI_Actions.LoginPage;
import c2info_ElMob.UI_Actions.Sales;

public class TC_002_VerifyHighestMrpIsDisplayed extends TestBase{

	@BeforeClass
	public void openAPP() throws InterruptedException, IOException{
		init();
		LoginPage lp = new LoginPage();
		lp.doLogin(OR.getProperty("userId"), OR.getProperty("pwd"));
	}
	
	@Test
	public void verifyHighestMrp() throws InterruptedException{
		HomePage hp = new HomePage(driver);
		Sales sales = new Sales(driver);
		
		hp.tapOnStartButton();
		sales.searchByItemName(APP.getProperty("ItemName0"));
		hideKeyboard();
		
		float actualResult = Collections.max(sales.getItemNamesWithPrice().values());
		sales.clickOnSearchedItem();
		Thread.sleep(2000);
		float expectedResult = sales.getHighestMrpFromAllBatches();
		Assert.assertEquals(actualResult, expectedResult);
	}
}
