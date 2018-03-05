package c2info_ElMob.UI_Actions;

import java.time.YearMonth;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Random;

import org.apache.commons.lang3.RandomStringUtils;
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
	
	@FindBy(id="com.c2info.ecolite:id/editext_batch_no")
	WebElement batchNo ;
	
	@FindBy(id="com.c2info.ecolite:id/edittext_marp")
	WebElement batchMRP ;
	
	@FindBy(id="com.c2info.ecolite:id/edittext_sale_rate")
	WebElement batchSaleRate ;
	
	@FindBy(id="com.c2info.ecolite:id/editext_ptr")
	WebElement batchPTR ;
	
	@FindBy(id="com.c2info.ecolite:id/editext_Qty")
	WebElement batchLooseQty ;
	
	@FindBy(id="com.c2info.ecolite:id/edittext_expiry")
	WebElement batchExpiry ;
	
	@FindBy(id="com.c2info.ecolite:id/btn_add_batch")
	WebElement batchAddBtn ;
	/*
	@FindBy(id="com.c2info.ecolite:id/radio_new_sale")
	WebElement salescheckbox
	
	@FindBy(id="com.c2info.ecolite:id/radio_new_sale")
	WebElement salescheckbox
	
	@FindBy(id="com.c2info.ecolite:id/radio_new_sale")
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
		//itemNames.remove(itemNames.size()-1);
		//System.out.println(itemNames);
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
	
	public int getStockOfSearchedItem(){
		String stk = driver.findElement(By.id("com.c2info.ecolite:id/textview_itemcount")).getText();
		stk = stk.replaceAll("\\+","");
		int stock = Integer.parseInt(stk);
		return stock ;
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
		
		/*touchAction.tap(driver.findElementByName(""+batchNo+""));
		touchAction.perform();*/
		//driver.findElementByName(""+batchNo+"").click();
		driver.findElementByXPath(".//*[@class='android.widget.TextView' and contains(text(),'"+batchNo+"')]").click();
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
	
	public int getQty(){
		String qty = enterQty.getText();
		int quantity = Integer.parseInt(qty);
		return quantity;
	}
	
	public void enterDiscount(String disc){
		discPer.clear();
		discPer.sendKeys(disc);
	}
	
	public double getDiscount(){
		String dis = discPer.getText();
		double discount = Double.parseDouble(dis);
		return discount ;
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
	
	public HashMap<String,Integer> getBatchesWithStock(){
		HashMap<String,Integer> batchesWithStock = new HashMap<String,Integer>();
		List<WebElement> itemBatches = driver.findElementsById("com.c2info.ecolite:id/text_Batch_No");
		ArrayList<String> batches = new ArrayList<String>();
		List<WebElement> itemstock = driver.findElementsById("com.c2info.ecolite:id/text_Batch_Qty");
		ArrayList<Integer> stock = new ArrayList<Integer>();
		
		for(WebElement we : itemBatches){
			batches.add(we.getText().toString().trim());			
		}
		System.out.println(batches);
		for(WebElement we : itemstock){
			String pr = we.getText().toString().trim();
			pr = pr.replaceAll("Stock : +","");
			int stk = Integer.parseInt(pr);
			stock.add(stk);
		}
		System.out.println(stock);
		for(int i=0;i<batches.size(); i++){
			
			batchesWithStock.put(batches.get(i),stock.get(i));
		}
		return batchesWithStock ;
		
	}
	
	public LinkedHashMap<String,YearMonth> getExpiryFromBatches(){
		LinkedHashMap<String,YearMonth> batchesWithExp = new LinkedHashMap<String,YearMonth>();
		
		List<WebElement> itemBatches = driver.findElementsById("com.c2info.ecolite:id/text_Batch_No");
		ArrayList<String> batches = new ArrayList<String>();
		
		ArrayList<String> expiryDates = new ArrayList<String>();
		List<WebElement> exp = driver.findElementsById("com.c2info.ecolite:id/text_Batch_Exp");
		
		for(WebElement we : itemBatches){
			batches.add(we.getText().toString().trim());
		}
		
		for(WebElement we : exp){
			String text = we.getText().trim().toString();
			expiryDates.add(text);
		}
		
		
		ArrayList<YearMonth> dateFormat = new ArrayList<YearMonth>();
		for(String a : expiryDates){
			String[] parts = a.split("/");
			String part1 = parts[0];
			String part2 = "20"+parts[1];
			int year = Integer.parseInt(part2);
			int month = Integer.parseInt(part1);
			YearMonth ym = YearMonth.of(year, month);
			dateFormat.add(ym);
		}
		
		for(int i=0; i<batches.size(); i++){
			batchesWithExp.put(batches.get(i), dateFormat.get(i));
		}
		
		return batchesWithExp;
		
	}
	
	public LinkedHashMap<String,YearMonth> getExpiryFromBatchesBySwiping(){
		LinkedHashMap<String,YearMonth> expDates = new LinkedHashMap<String, YearMonth>();
		int count=1 ;
		while(count<5){
			expDates.putAll(getExpiryFromBatches());
			swipeUpInBatchList();
			count++ ;
		}
		
		return expDates ;
	}
	
	public HashMap<String,Integer> getBatchesWithStockBySwiping(){
		HashMap<String, Integer> batchWithStk = new HashMap<String, Integer>();
		int count =1;
		while(count<5){
			batchWithStk.putAll(getBatchesWithStock());
			swipeUpInBatchList();
			count++ ;
		}
		
		
		return batchWithStk ;
	}
	
	public int getSumOfStockofAllBatchesbySwiping(){
		int sum = 0;
		for(Integer i : getBatchesWithStockBySwiping().values()){
			sum += i ;
		}
		return sum ;
	}
	
	public HashMap<String,Float> getBatchesWithPriceBySwiping(){
		HashMap<String, Float> batchWithPrice = new HashMap<String, Float>();
		int count =1;
		while(count<5){
			batchWithPrice.putAll(getBatchesWithPrice());
			swipeUpInBatchList();
			count++ ;
		}
		
		
		return batchWithPrice ;
	}
	
	public float getHighestMrpFromAllBatches(){
		//ArrayList<Float> values = (ArrayList<Float>) getBatchesWithPriceBySwiping().values();
		return Collections.max(getBatchesWithPriceBySwiping().values());
		
	}
	
	public float getPriceOfSingleBatch(){
		String pr = driver.findElementById("com.c2info.ecolite:id/text_Batch_Mrp").getText();
		pr = pr.replaceAll("\u20B9","");
		Float price = Float.parseFloat(pr);
		return price ;
	}
	
	
	public float getMRPAfterDisc(float discPer ,float mrp, float tax){
		float saleRate = getSaleRate(mrp, tax);
		float SaleRateAftrDisc = saleRate * ((100-discPer)/100) ;
		float taxVal = mrp - saleRate;
		float SalerateAfterDiscWithTax = SaleRateAftrDisc + taxVal ;
		return SalerateAfterDiscWithTax ;
	}
	
	public float getSaleRate(float mrp, float tax){
		
		float saleRate = (mrp * 100)/(100+ tax) ;
		System.out.println("Calculated Sale Rate="+saleRate);
		return saleRate ;
	}
	
	public float getDiscountValue(float discPer ,float mrp, float tax){
		float discValue = mrp - getMRPAfterDisc(discPer, mrp, tax);
		return discValue ;
	}
	
	public double getTaxAmtCalculated(float mrp, float tax){
		double taxAmt = mrp - getSaleRate(mrp, tax);
		return taxAmt ;
	}
	
	public double calculateItemCost(float mrp,int qty, double discPer){
		double itemCost = mrp * qty ;
		double discAmt = itemCost * (discPer/100) ;
		itemCost = itemCost - discAmt ;
		return itemCost ;
	}
	
	public String enterNewbatchDetails(String MRP,String PTR,String LsQty) throws InterruptedException{
		String batchNO = RandomStringUtils.randomAlphanumeric(6);
		batchNo.sendKeys(batchNO);
		batchMRP.sendKeys(MRP);
		batchPTR.sendKeys(PTR);
		batchExpiry.click();
		driver.findElementById("android:id/date_picker_year").click();
		swipeUpInBatchList();
		Thread.sleep(2000);
		driver.findElementByXPath("//android.widget.TextView[contains(@text,'2023')]").click();
		driver.findElementById("android:id/button1").click();
		batchLooseQty.sendKeys(LsQty);
		hideKeyboard();
		batchAddBtn.click();
		return batchNO ;
	}
	
	
}
