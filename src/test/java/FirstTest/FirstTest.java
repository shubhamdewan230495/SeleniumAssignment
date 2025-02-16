package FirstTest;

import DataProviders.SearchCriteria;
import Features.HomePageFeature;
import Features.ParfumPageFeature;
import Generic.BrowserFactory;
import Generic.ConfigReader;
import Generic.Utils;
import io.qameta.allure.*;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.ArrayList;

public class FirstTest {
    WebDriver driver;
    HomePageFeature homePageFeature;
    ParfumPageFeature parfumPageFeature;
    @BeforeClass
    public void initSetup() throws Exception {
        ConfigReader configReader = new ConfigReader();
        int TIMEOUT_IN_SECONDS = Integer.parseInt(configReader.getProperty("timeoutInSeconds"));
        driver = BrowserFactory.initiateDriver(configReader.getProperty("browserName"));
        Utils utils = new Utils(BrowserFactory.getDriver(), TIMEOUT_IN_SECONDS);
        utils.loadUrl(configReader.getProperty("applicationUrl"));
        homePageFeature = new HomePageFeature(driver);
        parfumPageFeature = new ParfumPageFeature(driver);
        parfumPageFeature = new ParfumPageFeature(driver);
        homePageFeature.clickAllowAllOnConsentModal();
    }

    @Severity(SeverityLevel.CRITICAL)
    @Description("Test Description: Verification of filters on parfum tab")
    @Epic("TC-001")
    @Feature("Feature: Parfum tab filters verification")
    @Story("Story: Parfum tab filters verification")
    @Step("Parfum tab filters verification")
    @Test(description = "Verification of test 1", dataProvider = "searchCriteria", dataProviderClass = SearchCriteria.class)
    public void firstTest(String produktart, String geschenkFur, String furWen) throws Exception {
        homePageFeature.navigateToParfumTab();
        parfumPageFeature.selectOptionFromDropdown("Produktart", produktart);
        parfumPageFeature.selectOptionFromDropdown("Für Wen", furWen);
        parfumPageFeature.selectOptionFromDropdown("Geschenk für", geschenkFur);
        ArrayList<String> allProducts = parfumPageFeature.getListOfAllProducts();
        System.out.println("Products found in Search Criteria : "+ allProducts.toString());
        parfumPageFeature.resetFilters();
    }

    @AfterClass
    public void closeDriver(){
        BrowserFactory.getDriver().quit();
    }
}
