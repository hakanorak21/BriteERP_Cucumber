package com.iteam.brite_erp.pages;

import com.iteam.brite_erp.utilities.BrowserUtils;
import com.iteam.brite_erp.utilities.ConfigurationReader;
import com.iteam.brite_erp.utilities.Driver;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage extends BasePage {

    @FindBy(xpath = "//a[@href='/web/login']/b")
    public WebElement loginLink;

    @FindBy(id = "login")
    public WebElement emailBox;

    @FindBy(id = "password")
    public WebElement passwordBox;

    @FindBy(css = ".btn.btn-primary")
    public  WebElement loginButton;


    public LoginPage(){
        PageFactory.initElements(Driver.get(), this);
    }


    // on the first page, we have to click on login link
    // Login method
    public void login(String username, String password) {
        loginLink.click();
        BrowserUtils.waitForVisibility(emailBox, 5);
        emailBox.sendKeys(username);
        passwordBox.sendKeys(password);
        BrowserUtils.wait(2);
        loginButton.click();
    }

    // By deafult, login as Inventory Manager
    public void login() {
        login(ConfigurationReader.getProperty("contactsUsername"), ConfigurationReader.getProperty("contactsPassword"));
    }

    /*
    Roles:
        Inventory Manager
        POS Manager
        Events Manager
     */
    public void login(String role) {
        String username = "";
        String password = "";

        switch (role) {
            case "Inventory Manager":
                username = ConfigurationReader.getProperty("contactsUsername");
                password = ConfigurationReader.getProperty("contactsPassword");
                break;
            case "POS Manager":
                username = ConfigurationReader.getProperty("posUsername");
                password = ConfigurationReader.getProperty("posPassword");
                break;
            case "Events Manager":
                username = ConfigurationReader.getProperty("crmUsername");
                password = ConfigurationReader.getProperty("crmPassword");
                break;
            default:
                throw new RuntimeException("Invalid role!");
        }
        login(username, password);
    }

}