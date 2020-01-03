package com.iteam.brite_erp.pages;

import com.iteam.brite_erp.utilities.BrowserUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.sql.Driver;

public class BasePage {

    // These elements are for login the page
    @FindBy(xpath = "//a [@href='/web/login']")
    public WebElement loginLink;

    @FindBy(xpath = "//input[@id='login']")
    public WebElement emailBox;

    @FindBy(xpath = "//input[@id='password']")
    public WebElement passwordBox;

    @FindBy(xpath = "//button[contains(text(),'Log in')]")
    public  WebElement loginButton;
                                                // String moduleLocator = "//a[text()='" + moduleName + "'][@class='navbar-item']";
    // These elements are from top navigation bar CONTACTS and POS (Point of Sale)
    @FindBy(xpath = "//a[@class='oe_menu_toggler'][@data-menu='68']")
    public WebElement contactsModule;

    @FindBy(xpath = "//a[@class='oe_menu_toggler'][@data-menu='484']")
    public WebElement posModule;
//-------------------------------------------------------------------------------------------------------------
    public BasePage(){
        PageFactory.initElements(Driver.get(), this);
    }
//--------------------------------------------------------------------------------------------------------------

    // Login method
    public void login(String email, String password) {
        emailBox.sendKeys(email);
        passwordBox.sendKeys(password, Keys.ENTER);
    }
//---------------------------------------------------------------------------------------------------------------

    // Method for navigation to CONTACTS module or POS

    public void navigateTo(String moduleName) {

        String moduleLocator = "//a[@class='oe_menu_toggler'][@data-menu='"+moduleName+"']";
        WebElement module = Driver.get().findElement(By.xpath(moduleLocator));

        try {
            waitForVisibilityOfElement(module);
            hoverToElement(module);
        } catch (Exception e) {
            waitForVisibilityOfElement(menuElement);
            menuElement.click();
        }

        waitForVisibilityOfElement(module);
        BrowserUtils.waitForClickablility(module,10);
        BrowserUtils.clickWithWait(module); //if click is not working well
        BrowserUtils.waitForPageToLoad(10);
    }

//--------------------------------------------------------------------------------------------------

    // Wait method for waiting till the page loaded for not missing any HTML code.
    public boolean waitUntilLoaderMaskDisappear() {
        WebDriverWait wait = new WebDriverWait(Driver.get(), 30);
        try {
            wait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector("div[class='loader-mask shown']")));
            return true;
        } catch (NoSuchElementException e) {
            System.out.println("Loader mask not found!");
            e.printStackTrace();
            return true; // no loader mask, all good, return true
        } catch (WebDriverException e) {
            e.printStackTrace();
        }
        return false;
    }


    public String getPageSubTitle() {
        //ant time we are verifying page name, or page subtitle, loader mask appears
//        waitUntilLoaderMaskDisappear();
        BrowserUtils.waitForStaleElement(pageSubTitle);
        return pageSubTitle.getText();
    }

    public String getPageTitle() {
        //ant time we are verifying page name, or page subtitle, loader mask appears
//        waitUntilLoaderMaskDisappear();
        BrowserUtils.waitForStaleElement(pageTitle);
        return pageTitle.getText();
    }


    public void logOut(){

        BrowserUtils.wait(2);
        String moduleLocator = "//a[text()='my'][@class='navbar-link']";
        WebElement module = Driver.get().findElement(By.xpath(moduleLocator));

        try {
            waitForVisibilityOfElement(module);
            hoverToElement(module);
        } catch (Exception e) {
            waitForVisibilityOfElement(menuElement);
            menuElement.click();
        }

        waitForVisibilityOfElement(logOutLink);
        BrowserUtils.clickWithJS(logOutLink);
        BrowserUtils.waitForPageToLoad(10);
    }


    public void waitForPageSubTitle(String pageSubtitleText) {
        new WebDriverWait(Driver.get(), 10).until(ExpectedConditions.textToBe(By.cssSelector("h2[class='subtitle']"), pageSubtitleText));
    }


    public void waitForVisibilityOfElement(WebElement element) {
        new WebDriverWait(Driver.get(), 10).until(ExpectedConditions.visibilityOf(element));
    }


    public static void selectFromDropDown(WebElement element , String Visibletext){
        Select select = new Select(element);
        select.selectByVisibleText(Visibletext);
    }


    public static void hoverToElement(WebElement element){
        Actions actions = new Actions(Driver.get());
        actions.moveToElement(element).build().perform();
    }





}