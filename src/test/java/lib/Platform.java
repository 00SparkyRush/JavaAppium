package lib;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.URL;
import java.util.HashMap;
import java.util.Map;

public class Platform {

    private static Platform instance;
    private Platform() {}

    public static Platform getInstance()
    {
        if (instance==null)
        {
            instance = new Platform();
        }
        return instance;
    }

    private static final String PLATFORM_IOS = "iOS";
    private static final String PLATFORM_ANDROID = "Android";
    private static final String PLATFORM_MOIBILE_WEB = "mobile_web";
    private static final String appiumUrl = "http://127.0.0.1:4723/wd/hub";

    public RemoteWebDriver getDriver() throws Exception
    {
        URL URL = new URL(appiumUrl);
        if (this.isAndroid())
        {
            return new AndroidDriver(URL, this.getAndroidDesiredCapabilities());
        }
        else if (this.isIOS())
        {
            return new IOSDriver(URL, this.getIOSDesiredCapabilities());
        }
        else if (this.isMW())
        {
            return new ChromeDriver(this.getMWChromeOptions());
        }
        else
        {
            return new AndroidDriver(URL, this.getAndroidDesiredCapabilities());
           // throw new Exception("Can`t detect driver type. Platform value is: "+this.getPlatformVar());
        }
    }

    public Boolean isAndroid()
    {
       return isPlatform(PLATFORM_ANDROID);
    }

    public Boolean isIOS()
    {
        return isPlatform(PLATFORM_IOS);
    }

    public Boolean isMW()
    {
        return isPlatform(PLATFORM_MOIBILE_WEB);
    }

    private DesiredCapabilities getAndroidDesiredCapabilities()
    {
        DesiredCapabilities capabilities = new DesiredCapabilities();

        capabilities.setCapability("platformName", "Android");
        capabilities.setCapability("deviceName", "and80");
        capabilities.setCapability("platformVersion", "8.0"); //AndroidOS version
        capabilities.setCapability("automationName", "Appium");
        capabilities.setCapability("appPackage", "org.wikipedia");
        capabilities.setCapability("appActivity", ".main.MainActivity");
        capabilities.setCapability("app",
                "C:/Users/Sparky/IdeaProjects/JavappiumAutomation/apks/org.wikipedia_50391_apps.evozi.com.apk"); //path to apk
        return capabilities;
    }

    private DesiredCapabilities getIOSDesiredCapabilities()
    {
        DesiredCapabilities capabilities = new DesiredCapabilities();

        capabilities.setCapability("platformName", "iOS");
        capabilities.setCapability("deviceName", "<Name of device>");
        capabilities.setCapability("platformVersion", "<iOS version>");
        capabilities.setCapability("app", "<path to .app>");
        return capabilities;
    }

    private ChromeOptions getMWChromeOptions(){
        Map<String, Object> deviceMetrics = new HashMap<String, Object>();
        deviceMetrics.put("width", 360);
        deviceMetrics.put("height", 640);
        deviceMetrics.put("pixelRatio", 3.0);

        Map<String, Object> mobileEmulation = new HashMap<String, Object>();
        mobileEmulation.put("deviceMetrics",deviceMetrics);
        mobileEmulation.put("userAgent",
                "Mozilla/5.0 (Linux; Android 4.2.1; en-us; Nexus 5 Build/JOP40D) AppleWebKit/535.19 (KHTML, like Gecko) Chrome/18.0.1025.166 Mobile Safari/535.19");

        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("window-size=340,640");

        return chromeOptions;
    }

    public String getPlatformVar()
    {
        return System.getenv("PLATFORM");
    }

    private Boolean isPlatform(String My_platform)
    {
        String platform = this.getPlatformVar();
        return My_platform.equals(platform);
    }
}
