package Features;

import Generic.Utils;
import Pages.HomePage;
import Pages.ParfumPage;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class HomePageFeature {
    public WebDriver driver;
    public HomePage homePage;
    public Utils utils;
    public ParfumPage parfumPage;
    public HomePageFeature(WebDriver driver){
        this.driver = driver;
        homePage = new HomePage(driver);
        parfumPage = new ParfumPage(driver);
        this.utils = new Utils(driver, 20);
    }

    public void clickAllowAllOnConsentModal() throws InterruptedException {
        homePage.ConsentFormModal.getShadowRoot().findElement(HomePage.cookieConsentAllowAllButton).click();
    }
    public void navigateToParfumTab() throws Exception {
        homePage.parfumOption.click();
        this.utils.waitUntilPresenceOfElement(parfumPage.parfumPageHeader);
        utils.mouseHover(parfumPage.applicationMainLogo);
    }
}
