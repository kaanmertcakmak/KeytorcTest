package page_elements;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class HomePage {

    private WebDriver webDriver;
    private By personIcon = By.cssSelector("a[href='/uye-girisi']");
    private By myAccountText = By.cssSelector("a[href='/hesabim'] span");
    private By searchEntryField = By.name("searchTerm");
    private By araButton = By.cssSelector("form[action='/arama'] .submit-button");
    private By closeCookie = By.cssSelector(".close-cookie-warning");

    public HomePage(WebDriver webDriver){
        this.webDriver = webDriver;
    }

    public WebElement getPersonIcon(){
        return webDriver.findElement(personIcon);
    }

    public WebElement getMyAccountText(){
        return webDriver.findElement(myAccountText);
    }

    public WebElement SearchEntryField(){
        return webDriver.findElement(searchEntryField);
    }

    public WebElement searchButton(){
        return webDriver.findElement(araButton);
    }

    public WebElement closeCookiePopupButton(){
        return webDriver.findElement(closeCookie);
    }


}
