package com.iteam.brite_erp.pages;

import com.iteam.brite_erp.utilities.BrowserUtils;
import com.iteam.brite_erp.utilities.Driver;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

<<<<<<< HEAD


public class BasePage {

    // if you login as a POSMANAGER you will see these elements
    // These elements are from top navigation bar CONTACTS and POS (Point of Sale), CRM, LOGOUT LINK
    @FindBy(xpath = "//a[@class='oe_menu_toggler'][@data-menu='68']")
    public WebElement contactsModule;
=======
public class BasePage {

    @FindBy(css = "[class=breadcrumb] > li")
    public WebElement pageSubTitle;
>>>>>>> 10ba3bc0b67ba038e6fbd155fa0cca2fb79fad65

    @FindBy(css = "[class=oe_topbar_name]")
    public WebElement userName;

    @FindBy(css = "[data-menu=logout]")
    public WebElement logOutLink;

    //Constructor
    public BasePage(){
        PageFactory.initElements(Driver.get(), this);
    }

    /**
     * This method stands for navigation in BriteERP app
     * provide tab name, for example "CRM" as a String
     * then based on this value, locator will be created
     *
     * @param moduleName
     */
    public void navigateTo(String moduleName) {
        Actions actions = new Actions(Driver.get());
        String moduleLocator = "//span[contains(text(),'"+moduleName+"')]";

        WebDriverWait wait = new WebDriverWait(Driver.get(), 20);
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(moduleLocator)));
        WebElement module = Driver.get().findElement(By.xpath(moduleLocator));

        wait.until(ExpectedConditions.visibilityOf(module));
        wait.until(ExpectedConditions.elementToBeClickable(module));
        BrowserUtils.clickWithWait(module);
        BrowserUtils.waitForPageToLoad(10);
    }

    /**
     * @return page name, for example: Notes
     */
    public String getPageSubTitle() {
        BrowserUtils.waitForStaleElement(pageSubTitle);
        return pageSubTitle.getText();
    }


    public void waitForVisibilityOfElement(WebElement element) {
        new WebDriverWait(Driver.get(), 10).until(ExpectedConditions.visibilityOf(element));
    }

    public static void selectFromDropDown(WebElement element , String visibleText){
        Select select = new Select(element);
        select.selectByVisibleText(visibleText);
    }


    public static void hoverToElement(WebElement element){
        Actions actions = new Actions(Driver.get());
        actions.moveToElement(element).build().perform();
    }
<<<<<<< HEAD
    //-----------------------------------------------------------------------------------------
=======

    public String getUserName() {
        BrowserUtils.waitForVisibility(userName, 5);
        return userName.getText();
        //jkjkjk
    }
>>>>>>> 10ba3bc0b67ba038e6fbd155fa0cca2fb79fad65

    public void logOut(){

        BrowserUtils.wait(2);
        BrowserUtils.clickWithJS(userName);
        BrowserUtils.clickWithJS(logOutLink);
    }

<<<<<<< HEAD





}
=======
}
>>>>>>> 10ba3bc0b67ba038e6fbd155fa0cca2fb79fad65
