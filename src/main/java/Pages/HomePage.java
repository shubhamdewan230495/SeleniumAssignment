package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HomePage {
    public WebDriver driver;

    @FindBy(id = "usercentrics-root")
    public WebElement ConsentFormModal;
    @FindBy(xpath = "//a[text()='PARFUM']")
    public WebElement parfumOption;

    public static By cookieConsentAllowAllButton = By.cssSelector("[data-testid='uc-accept-all-button']");

    public HomePage(WebDriver driver){
        PageFactory.initElements(driver, this);
    }
}
