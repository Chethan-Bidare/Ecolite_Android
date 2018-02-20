package c2info_ElMob.UI_Actions;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import c2info_ElMob.TestBase.TestBase;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

public class Purchase extends TestBase{

TouchAction touchAction = new TouchAction(driver);
	
	public Purchase(AndroidDriver<WebElement> driver){
		PageFactory.initElements(new AppiumFieldDecorator(driver), this);
	}
	
	@FindBy(id="com.c2info.ecolite:id/edit_scheme_Qty")
	WebElement schQtyEdit ;
	
	@FindBy(id="com.c2info.ecolite:id/image_sch_Plus")
	WebElement schQtyIncrease ;
	
	@FindBy(id="com.c2info.ecolite:id/image_Sch_Minus")
	WebElement schQtyDecrease ;
	
	@FindBy(id="com.c2info.ecolite:id/auto_complete_sch_disc")
	WebElement schQtyDisc ;
	
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
	
	@FindBy(id="com.c2info.ecolite:id/radio_new_sale")
	WebElement salescheckbox ;
	
	@FindBy(id="com.c2info.ecolite:id/radio_new_sale")
	WebElement salescheckbox ;

	*/
}
