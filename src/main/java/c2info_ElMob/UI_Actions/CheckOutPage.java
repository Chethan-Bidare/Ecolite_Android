package c2info_ElMob.UI_Actions;

import java.util.HashMap;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import c2info_ElMob.TestBase.TestBase;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

public class CheckOutPage extends TestBase{

TouchAction touchAction = new TouchAction(driver);
	
	public CheckOutPage(AndroidDriver<WebElement> driver){
		PageFactory.initElements(new AppiumFieldDecorator(driver),this);
	}
	
	
	@FindBy(id="com.c2info.ecolite:id/edit_customer_name")
	WebElement custName ;
	
	@FindBy(id="com.c2info.ecolite:id/edit_mobile_number")
	WebElement custMobileNo ;
	
	@FindBy(id="com.c2info.ecolite:id/edit_customer_city")
	WebElement city ;
	
	@FindBy(id="com.c2info.ecolite:id/textview_disc_amount")
	WebElement discAmt ;
	
	@FindBy(id="com.c2info.ecolite:id/button_get_payment")
	WebElement confirmButton ;
	
	@FindBy(id="com.c2info.ecolite:id/edit_customer_gst")
	WebElement custGSTNo ;
	
	@FindBy(id="com.c2info.ecolite:id/spStates")
	WebElement State ;
	
	@FindBy(id="com.c2info.ecolite:id/switch_home")
	WebElement homeDeliveryCheckbox ;
	
	@FindBy(id="com.c2info.ecolite:id/et_round_off")
	WebElement roundOff ;
	
	@FindBy(id="com.c2info.ecolite:id/button_calculate_tender")
	WebElement calculateTenderedButton ;
	
	@FindBy(id="com.c2info.ecolite:id/radio_card")
	WebElement card ;
	
	@FindBy(id="com.c2info.ecolite:id/radio_jio_money")
	WebElement jioMoney ;
	
	@FindBy(id="com.c2info.ecolite:id/radio_credit")
	WebElement credit ;
	
	@FindBy(id="com.c2info.ecolite:id/radio_cash")
	WebElement cash ;
	
	@FindBy(id="com.c2info.ecolite:id/text_view_items")
	WebElement totalItems ;
	
	@FindBy(id="com.c2info.ecolite:id/text_view_invoice_value")
	WebElement InvValue ;
	
	@FindBy(id="com.c2info.ecolite:id/text_view_tax")
	WebElement tax ;
	
	@FindBy(id="com.c2info.ecolite:id/text_view_discount")
	WebElement discInSuccessPage ;
	
	@FindBy(id="com.c2info.ecolite:id/text_view_amount")
	WebElement amtPaid ;
	
	@FindBy(id="com.c2info.ecolite:id/text_view_payment")
	WebElement paymentMode ;
	
	@FindBy(id="com.c2info.ecolite:id/tvSgst")
	WebElement totalSGST ;
	
	@FindBy(id="com.c2info.ecolite:id/tvCgst")
	WebElement totalCGST ;
	
	@FindBy(id="com.c2info.ecolite:id/tvIgst")
	WebElement totalIGST ;
	
	@FindBy(id="com.c2info.ecolite:id/tvCess")
	WebElement totalCess ;
	
	@FindBy(id="com.c2info.ecolite:id/button_new_sale")
	WebElement newSaleButton ;
	
	@FindBy(id="com.c2info.ecolite:id/fab_share")
	WebElement shareButton ;
	
	@FindBy(id="com.c2info.ecolite:id/fab_print")
	WebElement printButton ;
	
	@FindBy(id="android:id/text1")
	WebElement stateselected ;

	public HashMap<String,String> getCustomerDetailsInCheckOut(){
		HashMap<String, String> customerDetails = new HashMap<String, String>();
		String customerName = this.custName.getText().trim().toString();
		String customerMob = custMobileNo.getText().trim().toString();
		String customerCity = city.getText().trim().toString();
		customerDetails.put("CustName",customerName);
		customerDetails.put("CustMob", customerMob);
		customerDetails.put("CustCity", customerCity);
		
		return customerDetails ;
	}
	
	public String getGSTNoInCheckOut(){
		return custGSTNo.getText().trim().toString();	
	}
	
	
	public void selectStateFromDropdownInCheckOut(String state){
		state = state.toUpperCase();
		System.out.println(state);
		State.click();
		driver.scrollTo(state);
		//driver.findElementByName(state).click();
		driver.findElement(By.xpath("//android.widget.CheckedTextView[contains(@text,'"+state+"')]")).click();
	}
	
	public void enableHomeDeliveryInCheckOut(){
		homeDeliveryCheckbox.click();
	}
	
	public void selectPaymentModeInCheckOut(String paymode){
		if(paymode.equalsIgnoreCase("card")){
			driver.findElement(By.xpath("//android.widget.RadioButton[contains(@text,'"+paymode+"')]")).click();
			driver.findElementById("com.c2info.ecolite:id/etCardCode").sendKeys("321321");
		}
		else if(paymode.equalsIgnoreCase("jiomoney")){
			driver.findElement(By.xpath("//android.widget.RadioButton[contains(@text,'"+paymode+"')]")).click();
			driver.findElementById("com.c2info.ecolite:id/etJioCode").sendKeys("321321");
		}
		else
		driver.findElement(By.xpath("//android.widget.RadioButton[contains(@text,'"+paymode+"')]")).click();
	}
	
	
	public float getDiscValueInCheckOutPage(){
		String disc = discAmt.getText();
		disc = disc.replaceAll("\u20B9", "").trim().toString();
		float discValue = Float.parseFloat(disc);
		return discValue;
	}
	
	public float getDiscValueInSuccessPage(){
		String disc = discInSuccessPage.getText().trim().toString();
		disc = disc.replaceAll("\u20B9","");
		float discAmt = Float.parseFloat(disc);
		return discAmt ;
	}
	
	public int getTotalItemCountInSuccessPage(){
		String itemcount = totalItems.getText();
		itemcount = itemcount.replaceAll("Items","").trim().toString();
		itemcount = itemcount.replaceAll("item","").trim().toString();
		int itemCount = Integer.parseInt(itemcount);
		return itemCount ;
	}
	
	public float getInvoiceValueInSuccessPage(){
		String value = InvValue.getText().trim().toString();
		value = value.replaceAll("\u20B9", "").trim().toString();
		float invValue = Float.parseFloat(value);
		return invValue ;
	}
	
	public void clickOnConfirm(){
		confirmButton.click();
	}
	
	public double getTaxValueInSuccessPage(){
		String tax = this.tax.getText().trim().toString();
		tax = tax.replaceAll("\u20B9","");
		double taxAmt = Double.parseDouble(tax);
		return taxAmt ;
	}
	
	public String getPaymentModeInSuccessPage(){
		return paymentMode.getText().trim().toString();
	}
	
	public float getAmtPaidInSuccessPage(){
		String amt = amtPaid.getText().trim().toString();
		amt = amt.replaceAll("\u20B9","");
		float amtPaid = Float.parseFloat(amt);
		return amtPaid ;
	}
	
	public void clickOnDenyButton(){
		driver.findElementById("android:id/button2").click();
	}
	
	public void clickOnNewSaleButton(){
		newSaleButton.click();
	}
	
	public void clickOnPrintButton(){
		printButton.click();
	}
	
	public void clickOnShareButton(){
		shareButton.click();
	}
}
