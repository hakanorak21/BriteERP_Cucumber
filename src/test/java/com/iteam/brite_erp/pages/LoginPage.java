package com.iteam.brite_erp.pages;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.sql.Driver;

public class LoginPage extends BasePage {

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

    // Login method
    public void login(String email, String password) {
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
        String password = ConfigurationReader.getProperty("password");

        switch (role) {
            case "posmanager":
                userName = ConfigurationReader.getProperty("posmanager.username");
                break;
            case "eventscrmmanager":
                userName = ConfigurationReader.getProperty("eventscrmmanager.username");
                break;
            default:
                throw new RuntimeException("Invalid role!");
        }
        login(userName, password);
    }

}
