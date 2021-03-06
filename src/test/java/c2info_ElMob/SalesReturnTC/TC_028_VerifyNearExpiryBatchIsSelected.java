package c2info_ElMob.SalesReturnTC;

import java.io.IOException;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import c2info_ElMob.TestBase.TestBase;
import c2info_ElMob.UI_Actions.HomePage;
import c2info_ElMob.UI_Actions.LoginPage;
import c2info_ElMob.UI_Actions.Sales;


//incomplete
public class TC_028_VerifyNearExpiryBatchIsSelected extends TestBase{

String itemName = "A Gen 5 D Data";
	
	@BeforeClass
	public void openAPP() throws InterruptedException, IOException{
		init();
		LoginPage lp = new LoginPage();
		lp.doLogin(OR.getProperty("userId"), OR.getProperty("pwd"));
	}
	
	@Test
	public void verifyNearExpiry() throws InterruptedException{
		HomePage homepage = new HomePage(driver);
		Sales sales = new Sales(driver);
		
		homepage.tapOnStartButton();
		sales.searchByItemName(itemName);
		sales.clickOnSearchedItem();
		hideKeyboard();
		sales.clickOnAddNewBatch();
		sales.enterNewbatchDetails("250", "100", "10");
		
		
	}
}
