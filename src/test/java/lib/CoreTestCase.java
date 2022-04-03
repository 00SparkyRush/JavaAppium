package lib;

import io.appium.java_client.AppiumDriver;
import junit.framework.TestCase;
import org.openqa.selenium.ScreenOrientation;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.time.Duration;

public class CoreTestCase extends TestCase {

   // private static final String PLATFORM_IOS = "iOS";
   // private static final String PLATFORM_ANDROID = "Android";

    protected RemoteWebDriver driver;


    //private static String appiumUrl = "http://127.0.0.1:4723/wd/hub";

    @Override
    protected void setUp() throws Exception {

        super.setUp();
        driver = Platform.getInstance().getDriver();
        this.rotateScreenPortrait();
        this.openWikiWebPageForMobileWeb();

        //DesiredCapabilities capabilities = this.getCapabilitiesByPlatformEnv();
        //driver = new AndroidDriver(new URL(appiumUrl), capabilities);
       // driver.rotate(ScreenOrientation.PORTRAIT);
    }

    @Override
    protected void tearDown() throws Exception {
        driver.quit();
        super.tearDown();
    }

    protected void rotateScreenPortrait()
    {
        if (driver instanceof AppiumDriver){
            AppiumDriver driver = (AppiumDriver) this.driver;
            driver.rotate(ScreenOrientation.PORTRAIT);
        } else {
            System.out.println("Method rotateScreenPortrait() does nothing for platform "+
                    Platform.getInstance().getPlatformVar());
        }
    }

    protected void rotateScreenLandscape()
    {
        if (driver instanceof AppiumDriver){
            AppiumDriver driver = (AppiumDriver) this.driver;
            driver.rotate(ScreenOrientation.LANDSCAPE);
        } else {
            System.out.println("Method rotateScreenLandscape() does nothing for platform "+
                    Platform.getInstance().getPlatformVar());
        }
    }

    protected void backgroundApp(Duration timeAtBackgroundInSeconds)
    {
        if (driver instanceof AppiumDriver){
            AppiumDriver driver = (AppiumDriver) this.driver;
            driver.runAppInBackground(timeAtBackgroundInSeconds);
        } else {
            System.out.println("Method backgroundApp() does nothing for platform "+
                    Platform.getInstance().getPlatformVar());
        }
    }
    protected void openWikiWebPageForMobileWeb(){
        if (Platform.getInstance().isMW()){
            driver.get("https://en.m.wikipedia.org");
        }
        else {
            System.out.println("Method openWikiWebPageForMobileWeb() does nothing for platform "+
                    Platform.getInstance().getPlatformVar());
        }
    }
/*
    private DesiredCapabilities getCapabilitiesByPlatformEnv() throws Exception
    {
        String platform = System.getenv("PLATFORM");

        DesiredCapabilities capabilities = new DesiredCapabilities();
        if (platform.equals(PLATFORM_ANDROID))
        {
            capabilities.setCapability("platformName", "Android");
            capabilities.setCapability("deviceName", "AndroidTestDevicce");
            capabilities.setCapability("platformVersion", "8.0"); //AndroidOS version
            capabilities.setCapability("automationName", "Appium");
            capabilities.setCapability("appPackage", "org.wikipedia");
            capabilities.setCapability("appActivity", ".main.MainActivity");
            capabilities.setCapability("app",
                    "C:/Users/Sparky/IdeaProjects/JavappiumAutomation/apks/org.wikipedia_50391_apps.evozi.com.apk"); //path to apk
        }
        else if (platform.equals(PLATFORM_IOS))
        {
            capabilities.setCapability("platformName", "iOS");
            capabilities.setCapability("deviceName", "<Name of device>");
            capabilities.setCapability("platformVersion", "<iOS version>");
            capabilities.setCapability("app", "<path to .app>");
        }
        else
        {throw new Exception("Can`t get run platform from env variable. Platform value " + platform);}
        return capabilities;
    }
    */

}
