package c2info_ElMob.UI_Actions;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import c2info_ElMob.TestBase.TestBase;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

public class SwitchCartPage extends TestBase{
	
	
	

	
	@FindBy(id="com.c2info.ecolite:id/tvCartItem")
	WebElement cartIcon ;
	
	@FindBy(id="com.c2info.ecolite:id/linear_new_sales")
	WebElement newSales ;
	
	@FindBy(id="com.c2info.ecolite:id/linear_new_purchase")
	WebElement newPurchase ;
	
	@FindBy(id="com.c2info.ecolite:id/linear_new_sales_return")
	WebElement newSalesReturn ;
	
	@FindBy(id="com.c2info.ecolite:id/linear_new_purchase_return")
	WebElement newPurchaseReturn ;
	
	
	@FindBy(id="com.c2info.ecolite:id/lLytSelect")
	WebElement parkedInvoice ;
	
	@FindBy(id="com.c2info.ecolite:id/imageview_delete")
	WebElement deleteParkedInvoice ;
	/*
	@FindBy(id="com.c2info.ecolite:id/radio_new_sale")
	WebElement salescheckbox ;
	
	@FindBy(id="com.c2info.ecolite:id/radio_new_sale")
	WebElement salescheckbox ;
	
	@FindBy(id="com.c2info.ecolite:id/radio_new_sale")
	WebElement salescheckbox ;
	
	@FindBy(id="com.c2info.ecolite:id/radio_new_sale")
	WebElement salescheckbox ;
	
	@FindBy(id="com.c2info.ecolite:id/radio_new_sale")
	WebElement salescheckbox ;

	*/
	
	public SwitchCartPage(AndroidDriver<WebElement> driver){
		PageFactory.initElements(new AppiumFieldDecorator(driver), this);
	}
	
	
	public void clickOnCartIcon(){
		cartIcon.click();
	}
	
	public int getCartCount(){
		String cart = cartIcon.getText().toString();
		cart = cart.replaceAll("Cart ","").trim().toString();
		int cartCount = Integer.parseInt(cart);
		return cartCount ;
	}
	
	public void clickOnNewSales(){
		newSales.click();
	}
	
	public void clickOnNewSalesreturn(){
		newSalesReturn.click();
	}
	
	public void clickOnParkedInvoice(){
		parkedInvoice.click();
	}
	
	public void clickOnDelete(){
		deleteParkedInvoice.click();
	}
	
	public ArrayList<String> getListOfParkedInvoiceCustomer(){
		List<WebElement> customers = driver.findElementsById("com.c2info.ecolite:id/textview_cart");
		ArrayList<String> custNames = new ArrayList<String>();
		for(WebElement we : customers){
			String cust = we.getText().toString();
			cust = cust.substring(7,cust.length());
			custNames.add(cust);
		}
		
		return custNames ;
	}
	
	public ArrayList<Double> getListOfParkedInvoiceValues(){
		List<WebElement> customers = driver.findElementsById("com.c2info.ecolite:id/textview_cart_value");
		ArrayList<Double> invValues = new ArrayList<Double>();
		for(WebElement we :customers){
			String val = we.getText().toString();
			val = val.replaceAll("\u20B9","").trim();
			val = val.replaceAll(",","").trim();
			double invVal = Double.parseDouble(val);
			invVal = Math.round(invVal);
			invValues.add(invVal);
		}
		return invValues ;
	}
	
	public HashMap<String, Double> getCustNameAndInvoiceValue(){
		
		HashMap<String, Double> custNameAndInvoiceValue = new HashMap<String, Double>();
		ArrayList<String> custNames = getListOfParkedInvoiceCustomer();
		ArrayList<Double> invoiceValues = getListOfParkedInvoiceValues();
		
		for(int i=0; i<custNames.size(); i++){
			custNameAndInvoiceValue.put(custNames.get(i), invoiceValues.get(i));
		}
		return custNameAndInvoiceValue ;
	}
	
	public ArrayList<Integer> getItemCountInCart(){
		List<WebElement> itemCountList = driver.findElementsById("com.c2info.ecolite:id/textview_qty");
		ArrayList<Integer> itemCount = new ArrayList<Integer>();
		
		for(WebElement we : itemCountList){
			String sr = we.getText().toString();
			sr = sr.substring(0,sr.length()-5).trim().toString();
			int item = Integer.parseInt(sr);
			itemCount.add(item);
		}
		
		return itemCount ;
	}
	
	public HashMap<String, Integer> getCustNameAndItemCount(){
		HashMap<String, Integer> custNameAndItemCount = new HashMap<String, Integer>();
		ArrayList<String> custNames = getListOfParkedInvoiceCustomer();
		ArrayList<Integer> itemCount = getItemCountInCart();
		
		for(int i=0; i<custNames.size(); i++){
			custNameAndItemCount.put(custNames.get(i), itemCount.get(i));
		}
		return custNameAndItemCount ;
	}
}
