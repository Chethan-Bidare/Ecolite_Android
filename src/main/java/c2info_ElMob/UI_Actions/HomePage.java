package c2info_ElMob.UI_Actions;

import org.apache.commons.lang3.RandomStringUtils;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import c2info_ElMob.TestBase.TestBase;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidKeyCode;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

public class HomePage extends TestBase{

	
	
	@FindBy(xpath=".//*[@class='android.support.v7.app.ActionBar$Tab' and @index='0']")
	WebElement SalesTab ;
	
	@FindBy(xpath=".//*[@class='android.support.v7.app.ActionBar$Tab' and @index='1']")
	WebElement PurchaseTab ;
	
	@FindBy(xpath=".//*[@class='android.support.v7.app.ActionBar$Tab' and @index='2']")
	WebElement MyStoreTab ;
	
	@FindBy(id="com.c2info.ecolite:id/radio_new_sale_return")
	WebElement salesReturnCheckbox ;
	
	@FindBy(id="com.c2info.ecolite:id/radio_new_sale")
	WebElement salescheckbox ;
	
	@FindBy(xpath=".//*[@id='com.c2info.ecolite:id/btnReports' and contains(text(),'Sales Reports')]")
	WebElement salesreportButton ;
	
	@FindBy(id="com.c2info.ecolite:id/autocomplete_customer")
	WebElement customerName ;
	
	@FindBy(id="com.c2info.ecolite:id/btnStartBilling")
	WebElement startButton ;
	
	@FindBy(id="com.c2info.ecolite:id/autocomplete_supplier")
	WebElement supplierNameSearch ;
	
	@FindBy(id="com.c2info.ecolite:id/edittext_bill_no")
	WebElement suppBillNo ;
	
	@FindBy(id="com.c2info.ecolite:id/edittext_bill_amount")
	WebElement suppBillAmt ;
	
	@FindBy(id="com.c2info.ecolite:id/edittext_bill_discount")
	WebElement suppBillDisc ;
	
	
	@FindBy(id="com.c2info.ecolite:id/button_start")
	WebElement startPurchaseBtn ;
	
	@FindBy(id="com.c2info.ecolite:id/tvTotalSaleForDay")
	WebElement totalSaleForDay ;
	
	@FindBy(id="com.c2info.ecolite:id/tvCashSales")
	WebElement cashSales ;
	
	@FindBy(id="com.c2info.ecolite:id/tvCardSales")
	WebElement cardSales ;
	/*
	@FindBy(id="com.c2info.ecolite:id/radio_new_sale")
	WebElement salescheckbox
	@FindBy(id="com.c2info.ecolite:id/radio_new_sale")
	WebElement salescheckbox
	*/
	
	public HomePage(AndroidDriver<WebElement> driver){
		PageFactory.initElements(new AppiumFieldDecorator(driver),this);
	}
	
	public void tapOnPurchaseTab(){
		PurchaseTab.click();
	}
	
	public void tapOnSalesTab(){
		SalesTab.click();
	}
	
	public void tapOnMyStoreTab(){
		MyStoreTab.click();
	}
	
	public void selectSalesreturnCheckbox(){
		salesReturnCheckbox.click();
	}
	
	public void selectSalesCheckbox(){
		salescheckbox.click();
	}
	
	public void enterCustomerName(String custName) throws InterruptedException{
		customerName.sendKeys(custName);
		TouchAction touchAction = new TouchAction(driver);
		touchAction.tap(customerName).release().perform();
		driver.pressKeyCode(AndroidKeyCode.BACKSPACE);	
	}
	public void enterCustNameAgain(String custName){
		customerName.sendKeys(custName);
	}
	
	public void selectCustFromDropdown() throws InterruptedException{
		Thread.sleep(3000);
		int x = customerName.getLocation().getX();
		int y = customerName.getLocation().getY();
		TouchAction action = new TouchAction(driver).tap(x+100, y+100).release();
		action.perform();
	}
	
	/*public void clearText(){
		TouchAction touchAction = new TouchAction(driver);
		touchAction.tap(customerName).release().perform();
		driver.pressKeyCode(AndroidKeyCode.BACKSPACE);
		   //driver.getKeyboard().sendKeys(Keys.DELETE);
	}*/
	
	public void tapOnSalesReports(){
		salesreportButton.click();
	}
	
	public void tapOnStartButton(){
		startButton.click();
	}
	
	
	public void enterSupplierName(String suppName,String billAmt,String billDisc) throws InterruptedException{
		supplierNameSearch.clear();
		supplierNameSearch.sendKeys(suppName);
		Thread.sleep(3000);
		int x = supplierNameSearch.getLocation().getX();
		int y = supplierNameSearch.getLocation().getY();
		TouchAction action = new TouchAction(driver).tap(x+100, y+100).release();
		action.perform();
		suppBillNo.sendKeys(RandomStringUtils.randomAlphanumeric(7));
		suppBillAmt.sendKeys(billAmt);
		suppBillDisc.sendKeys(billDisc);
		hideKeyboard();
		startPurchaseBtn.click();
	}
	
	public double getTodaysTotalSales(){
		String tts = totalSaleForDay.getText().trim().toString();
		tts = tts.replaceAll(",", "");
		double totalSales = Double.parseDouble(tts) ;
		return totalSales ;
	}
	
	public double getTodaysTotalCashSales(){
		String ttcs = cashSales.getText().trim().toString();
		ttcs = ttcs.replaceAll(",", "");
		double cashSales = Double.parseDouble(ttcs) ;
		return cashSales ;
	}
	
	public double getTodaysTotalCardSales(){
		String ttcs = cardSales.getText().trim().toString();
		ttcs = ttcs.replaceAll(",", "");
		double cardSales = Double.parseDouble(ttcs) ;
		return cardSales ;
	}
	
}
