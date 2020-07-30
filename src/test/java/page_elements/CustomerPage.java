package page_elements;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class CustomerPage {

    private WebDriver webDriver;

    public CustomerPage(WebDriver webDriver) {
        this.webDriver = webDriver;
    }

    private By favoritesPageButton = By.cssSelector("a[href='/hesabim/favorilerim']");
    private By logout = By.cssSelector("a[href='/cikis']");

    public WebElement favoritesPageButton(){
        return webDriver.findElement(favoritesPageButton);
    }

    public WebElement logoutButton(){
        return webDriver.findElement(logout);
    }
}
