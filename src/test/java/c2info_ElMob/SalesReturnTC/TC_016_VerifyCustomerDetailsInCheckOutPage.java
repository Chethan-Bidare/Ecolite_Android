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

public class TC_016_VerifyCustomerDetailsInCheckOutPage extends TestBase{

	@BeforeClass
	public void openAPP() throws InterruptedException, IOException{
		init();
		LoginPage lp = new LoginPage();
		lp.doLogin(OR.getProperty("userId"), OR.getProperty("pwd"));
	}
	
	@Test
	public void verifyCustomerDetails() throws InterruptedException{
		
		HomePage homepage = new HomePage(driver);
		Sales sales = new Sales(driver);
		SalesCartPage salesCart = new SalesCartPage(driver);
		CheckOutPage checkOut = new CheckOutPage(driver);
		
		homepage.enterCustomerName("l");
		homepage.selectCustFromDropdown();
		homepage.selectSalesreturnCheckbox();
		homepage.tapOnStartButton();
		sales.searchByItemName(APP.getProperty("ItemName0"));
		sales.clickOnSearchedItem();
		hideKeyboard();
		sales.clickOnAddButton();
		salesCart.clickOnGetPayment();
		HashMap<String,String> custDetails = checkOut.getCustomerDetailsInCheckOut();
		System.out.println(custDetails);
		
		Assert.assertTrue(custDetails.get("CustName")!=null);
		Assert.assertTrue(custDetails.get("CustMob")!=null);
		Assert.assertTrue(custDetails.get("CustCity")!=null);
		Assert.assertTrue(custDetails.get("CustState")!=null);
		Assert.assertTrue(custDetails.get("CustGST")!=null);
		
		
	}
}
