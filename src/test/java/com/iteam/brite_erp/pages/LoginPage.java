package com.iteam.brite_erp.pages;

import com.iteam.brite_erp.utilities.ConfigurationReader;
import com.iteam.brite_erp.utilities.Driver;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;



public class LoginPage {

    @FindBy(xpath = "//a [@href='/web/login']")
    public WebElement loginLink;

    @FindBy(xpath = "//input[@id='login']")
    public WebElement emailBox;

    @FindBy(xpath = "//input[@id='password']")
    public WebElement passwordBox;

    @FindBy(xpath = "//button[contains(text(),'Log in')]")
    public  WebElement loginButton;

    //-------------------------------------------------------------------------------------------------------------
    public LoginPage(){

        PageFactory.initElements(Driver.get(), this);
    }
//--------------------------------------------------------------------------------------------------------------

    // on the firs page we have to click on login link
    // Login method
    public void login(String email, String password) {
        loginButton.click();
        emailBox.sendKeys(email);
        passwordBox.sendKeys(password, Keys.ENTER);
    }
//---------------------------------------------------------------------------------------------------------------

    // constructor
    public void login() {
        login(ConfigurationReader.getProperty("username"), ConfigurationReader.getProperty("password"));
    }

    //------------------------------------------------------------------------------------------------------------

    public void login(String role) {
        String userName = "";
        String password = "";

        switch (role) {
            case "Pos Manager":
                userName = ConfigurationReader.getProperty("posmanager.username");
                password = ConfigurationReader.getProperty("posmanager.password");
                break;
            case "Events CRM Manager":
                userName = ConfigurationReader.getProperty("eventscrmmanager.username");
                password = ConfigurationReader.getProperty("posmanager.password");
                break;
            default:
                throw new RuntimeException("Invalid role!");
        }
        login(userName, password);
    }

}
