package page_elements;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class ProductDetailPage {
    private WebDriver webDriver;
    public ProductDetailPage(WebDriver webDriver) {
        this.webDriver = webDriver;
    }

    private By addProductToFavoritesButtonLocator = By.cssSelector("form[ajaxcallname='addProductToFavorites'] span");
    private By successPopupTextLocator = By.cssSelector(".swal-content div");
    private By tamamButtonLocator = By.cssSelector(".swal-button--confirm");
    private By productNameInProductDetailLocator = By.cssSelector(".product-info:not(.mobile) .name span");

    public WebElement addProductToFavoritesButton(){
        return webDriver.findElement(addProductToFavoritesButtonLocator);
    }

    public WebElement successPopupText(){
        return webDriver.findElement(successPopupTextLocator);
    }

    public WebElement tamamButton(){
        return webDriver.findElement(tamamButtonLocator);
    }

    public WebElement productNameInProductDetail(){
        return webDriver.findElement(productNameInProductDetailLocator);
    }
}
