package page_elements;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class LoginPage {

    private WebDriver webDriver;

    public LoginPage(WebDriver webDriver){
        this.webDriver = webDriver;
    }
    private By emailFieldInLoginPage = By.id("Email");
    private By passwordFieldInLoginPage = By.id("Password");
    private By loginButtonInLoginPage = By.cssSelector(".button.red.submit-button");


    public WebElement getEmailFieldInLoginPage(){
        return webDriver.findElement(emailFieldInLoginPage);
    }

    public WebElement getPasswordFieldInLoginPage(){
        return webDriver.findElement(passwordFieldInLoginPage);
    }

    public WebElement getLoginButtonInLoginPage(){
        return webDriver.findElement(loginButtonInLoginPage);
    }
}
