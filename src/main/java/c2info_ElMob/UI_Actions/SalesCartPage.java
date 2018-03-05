package c2info_ElMob.UI_Actions;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import c2info_ElMob.TestBase.TestBase;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

public class SalesCartPage extends TestBase {

	TouchAction touchAction = new TouchAction(driver);
	
	public SalesCartPage(AndroidDriver<WebElement> driver){
		PageFactory.initElements(new AppiumFieldDecorator(driver), this);
	}
	
	
	@FindBy(id="com.c2info.ecolite:id/textview_cart_item_count")
	WebElement itemCountInCart ;
	
	@FindBy(id="com.c2info.ecolite:id/textview_cart_total")
	WebElement cartTotal ;
	
	@FindBy(id="com.c2info.ecolite:id/textview_amount")
	WebElement itemAmt ;
	
	@FindBy(id="com.c2info.ecolite:id/textview_qty")
	WebElement itemQty ;
	
	@FindBy(id="com.c2info.ecolite:id/linear_add_new_item")
	WebElement addNewItemFromCartPage ;
	
	@FindBy(id="com.c2info.ecolite:id/button_get_payment")
	WebElement getPaymentButton ;
	
	@FindBy(id="com.c2info.ecolite:id/tvCustName_toolbar")
	WebElement CustIconInCartPage ;
	
	@FindBy(id="com.c2info.ecolite:id/textview_itemname")
	WebElement singleItem ;
	
	/*@FindBy(id="com.c2info.ecolite:id/radio_new_sale")
	WebElement salescheckbox
	
	@FindBy(id="com.c2info.ecolite:id/radio_new_sale")
	WebElement salescheckbox
	
	@FindBy(id="com.c2info.ecolite:id/radio_new_sale")
	WebElement salescheckbox
	
	@FindBy(id="com.c2info.ecolite:id/radio_new_sale")
	WebElement salescheckbox
	
	@FindBy(id="com.c2info.ecolite:id/radio_new_sale")
	WebElement salescheckbox

	*/
	
	public int getCountOfItemsAddedToCart(){
		String itemcount = itemCountInCart.getText();
		itemcount = itemcount.replaceAll("Items","").trim().toString();
		itemcount = itemcount.replaceAll("item","").trim().toString();
		int itemCount = Integer.parseInt(itemcount);
		return itemCount ;
	}
	
	public float getCartTotal(){
		String carttot = cartTotal.getText().toString().trim();
		carttot =carttot.replaceAll("\u20B9","").trim();
		carttot =carttot.replaceAll(",","").trim();
		float cartTotal = Float.parseFloat(carttot);
		return cartTotal ;
	}
	
	public void clickOnCartPage(){
		cartTotal.click();
	}
	
	public void clickOnItemInCart(){
		driver.findElementById("com.c2info.ecolite:id/textview_batch").click();
		//touchAction.longPress(singleItem).perform();
	}
	
	public ArrayList<String> getItemNamesFromcartPage(){
		//HashSet<String> itemNames = new HashSet<String>();
		List<WebElement> itemName = driver.findElementsById("com.c2info.ecolite:id/textview_itemname");
		ArrayList<String> itemNames = new ArrayList<String>();
		for(WebElement we : itemName){
			String name = we.getText().trim().toString();
			itemNames.add(name);
		}
		return itemNames ;
	}
	
	public HashMap<String,Float> getCalculatedItemWiseTotalWithItemName(){
		List<WebElement> itemName = driver.findElementsById("com.c2info.ecolite:id/textview_itemname");
		List<WebElement> itemprice = driver.findElementsById("com.c2info.ecolite:id/textview_amount");
		List<WebElement> itemqty = driver.findElementsById("com.c2info.ecolite:id/textview_qty");
		ArrayList<Float> itemPrice = new ArrayList<Float>();
		ArrayList<Integer> itemQty = new ArrayList<Integer>();
		ArrayList<String> itemNames = new ArrayList<String>();
		for(WebElement we : itemName){
			String name = we.getText().trim().toString();
			itemNames.add(name);
		}
		for(WebElement we : itemprice){
			String it = we.getText().trim().toString();
			it = it.replaceAll("\u20B9", "");
			float itemCost = Float.parseFloat(it);
			itemPrice.add(itemCost);
		}
		for(WebElement we : itemqty){
			String qty = we.getText().toString();
			qty = qty.replaceAll("Qty :", "").trim();
			int itmqty = Integer.parseInt(qty);
			itemQty.add(itmqty);
		}
		
		for(int i=0;i<itemPrice.size();i++){
			float itemRate = itemPrice.get(i);
			int qty = itemQty.get(i);
			float totalItemprice = itemRate * qty ;
			itemPrice.remove(i);
			itemPrice.add(totalItemprice);
		}
		HashMap<String,Float> calculatedItemWiseTotal = new HashMap<String, Float>();
		for(int i=0; i< itemNames.size(); i++){
			calculatedItemWiseTotal.put(itemNames.get(i), itemPrice.get(i));
			
		}
		
		return calculatedItemWiseTotal;
	}
	
	public void clickOnAddNewItemFromCartPage(){
		addNewItemFromCartPage.click();
	}
	
	
	public void clickOnGetPayment(){
		getPaymentButton.click();
	}
	
	public void clickOnCustIconInCartPage(){
		CustIconInCartPage.click();
	}
	
	public String getCustomerNameFromCustIcon(){
		return CustIconInCartPage.getText().toString();
	}
	
	public float getPriceForSingleItemInCartPage(){
		String pr = driver.findElementById("com.c2info.ecolite:id/textview_amount").getText();
		pr = pr.replaceAll("\u20B9", "");
		Float price = Float.parseFloat(pr);
		return price ;
	}
	
	public int getQtyForSingleItemInCartPage(){
		String pr = driver.findElementById("com.c2info.ecolite:id/textview_qty").getText();
		pr = pr.replaceAll("Qty", "").trim();
		pr = pr.replaceAll(":", "").trim();
		int price = Integer.parseInt(pr);
		return price ;
	}
	
	public ArrayList<Float> getPriceForMultipleItemsInCartPage(){
		ArrayList<Float> prices = new ArrayList<Float>();
		List<WebElement> itemPrices = driver.findElementsById("com.c2info.ecolite:id/textview_amount");
		for(WebElement we :itemPrices){
			String pr = we.getText().trim().toString();
			pr = pr.replaceAll("\u20B9", "");
			Float price = Float.parseFloat(pr);
			prices.add(price);
			System.out.println(prices);
		}
		return prices ;
	}
	
	public void deleteItemBySwiping(){
		/*TouchAction action = new TouchAction(driver);
		List<WebElement> items = driver.findElementsById("android.widget.FrameLayout");
		for(int i=1; i<=itemCountToBeDeleted; i++){
			action.longPress(items.get(i-1)).mov
		}*/
		
		Dimension size = driver.manage().window().getSize();
		int starty = size.height / 2 ;
		int startx = (int) (size.width * 0.8) ;
		int endx = (int) (size.width * 0.2) ;
		driver.swipe(startx, starty, endx, starty, 3000);
	}
	
	
}
