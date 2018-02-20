package c2info_ElMob.UI_Actions;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import c2info_ElMob.TestBase.TestBase;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

public class Sales extends TestBase{

	TouchAction touchAction = new TouchAction(driver);
	public Sales(AndroidDriver<WebElement> driver){
		PageFactory.initElements(new AppiumFieldDecorator(driver), this);
	}

	@FindBy(id="com.c2info.ecolite:id/etGlobalSearch")
	WebElement itemSearch ;
	
	@FindBy(xpath="")
	WebElement batchNo ;
	
	@FindBy(id="com.c2info.ecolite:id/button_Add")
	WebElement addButton;
	
	
	@FindBy(id="com.c2info.ecolite:id/edit_Qty")
	WebElement enterQty ;
	
	@FindBy(id="com.c2info.ecolite:id/image_Plus")
	WebElement increaseQty ;
	
	@FindBy(id="com.c2info.ecolite:id/image_Minus")
	WebElement decreaseQty ;
	
	@FindBy(id="com.c2info.ecolite:id/edit_disc_percentage")
	WebElement discPer ;
	
	@FindBy(id="com.c2info.ecolite:id/text_add_new_batch")
	WebElement addNewBatchLink ;
	
	@FindBy(id="com.c2info.ecolite:id/text_add_from_liveconnect")
	WebElement loadBatchFromLiveConnect;
	
	@FindBy(xpath=".//*[@class='android.widget.FrameLayout' and @index=0]")
	WebElement searchedCustomerName;
	
	/*
	
	@FindBy(id="com.c2info.ecolite:id/radio_new_sale")
	WebElement salescheckbox
	
	@FindBy(id="com.c2info.ecolite:id/radio_new_sale")
	WebElement salescheckbox
	
	@FindBy(id="com.c2info.ecolite:id/radio_new_sale")
	WebElement salescheckbox

	*/
	
	public ArrayList<String> getItemName(){
	List<WebElement> items = driver.findElementsById("com.c2info.ecolite:id/textview_itemname");
	ArrayList<String> itemNames = new ArrayList<String>();
	for(WebElement we : items){
		String itemName = we.getText().toString().trim();
		itemNames.add(itemName);
		
	}
	System.out.println(itemNames);
	return itemNames ;
	}
	
	public HashMap<String, Float> getItemNamesWithPrice(){
		List<WebElement> itemrates = driver.findElementsById("com.c2info.ecolite:id/textview_rate");
		HashMap<String, Float> itemNamesWithPrice = new HashMap<String, Float>();
		ArrayList<Float> rates = new ArrayList<Float>();
		for(WebElement we : itemrates){
			String itemrate = we.getText().toString().trim();
			//itemrate = itemrate.substring(1);
			itemrate = itemrate.replace("\u20B9", "");
			float itemRate = Float.parseFloat(itemrate);
			rates.add(itemRate);
		}
		System.out.println(rates);
		ArrayList<String> itemNames = getItemName();
		itemNames.remove(itemNames.size()-1);
		System.out.println(itemNames);
		for(int i=0; i<itemNames.size();i++){
			itemNamesWithPrice.put(itemNames.get(i), rates.get(i));
		}
		System.out.println(itemNamesWithPrice);
		return itemNamesWithPrice ;
		
		
	}
	
	public HashMap<String, Integer> getItemNamesWithStock(){
		List<WebElement> itemStk = driver.findElementsById("com.c2info.ecolite:id/textview_itemcount");
		HashMap<String,Integer> itemStock = new HashMap<String, Integer>();
		ArrayList<String> itemNames = getItemName();
		//itemNames.remove(itemNames.size()-1);
		System.out.println(itemNames);
		int index = 0;
		for(WebElement we : itemStk){
			String val = we.getText().toString();
			val = val.substring(1);
			int value = Integer.parseInt(val);
			itemStock.put(itemNames.get(index), value);
			index=index+1;
		}
		System.out.println(itemStock);
		return itemStock ;
	}
	
	public void tapOnTabOptionsInSales(String options){
		if(options.equalsIgnoreCase("favourites")){
			driver.findElementByXPath(".//*[@class='android.support.v7.app.ActionBar$Tab' and @index=1]").click();
		}
		else if(options.equalsIgnoreCase("Old Invoice")){
			driver.findElementByXPath(".//*[@class='android.support.v7.app.ActionBar$Tab' and @index=2]").click();
		}
		else if(options.equalsIgnoreCase("Priority")){
			driver.findElementByXPath(".//*[@class='android.support.v7.app.ActionBar$Tab' and @index=0]").click();
		}
	}
	
	public void searchByItemName(String itemName){
		itemSearch.clear();
		itemSearch.sendKeys(itemName);
		
	}
	
	public void clickOnSearchedItem(){
		driver.findElement(By.id("com.c2info.ecolite:id/textview_itemname")).click();
		/*TouchAction touchAction = new TouchAction(driver);
		touchAction.tap(driver.findElement(By.xpath(".//*[@class='android.widget.FrameLayout' and @index=0]")));
		touchAction.perform();*/
		
	}
	
	public void searchByCustomerName(String custName){
		itemSearch.clear();
		itemSearch.sendKeys(custName);
		
		
	}
	
	public void clickOnSearchedCustomer(){
		touchAction.tap(searchedCustomerName).perform();
		driver.findElementById("com.c2info.ecolite:id/textview_name").click();
	}
	
	public void selectBatchByName(String batchNo){
		
		touchAction.tap(driver.findElementByName(""+batchNo+""));
		touchAction.perform();
		//driver.findElementByName(""+batchNo+"").click();
		//driver.findElementByXPath(".//*[@class='android.widget.TextView' and contains(text(),'"+batchNo+"')]").click();
	//	driver.findElement(By.xpath(".//*[@class='android.widget.TextView' and @text='"+batchNo+"')]")).click();
	}
	
	public void selectItemByName(String itemName){
		/*TouchAction touchAction = new TouchAction(driver);
		touchAction.tap(driver.findElement(By.xpath(".//android.widget.TextView[@text='"+itemName+"')]")));
		//@id='com.c2info.ecolite:id/textview_itemname' and 
		touchAction.perform();*/
		//driver.findElementByXPath(".//*[@class='android.widget.TextView' and @text='"+itemName+"')]").click();
		
		
		/*List<WebElement> itemList = driver.findElementsById("com.c2info.ecolite:id/lLytItem");
		for(WebElement we : itemList){
			
		}*/
		//System.out.println(driver.getPageSource());
		driver.findElementByName(""+itemName+"").click();
		//driver.findElement(By.xpath("android.widget.TextView[@index=1 and contains(@text,'"+itemName+"')]")).click();
	}
	
	public void clickOnAddButton(){
		touchAction.tap(addButton).perform();
	}
	
	public void addQtyManually(String qty){
		touchAction.tap(enterQty).perform();
		enterQty.clear();
		enterQty.sendKeys(qty);
		enterQty.clear();
		enterQty.sendKeys(qty);
	}
	
	public void increaseQty(int qty){
		for(int i=1; i<=qty; i++){
			touchAction.tap(increaseQty).perform();
		}		
	}
	
	public void decreaseQty(int qty){
		for(int i=1; i<=qty; i++){
			touchAction.tap(decreaseQty).perform();
		}		
	}
	
	public void enterDiscount(String disc){
		discPer.clear();
		discPer.sendKeys(disc);
	}
	
	public void clickOnAddNewBatch(){
		addNewBatchLink.click();
	}
	
	public void clickOnLoadBatchFromLiveConnect(){
		loadBatchFromLiveConnect.click();
	}
	
	public HashMap<String,Float> getBatchesWithPrice(){
		HashMap<String,Float> batchesWithprice = new HashMap<String,Float>();
		List<WebElement> itemBatches = driver.findElementsById("com.c2info.ecolite:id/text_Batch_No");
		ArrayList<String> batches = new ArrayList<String>();
		List<WebElement> itemPrices = driver.findElementsById("com.c2info.ecolite:id/text_Batch_Mrp");
		ArrayList<Float> prices = new ArrayList<Float>();
		for(WebElement we : itemBatches){
			batches.add(we.getText().toString().trim());			
		}
		for(WebElement we : itemPrices){
			String pr = we.getText().toString().trim();
			pr = pr.replaceAll("\u20B9","");
			float price = Float.parseFloat(pr);
			prices.add(price);
		}
		for(int i=0;i<batches.size(); i++){
			batchesWithprice.put(batches.get(i), prices.get(i));
		}
		return batchesWithprice ;
		
	}
	
	
	
	
	
}
