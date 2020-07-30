package page_elements;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class FavoritesPage {

    private WebDriver webDriver;

    public FavoritesPage(WebDriver webDriver) {
        this.webDriver = webDriver;
    }

    private By productsNameInFavoritesPageLocator = By.cssSelector(".account-favorite-item .name");
    private By removeFromFavoritesLocator = By.cssSelector(".remove-button");
    private By noProductsInFavoritesPageLocator = By.cssSelector(".no-products");

    public WebElement productsNameInFavoritesPage(){
        return webDriver.findElement(productsNameInFavoritesPageLocator);
    }

    public WebElement removeFromFavoritesButton(){
        return webDriver.findElement(removeFromFavoritesLocator);
    }

    public WebElement noProductsInFavoritesPage(){
        return webDriver.findElement(noProductsInFavoritesPageLocator);
    }
}
