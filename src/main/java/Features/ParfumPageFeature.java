package Features;

import Generic.Utils;
import Pages.HomePage;
import Pages.ParfumPage;
import org.openqa.selenium.By;
import org.openqa.selenium.ElementClickInterceptedException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;

public class ParfumPageFeature {
    public WebDriver driver;
    public HomePage homePage;
    public ParfumPage parfumPage;
    public Utils utils;
    public static int TIMEOUT_IN_SECONDS = 30;
    public ParfumPageFeature(WebDriver driver){
        this.driver = driver;
        homePage = new HomePage(driver);
        parfumPage = new ParfumPage(driver);
        this.utils = new Utils(driver, TIMEOUT_IN_SECONDS);
    }

    public void openDropdownByName(String dropdownName) throws Exception {
        if(!utils.checkIfElementIsVisible(parfumPage.dropDownByName(dropdownName))){
            utils.scrollToElement(parfumPage.dropDownByName(dropdownName));
        }
        try {
            utils.clickElement(parfumPage.dropDownByName(dropdownName));
        }
        catch (ElementClickInterceptedException e){
            utils.clickUsingJavascript(parfumPage.dropDownByName(dropdownName));
        }
    }
    public void selectOption(String optionName) throws Exception {
        utils.clickUsingJavascript(parfumPage.dropDownOptionByName(optionName));
    }
    public void selectOptionFromDropdown(String dropdownName, String valueToSelect) throws Exception {
        if(!utils.getElements(ParfumPage.moreFiltersButton).isEmpty()){
            utils.clickUsingJavascript(ParfumPage.moreFiltersButton);
        }
        openDropdownByName(dropdownName);
        selectOption(valueToSelect);
        utils.waitForPageLoad(TIMEOUT_IN_SECONDS);
        utils.waitUntilPresenceOfElement(ParfumPage.applicationMainLogo);
    }
    public void resetFilters() throws Exception {
        utils.waitUntilPresenceOfElement(ParfumPage.resetFilterButton);
        if(!utils.checkIfElementIsVisible(ParfumPage.resetFilterButton)){
            utils.scrollToElement(ParfumPage.resetFilterButton);
        }
        utils.clickUsingJavascript(ParfumPage.resetFilterButton);
    }
    public ArrayList<String> getListOfAllProducts() throws Exception {
        utils.waitUntilPresenceOfElement(ParfumPage.productInfoAllCards);
        ArrayList<String> as = new ArrayList<>();
        utils.scrollToFooter();
        utils.waitForFewSeconds(5);
        List<WebElement> elems = utils.getElements(ParfumPage.productInfoAllCards);
        for(WebElement element : elems){
            as.add(element.getText());
        }
        return as;
    }
}
