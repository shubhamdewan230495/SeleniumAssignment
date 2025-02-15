package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ParfumPage {
    public WebDriver driver;

    public static String parfumPageHeader = "//h1[text()='Parfüm & Düfte']";
    public static String applicationMainLogo = "[data-testid='tenant-logo-link']";

    public static String dropDownByName(String dropdownName){
        return "//div[text()='"+dropdownName+"']/span";
    }
    public String dropDownOptionByName(String optionName){
        return "//div[@class='facet__menu']//a/div/div[text()='"+optionName+"']";
    }
    public static String resetFilterButton = "//button[text()='Alle Filter löschen']";
    public static String moreFiltersButton ="//button[text()='Mehr Filter anzeigen']";
    public static String productInfoAllCards = "//div[@class='product-info']//div[contains(@class,'top-brand')]";

    public ParfumPage(WebDriver driver){
        PageFactory.initElements(driver, this);
    }
}
