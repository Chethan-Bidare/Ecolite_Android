package c2info_ElMob.UI_Actions;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import c2info_ElMob.TestBase.TestBase;

public class LoginPage extends TestBase{

	WebDriverWait wait = new WebDriverWait(driver, 60);
	@FindBy(id="com.c2info.ecolite:id/edittext_remember_password")
	WebElement pwd ;
	
	@FindBy(id="com.c2info.ecolite:id/edit_user_pwd")
	WebElement password ;
	
	@FindBy(id="com.c2info.ecolite:id/edit_user_id")
	WebElement userid ;
	
	@FindBy(id="com.c2info.ecolite:id/button_sign_in")
	WebElement signinButton ;
	
	@FindBy(id="com.c2info.ecolite:id/main_toolbar_title")
	WebElement homepage ;
	
	public LoginPage(){
		PageFactory.initElements(driver, this);
	}
	
	public void doLogin(String userid, String password) throws InterruptedException{
		Thread.sleep(10000);
		try {
			this.userid.sendKeys(userid);
			this.password.sendKeys(password);
			hideKeyboard();
			signinButton.click();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			pwd.sendKeys(password);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Thread.sleep(40000);
	}
	
	
	public String getHomepageTitle(){
		return homepage.getText();
	}
}
