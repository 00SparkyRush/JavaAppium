package lib;

import io.appium.java_client.AppiumDriver;
import io.qameta.allure.Step;
import junit.framework.TestCase;
import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.ScreenOrientation;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.io.FileOutputStream;
import java.time.Duration;
import java.util.Properties;
import java.util.concurrent.ExecutionException;

public class CoreTestCase {

   // private static final String PLATFORM_IOS = "iOS";
   // private static final String PLATFORM_ANDROID = "Android";

    protected RemoteWebDriver driver;


    //private static String appiumUrl = "http://127.0.0.1:4723/wd/hub";

    @Before
    @Step("Run drever and session")
    public void setUp() throws Exception {

        //super.setUp();
        driver = Platform.getInstance().getDriver();
        this.createAllurePropertyFile();
        this.rotateScreenPortrait();
        this.openWikiWebPageForMobileWeb();

        //DesiredCapabilities capabilities = this.getCapabilitiesByPlatformEnv();
        //driver = new AndroidDriver(new URL(appiumUrl), capabilities);
       // driver.rotate(ScreenOrientation.PORTRAIT);
    }

    @After
    @Step("Remove drever and session")
    public void tearDown() {
        driver.quit();
        //super.tearDown();
    }

    @Step("Rotate screen to portrait mode")
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

    @Step("Rotate screen to landscape mode")
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

    @Step("Send app to background (does nothing for mobile web)")
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
    @Step("Open mobile web page for wiki (does nothing for mobile platforms)")
    protected void openWikiWebPageForMobileWeb(){
        if (Platform.getInstance().isMW()){
            driver.get("https://en.m.wikipedia.org");
        }
        else {
            System.out.println("Method openWikiWebPageForMobileWeb() does nothing for platform "+
                    Platform.getInstance().getPlatformVar());
        }
    }

    private void createAllurePropertyFile()
    {
        String path = System.getProperty("allyre.results.directory");
        try
        {
            Properties props = new Properties();
            FileOutputStream fos = new FileOutputStream(path+"/environment.properties");
            props.setProperty("Environment", Platform.getInstance().getPlatformVar());
            props.store(fos, "See https://github.com/allure-frameworks/allere-app/wiki/Environment");
            fos.close();
        } catch (Exception e) {
            System.err.println("IO problem when wrighting allure properties file");
            e.printStackTrace();
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
