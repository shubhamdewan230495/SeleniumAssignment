package Generic;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class Utils {
    private WebDriver driver;
    private WebDriverWait wait;
    public Utils(WebDriver driver, int timeoutInSeconds){
        this.driver = driver;
        wait = new WebDriverWait(driver, Duration.ofSeconds(timeoutInSeconds));
    }
    public void loadUrl(String url){
        this.driver.navigate().to(url);
    }
    public  WebElement getElement(String locator) throws Exception {
        if(locator.startsWith("//")){
            return BrowserFactory.getDriver().findElement(By.xpath(locator));
        } else if(locator.startsWith("#")) {
            return BrowserFactory.getDriver().findElement(By.id(locator));
        } else if(locator.startsWith(".")) {
            return BrowserFactory.getDriver().findElement(By.className(locator));
        }else if(locator.startsWith("[") || locator.substring(0, 1).matches("[a-z]")){
            return BrowserFactory.getDriver().findElement(By.cssSelector(locator));
        }
        else{
            throw new Exception("Locator not identified");
        }
    }
    public List<WebElement> getElements(String locator) throws Exception {
        if(locator.startsWith("//")){
            return BrowserFactory.getDriver().findElements(By.xpath(locator));
        } else if(locator.startsWith("#")) {
            return BrowserFactory.getDriver().findElements(By.id(locator));
        } else if(locator.startsWith(".")) {
            return BrowserFactory.getDriver().findElements(By.className(locator));
        } else if(locator.startsWith("[") || locator.substring(0, 1).matches("[a-z]")){
            return BrowserFactory.getDriver().findElements(By.cssSelector(locator));
        }
        else{
            throw new Exception("Locator not identified");
        }
    }

    public By getElementBy(String locator) throws Exception {
        if(locator.startsWith("//")){
            return By.xpath(locator);
        } else if(locator.startsWith("#")) {
            return By.id(locator);
        } else if(locator.startsWith(".")) {
            return By.className(locator);
        }else if(locator.startsWith("[") || locator.substring(0, 1).matches("[a-z]")){
            return By.cssSelector(locator);
        }
        else{
            throw new Exception("Locator not identified");
        }
    }
    public void waitUntilPresenceOfElement(String locator) throws Exception {
        wait.until(ExpectedConditions.presenceOfElementLocated(getElementBy(locator)));
    }
    public void waitUntilVisibilityOfElement(String locator) throws Exception {
        wait.until(ExpectedConditions.visibilityOfElementLocated(getElementBy(locator)));
    }
    public void clickElement(String locator) throws Exception {
        wait.until(ExpectedConditions.presenceOfElementLocated(getElementBy(locator))).click();
    }
    public boolean checkIfElementIsVisible(String locator) throws Exception {
        return getElement(locator).isDisplayed();
    }
    public void scrollToElement(String locator) throws Exception {
        ((JavascriptExecutor)BrowserFactory.getDriver()).executeScript("arguments[0].scrollIntoView(true)", getElement(locator));
    }
    public void clickUsingJavascript(String locator) throws Exception {
        WebElement element = wait.until(ExpectedConditions.presenceOfElementLocated(getElementBy(locator)));
        ((JavascriptExecutor)BrowserFactory.getDriver()).executeScript("arguments[0].click()", element);
    }
    public void waitForPageLoad(int timeoutInSeconds) {
        new WebDriverWait(BrowserFactory.getDriver(), Duration.ofSeconds(timeoutInSeconds)).until(
                (ExpectedCondition<Boolean>) wd ->
                        ((JavascriptExecutor) wd).executeScript("return document.readyState").equals("complete")
        );
    }
    public void scrollToFooter() {
        JavascriptExecutor js = (JavascriptExecutor) BrowserFactory.getDriver();
        long lastHeight = (long) js.executeScript("return document.body.scrollHeight;");
        js.executeScript("window.scrollBy(0, "+(lastHeight/4)*3+");");
    }
    public void mouseHover(String locator) throws Exception {
        Actions actions = new Actions(BrowserFactory.getDriver());
        actions.moveToElement(getElement(locator)).perform();
    }
    public void waitForFewSeconds(int seconds){
        try {
            Thread.sleep(seconds* 1000L);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

}
