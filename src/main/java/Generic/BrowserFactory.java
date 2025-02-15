package Generic;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.safari.SafariDriver;

import java.sql.Driver;
import java.time.Duration;

public class BrowserFactory {
    private static WebDriver driver;
    public static WebDriver initiateDriver(String browserName) throws Exception {
        switch (browserName){
            case "chrome":
                driver = new ChromeDriver();
                break;
            case "edge":
                driver = new EdgeDriver();
                break;
            case "safari":
                driver = new SafariDriver();
                break;
            default:
                throw new Exception("Browser "+ browserName +" not supported");
        }
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
        driver.manage().window().maximize();
        return  driver;
    }

    public static WebDriver getDriver(){
        return driver;
    }
}
