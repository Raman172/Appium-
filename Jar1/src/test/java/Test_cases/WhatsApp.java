package Test_cases;

import java.net.MalformedURLException;
import java.net.URL;

import org.openqa.selenium.remote.DesiredCapabilities;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;

public class WhatsApp {
	
	public static AppiumDriver driver;

	public static void main(String[] args) throws MalformedURLException {
		
		DesiredCapabilities cap = new DesiredCapabilities();
		// This is Raman 
		
		cap.setCapability("deviceName","Redmi");
		
		cap.setCapability("appPackage", "com.whatsapp");
		cap.setCapability("appActivity", "com.whatsapp.HomeActivity");
		
		driver = new AndroidDriver<MobileElement>(new URL("http://0.0.0.0:4723/wd/hub"),cap);

	}

}
