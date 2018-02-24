package c2info_ElMob.TestBase;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Properties;

import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;

import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;

public class TestBase {

	public static AndroidDriver<WebElement> driver ;
	TouchAction touchAction = new TouchAction(driver);
	public Properties OR = new Properties();
	public Properties APP = new Properties();
	DesiredCapabilities capabilities = new DesiredCapabilities();
	
	
	
	
	
	public void init() throws IOException{
		DeviceCapabilities();
		loadFromAPPproperties();
		loadFromORProperties();
	}
	
	public void DeviceCapabilities() throws MalformedURLException{	
		capabilities.setCapability(CapabilityType.BROWSER_NAME,"ANDROID");
		capabilities.setCapability(CapabilityType.VERSION,"5.1");
		capabilities.setCapability("deviceName","ZX1D64CPT2");
		capabilities.setCapability("platformName","ANDROID");
		capabilities.setCapability("appPackage","com.c2info.ecolite");
		capabilities.setCapability("appActivity","com.c2info.ecolite.ui.activity.SplashScreenActivity");
		driver = new AndroidDriver<WebElement>(new URL("http://127.0.0.1:4723/wd/hub"),capabilities);
		//return new LoginPage();		
	}
	
	public void loadFromORProperties() throws IOException{
		File path = new File(System.getProperty("user.dir")+"//src//main//java//c2info_ElMob//Config//OR.properties");
		FileInputStream fis = new FileInputStream(path);
		OR.load(fis);
	}
	
	public void loadFromAPPproperties() throws IOException{
		File path = new File(System.getProperty("user.dir")+"//src//main//java//c2info_ElMob//Config//APP.properties");
		FileInputStream fis = new FileInputStream(path);
		APP.load(fis);
	}
	
	public void hideKeyboard(){
		driver.hideKeyboard();
	}
	
	public void swipeUpInBatchList(){	
		
		Dimension size = driver.manage().window().getSize();
		int startx = size.width / 2 ;
		int starty = (int) (size.height * 0.6) ;
		int endy = (int) (size.height * 0.2) ;
		driver.swipe(startx, starty, startx, endy, 3000);
		/*touchAction.longPress(390,670).moveTo(390,430).release().perform();*/
	}
	
	
	
	public float getSumOfArraysFloat(ArrayList<Float> arr){
		ArrayList<Float> arr1 =  arr;
		float sum = 0;
		for(Float x : arr1){
			sum+=x ;
		}
		return sum ;
		
	}
	
	public double getSumOfArraysDouble(ArrayList<Double> arr){
		ArrayList<Double> arr1 =  arr;
		double sum = 0;
		for(double x : arr1){
			sum+=x ;
		}
		return sum ;
		
	}

}
