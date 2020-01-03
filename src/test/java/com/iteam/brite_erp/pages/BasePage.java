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

      // if you login as a POSMANAGER you will see these elements
      // These elements are from top navigation bar CONTACTS and POS (Point of Sale), CRM, LOGOUT LINK
    @FindBy(xpath = "//a[@class='oe_menu_toggler'][@data-menu='68']")
    public WebElement contactsModule;

    @FindBy(xpath = "//a[@class='oe_menu_toggler'][@data-menu='484']")
    public WebElement posModule;

    @FindBy(xpath = "//span[@class='oe_topbar_name']")
    public WebElement logOutLink;

    // if you login as an EVENTSCRMMANAGER you will need only CRM module on the top navigation bar for this assignment
    @FindBy(xpath = "//a[@class='oe_menu_toggler'][@data-menu='261']")
    public WebElement crmModule;


    //-------------------------------------------------------------------------------------------------------------
    public BasePage(){
        PageFactory.initElements(Driver.get(), this);
    }
//--------------------------------------------------------------------------------------------------------------

    // Method for navigation to CONTACTS module or POS module

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

//-------------------------------------------------------------------------------------

    // For getting title
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

//-----------------------------------------------------------------------------------------

    // Wait methods
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

    public void waitForPageSubTitle(String pageSubtitleText) {
        new WebDriverWait(Driver.get(), 10).until(ExpectedConditions.textToBe(By.cssSelector("h2[class='subtitle']"), pageSubtitleText));
    }


    public void waitForVisibilityOfElement(WebElement element) {
        new WebDriverWait(Driver.get(), 10).until(ExpectedConditions.visibilityOf(element));
    }

    //--------------------------------------------------------------------------------------------------

    public static void selectFromDropDown(WebElement element , String Visibletext){
        Select select = new Select(element);
        select.selectByVisibleText(Visibletext);
    }


    public static void hoverToElement(WebElement element){
        Actions actions = new Actions(Driver.get());
        actions.moveToElement(element).build().perform();
    }
 //-----------------------------------------------------------------------------------------

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






}
