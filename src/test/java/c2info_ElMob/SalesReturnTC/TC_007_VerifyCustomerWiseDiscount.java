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

public class TC_007_VerifyCustomerWiseDiscount extends TestBase{

	@BeforeClass
	public void openAPP() throws InterruptedException, IOException{
		init();
		LoginPage lp = new LoginPage();
		lp.doLogin(OR.getProperty("userId"), OR.getProperty("pwd"));
	}
	
	double expectedResult = 25.0 ;
	
	
	@Test
	public void verifyCustomerWiseDisc(){
		HomePage homepage = new HomePage(driver);
		Sales sales = new Sales(driver);
		SalesCartPage salesCart = new SalesCartPage(driver);
		
		homepage.selectSalesreturnCheckbox();
		homepage.tapOnStartButton();
		salesCart.clickOnCustIconInCartPage();
		sales.searchByCustomerName(OR.getProperty("custName"));
		sales.clickOnSearchedCustomer();
		sales.searchByItemName(APP.getProperty("ItemName0"));
		sales.clickOnSearchedItem();
		hideKeyboard();
		Assert.assertEquals(sales.getDiscount(), expectedResult);
	}
	
}
