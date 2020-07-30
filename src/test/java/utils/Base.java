package utils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

import java.util.concurrent.TimeUnit;

import static io.github.bonigarcia.wdm.WebDriverManager.chromedriver;
import static io.github.bonigarcia.wdm.WebDriverManager.firefoxdriver;

public class Base {
    private WebDriver webDriver;

    public WebDriver initializeDriver() {
        String browserName = System.getProperty("browser");

        if(browserName == null || browserName.equals("chrome")){
            System.setProperty("webdriver.chrome.driver", "./chromedriver.exe");
            //chromedriver().setup();
            ChromeOptions chromeOptions = new ChromeOptions();
            chromeOptions.addArguments("start-maximized");
            webDriver = new ChromeDriver(chromeOptions);
        }else{
            System.setProperty("webdriver.gecko.driver", "./geckodriver.exe");
            //firefoxdriver().setup();
            webDriver = new FirefoxDriver();
        }
        webDriver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
        return webDriver;
    }
}
